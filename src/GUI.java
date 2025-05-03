import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import models.BankAccount;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GUI extends JFrame{
  private JPanel main;
  private CardLayout mainLayout;

  public GUI() {
    setTitle("BANK");
    setSize(new Dimension(1000, 600));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    mainLayout = new CardLayout();
    main = new JPanel(mainLayout);

    JPanel loginPage = createLoginPage();
    //JPanel signUpPage = createSignUpPage();
    JPanel customerListPage = createCustomerListPage();
    JPanel transactionProcessPage = createTransactionProcessPage();
    JPanel transactionHistoryPage = createTransactionHistoryPage();
    JPanel addCustomerPage = createAddCustomerPage();

    main.add(loginPage, "LOG IN");
    //main.add(signUpPage, "SIGN UP");
    main.add(customerListPage, "CUSTOMER LIST");
    main.add(transactionProcessPage, "TRANSACTION PROCESS");
    main.add(transactionHistoryPage, "TRANSACTION HISTORY");
    main.add(addCustomerPage, "ADD CUSTOMER");

    add(main);
    setLocationRelativeTo(null);
  }

  private JPanel createLoginPage(){
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    JPanel panelLeft = new JPanel();
    panelLeft.setBackground(new Color(13, 161, 204));
    panelLeft.setPreferredSize(new Dimension(300, 600));


    JLabel bankTitle = new JLabel("BANK NAME");
    bankTitle.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0));
    bankTitle.setForeground(new Color(235, 241, 238));
    bankTitle.setFont(new Font("Arial", Font.BOLD, 32));
    bankTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bankSubtitle = new JLabel("Number One Bank");
    bankSubtitle.setForeground(new Color(235, 241, 238));
    bankSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 130, 0));

    /*JButton signUpBtn = new JButton("Create Account");
    signUpBtn.setPreferredSize(new Dimension(300, 80));
    signUpBtn.setFont(new Font("Arial", Font.BOLD, 18));
    signUpBtn.addActionListener(e -> {
      mainLayout.show(main, "SIGN UP");});

    JButton exitBtn = new JButton("Exit");
    exitBtn.setPreferredSize(new Dimension(300, 80));
    exitBtn.setFont(new Font("Arial", Font.BOLD, 18));

    exitBtn.addActionListener(e -> System.exit(0));*/

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10));
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    JLabel greeting = new JLabel("Welcome");
    greeting.setFont(new Font("Arial", Font.BOLD, 36));
    greeting.setAlignmentX(CENTER_ALIGNMENT);

    //USERNAME
    JLabel accountNumber = new JLabel("Username:");
    accountNumber.setFont(new Font("Arial", Font.BOLD, 18));
    accountNumber.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));


    JPanel accNumPanel = new JPanel();
    accNumPanel.setBackground(new Color(255, 255, 255));
    accNumPanel.setPreferredSize(new Dimension(700, 50));
    accNumPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0 ,50));

    //USERNAME TEXTFIELD
    JTextField inputAccNumber = new JTextField(20);
    inputAccNumber.setFont(new Font("Arial", Font.PLAIN, 18));
    accNumPanel.add(inputAccNumber);



    JLabel accountPasswd = new JLabel("Password:");
    accountPasswd.setFont(new Font("Arial", Font.BOLD, 18));
    accountPasswd.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 168));


    JPanel accPassPanel = new JPanel();
    accPassPanel.setBackground(new Color(255, 255, 255));
    accPassPanel.setSize(new Dimension(700, 90));
    accPassPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20 ,50));

    JPasswordField inputAccPasswd= new JPasswordField(20);
    inputAccPasswd.setFont(new Font("Arial", Font.PLAIN, 18));
    accPassPanel.add(inputAccPasswd);


    JPanel loginBtnPanel = new JPanel();
    loginBtnPanel.setBackground(new Color(255, 255, 255));
    loginBtnPanel.setSize(new Dimension(700, 90));
    loginBtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20 ,50));


    JButton loginBtn = new JButton("Log In");
    loginBtn.setPreferredSize(new Dimension(100, 40));
    loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
    loginBtn.setBackground(new Color(13, 161, 204));
    loginBtn.setForeground(new Color(255, 255, 255));
    loginBtnPanel.add(loginBtn);

    /**
     * PRACTICE
    try{
      loginBtn.addActionListener(e -> {
        char [] password = inputAccPasswd.getPassword();
      });
    }catch (Exception e){

    }**/

    loginBtn.addActionListener(e -> {
      char[] password = inputAccPasswd.getPassword();
      String inputPassword = new String(password);

      // Clear the password from memory
      Arrays.fill(password, '0');

      // Verify password (compare to "123" in this example)
      if (!inputPassword.equals("123")) {
        // Show error message
        JOptionPane.showMessageDialog(
                this,                       // Parent component
                "Incorrect password!",      // Message
                "Login Error",              // Title
                JOptionPane.ERROR_MESSAGE   // Message type
        );

        // Clear the password field
        inputAccPasswd.setText("");

        // Optionally set focus back to password field
        inputAccPasswd.requestFocusInWindow();
      } else {
        // Password is correct - proceed with login
        JOptionPane.showMessageDialog(
                this,
                "Login successful!",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE
        );
        // Show the home screen
        mainLayout.show(main, "CUSTOMER LIST");
      }
    });



    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
   // panelLeft.add(signUpBtn);
    //panelLeft.add(exitBtn);

    panelRight.add(greeting);
    panelRight.add(accountNumber);
    panelRight.add(accNumPanel);
    panelRight.add(accountPasswd);
    panelRight.add(accPassPanel);
    panelRight.add(loginBtnPanel);
    return panel;
  }

  //CREATING ACCOUNT
  /**private JPanel createSignUpPage(){
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    JPanel panelLeft = new JPanel();
    panelLeft.setBackground(new Color(13, 161, 204));
    panelLeft.setPreferredSize(new Dimension(300, 600));


    JLabel bankTitle = new JLabel("BANK NAME");
    bankTitle.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0));
    bankTitle.setForeground(new Color(235, 241, 238));
    bankTitle.setFont(new Font("Arial", Font.BOLD, 32));
    bankTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bankSubtitle = new JLabel("Number One Bank");
    bankSubtitle.setForeground(new Color(235, 241, 238));
    bankSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 130, 0));

    JButton backBtn = new JButton("Back");
    backBtn.setFont(new Font("Arial", Font.BOLD, 16));
    backBtn.setPreferredSize(new Dimension(300, 80));
    backBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10));
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    JLabel greeting = new JLabel("Create Account");
    greeting.setFont(new Font("Arial", Font.BOLD, 36));
    greeting.setAlignmentX(CENTER_ALIGNMENT);

    //USERNAME
    JLabel accountNumber = new JLabel("Bank Account:");
    accountNumber.setFont(new Font("Arial", Font.BOLD, 18));
    accountNumber.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 200));


    JPanel accNumPanel = new JPanel();
    accNumPanel.setBackground(new Color(255, 255, 255));
    accNumPanel.setPreferredSize(new Dimension(700, 50));
    accNumPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0 ,50));

    //USERNAME TEXTFIELD
    JTextField inputAccNumber = new JTextField(20);
    inputAccNumber.setFont(new Font("Arial", Font.PLAIN, 18));
    accNumPanel.add(inputAccNumber);



    JLabel accountPasswd = new JLabel("Password:");
    accountPasswd.setFont(new Font("Arial", Font.BOLD, 18));
    accountPasswd.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 323));

    JPanel accPassPanel = new JPanel();
    accPassPanel.setBackground(new Color(255, 255, 255));
    accPassPanel.setSize(new Dimension(700, 90));
    accPassPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20 ,50));

    JTextField inputAccPasswd= new JTextField(20);
    inputAccPasswd.setFont(new Font("Arial", Font.PLAIN, 18));
    accPassPanel.add(inputAccPasswd);

    JPanel signInPanel = new JPanel();
    signInPanel.setBackground(new Color(255, 255, 255));
    signInPanel.setSize(new Dimension(700, 90));
    signInPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20 ,50));

    JButton signInBtn = new JButton("Sign In");
    signInBtn.setPreferredSize(new Dimension(100, 40));
    signInBtn.setFont(new Font("Arial", Font.BOLD, 16));
    signInBtn.setBackground(new Color(13, 161, 204));
    signInBtn.setForeground(new Color(255, 255, 255));
    signInPanel.add(signInBtn);
    signInBtn.addActionListener(e -> {
      mainLayout.show(main, "CUSTOMER LIST");
    });

    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight,BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(backBtn);

    panelRight.add(greeting);
    panelRight.add(accountNumber);
    panelRight.add(accNumPanel);
    panelRight.add(accountPasswd);
    panelRight.add(accPassPanel);
    panelRight.add(signInPanel);

    return panel;
  }*/

  private JPanel createCustomerListPage() {
    // Main panel with left and right sections
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    // LEFT PANEL - Navigation (unchanged)
    JPanel panelLeft = new JPanel();
    panelLeft.setBackground(new Color(13, 161, 204));
    panelLeft.setPreferredSize(new Dimension(300, 600));

    JLabel bankTitle = new JLabel("BANK NAME");
    bankTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
    bankTitle.setForeground(new Color(235, 241, 238));
    bankTitle.setFont(new Font("Arial", Font.BOLD, 32));
    bankTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bankSubtitle = new JLabel("Number One Bank");
    bankSubtitle.setForeground(new Color(235, 241, 238));
    bankSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

    JButton customerListBtn = new JButton("Customer List");
    customerListBtn.setPreferredSize(new Dimension(300, 60));
    customerListBtn.setBackground(new Color(7, 65, 81));
    customerListBtn.setForeground(Color.WHITE);
    customerListBtn.addActionListener(e -> mainLayout.show(main, "CUSTOMER LIST"));

    JButton transactionProcessBtn = new JButton("Transaction Process");
    transactionProcessBtn.setPreferredSize(new Dimension(300, 60));
    transactionProcessBtn.addActionListener(e -> mainLayout.show(main, "TRANSACTION PROCESS"));

    JButton transactionHistoryBtn = new JButton("Transaction History");
    transactionHistoryBtn.setPreferredSize(new Dimension(300, 60));
    transactionHistoryBtn.addActionListener(e -> mainLayout.show(main, "TRANSACTION HISTORY"));

    JButton addCustomerBtn = new JButton("Add Customer");
    addCustomerBtn.setPreferredSize(new Dimension(300, 60));
    addCustomerBtn.addActionListener(e -> mainLayout.show(main, "ADD CUSTOMER"));

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> mainLayout.show(main, "LOG IN"));

    // RIGHT PANEL - Customer List and Account Profile
    JPanel panelRight = new JPanel(new BorderLayout());
    panelRight.setBackground(Color.WHITE);
    panelRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // TOP SECTION - Header and Search
    JPanel topSection = new JPanel();
    topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
    topSection.setBackground(Color.WHITE);

    // Header Panel
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);

    JLabel customerListLabel = new JLabel("Customer Lists");
    customerListLabel.setFont(new Font("Arial", Font.BOLD, 26));
    headerPanel.add(customerListLabel, BorderLayout.WEST);

    JPanel rightHeaderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    rightHeaderPanel.setBackground(Color.WHITE);

    JLabel totalLabel = new JLabel("TOTAL: 0");
    totalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    rightHeaderPanel.add(totalLabel);

    JLabel dateTimeLabel = new JLabel("", SwingConstants.RIGHT);
    dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    rightHeaderPanel.add(dateTimeLabel);

    headerPanel.add(rightHeaderPanel, BorderLayout.CENTER);
    topSection.add(headerPanel);

    // Search Panel
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    searchPanel.setBackground(Color.WHITE);
    searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    searchPanel.add(new JLabel("Search for Account ID:"));

    JTextField searchInput = new JTextField(15);
    searchPanel.add(searchInput);

    JButton searchBtn = new JButton("Search");
    searchBtn.setBackground(new Color(13, 161, 204));
    searchBtn.setForeground(Color.WHITE);
    searchBtn.setFont(new Font("Arial", Font.BOLD, 12));
    searchPanel.add(searchBtn);

    topSection.add(searchPanel);

    // MAIN CONTENT - Table and Account Profile
    JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
    contentPanel.setBackground(Color.WHITE);

    // Customer Table Panel
    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));



    loadAccountsFromCsv();

    JTable customerTable = new JTable(model);
    customerTable.setFont(new Font("Arial", Font.PLAIN, 14));
    customerTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    customerTable.setRowHeight(25);

    JScrollPane scrollPane = new JScrollPane(customerTable);
    tablePanel.add(scrollPane, BorderLayout.CENTER);
    contentPanel.add(tablePanel);

    // Update total count
    totalLabel.setText("TOTAL: " + model.getRowCount());

    // Account Profile Panel
    JPanel accountProfilePanel = new JPanel();
    accountProfilePanel.setBorder(BorderFactory.createTitledBorder("Account Profile"));
    accountProfilePanel.setLayout(new BoxLayout(accountProfilePanel, BoxLayout.Y_AXIS));

    // Account ID
    JPanel accountIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountIdPanel.add(new JLabel("Account ID:"));
    JLabel accountIdValue = new JLabel("");
    accountIdPanel.add(accountIdValue);
    accountProfilePanel.add(accountIdPanel);

    // Account Name
    JPanel accountNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountNamePanel.add(new JLabel("Account Name:"));
    JLabel accountNameValue = new JLabel("");
    accountNamePanel.add(accountNameValue);
    accountProfilePanel.add(accountNamePanel);

    // Account Type
    JPanel accountTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountTypePanel.add(new JLabel("Account Type:"));
    JLabel accountTypeValue = new JLabel("");
    accountTypePanel.add(accountTypeValue);
    accountProfilePanel.add(accountTypePanel);

    // Balance (kept in profile but removed from table)
    JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    balancePanel.add(new JLabel("Balance Amount:"));
    JLabel balanceValue = new JLabel("");
    balancePanel.add(balanceValue);
    accountProfilePanel.add(balancePanel);

    // Close Account Button
    JButton closeAccountBtn = new JButton("Close Account");
    closeAccountBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
    closeAccountBtn.setBackground(new Color(220, 53, 69));
    closeAccountBtn.setForeground(Color.WHITE);
    accountProfilePanel.add(Box.createVerticalStrut(20));
    accountProfilePanel.add(closeAccountBtn);

    contentPanel.add(accountProfilePanel);
    topSection.add(contentPanel);

    // BOTTOM BUTTONS
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

    // Add components to right panel
    panelRight.add(topSection, BorderLayout.CENTER);
    panelRight.add(buttonPanel, BorderLayout.SOUTH);

    // Timer for date/time
    Timer timer = new Timer(1000, e -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      dateTimeLabel.setText(LocalDateTime.now().format(formatter));
    });
    timer.start();

    // Search Functionality - updated to handle new column indices
    searchBtn.addActionListener(e -> {
      String searchText = searchInput.getText().trim();
      if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(panel, "Please enter an account ID", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      boolean found = false;
      for (int i = 0; i < model.getRowCount(); i++) {
        if (model.getValueAt(i, 0).toString().equals(searchText)) {
          customerTable.setRowSelectionInterval(i, i);
          customerTable.scrollRectToVisible(customerTable.getCellRect(i, 0, true));

          // Update account profile
          accountIdValue.setText(model.getValueAt(i, 0).toString());
          accountNameValue.setText(model.getValueAt(i, 1).toString());
          accountTypeValue.setText(model.getValueAt(i, 2).toString());

          // For balance, we need to read it from the CSV again since it's not in the table
          try (BufferedReader br = new BufferedReader(new FileReader("accounts.csv"))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
              if (firstLine) {
                firstLine = false;
                continue;
              }
              String[] values = line.split(",");
              if (values.length >= 4 && values[0].trim().equals(searchText)) {
                double balance = Double.parseDouble(values[2].trim());
                balanceValue.setText(String.format("$%,.2f", balance));
                break;
              }
            }
          } catch (Exception ex) {
            balanceValue.setText("N/A");
          }

          found = true;
          break;
        }
      }

      if (!found) {
        JOptionPane.showMessageDialog(panel, "Account not found", "Search Result", JOptionPane.WARNING_MESSAGE);
      }
    });

    // Close Account Functionality
    closeAccountBtn.addActionListener(e -> {
      if (accountIdValue.getText().isEmpty()) {
        JOptionPane.showMessageDialog(panel, "No account selected", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      int confirm = JOptionPane.showConfirmDialog(panel,
              "Are you sure you want to close account " + accountIdValue.getText() + "?",
              "Confirm Account Closure",
              JOptionPane.YES_NO_OPTION);

      if (confirm == JOptionPane.YES_OPTION) {
        // Here you would add your account closure logic
        JOptionPane.showMessageDialog(panel, "Account " + accountIdValue.getText() + " closed");
        accountIdValue.setText("");
        accountNameValue.setText("");
        accountTypeValue.setText("");
        balanceValue.setText("");
      }
    });

    // Add panels to main panel
    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    // Add components to left panel
    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn);

    return panel;
  }

  private JPanel createTransactionProcessPage() {
    // Main panel with left and right sections
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    // LEFT PANEL - Keep exactly as is
    JPanel panelLeft = new JPanel();
    panelLeft.setBackground(new Color(13, 161, 204));
    panelLeft.setPreferredSize(new Dimension(300, 600));

    JLabel bankTitle = new JLabel("BANK NAME");
    bankTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
    bankTitle.setForeground(new Color(235, 241, 238));
    bankTitle.setFont(new Font("Arial", Font.BOLD, 32));
    bankTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bankSubtitle = new JLabel("Number One Bank");
    bankSubtitle.setForeground(new Color(235, 241, 238));
    bankSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

    JButton customerListBtn = new JButton("Customer List");
    customerListBtn.setPreferredSize(new Dimension(300, 60));
    customerListBtn.addActionListener(e -> {
      mainLayout.show(main,"CUSTOMER LIST");
    });

    JButton transactionProcessBtn = new JButton("Transaction Process");
    transactionProcessBtn.setPreferredSize(new Dimension(300, 60));
    transactionProcessBtn.setBackground(new Color(7, 65, 81));
    transactionProcessBtn.setForeground(new Color(255, 255, 255));
    transactionProcessBtn.addActionListener(e -> {
      mainLayout.show(main, "TRANSACTION PROCESS");
    });

    JButton transactionHistoryBtn = new JButton("Transaction History");
    transactionHistoryBtn.setPreferredSize(new Dimension(300, 60));
    transactionHistoryBtn.addActionListener(e -> {
      mainLayout.show(main, "TRANSACTION HISTORY");
    });

    JButton addCustomerBtn = new JButton("Add Customer");
    addCustomerBtn.setPreferredSize(new Dimension(300, 60));
    addCustomerBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD CUSTOMER");
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    // RIGHT PANEL - Revised with search functionality
    JPanel panelRight = new JPanel(new BorderLayout());
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Transaction type buttons
    JPanel transactionTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    transactionTypePanel.setBackground(Color.WHITE);

    JButton depositBtn = createTransactionButton("Deposit");
    JButton withdrawBtn = createTransactionButton("Withdraw");
    JButton transferBtn = createTransactionButton("Transfer");

    transactionTypePanel.add(depositBtn);
    transactionTypePanel.add(withdrawBtn);
    transactionTypePanel.add(transferBtn);

    panelRight.add(transactionTypePanel, BorderLayout.NORTH);

    // Transaction form panel
    JPanel transactionFormPanel = new JPanel();
    transactionFormPanel.setLayout(new BoxLayout(transactionFormPanel, BoxLayout.Y_AXIS));
    transactionFormPanel.setBorder(BorderFactory.createTitledBorder("Deposit"));
    transactionFormPanel.setBackground(Color.WHITE);

    // Account search panel
    JPanel accountSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountSearchPanel.setBackground(Color.WHITE);
    accountSearchPanel.add(new JLabel("Account No:"));

    JTextField accountField = new JTextField(10);
    accountSearchPanel.add(accountField);

    JButton searchBtn = new JButton("Search");
    searchBtn.setBackground(new Color(13, 161, 204));
    searchBtn.setForeground(Color.WHITE);
    searchBtn.setFont(new Font("Arial", Font.BOLD, 12));
    accountSearchPanel.add(searchBtn);

    transactionFormPanel.add(accountSearchPanel);

    // Account information
    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    namePanel.setBackground(Color.WHITE);
    namePanel.add(new JLabel("Name:"));
    JLabel nameLabel = new JLabel("");
    namePanel.add(nameLabel);
    transactionFormPanel.add(namePanel);

    JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    balancePanel.setBackground(Color.WHITE);
    balancePanel.add(new JLabel("Balance:"));
    JLabel balanceLabel = new JLabel("$0.00");
    balancePanel.add(balanceLabel);
    transactionFormPanel.add(balancePanel);

    // Transaction amount panel (will be updated based on transaction type)
    JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    amountPanel.setBackground(Color.WHITE);
    amountPanel.add(new JLabel("Amount: "));
    JTextField amountField = new JTextField(10);
    amountPanel.add(amountField);

    // Destination account panel (for transfers)
    JPanel destAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    destAccountPanel.setBackground(Color.WHITE);
    destAccountPanel.add(new JLabel("To Account:"));
    JTextField destAccountField = new JTextField(10);
    destAccountPanel.add(destAccountField);
    destAccountPanel.setVisible(false); // Hidden by default

    // Total panel
    JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    totalPanel.setBackground(Color.WHITE);
    totalPanel.add(new JLabel("Total:"));
    JLabel totalLabel = new JLabel("$0.00");
    totalPanel.add(totalLabel);

    // Process button
    JButton processBtn = new JButton("DEPOSIT");
    processBtn.setBackground(new Color(13, 161, 204));
    processBtn.setForeground(Color.WHITE);
    processBtn.setFont(new Font("Arial", Font.BOLD, 14));
    processBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

    // Add components to form
    transactionFormPanel.add(amountPanel);
    transactionFormPanel.add(destAccountPanel);
    transactionFormPanel.add(totalPanel);
    transactionFormPanel.add(Box.createVerticalStrut(20));
    transactionFormPanel.add(processBtn);

    panelRight.add(transactionFormPanel, BorderLayout.CENTER);

    // Add left and right panels to main panel
    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    // Add components to left panel
    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn);

    // Search button action
    searchBtn.addActionListener(e -> {
      String accountNo = accountField.getText().trim();
      if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(panel, "Please enter an account number", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      boolean found = false;
      try (BufferedReader br = new BufferedReader(new FileReader("accounts.csv"))) {
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
          if (firstLine) {
            firstLine = false;
            continue; // Skip header row
          }

          String[] values = line.split(",");
          if (values.length >= 5) {
            String csvAccountNo = values[0].trim();
            String name = values[1].trim();
            String balance = values[2].trim();
            String accountType = values[3].trim();
            String accountExists = values[4].trim();

            if (csvAccountNo.equals(accountNo) && accountExists.equalsIgnoreCase("TRUE")) {
              nameLabel.setText(name);
              balanceLabel.setText("$" + balance);
              found = true;
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
    });

    // Transaction type button actions
    depositBtn.addActionListener(e -> {
      transactionFormPanel.setBorder(BorderFactory.createTitledBorder("Deposit"));
      processBtn.setText("DEPOSIT");

      amountPanel.removeAll(); // Ensure a fresh panel update
      amountPanel.add(new JLabel("Deposit Amount:"));
      amountPanel.add(amountField); // Always add amountField back
      amountPanel.revalidate();
      amountPanel.repaint();

      destAccountPanel.setVisible(false);
    });

    withdrawBtn.addActionListener(e -> {
      transactionFormPanel.setBorder(BorderFactory.createTitledBorder("Withdraw"));
      processBtn.setText("WITHDRAW");

      amountPanel.removeAll();
      amountPanel.add(new JLabel("Withdraw Amount:"));
      amountPanel.add(amountField);
      amountPanel.revalidate();

      destAccountPanel.setVisible(false);
    });

    transferBtn.addActionListener(e -> {
      transactionFormPanel.setBorder(BorderFactory.createTitledBorder("Transfer"));
      processBtn.setText("TRANSFER");

      amountPanel.removeAll();
      amountPanel.add(new JLabel("Transfer Amount:"));
      amountPanel.add(amountField);
      amountPanel.revalidate();

      destAccountPanel.setVisible(true);
    });

    // Process button action
    processBtn.addActionListener(e -> {
      if (nameLabel.getText().isEmpty()) {
        JOptionPane.showMessageDialog(panel,
                "Please search for an account first",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
      }

      String amountText = amountField.getText();
      try {
        double amount = Double.parseDouble(amountText);
        String transactionType = processBtn.getText();

        // Here you would add your transaction processing logic
        String message = transactionType + " of $" + amount + " processed for account " + accountField.getText();

        if (transactionType.equals("TRANSFER")) {
          message += " to account " + destAccountField.getText();
        }

        JOptionPane.showMessageDialog(panel,
                message,
                "Transaction Successful",
                JOptionPane.INFORMATION_MESSAGE);

        // Clear fields after successful transaction
        amountField.setText("");
        destAccountField.setText("");
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(panel,
                "Please enter a valid amount",
                "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    });


    return panel;
  }

  private JButton createTransactionButton(String text) {
    JButton button = new JButton(text);
    button.setPreferredSize(new Dimension(120, 40));
    button.setBackground(new Color(13, 161, 204));
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    return button;
  }

  private JPanel createTransactionHistoryPage(){
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    JPanel panelLeft = new JPanel();
    panelLeft.setBackground(new Color(13, 161, 204));
    panelLeft.setPreferredSize(new Dimension(300, 600));


    JLabel bankTitle = new JLabel("BANK NAME");
    bankTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
    bankTitle.setForeground(new Color(235, 241, 238));
    bankTitle.setFont(new Font("Arial", Font.BOLD, 32));
    bankTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bankSubtitle = new JLabel("Number One Bank");
    bankSubtitle.setForeground(new Color(235, 241, 238));
    bankSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

    JButton customerListBtn = new JButton("Customer List");
    customerListBtn.setPreferredSize(new Dimension(300, 60));
    customerListBtn.addActionListener(e -> {
      mainLayout.show(main,"CUSTOMER LIST");
    });

    JButton transactionProcessBtn = new JButton("Transaction Process");
    transactionProcessBtn.setPreferredSize(new Dimension(300, 60));
    transactionProcessBtn.addActionListener(e -> {
      mainLayout.show(main, "TRANSACTION PROCESS");
    });

    JButton transactionHistoryBtn = new JButton("Transaction History");
    transactionHistoryBtn.setPreferredSize(new Dimension(300, 60));
    transactionHistoryBtn.setBackground(new Color(7, 65, 81));
    transactionHistoryBtn.setForeground(new Color(255, 255, 255));
    transactionHistoryBtn.addActionListener(e -> {
      mainLayout.show(main, "TRANSACTION HISTORY" );
    });

    JButton addCustomerBtn = new JButton("Add Customer");
    addCustomerBtn.setPreferredSize(new Dimension(300, 60));
    addCustomerBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD CUSTOMER" );
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel();
    panelRight.setBackground(Color.WHITE);
    panelRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);

    JLabel historyLabel = new JLabel("Transaction History");
    historyLabel.setFont(new Font("Arial", Font.BOLD, 26));
    headerPanel.add(historyLabel, BorderLayout.WEST);

    JPanel rightHeaderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    rightHeaderPanel.setBackground(Color.WHITE);

    JLabel totalLabel = new JLabel("TOTAL: 0");
    totalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    rightHeaderPanel.add(totalLabel);

    JLabel dateTimeLabel = new JLabel("", SwingConstants.RIGHT);
    dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    rightHeaderPanel.add(dateTimeLabel);

    headerPanel.add(rightHeaderPanel, BorderLayout.CENTER);
    panelRight.add(headerPanel, BorderLayout.NORTH);

// Transaction Table Setup
    String[] columnNames = {"Account No", "Name", "Type", "Amount", "Date"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

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

// Table Display
    JTable historyTable = new JTable(model);
    historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
    historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    historyTable.setRowHeight(25);

    JScrollPane scrollPane = new JScrollPane(historyTable);
    panelRight.add(scrollPane, BorderLayout.CENTER);

// Update transaction count dynamically
    totalLabel.setText("TOTAL: " + model.getRowCount());

    // Create a Refresh button
    JButton refreshBtn = new JButton("Refresh");
    refreshBtn.setBackground(new Color(13, 161, 204));
    refreshBtn.setForeground(Color.WHITE);
    refreshBtn.setFont(new Font("Arial", Font.BOLD, 14));

// Add refresh button action listener
    refreshBtn.addActionListener(e -> {
      // Clear existing table data
      model.setRowCount(0);

      // Reload data from CSV file
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
      } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading transaction history from CSV", "Error", JOptionPane.ERROR_MESSAGE);
      }

      // Update total count dynamically after refresh
      totalLabel.setText("TOTAL: " + model.getRowCount());
    });

// Add Refresh button below the table
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.add(refreshBtn);

    panelRight.add(buttonPanel, BorderLayout.SOUTH);



    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn, BorderLayout.SOUTH);

    return panel;
  }

  private JPanel createAddCustomerPage(){
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    JPanel panelLeft = new JPanel();
    panelLeft.setBackground(new Color(13, 161, 204));
    panelLeft.setPreferredSize(new Dimension(300, 600));


    JLabel bankTitle = new JLabel("BANK NAME");
    bankTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
    bankTitle.setForeground(new Color(235, 241, 238));
    bankTitle.setFont(new Font("Arial", Font.BOLD, 32));
    bankTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bankSubtitle = new JLabel("Number One Bank");
    bankSubtitle.setForeground(new Color(235, 241, 238));
    bankSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

    JButton customerListBtn = new JButton("Customer List");
    customerListBtn.setPreferredSize(new Dimension(300, 60));
    customerListBtn.addActionListener(e -> {
      mainLayout.show(main,"CUSTOMER LIST");
    });

    JButton transactionProcessBtn = new JButton("Transaction Process");
    transactionProcessBtn.setPreferredSize(new Dimension(300, 60));
    transactionProcessBtn.addActionListener(e -> {
      mainLayout.show(main, "TRANSACTION PROCESS");
    });

    JButton transactionHistoryBtn = new JButton("Transaction History");
    transactionHistoryBtn.setPreferredSize(new Dimension(300, 60));
    transactionHistoryBtn.addActionListener(e -> {
      mainLayout.show(main, "TRANSACTION HISTORY" );
    });

    JButton addCustomerBtn = new JButton("Add Customer");
    addCustomerBtn.setPreferredSize(new Dimension(300, 60));
    addCustomerBtn.setBackground(new Color(7, 65, 81));
    addCustomerBtn.setForeground(new Color(255, 255, 255));
    addCustomerBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD CUSTOMER" );
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10));
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    JLabel greeting = new JLabel("Create Account");
    greeting.setFont(new Font("Arial", Font.BOLD, 36));
    greeting.setAlignmentX(CENTER_ALIGNMENT);

    //USERNAME
    JLabel accountNumber = new JLabel("Bank Account:");
    accountNumber.setFont(new Font("Arial", Font.BOLD, 18));
    accountNumber.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 200));


    JPanel accNumPanel = new JPanel();
    accNumPanel.setBackground(new Color(255, 255, 255));
    accNumPanel.setPreferredSize(new Dimension(700, 50));
    accNumPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0 ,50));

    //USERNAME TEXTFIELD
    JTextField inputAccNumber = new JTextField(20);
    inputAccNumber.setFont(new Font("Arial", Font.PLAIN, 18));
    accNumPanel.add(inputAccNumber);



    JLabel accountPasswd = new JLabel("Password:");
    accountPasswd.setFont(new Font("Arial", Font.BOLD, 18));
    accountPasswd.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 323));

    JPanel accPassPanel = new JPanel();
    accPassPanel.setBackground(new Color(255, 255, 255));
    accPassPanel.setSize(new Dimension(700, 90));
    accPassPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0 ,50));

    JTextField inputAccPasswd= new JTextField(20);
    inputAccPasswd.setFont(new Font("Arial", Font.PLAIN, 18));
    accPassPanel.add(inputAccPasswd);

    JLabel accountBalance = new JLabel("Balance:");
    accountBalance.setFont(new Font("Arial", Font.BOLD, 18));
    accountBalance.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 323));

    JPanel accBalancePanel = new JPanel();
    accBalancePanel.setBackground(new Color(255, 255, 255));
    accBalancePanel.setSize(new Dimension(700, 90));
    accBalancePanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0 ,50));

    JTextField inputAccBalance= new JTextField(20);
    inputAccBalance.setFont(new Font("Arial", Font.PLAIN, 18));
    accBalancePanel.add(inputAccBalance);

    JLabel accountTypeLabel = new JLabel("Account Type:");
    accountTypeLabel.setFont(new Font("Arial", Font.BOLD, 18));
    accountTypeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 323));

    JPanel accTypePanel = new JPanel();
    accTypePanel.setBackground(Color.WHITE);
    accTypePanel.setSize(new Dimension(700, 90));
    accTypePanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 50));

    String[] accountTypes = {"Regular", "Investment"};
    JComboBox<String> accountTypeComboBox = new JComboBox<>(accountTypes);
    accountTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
    accTypePanel.add(accountTypeComboBox);

    JPanel signInPanel = new JPanel();
    signInPanel.setBackground(new Color(255, 255, 255));
    signInPanel.setSize(new Dimension(700, 90));
    signInPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20 ,50));

    JButton createAcctBtn = new JButton("Create");
    createAcctBtn.setPreferredSize(new Dimension(100, 40));
    createAcctBtn.setFont(new Font("Arial", Font.BOLD, 16));
    createAcctBtn.setBackground(new Color(13, 161, 204));
    createAcctBtn.setForeground(new Color(255, 255, 255));
    createAcctBtn.addActionListener(e -> handleCreateAccount(inputAccBalance, inputAccNumber, inputAccPasswd, accountTypeComboBox));
    signInPanel.add(createAcctBtn);

    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight,BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn, BorderLayout.SOUTH);

    panelRight.add(greeting);
    panelRight.add(accountNumber);
    panelRight.add(accNumPanel);
    panelRight.add(accountPasswd);
    panelRight.add(accPassPanel);
    panelRight.add(accountBalance);
    panelRight.add(accBalancePanel);
    panelRight.add(accountTypeLabel);
    panelRight.add(accTypePanel);
    panelRight.add(signInPanel);
    return panel;
  }

  private void handleCreateAccount(JTextField inputAccBalance, JTextField inputAccNumber, JTextField inputAccPasswd, JComboBox<String> accountTypeComboBox) {
    String accountNo = inputAccNumber.getText().trim();
    String password = inputAccPasswd.getText().trim();
    String balanceStr = inputAccBalance.getText().trim();
    String accountType = accountTypeComboBox.getSelectedItem().toString();

    if (accountNo.isEmpty() || password.isEmpty() || balanceStr.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try {
      double balance = Double.parseDouble(balanceStr);

      BankAccount newAccount = new BankAccount(accountNo, password, balance, accountType);
      addAccountToCSV(newAccount);

      JOptionPane.showMessageDialog(null, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

      inputAccNumber.setText("");
      inputAccPasswd.setText("");
      inputAccBalance.setText("");
      accountTypeComboBox.setSelectedIndex(0);

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Invalid balance format", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }



  private void addAccountToCSV(BankAccount account) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.csv", true))) {
      writer.write(
              account.getAccountNumber() + "," +
              account.getName() + "," +
              account.getPassword() + "," +
              account.getBalance() + "," +
              account.getAccountType() + "," +
              account.isActive());
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error saving account to CSV!");
    }
  }

  public void loadAccountsFromCsv() {
    // Load data from CSV
    try (BufferedReader br = new BufferedReader(new FileReader("accounts.csv"))) {
      String line;
      boolean firstLine = true;

      while ((line = br.readLine()) != null) {
        if (firstLine) {
          firstLine = false;
          continue; // Skip header
        }

        String[] values = line.split(",");
        if (values.length >= 4) {
          String accountNumber = values[0].trim();
          String name = values[1].trim();
          String accountType = values[3].trim();

          // Add data without balance to the table
          model.addRow(new Object[]{
                  accountNumber,
                  name,
                  accountType
          });
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error loading customer data from CSV", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Error parsing balance amounts", "Data Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  // Table Data - Removed Balance column
  String[] columnNames = {"Acc. No", "Name", "Acc. Type"};
  DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };


}
