package exceptions;

/**
 * Thrown when a transaction involves an invalid amount
 * (e.g., negative values, zero, or exceeds balance).
 */

public class InvalidAmountException extends GlobalExceptionHandler {
  private final double invalidAmount;
  private final String transactionType;

  public InvalidAmountException(double invalidAmount, String transactionType) {
    super(
            String.format("Invalid amount %.2f for %s: Amount must be positive and non-zero.", invalidAmount, transactionType),
            "INVALID_AMOUNT"
    );
    this.invalidAmount = invalidAmount;
    this.transactionType = transactionType;
  }

  // Getters for error handling
  public double getInvalidAmount() { return invalidAmount; }
  public String getTransactionType() { return transactionType; }
}
