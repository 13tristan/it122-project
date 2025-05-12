package exceptions;

/**
 * Thrown when an account operation fails due to invalid account details
 * (e.g., account not found, closed, or unauthorized access).
 */
public class InvalidAccountException extends GlobalExceptionHandler {
  private final int accountNumber;
  private final String reason;

  public InvalidAccountException(int accountNumber, String reason) {
    super(String.format("Account %d is invalid: %s", accountNumber, reason), "INVALID_ACCOUNT");
    this.accountNumber = accountNumber;
    this.reason = reason;
  }

  // Getters for error handling
  public int getAccountNumber() { return accountNumber; }
  public String getReason() { return reason; }
}