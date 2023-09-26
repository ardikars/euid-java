package euid;

import euid.exception.InvalidCharacterException;
import euid.exception.InvalidCheckmodException;
import euid.exception.InvalidLengthException;

final class Base32 {

    private static final byte[] ENCODING_CHARS = { //
            '0', '1', '2', '3', '4', '5', '6', '7', //
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', //
            'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', //
            'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z', //
    };

    private static final byte[] DECODING_CHARS = { //
            -1, -1, -1, -1, -1, -1, -1, -1, // 0
            -1, -1, -1, -1, -1, -1, -1, -1, // 8
            -1, -1, -1, -1, -1, -1, -1, -1, // 16
            -1, -1, -1, -1, -1, -1, -1, -1, // 24
            -1, -1, -1, -1, -1, -1, -1, -1, // 32
            -1, -1, -1, -1, -1, -1, -1, -1, // 40
            0, 1, 2, 3, 4, 5, 6, 7, // 48
            8, 9, -1, -1, -1, -1, -1, -1, // 56
            -1, 10, 11, 12, 13, 14, 15, 16, // 64
            17, 1, 18, 19, 1, 20, 21, 0, // 72
            22, 23, 24, 25, 26, -1, 27, 28, // 80
            29, 30, 31, -1, -1, -1, -1, -1, // 88
            -1, 10, 11, 12, 13, 14, 15, 16, // 96
            17, 1, 18, 19, 1, 20, 21, 0, // 104
            22, 23, 24, 25, 26, -1, 27, 28, // 112
            29, 30, 31, // 120
    };

    Base32() throws IllegalAccessException {
        throw new IllegalAccessException("illegal access to Base32 constructor.");
    }

    static byte[] to5BitSlice(final long hi, final long lo) {
        final byte[] dst = new byte[27];
        dst[0] = (byte) ((hi >>> 59) & 0x1f);
        dst[1] = (byte) ((hi >>> 54) & 0x1f);
        dst[2] = (byte) ((hi >>> 49) & 0x1f);
        dst[3] = (byte) ((hi >>> 44) & 0x1f);
        dst[4] = (byte) ((hi >>> 39) & 0x1f);
        dst[5] = (byte) ((hi >>> 34) & 0x1f);
        dst[6] = (byte) ((hi >>> 29) & 0x1f);
        dst[7] = (byte) ((hi >>> 24) & 0x1f);
        dst[8] = (byte) ((hi >>> 19) & 0x1f);
        dst[9] = (byte) ((hi >>> 14) & 0x1f);
        dst[10] = (byte) ((hi >>> 9) & 0x1f);
        dst[11] = (byte) ((hi >>> 4) & 0x1f);
        dst[12] = (byte) (((hi & 0xf) << 1) | ((lo >>> 63) & 0x1));
        //
        dst[13] = (byte) ((lo >>> 58) & 0x1f);
        dst[14] = (byte) ((lo >>> 53) & 0x1f);
        dst[15] = (byte) ((lo >>> 48) & 0x1f);
        dst[16] = (byte) ((lo >>> 43) & 0x1f);
        dst[17] = (byte) ((lo >>> 38) & 0x1f);
        dst[18] = (byte) ((lo >>> 33) & 0x1f);
        dst[19] = (byte) ((lo >>> 28) & 0x1f);
        dst[20] = (byte) ((lo >>> 23) & 0x1f);
        dst[21] = (byte) ((lo >>> 18) & 0x1f);
        dst[22] = (byte) ((lo >>> 13) & 0x1f);
        dst[23] = (byte) ((lo >>> 8) & 0x1f);
        dst[24] = (byte) ((lo >>> 3) & 0x1f);
        dst[25] = (byte) ((lo & 0x7) << 2);
        return dst;
    }

