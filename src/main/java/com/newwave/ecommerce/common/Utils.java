package com.newwave.ecommerce.common;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.Date;

public class Utils {
    public Date getDatePlusPeriodMs(String period) {
        Instant now = Instant.now();
        Instant futureTime = now.plusMillis(Long.parseLong(period)); // 500ms
        return Date.from(futureTime);
    }

    public Date getToday() {
        return Date.from(Instant.now());
    }

    public void checkAuthentication(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        assert currentUsername != null;
        if (!currentUsername.equals(username)) {
            throw new AccessDeniedException("You are not allowed to modify the cart of another user.");
        }
    }
}
