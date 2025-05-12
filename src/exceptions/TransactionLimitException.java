package exceptions;

/**
 * Thrown when an account exceeds predefined transaction limits
 * (e.g., daily withdrawal count or maximum transfer amount).
 */

public class TransactionLimitException extends GlobalExceptionHandler {
  private final int accountNumber;
  private final String transactionType;
  private final double limit;

  public TransactionLimitException(int accountNumber, String transactionType, double limit) {
    super(String.format("Account %d exceeded %s limit of %.2f", accountNumber, transactionType, limit), "TRANSACTION_LIMIT");
    this.accountNumber = accountNumber;
    this.transactionType = transactionType;
    this.limit = limit;
  }

  // Getters for error handling
  public int getAccountNumber() { return accountNumber; }
  public String getTransactionType() { return transactionType; }
  public double getLimit() { return limit; }
}
