import models.BankAccount;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class AccountManager {
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
            // Create new file if doesn't exist
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

        int confirm = JOptionPane.showConfirmDialog(panel,
                "Are you sure you want to close account " + accountIdValue.getText() + "?",
                "Confirm Account Closure",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String accountId = accountIdValue.getText();
            File inputFile = new File("accounts.csv");
            List<String> updatedLines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length > 0 && fields[0].equals(accountId)) {
                        // Set status to false (assuming status is at index 5)
                        fields[5] = "false";
                        line = String.join(",", fields);
                    }
                    updatedLines.add(line);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(panel, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

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


}
