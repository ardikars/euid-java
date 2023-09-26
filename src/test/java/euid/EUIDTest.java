package euid;

import euid.exception.InvalidCharacterException;
import euid.exception.InvalidCheckmodException;
import euid.exception.InvalidLengthException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.time.*;
import java.util.*;

class EUIDTest {

    static long getTimestampDiff(final long start, final long timestamp) {
        if (start < timestamp) {
            return timestamp - start;
        } else {
            return start - timestamp;
        }
    }

    static long getExtBitLen0(int v) {
        int i = 14;
        while (i > 0) {
            if ((v >>> i) != 0) {
                return i + 1;
            }
            i -= 1;
        }
        return 1;
    }

    static long normalizeTimestamp(final long now, final long epoch) {
        if (epoch < now) {
            return now - epoch;
        } else {
            return now;
        }
    }

    static long getTimestampFromEpoch(final long epoch) {
        final long millis = Time.currentTimestamp();
        final long finalEpoch = epoch & 0x3ffffffffffL;
        return normalizeTimestamp(millis, finalEpoch);
    }

    @Test
    void getTimestampDiff() {
        Assertions.assertEquals(1, getTimestampDiff(1, 2));
        Assertions.assertEquals(1, getTimestampDiff(2, 1));
    }

    @Test
    void createWithTimestamp() {
        Assertions.assertFalse(EUID.createWithTimestamp(-1).isPresent());
        Assertions.assertFalse(EUID.createWithTimestamp(Time.TIMESTAMP_BITMASK + 1).isPresent());
        Assertions.assertTrue(EUID.createWithTimestamp(Time.currentTimestamp()).isPresent());
    }

    @Test
    void createWithTimestampAndExtension() {
        Assertions.assertTrue(EUID.createWithTimestampAndExtension(0, 0).isPresent());
        Assertions.assertFalse(EUID.createWithTimestampAndExtension(35184372088832L, 0).isPresent());
        Assertions.assertFalse(EUID.createWithTimestampAndExtension(-1, 0).isPresent());
        Assertions.assertFalse(EUID.createWithTimestampAndExtension(0, 32768).isPresent());
        Assertions.assertFalse(EUID.createWithTimestampAndExtension(0, -1).isPresent());
        final Random random = new Random();
        for (int i = 0; i < 65535; i++) {
            final long epoch = random.nextInt() & 0xffffffffL;
            final long ts = getTimestampFromEpoch(epoch);
            final long now = Time.currentTimestamp();
            final EUID euid = EUID.createWithTimestampAndExtension(ts, (int) (i & EUID.EXT_DATA_BITMASK)).orElseThrow(() -> new RuntimeException("timestamp overflow"));
            final long timestamp = euid.timestamp();
            Assertions.assertTrue(timestamp <= Time.TIMESTAMP_BITMASK);
            final long t = now - epoch;
            final long diff = getTimestampDiff(t, timestamp);
            Assertions.assertTrue(diff < 50);
            Assertions.assertEquals((int) (i & EUID.EXT_DATA_BITMASK), euid.extension().orElse(0));
            Assertions.assertTrue(euid.extension().orElse(0) <= EUID.EXT_DATA_BITMASK);
            Assertions.assertTrue(EUID.getExtBitLen(euid.extension().orElse(0)) <= EUID.EXT_LEN_BITMASK);
        }
    }

    @Test
    void getExtBitLen() {
        int max = 1 << 15;
        for (int i = 0; i < max; i++) {
            Assertions.assertEquals(EUID.getExtBitLen(i), getExtBitLen0(i));
        }
    }

    @Test
    void create() {
        final long now = Time.currentTimestamp();
        final EUID euid = EUID.create().orElse(EUID.MIN);
        final long timestamp = euid.timestamp();
        final long diff = getTimestampDiff(now, timestamp);
        Assertions.assertTrue(diff < 50);
        Assertions.assertFalse(euid.extension().isPresent());
    }

    @Test
    void createWithExtension() {
        for (int i = 0; i < EUID.EXT_DATA_BITMASK; i++) {
            final long now = Time.currentTimestamp();
            final EUID euid = EUID.createWithExtension(i).orElse(EUID.MIN);
            final long timestamp = euid.timestamp();
            final long diff = getTimestampDiff(now, timestamp);
            Assertions.assertTrue(diff < 50);
            Assertions.assertTrue(euid.extension().isPresent());
            Assertions.assertEquals(i, euid.extension().orElse(-1));
        }
        Assertions.assertFalse(EUID.createWithExtension(((int) (EUID.EXT_DATA_BITMASK)) + 1).isPresent());
    }

    @Test
    void conversion() throws InvalidCheckmodException, InvalidCharacterException, InvalidLengthException {
        for (int i = 0; i < EUID.EXT_DATA_BITMASK; i++) {
            final EUID euid = EUID.createWithExtension(i).orElse(EUID.MIN);
            final String encoded = euid.toString();
            Assertions.assertEquals(27, encoded.length());
            final EUID decoded = EUID.fromStringUnchecked(encoded).orElse(EUID.MIN);
            Assertions.assertEquals(decoded, EUID.fromString(encoded));
            Assertions.assertArrayEquals(euid.toBytes(), decoded.toBytes());
            Assertions.assertEquals(euid, decoded);
            Assertions.assertEquals(0, euid.compareTo(decoded));
            Assertions.assertFalse(EUID.createWithExtension(0x7fff + 1).isPresent());
            Assertions.assertFalse(EUID.fromStringUnchecked("C8754X9NN8H80X298KRKERG8K").isPresent());
            Assertions.assertThrows(InvalidLengthException.class, () -> EUID.fromString("C8754X9NN8H80X298KRKERG8K"));
            Assertions.assertFalse(EUID.fromStringUnchecked("C8754X9NN8H80X298KRKERG8K888").isPresent());
            Assertions.assertThrows(InvalidLengthException.class, () -> EUID.fromString("C8754X9NN8H80X298KRKERG8K888"));
            Assertions.assertFalse(EUID.fromStringUnchecked("C8754X9NN8H80X298KRKERG8KU8").isPresent());
            Assertions.assertThrows(InvalidCharacterException.class, () -> EUID.fromString("C8754X9NN8H80X298KRKERG8KU8"));
            Assertions.assertFalse(EUID.fromStringUnchecked("C8X2HA87098A0W837DX13FEAWVV").isPresent());
            Assertions.assertThrows(InvalidCheckmodException.class, () -> EUID.fromString("C8X2HA87098A0W837DX13FEAWVV"));
        }
    }

