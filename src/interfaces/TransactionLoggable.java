package interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface TransactionLoggable {
  void logTransaction(int accountNumber, String transactionType, double amount, String targetAccount);
  void getTransactions(JLabel totalLabel , DefaultTableModel model);
}
