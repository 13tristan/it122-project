import models.BankAccount;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
}