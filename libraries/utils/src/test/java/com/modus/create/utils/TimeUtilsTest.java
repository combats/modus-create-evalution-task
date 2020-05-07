package com.modus.create.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = UtilsConfiguration.class)
public class TimeUtilsTest {

    @Autowired
    private TimeUtils timeUtils;

    @Test
    public void now() {
        LocalDateTime before = LocalDateTime.now(Clock.systemUTC());
        LocalDateTime now = timeUtils.now();
        LocalDateTime after = LocalDateTime.now(Clock.systemUTC());

        assertThat(now, greaterThanOrEqualTo(before));
        assertThat(now, lessThanOrEqualTo(after));
    }

    @Test
    public void now_compareWithJodaTime_shouldReturnUtcTime() {
        DateTime jodaBefore = new DateTime(DateTimeZone.UTC);
        LocalDateTime before = timeUtils.toLocalDateTime(jodaBefore.getMillis());

        LocalDateTime now = timeUtils.now();

        DateTime jodaAfter = new DateTime(DateTimeZone.UTC);
        LocalDateTime after = timeUtils.toLocalDateTime(jodaAfter.getMillis()).plusNanos(999000);

        assertThat(now, greaterThanOrEqualTo(before));
        assertThat(now, lessThanOrEqualTo(after));
    }

    @Test
    public void nowLong() {
        long before = LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();
        long now = timeUtils.nowLong();
        long after = LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli();

        assertThat(now, greaterThanOrEqualTo(before));
        assertThat(now, lessThanOrEqualTo(after));
    }

    @Test
    public void nowLong_compareWithJodaTime_shouldReturnUtcTime() {
        long before = new DateTime(DateTimeZone.UTC).getMillis();
        long now = timeUtils.nowLong();
        long after = new DateTime(DateTimeZone.UTC).getMillis();

        assertThat(now, greaterThanOrEqualTo(before));
        assertThat(now, lessThanOrEqualTo(after));
    }

    @Test
    public void toLong() {
        LocalDateTime localDateTime1 = LocalDateTime.of(2018, 2, 7, 12, 13, 14);
        LocalDateTime localDateTime2 = LocalDateTime.of(2018, 3, 7, 12, 13, 14);

        long actualTimestamp1 = timeUtils.toLong(localDateTime1);
        long actualTimestamp2 = timeUtils.toLong(localDateTime2);
        long actualTimestamp3 = timeUtils.toLong(null);

        assertEquals(1518005594000L, actualTimestamp1);
        assertEquals(1520424794000L, actualTimestamp2);
        assertEquals(0L, actualTimestamp3);
    }

    @Test
    public void toLocalDateTime() {
        LocalDateTime expectedLocalDateTime1 = LocalDateTime.of(2018, 2, 7, 12, 13, 14);
        LocalDateTime expectedLocalDateTime2 = LocalDateTime.of(2018, 3, 7, 12, 13, 14);

        LocalDateTime actualLocalDateTime1 = timeUtils.toLocalDateTime(1518005594000L);
        LocalDateTime actualLocalDateTime2 = timeUtils.toLocalDateTime(1520424794000L);

        assertEquals(expectedLocalDateTime1, actualLocalDateTime1);
        assertEquals(expectedLocalDateTime2, actualLocalDateTime2);
    }
}