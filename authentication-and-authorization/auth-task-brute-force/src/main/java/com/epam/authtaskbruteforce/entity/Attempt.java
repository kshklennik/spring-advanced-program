package com.epam.authtaskbruteforce.entity;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attempt {

    private final String user;
    private AtomicInteger attempts;
    private Instant attemptAfter;
}
