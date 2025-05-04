package interfaces;

import exceptions.InvalidAccountException;

import javax.swing.*;

public interface AccountVerifiable {
  boolean verifyAccountDetails(JTextField username, JTextField password) throws InvalidAccountException;
}
