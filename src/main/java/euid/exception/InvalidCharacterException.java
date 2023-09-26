package euid.exception;

public final class InvalidCharacterException extends EUIDException {

  private final char c;

  public InvalidCharacterException(final char c) {
    super(toStringBuild(c));
    this.c = c;
  }

  public char character() {
    return c;
  }

  private static String toStringBuild(final char c) {
    return "invalid character `" + c + "`";
  }

  @Override
  public String toString() {
    return toStringBuild(c);
  }
}
