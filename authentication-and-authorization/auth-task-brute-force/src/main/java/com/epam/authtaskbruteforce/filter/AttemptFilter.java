package com.epam.authtaskbruteforce.filter;

import com.epam.authtaskbruteforce.exception.UserBlockedException;
import com.epam.authtaskbruteforce.service.LoginAttemptService;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.AsyncContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
public class AttemptFilter implements Filter {

    private final LoginAttemptService loginAttemptService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");

        if (null != username) {
            String userKey = username + request.getRemoteAddr();
            if (loginAttemptService.canAttemptNow(userKey)) {
                chain.doFilter(request, response);
            } else {
                AsyncContext ctx = request.startAsync();
                long duration = Duration.between(Instant.now(), loginAttemptService.cantAttemptAfter(userKey)).toMillis();
                duration = duration > 0 ? duration : 1;

                log.warn("Holding back response for next {} milliseconds", duration);
                ctx.setTimeout(-1);

                loginAttemptService.addToWaitingQueue(userKey, ctx);
             }
        } else {
            chain.doFilter(request, response);
        }
    }
}
