package exceptions;

/**
 * Thrown when an operation is attempted on a closed account.
 * Includes contextual details like the closed account number and operation type.
 */
public class AccountClosedException extends GlobalExceptionHandler {
  private final int accountNumber;
  private final String operationType; // e.g., "withdrawal", "deposit", "transfer"

  public AccountClosedException(int accountNumber, String operationType) {
    super(String.format("Account %d is closed. Operation denied: %s", accountNumber, operationType), "ACCOUNT_CLOSED");
    this.accountNumber = accountNumber;
    this.operationType = operationType;
  }

  // Getters for programmatic handling
  public int getAccountNumber() { return accountNumber; }
  public String getOperationType() { return operationType; }
}