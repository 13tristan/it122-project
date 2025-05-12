package exceptions;

/**
 * Thrown when an account lacks sufficient funds for a transaction
 * (e.g., withdrawal or transfer exceeds available balance).
 */
public class InsufficientFundsException extends GlobalExceptionHandler {
  private final int accountNumber;
  private final double currentBalance;
  private final double requestedAmount;

  public InsufficientFundsException(int accountNumber, double currentBalance, double requestedAmount) {
    super(String.format("Account %d has insufficient funds. Balance: %.2f, Requested: %.2f", accountNumber, currentBalance, requestedAmount),
            "INSUFFICIENT_FUNDS");this.accountNumber = accountNumber;
    this.currentBalance = currentBalance;
    this.requestedAmount = requestedAmount;
  }

  // Getters for error handling
  public int getAccountNumber() { return accountNumber; }
  public double getCurrentBalance() { return currentBalance; }
  public double getRequestedAmount() { return requestedAmount; }
}