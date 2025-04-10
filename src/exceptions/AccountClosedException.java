package exceptions;

public class AccountClosedException extends GlobalExceptionHandler {
  public AccountClosedException(String message) {
    super(message);
  }
}
