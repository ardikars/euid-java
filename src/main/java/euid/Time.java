package euid;

import java.time.*;

class Time {

    static final long TIMESTAMP_BITMASK = 0x1fffffffffffL;

    Time() throws IllegalAccessException {
        throw new IllegalAccessException("illegal access to Time constructor");
    }

    static long currentTimestamp() {
        return System.currentTimeMillis();
    }

    static LocalDateTime toLocalDateTime(final long timestamp, final ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
    }

    static ZonedDateTime toZonedDateTime(final long timestamp, final ZoneId zoneId) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
    }

    static OffsetDateTime toOffsetDateTime(final long timestamp, final ZoneId zoneId) {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), zoneId);
    }
}
