package BankInterface;

public interface TransactionLoggable {
  void logTransaction(String transactionType, Double amount);
  String getTransactionHistory();
}
