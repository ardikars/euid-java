package euid.exception;

public final class InvalidLengthException extends EUIDException {

  private final int length;
  private final int expected;

  public InvalidLengthException(int length, int expected) {
    super(toStringBuilder(length, expected));
    this.length = length;
    this.expected = expected;
  }

  private static String toStringBuilder(final int length, final int expected) {
    return "invalid length: " + length + ", excepted: " + expected;
  }

  public int length() {
    return length;
  }

  public int expected() {
    return expected;
  }

  @Override
  public String toString() {
    return toStringBuilder(length, expected);
  }
}