    @Test
    void monotonic() {
        java.util.Random random = new SecureRandom();
        final EUID euid = new EUID(EUID.create().get().hi, -1);
        Assertions.assertFalse(euid.next().isPresent());
        final List<EUID> euids = new LinkedList<>();
        final List<EUID> euidsWithExt = new LinkedList<>();
        for (int i = 0; i < 0x7fff; i++) {
            if (i == 0) {
                euids.add(EUID.create().orElseThrow(() -> new RuntimeException("something went wrong")));
                euidsWithExt.add(EUID.createWithExtension(i).orElseThrow(() -> new RuntimeException("something went wrong")));
            } else {
                euids.add(euids.get(i - 1).next().orElseThrow(() -> new RuntimeException("something went wrong")));
                euidsWithExt.add(euidsWithExt.get(i - 1).next().orElseThrow(() -> new RuntimeException("something went wrong")));
            }
        }
        final List<EUID> unordered = new ArrayList<>(euids);
        final List<EUID> unorderedWithExt = new ArrayList<>(euidsWithExt);
        Collections.shuffle(unordered, random);
        Collections.shuffle(unorderedWithExt, random);

        final List<EUID> ordered = new ArrayList<>(unordered);
        final List<EUID> orderedWithExt = new ArrayList<>(unorderedWithExt);
        Collections.sort(ordered);
        Collections.sort(orderedWithExt);

        for (int i = 0; i < euidsWithExt.size(); i++) {
            Assertions.assertEquals(euids.get(i), ordered.get(i));
            Assertions.assertEquals(euids.get(i).toString(), ordered.get(i).toString());
            Assertions.assertEquals(euids.get(i).encode(false), ordered.get(i).encode(false));
            Assertions.assertArrayEquals(euids.get(i).toBytes(), ordered.get(i).toBytes());

            Assertions.assertEquals(euidsWithExt.get(i), orderedWithExt.get(i));
            Assertions.assertEquals(euidsWithExt.get(i).toString(), orderedWithExt.get(i).toString());
            Assertions.assertEquals(euidsWithExt.get(i).encode(false), orderedWithExt.get(i).encode(false));
            Assertions.assertArrayEquals(euidsWithExt.get(i).toBytes(), orderedWithExt.get(i).toBytes());
        }
    }

    @Test
    void exception() {
        try {
            EUID.fromString("C8754X9NN8H80X298KRKERG8K");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof InvalidLengthException);
            Assertions.assertEquals(25, ((InvalidLengthException) e).length());
            Assertions.assertEquals(27, ((InvalidLengthException) e).expected());
            Assertions.assertEquals("invalid length: 25, excepted: 27", e.toString());
        }
        try {
            EUID.fromString("C8754X9NN8H80X298KRKERG8K888");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof InvalidLengthException);
            Assertions.assertEquals(28, ((InvalidLengthException) e).length());
            Assertions.assertEquals(27, ((InvalidLengthException) e).expected());
            Assertions.assertEquals("invalid length: 28, excepted: 27", e.toString());
        }
        try {
            EUID.fromString("C8754X9NN8H80X298KRKERG8KU8");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof InvalidCharacterException);
            Assertions.assertEquals('U', ((InvalidCharacterException) e).character());
            Assertions.assertEquals("invalid character `U`", e.toString());
        }
        try {
            EUID.fromString("C8X2HA87098A0W837DX13FEAWVV");
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof InvalidCheckmodException);
            Assertions.assertEquals("invalid checkmod: 123, expected: 56", e.toString());
        }
    }

    @Test
    void equalsHashCode() {
        final EUID euid = EUID.create().get();
        final EUID euid1 = new EUID(euid.hi, euid.lo);
        final EUID euid2 = EUID.create().get();
        Assertions.assertTrue(euid.equals(euid));
        Assertions.assertTrue(euid.equals(euid1));
        Assertions.assertFalse(euid.equals(euid2));
        Assertions.assertFalse(euid.equals(""));
        Assertions.assertFalse(euid.equals(null));
        Assertions.assertNotNull(euid1.hashCode());
    }

    @Test
    void dateTime() {
        final EUID euid = EUID.create().get();
        final ZoneId zoneId = ZoneId.systemDefault();
        final long timestamp = euid.timestamp();
        Assertions.assertTrue(timestamp <= Time.TIMESTAMP_BITMASK);
        final LocalDateTime local = euid.localDateTime(zoneId);
        final ZonedDateTime zoned = euid.zonedDateTime(zoneId);
        final OffsetDateTime offset = euid.offsetDateTime(zoneId);
        Assertions.assertEquals(euid.localDateTime(), local);
        Assertions.assertEquals(euid.zonedDateTime(), zoned);
        Assertions.assertEquals(euid.offsetDateTime(), offset);
    }
}
