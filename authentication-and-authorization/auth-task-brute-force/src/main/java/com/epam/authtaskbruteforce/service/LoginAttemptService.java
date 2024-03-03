package com.epam.authtaskbruteforce.service;

import com.epam.authtaskbruteforce.entity.Attempt;

import java.time.Instant;
import java.util.List;

import javax.servlet.AsyncContext;

public interface LoginAttemptService {

    List<Attempt> getBlocked();

    boolean canAttemptNow(String user);

    Instant cantAttemptAfter(String user);

    void registerFailedAttempt(String user);

    void clearHistory(String name);

    void addToWaitingQueue(String name, AsyncContext chainNAsyncContext);
}
