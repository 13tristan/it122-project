package BankAccountCustomException;

public class AccountClosedException extends GlobalExceptionHandler {
  public AccountClosedException(String message) {
    super(message);
  }
}
