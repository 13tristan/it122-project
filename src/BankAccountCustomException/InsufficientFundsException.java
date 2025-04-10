package BankAccountCustomException;

public class InsufficientFundsException extends GlobalExceptionHandler {

  public InsufficientFundsException(String message) {
    super(message);
  }
}
