package com.FlashSaleApllication.ratelimiter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(2)
public class RateLimitFilter extends OncePerRequestFilter {

    private final RateLimiter rateLimiter;

    public RateLimitFilter(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Apply only to buy endpoint
        if (request.getRequestURI().equals("/orders/buy")) {

            Long userId = (Long) request.getAttribute("userId");

            if (userId != null) {
                boolean allowed = rateLimiter.allowRequest(userId);

                if (!allowed) {
                    response.setStatus(429);
                    response.getWriter().write("Too Many Requests");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
