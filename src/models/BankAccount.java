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
  public BankAccount( String name, String password ,double balance, String accountType) {
    this.accountNumber = generateUniqueAccountNumber();
    this.name = name;
    this.password = password;
    this.balance = balance;
    this.accountType = accountType;

  }
  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
    generatedAccountNumbers.add(accountNumber); // Add to used numbers
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
    return this.accountNumber;
  }

  // Getter for account type
  public String getAccountType() {
    return accountType;
  }

  // Getter for password
  public String getPassword() {
    return password;
  }

  public static String generateRandomPassword(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
    Random random = new Random();
    StringBuilder password = new StringBuilder();

    for (int i = 0; i < length; i++) {
      password.append(chars.charAt(random.nextInt(chars.length())));
    }

    return password.toString();
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

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }


  public boolean isActive() {
    return isActive;
  }

  // Deposit method
  public void deposit(double amount) throws GlobalExceptionHandler {
    if (amount <= 0) {
      throw new InvalidAmountException("Amount must be positive");
    }

    // Deposit amount to the balance
    balance += amount;
  }

  // Withdraw method
  public void withdraw(double amount) throws GlobalExceptionHandler {
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
