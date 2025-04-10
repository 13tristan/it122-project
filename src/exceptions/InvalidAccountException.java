package exceptions;

public class InvalidAccountException extends GlobalExceptionHandler {
  public InvalidAccountException(String message) {
    super(message);
  }
}
