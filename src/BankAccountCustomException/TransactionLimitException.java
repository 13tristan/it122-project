package BankAccountCustomException;

public class TransactionLimitException extends GlobalExceptionHandler {
  public TransactionLimitException(String message) {
    super(message);
  }
}
