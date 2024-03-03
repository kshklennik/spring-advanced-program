package com.epam.authtaskbruteforce.service.impl;

import com.epam.authtaskbruteforce.entity.Attempt;
import com.epam.authtaskbruteforce.service.LoginAttemptService;
import com.epam.authtaskbruteforce.util.AttemptUtils;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.AsyncContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginAttemptServiceImpl implements LoginAttemptService, ApplicationListener<AbstractAuthenticationEvent> {
    private final ConcurrentHashMap<String, Attempt> attemptMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<AsyncContext>> ctxMap = new ConcurrentHashMap<>();

    @Override
    public List<Attempt> getBlocked() {
        return attemptMap.values()
            .stream().toList();
    }

    @Override
    public boolean canAttemptNow(String user) {
        Attempt attempt = attemptMap.get(user);
        if (null != attempt) {
            return AttemptUtils.canAttempt(attempt);
        }

        return true;
    }

    @Override
    public Instant cantAttemptAfter(String user) {
        Attempt attempt = attemptMap.get(user);
        if (attempt != null) {
            return attempt.getAttemptAfter();
        }

        return Instant.ofEpochMilli(0);
    }

    @Override
    public void registerFailedAttempt(String user) {
        log.warn("Registering failed event for user {}", user);
        Attempt attempt = attemptMap.get(user);
        if (attempt == null) {
            attempt = new Attempt(user, new AtomicInteger(1), Instant.now());
            attemptMap.put(user, attempt);
        }
        AttemptUtils.attemptFailed(attempt);
    }

    @Override
    public void clearHistory(String name) {
        log.info("Login successful clearing records for user {}!", name);
        attemptMap.remove(name);
    }

    @Override
    public void addToWaitingQueue(String name, AsyncContext ctx) {
        ctxMap.computeIfAbsent(name, k -> new ArrayList<>());
        ctxMap.get(name).add(ctx);
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        String remoteAddress = "";
        String userKey = event.getAuthentication().getName();
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            Object source = event.getSource();
            if (source instanceof UsernamePasswordAuthenticationToken) {
                Object details = ((UsernamePasswordAuthenticationToken) source).getDetails();
                if (details instanceof WebAuthenticationDetails) {
                    remoteAddress = ((WebAuthenticationDetails) details).getRemoteAddress();
                }
            }
            userKey += remoteAddress;
            registerFailedAttempt(userKey);
        } else if (event instanceof AuthenticationSuccessEvent) {
            clearHistory(userKey);
        }
    }

    @Scheduled(fixedDelay = 500)
    public void scanner() {
        final Instant now = Instant.now();
        final List<String> badUsers = attemptMap.values().stream()
            .filter(attempt -> attempt.getAttemptAfter().isBefore(now)
                && attempt.getAttempts().get() >= AttemptUtils.ALLOWED_FAILED_ATTEMPTS
                && null != ctxMap.get(attempt.getUser())
                && !ctxMap.get(attempt.getUser()).isEmpty())
            .map(Attempt::getUser)
            .toList();

        if (!badUsers.isEmpty()) {
            log.debug("Found {} users for processing", badUsers.size());
        }
        for (String badUser : badUsers) {
            final List<AsyncContext> chainNAsyncContexts = ctxMap.get(badUser);
            if (null != chainNAsyncContexts) {
                for (AsyncContext chainNAsyncContext : chainNAsyncContexts) {
                    chainNAsyncContext.dispatch("/login");
                }
            }
        }
        badUsers.forEach(badUser -> ctxMap.get(badUser).clear());
    }
}