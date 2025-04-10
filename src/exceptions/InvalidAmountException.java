package exceptions;

public class InvalidAmountException extends GlobalExceptionHandler {
  public InvalidAmountException(String message) {
    super(message);
  }
}
