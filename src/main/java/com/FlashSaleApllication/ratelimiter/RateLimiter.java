package com.FlashSaleApllication.ratelimiter;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiter {

    // userId -> list of request timestamps
    private final Map<Long, Deque<Long>> userRequests = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS = 5;
    private static final long WINDOW_MS = 10_000; // 10 seconds

    public synchronized boolean allowRequest(Long userId) {
        long now = System.currentTimeMillis();

        userRequests.putIfAbsent(userId, new ArrayDeque<>());
        Deque<Long> timestamps = userRequests.get(userId);

        // Remove old requests
        while (!timestamps.isEmpty() && now - timestamps.peekFirst() > WINDOW_MS) {
            timestamps.pollFirst();
        }

        if (timestamps.size() >= MAX_REQUESTS) {
            return false; // rate limit exceeded
        }

        timestamps.addLast(now);
        return true;
    }
}
