import BankAccountCustomException.InsufficientFundsException;
import BankAccountCustomException.InvalidAccountException;
import BankAccountCustomException.InvalidAmountException;

import java.util.Random;

public class BankAccount_Model {

  Random random = new Random();


  int accountNumber, balance;
  String fname, lname;
  boolean accountExists;


  public String getName() {
    return fname;
  }

  public String getLname() {
    return lname;
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

  public void deposit(int amount) {
    try {
      if (amount < 0) {
        throw new InvalidAmountException("Invalid amount");
      }

      //if amount to be deposited is valid
      balance += amount;

    }
    catch (Exception e) {}
  }

  public void withdraw(int amount) {
    try {

      if (amount < balance && !accountExists) {
        throw new InsufficientFundsException("Insufficient funds");
      }

      if (amount < 0) {
        throw new InvalidAmountException("Invalid amount");
      }

      //if amount to be withdrawn is valid
      balance -= amount;
    }
    catch (Exception e) {}
  }
}
