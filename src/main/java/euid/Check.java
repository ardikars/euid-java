package euid;

final class Check {

    private static final long P = 0x7f;

    Check() throws IllegalAccessException {
        throw new IllegalAccessException("illegal access to Check constructor.");
    }

    static long[] shiftRight7(final long[] v) {
        final long hi = v[0] >>> 7;
        final long lo = (v[1] >>> 7) | ((v[0] & P) << 57);
        return new long[]{hi, lo};
    }

    static long[] addU128(final long[] a, final long[] b) {
        long a1 = a[0];
        long a2 = a[1];
        long b1 = b[0];
        long b2 = b[1];
        long sum1 = 0;
        long sum2 = 0;
        long carry1 = 0;
        long carry2 = 1;
        while ((carry1 != 0) || (carry2 != 0)) {
            sum1 = a1 ^ b1;
            sum2 = a2 ^ b2;
            final long a2b2 = a2 & b2;
            carry2 = a2b2 << 1;
            carry1 = ((a1 & b1) << 1) | (a2b2 >>> 63);
            a1 = sum1;
            a2 = sum2;
            b1 = carry1;
            b2 = carry2;
        }
        return new long[]{sum1, sum2};
    }

    static boolean isGtP(final long[] v) {
        if (v[0] != 0) {
            return true;
        } else {
            return v[1] < 0 || v[1] > P;
        }
    }

    static int m7(final long hi, final long lo) {
        long[] i = addU128(new long[]{0, lo & P}, shiftRight7(new long[]{hi, lo}));
        while (isGtP(i)) {
            i = addU128(new long[]{0, i[1] & P}, shiftRight7(i));
        }
        if (i[1] == P) {
            return 0;
        } else {
            return (int) (i[1] & P);
        }
    }
}
