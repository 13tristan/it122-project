package interfaces;

public interface TransactionLoggable {
  void logTransaction(int accountNumber, String transactionType, double amount, String targetAccount);
}
