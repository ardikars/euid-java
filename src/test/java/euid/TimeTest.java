package euid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.*;

class TimeTest {

    @Test
    void init() {
        Assertions.assertThrows(IllegalAccessException.class, () -> new Time());
    }

    @Test
    void currentTimestamp() {
        final long timestamp = Time.currentTimestamp();
        Assertions.assertNotEquals(0, timestamp);
    }

    @Test
    void toDateTime() {
        final ZoneId zoneId = ZoneId.systemDefault();
        final long timestamp = System.currentTimeMillis();
        final LocalDateTime local = Time.toLocalDateTime(timestamp, zoneId);
        final ZonedDateTime zoned = Time.toZonedDateTime(timestamp, zoneId);
        final OffsetDateTime offset = Time.toOffsetDateTime(timestamp, zoneId);
        Assertions.assertEquals(Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDateTime(), local);
        Assertions.assertEquals(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId), zoned);
        Assertions.assertEquals(Instant.ofEpochMilli(timestamp).atZone(zoneId).toOffsetDateTime(), offset);
    }
}
