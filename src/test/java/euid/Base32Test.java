package euid;

import euid.exception.InvalidCharacterException;
import euid.exception.InvalidCheckmodException;
import euid.exception.InvalidLengthException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;

class Base32Test {

    private static final byte[] NULL_BYTES = null;
    private static final String NULL_STR = null;

    @Test
    void init() {
        Assertions.assertThrows(IllegalAccessException.class, () -> new Base32());
    }

    @Test
    void convertBits() {
        final long hi = -786939978696720609L;
        final long lo = 2110064558162108947L;
        final byte[] bitSlice = Base32.to5BitSlice(hi, lo);
        final long[] v = Base32.to64BitSlice(bitSlice);
        Assertions.assertEquals(hi, v[0]);
        Assertions.assertEquals(lo, v[1]);
        Assertions.assertThrows(AssertionError.class, () -> Base32.to64BitSlice(Arrays.copyOf(bitSlice, 26)));
        Assertions.assertThrows(AssertionError.class, () -> Base32.to64BitSlice(NULL_BYTES));
    }

    @Test
    void encode() {
        final long hi = 7079448135464172288L;
        final long lo = 5980181632016143018L;
        String encodedWithoutCheckmod = Base32.encode(hi, lo, false);
        String encodedWithCheckmod = Base32.encode(hi, lo, true);
        Assertions.assertEquals("C8ZM14GR4JXG0MQXVY18S8TJNBZ", encodedWithoutCheckmod);
        Assertions.assertEquals("C8ZM14GR4JXG0MQXVY18S8TJNA6", encodedWithCheckmod);
    }

    @Test
    void decode() throws InvalidCheckmodException, InvalidCharacterException, InvalidLengthException {
        Assertions.assertThrows(InvalidCheckmodException.class, () -> Base32.decode("C8X2HA87098A0W837DX13FEAWVV"));
        Assertions.assertThrows(InvalidCharacterException.class, () -> Base32.decode("C8EE934SR007G5Q94QKKXFRFV8U"));
        Assertions.assertThrows(InvalidCharacterException.class, () -> Base32.decode("C8EE934SR007G5Q94QKKXFRFV8}"));
        Assertions.assertThrows(InvalidCharacterException.class, () -> Base32.decode("C8EE934SR007G5Q94QKKXFRFV8@"));
        Assertions.assertThrows(InvalidLengthException.class, () -> Base32.decode("C8EE934SR007G5Q94QKKXFRFV8"));
        Assertions.assertThrows(InvalidLengthException.class, () -> Base32.decode(NULL_STR));
        Assertions.assertArrayEquals(new long[]{7079448135464172288L, 5980181632016143018L}, Base32.decode("C8ZM14GR4JXG0MQXVY18S8TJNBZ"));
        Assertions.assertArrayEquals(new long[]{7069776807590887544L, 1650892413528379354L}, Base32.decode("C8EE934SR007G5Q94QKKXFRFV8B"));
    }
}
