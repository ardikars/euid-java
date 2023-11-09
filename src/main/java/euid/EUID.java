package euid;

import euid.exception.InvalidCharacterException;
import euid.exception.InvalidCheckmodException;
import euid.exception.InvalidLengthException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

/*
        0               1               2               3
 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7 0 1 2 3 4 5 6 7
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       32_bit_uint_t_high                      |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|    13_bit_uint_t_low    |   N Bit Random + Ext Data   |Ext Len|
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       32_bit_uint_random                      |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       32_bit_uint_random                      |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 */
public final class EUID implements Comparable<EUID> {

    public static final EUID MIN = new EUID(0, 0);

    static final Random DEFAULT_PRNG = new Random();

    static final long EXT_DATA_BITMASK = 0x7fffL;
    static final long EXT_LEN_BITMASK = 0xfL;

    final long hi;
    final long lo;

    EUID(final long hi, final long lo) {
        this.hi = hi;
        this.lo = lo;
    }

    public static EUID fromString(final String str) throws InvalidLengthException, InvalidCharacterException, InvalidCheckmodException {
        final long[] decode = Base32.decode(str);
        return new EUID(decode[0], decode[1]);
    }

    public static EUID fromBytes(final byte[] bytes) throws InvalidLengthException {
        if (bytes == null || bytes.length != 16) {
            throw new InvalidLengthException(bytes == null ? 0 : bytes.length, 16);
        }
        final long hi = (((bytes[0] & 0xFFL) << 56) //
                | ((bytes[1] & 0xFFL) << 48) //
                | ((bytes[2] & 0xFFL) << 40) //
                | ((bytes[3] & 0xFFL) << 32) //
                | ((bytes[4] & 0xFFL) << 24) //
                | ((bytes[5] & 0xFFL) << 16) //
                | ((bytes[6] & 0xFFL) << 8) //
                | (bytes[7] & 0xFFL)); //
        final long lo = (((bytes[8] & 0xFFL) << 56) //
                | ((bytes[9] & 0xFFL) << 48) //
                | ((bytes[10] & 0xFFL) << 40) //
                | ((bytes[11] & 0xFFL) << 32) //
                | ((bytes[12] & 0xFFL) << 24) //
                | ((bytes[13] & 0xFFL) << 16) //
                | ((bytes[14] & 0xFFL) << 8) //
                | (bytes[15] & 0xFFL)); //
        return new EUID(hi, lo);
    }

    public static Optional<EUID> fromStringUnchecked(final String str) {
        try {
            final long[] decode = Base32.decode(str);
            return Optional.of(new EUID(decode[0], decode[1]));
        } catch (final InvalidLengthException | InvalidCharacterException | InvalidCheckmodException e) {
            return Optional.empty();
        }
    }

    public static Optional<EUID> create() {
        return createWithTimestamp(Time.currentTimestamp());
    }

    public static Optional<EUID> createWithExtension(final int extension) {
        return createWithTimestampAndExtension(Time.currentTimestamp(), extension);
    }

    public long timestamp() {
        return ((hi >>> 19) & Time.TIMESTAMP_BITMASK);
    }

    public LocalDateTime localDateTime() {
        return Time.toLocalDateTime(timestamp(), ZoneId.systemDefault());
    }

    public LocalDate localDate() {
        return localDateTime().toLocalDate();
    }

    public LocalTime localTime() {
        return localDateTime().toLocalTime();
    }

    public LocalDateTime localDateTime(final ZoneId zoneId) {
        return Time.toLocalDateTime(timestamp(), zoneId);
    }

    public LocalDate localDate(final ZoneId zoneId) {
        return localDateTime(zoneId).toLocalDate();
    }

    public LocalTime localTime(final ZoneId zoneId) {
        return localDateTime(zoneId).toLocalTime();
    }

    public ZonedDateTime zonedDateTime() {
        return Time.toZonedDateTime(timestamp(), ZoneId.systemDefault());
    }

    public ZonedDateTime zonedDateTime(final ZoneId zoneId) {
        return Time.toZonedDateTime(timestamp(), zoneId);
    }

    public OffsetDateTime offsetDateTime() {
        return Time.toOffsetDateTime(timestamp(), ZoneId.systemDefault());
    }

    public OffsetDateTime offsetDateTime(final ZoneId zoneId) {
        return Time.toOffsetDateTime(timestamp(), zoneId);
    }

    public Optional<Integer> extension() {
        final long extLen = this.hi & EXT_LEN_BITMASK;
        if (extLen == 0) {
            return Optional.empty();
        } else {
            final long bitmask = (1L << extLen) - 1L;
            return Optional.of((int) ((this.hi >>> 4) & bitmask));
        }
    }

    public Optional<EUID> next() {
        final long timestamp = timestamp();
        final long now = Time.currentTimestamp();
        if (now == timestamp) {
            return createMonotonicEUID(this.hi, this.lo);
        } else {
            return extension() //
                    .map(ext -> EUID.createWithTimestampAndExtension(now, ext)) //
                    .orElseGet(() -> EUID.createWithTimestamp(now));
        }
    }

    public String encode(final boolean checkmod) {
        return Base32.encode(this.hi, this.lo, checkmod);
    }

