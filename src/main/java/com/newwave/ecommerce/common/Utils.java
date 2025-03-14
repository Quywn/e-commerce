package com.newwave.ecommerce.common;

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
}
