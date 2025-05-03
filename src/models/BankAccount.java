package models;

import exceptions.GlobalExceptionHandler;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BankAccount {

  Random random = new Random();
  private int accountNumber;
  private double balance;
  private String name;
  private String accountType; // Add account type
  private String password;    // Add password
  private boolean isActive = true;
  private static Set<Integer> generatedAccountNumbers = new HashSet<>(); // To track used account numbers

  // Constructor to initialize account with name, balance, and account type
  public BankAccount(String name, String password ,double balance, String accountType) {
    this.name = name;
    this.balance = balance;
    this.accountType = accountType;
    this.password = password;
  }

  // Getter for account name
  public String getName() {
    return name;
  }

  // Getter for account balance
  public double getBalance() {
    return balance;
  }

  // Getter for account number
  public int getAccountNumber() {
    return generateUniqueAccountNumber();
  }

  // Getter for account type
  public String getAccountType() {
    return accountType;
  }

  // Getter for password
  public String getPassword() {
    return password;
  }

  // Generate unique account number
  public int generateUniqueAccountNumber() {
    int generatedNumber;
    do {
      generatedNumber = random.nextInt(1000000); // Generate a random 5-digit account number
    } while (generatedAccountNumbers.contains(generatedNumber)); // Ensure it's unique

    generatedAccountNumbers.add(generatedNumber); // Add the number to the set
    return generatedNumber;
  }

  public boolean isActive() {
    return isActive;
  }

  // Deposit method
  public void deposit(int amount) throws GlobalExceptionHandler {
    if (amount <= 0) {
      throw new InvalidAmountException("Amount must be positive");
    }

    // Deposit amount to the balance
    balance += amount;
  }

  // Withdraw method
  public void withdraw(int amount) throws GlobalExceptionHandler {
    if (amount <= 0) {
      throw new InvalidAmountException("Amount must be positive");
    }

    if (amount > balance) {
      throw new InsufficientFundsException("Insufficient funds");
    }

    // Withdraw amount from balance
    balance -= amount;
  }
}
