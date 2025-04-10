import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import models.BankAccount;

public class GUI {
  JFrame frame = new JFrame("Admin Dashboard");

  // Buttons for the navigation
  JButton btnHome = new JButton("Home");
  JButton btnUsers = new JButton("Manage Users");
  JButton btnAddUser = new JButton("Add Users");
  JButton btnLogout = new JButton("Logout");

  // Panels
  JPanel homePanel = new JPanel();
  JPanel contentPanel = new JPanel();
  JPanel navPanel = new JPanel();
  JPanel panel = new JPanel();

  public GUI() {
    // Set up the main window (JFrame)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null); // Center the window

    panel.setLayout(new BorderLayout());

    // Create a navigation panel with buttons (like a sidebar)
    navPanel.setLayout(new GridLayout(0, 1));
    navPanel.setBackground(new Color(45, 45, 45)); // Dark background for sidebar
    navPanel.setPreferredSize(new Dimension(200, 600));

    // Set button properties
    btnHome.setBackground(new Color(60, 60, 60));
    btnHome.setForeground(Color.WHITE);
    btnUsers.setBackground(new Color(60, 60, 60));
    btnUsers.setForeground(Color.WHITE);
    btnAddUser.setBackground(new Color(60, 60, 60));
    btnAddUser.setForeground(Color.WHITE);
    btnLogout.setBackground(new Color(60, 60, 60));
    btnLogout.setForeground(Color.WHITE);

    // Add buttons to the navigation panel
    navPanel.add(btnHome);
    navPanel.add(btnUsers);
    navPanel.add(btnAddUser);
    navPanel.add(btnLogout);

    // Create a content area for displaying information
    contentPanel.setLayout(new CardLayout());

    // Add a home panel as a placeholder
    homePanel.setBackground(Color.WHITE);
    homePanel.add(new JLabel("Welcome to the Admin Dashboard"));

    // Add components to the content panel
    contentPanel.add(homePanel, "Home");

    // Add the navigation and content panels to the main panel
    panel.add(navPanel, BorderLayout.WEST);
    panel.add(contentPanel, BorderLayout.CENTER);


    // Set up the frame
    frame.add(panel);
    frame.setVisible(true);
  }


  // Save account data to a CSV file
  private void addAccountToCSV(BankAccount account) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.csv", true))) {
      writer.write(account.generateAccountNumber() + "," + account.getName() + "," + account.getBalance());
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(frame, "Error saving account to CSV!");
    }
  }

}
