package models;

import exceptions.GlobalExceptionHandler;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

import java.util.Random;

public class BankAccount {

  Random random = new Random();


  private int accountNumber, balance;
  private String name;
  private boolean accountExists;


  public BankAccount(String name, int balance, int accountNumber,boolean accountExists) {
    this.accountExists = accountExists;
    this.name = name;
    this.balance = balance;
    this.accountNumber = accountNumber;
  }

  public String getName() {
    return name;
  }

  public int getBalance() {
    return balance;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public int generateAccountNumber() {

    return random.nextInt(100000);
  }

  public void deposit(int amount) throws GlobalExceptionHandler{

    if (amount < 0 && !accountExists) {
      throw new InvalidAmountException("Invalid amount");
    }

    //if amount to be deposited is valid
    balance += amount;
  }

  public void withdraw(int amount) throws GlobalExceptionHandler {

    if (amount < balance) {
      throw new InsufficientFundsException("Insufficient funds");
    }

    if (amount < 0) {
      throw new InvalidAmountException("Invalid amount");
    }

    //if amount to be withdrawn is valid
    balance -= amount;
  }
}
