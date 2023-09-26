package euid.exception;

public final class InvalidCheckmodException extends EUIDException {

  private final int check;
  private final int actual;

  public InvalidCheckmodException(final int check, final int actual) {
    super(toStringBuild(check, actual));
    this.check = check;
    this.actual = actual;
  }

  private static String toStringBuild(final int check, final int actual) {
    return "invalid checkmod: " + check + ", expected: " + actual + "";
  }

  @Override
  public String toString() {
    return toStringBuild(check, actual);
  }
}
