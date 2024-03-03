package com.epam.authtaskbruteforce.util;

import com.epam.authtaskbruteforce.entity.Attempt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class AttemptUtils {

    public final static int ALLOWED_FAILED_ATTEMPTS = 3;

    public static boolean canAttempt(Attempt attempt) {
        return Instant.now().isAfter(attempt.getAttemptAfter());
    }

    public static void attemptFailed(Attempt attempt) {
        if (ALLOWED_FAILED_ATTEMPTS <= attempt.getAttempts().incrementAndGet()) {
            long timeInSeconds = getFibonacciTimeInSeconds(attempt.getAttempts().get() - ALLOWED_FAILED_ATTEMPTS + 1);
            attempt.setAttemptAfter(Instant.now().plus(timeInSeconds, ChronoUnit.SECONDS));

            log.warn("{} needs to wait till {} ( in {} seconds) for next attempt!", attempt.getUser(),
                attempt.getAttemptAfter(), timeInSeconds);
        }
    }

    private static long getFibonacciTimeInSeconds(int count) {
        log.debug("Get fibonacci time for count {}", count);
        int first = 1, second = 2;
        if (count == 1) {
            return 10;
        }
        if (count == 2) {
            return 20;
        }
        for (int i = 0; i < count - 2; i++) {
            int temp = first;
            first = second;
            second += temp;
        }
        return second * 10L;
    }
}
