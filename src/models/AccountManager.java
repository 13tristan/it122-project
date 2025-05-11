package models;

import exceptions.GlobalExceptionHandler;
import exceptions.InvalidAccountException;
import interfaces.AccountVerifiable;
import interfaces.InterestBearing;
import interfaces.TransactionLoggable;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class AccountManager implements TransactionLoggable, AccountVerifiable, InterestBearing {
    private String username = "admin ";
    private String password = "admin123";
    private static AccountManager instance;
    private List<BankAccount> accounts = new ArrayList<>();
    private DefaultTableModel tableModel;

    private AccountManager() {}

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public void setTableModel(DefaultTableModel model) {
        this.tableModel = model;
    }

    // Add this method to find an account by account number
    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.isActive()) {
                return account;
            }
        }
        return null;
    }

    public BankAccount deposit(int accountNumber, double amount)
            throws GlobalExceptionHandler {

        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found: " + accountNumber);
        }

        account.deposit(amount); // This may throw InvalidAmountException or AccountClosedException
        saveAccountsToCSV();
        return account;
    }

    // Withdraw method
    public boolean withdraw(int accountNumber, double amount) throws GlobalExceptionHandler {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            throw new InvalidAccountException("Account not found: " + accountNumber);
        }

        account.withdraw(amount);
        saveAccountsToCSV();
        return true;
    }

    // Transfer method
    public boolean transfer(int fromAccountNumber, int toAccountNumber, double amount) throws GlobalExceptionHandler {
        BankAccount fromAccount = findAccount(fromAccountNumber);
        BankAccount toAccount = findAccount(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new InvalidAccountException("Account not found: " + fromAccountNumber + " or " + toAccountNumber);
        }

        try {
            // First withdraw from source account
            fromAccount.withdraw((int) amount);
            // Then deposit to destination account
            toAccount.deposit((int) amount);
            saveAccountsToCSV();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void addAccount(BankAccount account) {
        accounts.add(account);
        if (tableModel != null) {
            tableModel.addRow(new Object[]{
                    account.getAccountNumber(),
                    account.getName(),
                    account.getAccountType()
            });
        }
        saveAccountsToCSV();
    }

    public void loadAccountsFromCSV() {
        accounts.clear(); // Clear existing accounts before loading

        try (BufferedReader br = new BufferedReader(new FileReader("accounts.csv"))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }

                String[] values = line.split(",");
                if (values.length >= 6) {
                    try {
                        BankAccount account = new BankAccount(
                                values[1].trim(),       // name
                                values[2].trim(),       // password
                                Double.parseDouble(values[3].trim()), // balance
                                values[4].trim()        // accountType
                        );
                        account.setAccountNumber(Integer.parseInt(values[0].trim()));
                        account.setActive(Boolean.parseBoolean(values[5].trim()));
                        accounts.add(account);

                        // Add to table if model exists
                        if (tableModel != null) {
                            tableModel.addRow(new Object[]{
                                    account.getAccountNumber(),
                                    account.getName(),
                                    account.getAccountType()
                            });
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing account data: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
            // Create new file if it doesn't exist
            try {
                new File("accounts.csv").createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveAccountsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.csv"))) {
            // Write header
            writer.write("AccountNumber,Name,Password,Balance,AccountType,IsActive");
            writer.newLine();

            // Write accounts
            for (BankAccount account : accounts) {
                writer.write(String.join(",",
                        String.valueOf(account.getAccountNumber()),
                        account.getName(),
                        account.getPassword(),
                        String.valueOf(account.getBalance()),
                        account.getAccountType(),
                        String.valueOf(account.isActive())
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }

    public void handleCloseAccount(JPanel panel, JLabel accountIdValue, JLabel accountNameValue,
                                   JLabel accountTypeValue, JLabel balanceValue,
                                   JLabel passwordValue, JLabel statusValue) {
        if (accountIdValue.getText().isEmpty()) {
            JOptionPane.showMessageDialog(panel, "No account selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String accountId = accountIdValue.getText();
        File inputFile = new File("accounts.csv");
        List<String> updatedLines = new ArrayList<>();
        boolean accountFound = false;
        boolean alreadyClosed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && fields[0].equals(accountId)) {
                    accountFound = true;
                    if (fields.length > 5 && fields[5].equalsIgnoreCase("false")) {
                        alreadyClosed = true;
                    } else {
                        // Mark account as closed
                        fields[5] = "false";
                        line = String.join(",", fields);
                    }
                }
                updatedLines.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!accountFound) {
            JOptionPane.showMessageDialog(panel, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (alreadyClosed) {
            JOptionPane.showMessageDialog(panel, "Account is already closed", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(panel,
                "Are you sure you want to close account " + accountId + "?",
                "Confirm Account Closure",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(panel, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(panel, "Account " + accountId + " closed");

            accountIdValue.setText("");
            accountNameValue.setText("");
            accountTypeValue.setText("");
            balanceValue.setText("");
            passwordValue.setText("");
            statusValue.setText("");
        }
    }


  public void handleSearchAccount(JPanel panel, JTextField accountIdValue, JLabel nameLabel, JLabel balanceLabel) {
    String inputId = accountIdValue.getText().trim();

    if (inputId.isEmpty()) {
      JOptionPane.showMessageDialog(panel, "No account selected", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    boolean found = false;

    try (BufferedReader br = new BufferedReader(new FileReader("accounts.csv"))) {
      String line;
      boolean firstLine = true;

      while ((line = br.readLine()) != null) {
        if (firstLine) {
          firstLine = false;
          continue; // Skip header
        }

        String[] values = line.split(",");
        if (values.length >= 6) {
          String csvAccountNo = values[0].trim();
          String name = values[1].trim();
          String balance = values[3].trim();
          String isActive = values[5].trim();



          if (csvAccountNo.equals(inputId)) {

            if (!isActive.equals("true")) {
              JOptionPane.showMessageDialog(panel, "Account is not active", "Error", JOptionPane.ERROR_MESSAGE);
              nameLabel.setText("");
              balanceLabel.setText("$0.00");
              return;
            }


            found = true;
            nameLabel.setText(name);
            balanceLabel.setText("$" + balance);
            break;
          }
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(panel, "Error loading accounts.csv file", "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (!found) {
      JOptionPane.showMessageDialog(panel, "Account not found", "Search Result", JOptionPane.WARNING_MESSAGE);
      nameLabel.setText("");
      balanceLabel.setText("$0.00");
    }
  }


    // Add this method to record transactions
    @Override
    public void logTransaction(int accountNumber, String transactionType, double amount, String targetAccount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            BankAccount account = findAccount(accountNumber);
            if (account == null) return;

            String transactionRecord = String.format("%d,%s,%s,%.2f,%s",
                    accountNumber,
                    account.getName(),
                    transactionType,
                    amount,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            if (targetAccount != null) {
                transactionRecord += "," + targetAccount;
            }

            writer.write(transactionRecord);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error recording transaction: " + e.getMessage());
        }
    }

    // In AccountManager.java
    @Override
    public boolean verifyAccountDetails(JTextField passwordField, JTextField usernameField)
            throws InvalidAccountException {

        char[] passwordChars = passwordField.getText().toCharArray();
        String password = new String(passwordChars);
        String accountNumberStr = usernameField.getText().trim();

        // Clear the password from memory immediately after use
        Arrays.fill(passwordChars, '0');

        if (accountNumberStr.isEmpty() || password.isEmpty()) {
            throw new InvalidAccountException("Username or password cannot be empty");
        }

        if (!password.equals(this.password) && !accountNumberStr.equals(this.username)) {
          throw new InvalidAccountException("Invalid username or password");
        }
        return true;
    }

    @Override
    public void getTransactions(JLabel totalLabel , DefaultTableModel model) {
      model.setRowCount(0);
// Load data from CSV
      try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
          if (firstLine) {
            firstLine = false;
            continue; // Skip header
          }

          String[] values = line.split(",");
          if (values.length >= 5) { // Ensure valid row
            String accountNo = values[0].trim();
            String name = values[1].trim();
            String type = values[2].trim();
            String amount = values[3].trim();
            String date = values[4].trim();

            model.addRow(new Object[]{accountNo, name, type, amount, date});
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading transaction history from CSV", "Error", JOptionPane.ERROR_MESSAGE);
      }

      totalLabel.setText("TOTAL: " + model.getRowCount());
    }


  @Override
  public double calculateInterest() {
    return 0;
  }

  @Override
  public void applyInterest() {

  }
}
