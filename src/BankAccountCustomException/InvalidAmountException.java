package BankAccountCustomException;

public class InvalidAmountException extends GlobalExceptionHandler {
  public InvalidAmountException(String message) {
    super(message);
  }
}