    static long[] to64BitSlice(final byte[] slice) {
        assert slice != null && slice.length == 27;
        final long hi = (((long) slice[0]) << 59) //
                | (((long) slice[1]) << 54) //
                | (((long) slice[2]) << 49) //
                | (((long) slice[3]) << 44) //
                | (((long) slice[4]) << 39) //
                | (((long) slice[5]) << 34) //
                | (((long) slice[6]) << 29) //
                | (((long) slice[7]) << 24) //
                | (((long) slice[8]) << 19) //
                | (((long) slice[9]) << 14) //
                | (((long) slice[10]) << 9) //
                | (((long) slice[11]) << 4) //
                | ((((long) slice[12]) >>> 1) & 0xf); //
        final long lo = ((((long) slice[13]) << 58) | (((long) slice[12]) & 0x1) << 63) //
                | (((long) slice[14]) << 53) //
                | (((long) slice[15]) << 48) //
                | (((long) slice[16]) << 43) //
                | (((long) slice[17]) << 38) //
                | (((long) slice[18]) << 33) //
                | (((long) slice[19]) << 28) //
                | (((long) slice[20]) << 23) //
                | (((long) slice[21]) << 18) //
                | (((long) slice[22]) << 13) //
                | (((long) slice[23]) << 8) //
                | (((long) slice[24]) << 3) //
                | (((long) slice[25]) >>> 2); //
        return new long[]{hi, lo};
    }

    static String encode(final long hi, final long lo, final boolean checkmod) {
        final byte[] slice = to5BitSlice(hi, lo);
        final byte[] chars = new byte[slice.length];
        chars[0] = ENCODING_CHARS[slice[0]];
        chars[1] = ENCODING_CHARS[slice[1]];
        chars[2] = ENCODING_CHARS[slice[2]];
        chars[3] = ENCODING_CHARS[slice[3]];
        chars[4] = ENCODING_CHARS[slice[4]];
        chars[5] = ENCODING_CHARS[slice[5]];
        chars[6] = ENCODING_CHARS[slice[6]];
        chars[7] = ENCODING_CHARS[slice[7]];
        chars[8] = ENCODING_CHARS[slice[8]];
        chars[9] = ENCODING_CHARS[slice[9]];
        chars[10] = ENCODING_CHARS[slice[10]];
        chars[11] = ENCODING_CHARS[slice[11]];
        chars[12] = ENCODING_CHARS[slice[12]];
        chars[13] = ENCODING_CHARS[slice[13]];
        chars[14] = ENCODING_CHARS[slice[14]];
        chars[15] = ENCODING_CHARS[slice[15]];
        chars[16] = ENCODING_CHARS[slice[16]];
        chars[17] = ENCODING_CHARS[slice[17]];
        chars[18] = ENCODING_CHARS[slice[18]];
        chars[19] = ENCODING_CHARS[slice[19]];
        chars[20] = ENCODING_CHARS[slice[20]];
        chars[21] = ENCODING_CHARS[slice[21]];
        chars[22] = ENCODING_CHARS[slice[22]];
        chars[23] = ENCODING_CHARS[slice[23]];
        chars[24] = ENCODING_CHARS[slice[24]];
        final int check;
        if (checkmod) {
            check = Check.m7(hi, lo);
        } else {
            check = 0x7f;
        }
        chars[25] = ENCODING_CHARS[(slice[25] & 0x7f) | (check >>> 5)];
        chars[26] = ENCODING_CHARS[check & 0x1f];
        return new String(chars);
    }

    static long[] decode(final String encoded) throws InvalidLengthException, InvalidCharacterException, InvalidCheckmodException {
        if (encoded == null || encoded.length() != 27) {
            throw new InvalidLengthException(encoded == null ? 0 : encoded.length(), 27);
        }
        final byte[] slice = new byte[27];
        for (int i = 0; i < 27; i++) {
            final int cp = encoded.codePointAt(i);
            if (cp >= DECODING_CHARS.length) {
                throw new InvalidCharacterException(encoded.charAt(i));
            }
            slice[i] = DECODING_CHARS[cp];
            if (slice[i] == -1) {
                throw new InvalidCharacterException(encoded.charAt(i));
            }
        }
        final int r = slice[25] & 0x3;
        slice[25] = (byte) (slice[25] & 0x1c);
        final long[] e = to64BitSlice(slice);
        final int check = (r << 5) | (slice[26] & 0x7f);
        if (check == 0x7f) {
            return e;
        }
        final int w = Check.m7(e[0], e[1]);
        if (check != w) {
            throw new InvalidCheckmodException(check, w);
        } else {
            return e;
        }
    }
}
