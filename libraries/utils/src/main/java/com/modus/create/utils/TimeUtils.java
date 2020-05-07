package com.modus.create.utils;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TimeUtils {

    private static final int ZERO_OFFSET = 0;

    //todo use everywhere instead of LocalDateTime.now();
    public LocalDateTime now() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    public long nowLong() {
        return toLong(now());
    }

    public long toLong(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0;
        }
        ZoneOffset offset = ZoneOffset.ofTotalSeconds(ZERO_OFFSET);
        Instant instant = localDateTime.toInstant(offset);

        return instant.toEpochMilli();
    }

    public LocalDateTime toLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneOffset zone = ZoneOffset.ofTotalSeconds(ZERO_OFFSET);

        return LocalDateTime.ofInstant(instant, zone);
    }
}