    public byte[] toBytes() {
        final byte[] bytes = new byte[16];
        bytes[0] = (byte) ((this.hi >>> 56) & 0xff);
        bytes[1] = (byte) ((this.hi >>> 48) & 0xff);
        bytes[2] = (byte) ((this.hi >>> 40) & 0xff);
        bytes[3] = (byte) ((this.hi >>> 32) & 0xff);
        bytes[4] = (byte) ((this.hi >>> 24) & 0xff);
        bytes[5] = (byte) ((this.hi >>> 16) & 0xff);
        bytes[6] = (byte) ((this.hi >>> 8) & 0xff);
        bytes[7] = (byte) (this.hi & 0xff);
        bytes[8] = (byte) ((this.lo >>> 56) & 0xff);
        bytes[9] = (byte) ((this.lo >>> 48) & 0xff);
        bytes[10] = (byte) ((this.lo >>> 40) & 0xff);
        bytes[11] = (byte) ((this.lo >>> 32) & 0xff);
        bytes[12] = (byte) ((this.lo >>> 24) & 0xff);
        bytes[13] = (byte) ((this.lo >>> 16) & 0xff);
        bytes[14] = (byte) ((this.lo >>> 8) & 0xff);
        bytes[15] = (byte) (this.lo & 0xff);
        return bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EUID euid = (EUID) o;
        return compareTo(euid) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hi, lo);
    }

    @Override
    public int compareTo(EUID o) {
        if (hi != o.hi) {
            return hi > o.hi ? 1 : -1;
        }
        if (lo != o.lo) {
            return lo > o.lo ? 1 : -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return encode(true);
    }

    static long getExtBitLen(final int v) {
        int x = v & 0x7fff;
        long n = 0;
        if (x <= 0x00ff) {
            n += 8;
            x <<= 8;
        }
        if (x <= 0x0fff) {
            n += 4;
            x <<= 4;
        }
        if (x <= 0x3fff) {
            n += 2;
            x <<= 2;
        }
        if (x <= 0x7fff) {
            n += 1;
        }
        return (16 - n);
    }

    static Optional<EUID> createMonotonicEUID(final long hi, final long lo) {
        final long rHi = lo >>> 32;
        if (rHi == 0xffffffffL) {
            return Optional.empty();
        } else {
            final byte[] random = new byte[4];
            DEFAULT_PRNG.nextBytes(random);
            final long r1 = (random[0] & 0xFFL) | ((random[1] & 0xFFL) << 8) | ((random[2] & 0xFFL) << 16) | ((random[3] & 0xFFL) << 24);
            return Optional.of(new EUID(hi, ((rHi + 1) << 32) | r1));
        }
    }

    static Optional<EUID> createWithTimestamp(final long timestamp) {
        if (timestamp < 0 || timestamp > Time.TIMESTAMP_BITMASK) {
            return Optional.empty();
        } else {
            synchronized (DEFAULT_PRNG) {
                final byte[] random = new byte[16];
                DEFAULT_PRNG.nextBytes(random);
                final long r0 = (random[0] & 0xFFL) | ((random[1] & 0xFFL) << 8) | ((random[2] & 0xFFL) << 16) | ((random[3] & 0xFFL) << 24) | ((random[4] & 0xFFL) << 32) | ((random[5] & 0xFFL) << 40) | ((random[6] & 0xFFL) << 48) | ((random[7] & 0xFFL) << 56);
                final long r1 = (random[8] & 0xFFL) | ((random[9] & 0xFFL) << 8) | ((random[10] & 0xFFL) << 16) | ((random[11] & 0xFFL) << 24) | ((random[12] & 0xFFL) << 32) | ((random[13] & 0xFFL) << 40) | ((random[14] & 0xFFL) << 48) | ((random[15] & 0xFFL) << 56);
                return Optional.of(new EUID((timestamp << 19) | ((r0 & 0x7fff) << 4), r1));
            }
        }
    }

    static Optional<EUID> createWithTimestampAndExtension(final long timestamp, final int extension) {
        if (timestamp < 0 || timestamp > Time.TIMESTAMP_BITMASK) {
            return Optional.empty();
        } else {
            if (extension < 0 || extension > EXT_DATA_BITMASK) {
                return Optional.empty();
            } else {
                final long extBitLen = getExtBitLen(extension);
                synchronized (DEFAULT_PRNG) {
                    final byte[] random = new byte[16];
                    DEFAULT_PRNG.nextBytes(random);
                    final long r0 = (random[0] & 0xFFL) | ((random[1] & 0xFFL) << 8) | ((random[2] & 0xFFL) << 16) | ((random[3] & 0xFFL) << 24) | ((random[4] & 0xFFL) << 32) | ((random[5] & 0xFFL) << 40) | ((random[6] & 0xFFL) << 48) | ((random[7] & 0xFFL) << 56);
                    final long r1 = (random[8] & 0xFFL) | ((random[9] & 0xFFL) << 8) | ((random[10] & 0xFFL) << 16) | ((random[11] & 0xFFL) << 24) | ((random[12] & 0xFFL) << 32) | ((random[13] & 0xFFL) << 40) | ((random[14] & 0xFFL) << 48) | ((random[15] & 0xFFL) << 56);
                    final long remainRand = r0 & ((1L << (15L - extBitLen)) - 1L);
                    final long hi = (timestamp << 19) //
                            | (remainRand << (4 + extBitLen)) //
                            | (((long) extension) << 4) //
                            | extBitLen; //
                    return Optional.of(new EUID(hi, r1));
                }
            }
        }
    }
}
