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
    // Main panel with left and right sections (keep left panel unchanged)
    JPanel panel = new JPanel(new BorderLayout());
    panel.setPreferredSize(new Dimension(1000, 600));

    // Left panel (keep exactly as is)
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
    customerListBtn.setForeground(new Color(255, 255, 255));
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
    addCustomerBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD CUSTOMER" );
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    // RIGHT PANEL - COMPLETELY REVISED TO MATCH SCREENSHOT
    JPanel panelRight = new JPanel(new BorderLayout());
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // TOP SECTION - Customer Lists header and search
    JPanel topSection = new JPanel();
    topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
    topSection.setBackground(Color.WHITE);

    // Header with Customer Lists title and total
    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBackground(Color.WHITE);

    JLabel customerListLabel = new JLabel("Customer Lists");
    customerListLabel.setFont(new Font("Arial", Font.BOLD, 26));
    headerPanel.add(customerListLabel, BorderLayout.WEST);

    JPanel rightHeaderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    rightHeaderPanel.setBackground(Color.WHITE);

    JLabel totalLabel = new JLabel("TOTAL: 3"); // Will update dynamically
    totalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    rightHeaderPanel.add(totalLabel);

    JLabel dateTimeLabel = new JLabel("", SwingConstants.RIGHT);
    dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    rightHeaderPanel.add(dateTimeLabel);

    headerPanel.add(rightHeaderPanel, BorderLayout.CENTER);
    topSection.add(headerPanel);

    // Search panel
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

    // MAIN CONTENT - Split between customer table and account profile
    JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
    contentPanel.setBackground(Color.WHITE);

    // LEFT CONTENT - Customer table
    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    // Table data - replace with your actual data loading
    String[] columnNames = {"Account No", "Name", "Account Type"};
    Object[][] data = {
            {"117816", "Dave Scott", "Regular"},
            {"607816", "John Scott", "Regular"},
            {"642563", "Rahul Jha", "Investment"}
    };

    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    JTable customerTable = new JTable(model);
    customerTable.setFont(new Font("Arial", Font.PLAIN, 14));
    customerTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    customerTable.setRowHeight(25);

    JScrollPane scrollPane = new JScrollPane(customerTable);
    tablePanel.add(scrollPane, BorderLayout.CENTER);
    contentPanel.add(tablePanel);

    // RIGHT CONTENT - Account Profile
    JPanel accountProfilePanel = new JPanel();
    accountProfilePanel.setBorder(BorderFactory.createTitledBorder("Account Profile"));
    accountProfilePanel.setLayout(new BoxLayout(accountProfilePanel, BoxLayout.Y_AXIS));

    // Account ID section
    JPanel accountIdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountIdPanel.add(new JLabel("Account ID:"));
    JLabel accountIdValue = new JLabel("");
    accountIdPanel.add(accountIdValue);
    accountProfilePanel.add(accountIdPanel);

    // Account Name section
    JPanel accountNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountNamePanel.add(new JLabel("Account Name:"));
    JLabel accountNameValue = new JLabel("");
    accountNamePanel.add(accountNameValue);
    accountProfilePanel.add(accountNamePanel);

    // Account Type section
    JPanel accountTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accountTypePanel.add(new JLabel("Account Type:"));
    JLabel accountTypeValue = new JLabel("");
    accountTypePanel.add(accountTypeValue);
    accountProfilePanel.add(accountTypePanel);

    // Balance section
    JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    balancePanel.add(new JLabel("Balance Amount:"));
    JLabel balanceValue = new JLabel("");
    balancePanel.add(balanceValue);
    accountProfilePanel.add(balancePanel);

    // Close Account button
    JButton closeAccountBtn = new JButton("Close Account");
    closeAccountBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
    closeAccountBtn.setBackground(new Color(220, 53, 69));
    closeAccountBtn.setForeground(Color.WHITE);
    accountProfilePanel.add(Box.createVerticalStrut(20));
    accountProfilePanel.add(closeAccountBtn);

    contentPanel.add(accountProfilePanel);
    topSection.add(contentPanel);

    // BOTTOM SECTION - Action buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

    // Add all sections to right panel
    panelRight.add(topSection, BorderLayout.CENTER);
    panelRight.add(buttonPanel, BorderLayout.SOUTH);

    // Timer to update date/time
    Timer timer = new Timer(1000, e -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      dateTimeLabel.setText(LocalDateTime.now().format(formatter));
    });
    timer.start();

    // Search functionality with account profile display
    searchBtn.addActionListener(e -> {
      String searchText = searchInput.getText().trim();
      if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(panel, "Please enter an account ID");
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
          // You would set actual balance from your data here
          balanceValue.setText("$10,000.00"); // Example value

          found = true;
          break;
        }
      }

      if (!found) {
        JOptionPane.showMessageDialog(panel, "Account not found", "Search Result", JOptionPane.WARNING_MESSAGE);
      }
    });

    // Close Account button action
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
        // Add your account closure logic here
        JOptionPane.showMessageDialog(panel, "Account " + accountIdValue.getText() + " closed");
        accountIdValue.setText("");
        accountNameValue.setText("");
        accountTypeValue.setText("");
        balanceValue.setText("");
      }
    });

    // Add left and right panels to main panel
    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    // Add components to left panel (unchanged)
    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn);

    return panel;
  }

  private JPanel createTransactionProcessPage(){
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
    transactionProcessBtn.setBackground(new Color(7, 65, 81));
    transactionProcessBtn.setForeground(new Color(255, 255, 255));
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

    JLabel header = new JLabel("Create Account");
    header.setFont(new Font("Arial", Font.BOLD, 36));
    header.setAlignmentX(LEFT_ALIGNMENT);





    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn, BorderLayout.SOUTH);

    panelRight.add(header);
    return panel;
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
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10));
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    JLabel header = new JLabel("Create Account");
    header.setFont(new Font("Arial", Font.BOLD, 36));
    header.setAlignmentX(LEFT_ALIGNMENT);





    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn, BorderLayout.SOUTH);

    panelRight.add(header);
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

    JLabel header = new JLabel("Create Account");
    header.setFont(new Font("Arial", Font.BOLD, 36));
    header.setAlignmentX(LEFT_ALIGNMENT);





    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(customerListBtn);
    panelLeft.add(transactionProcessBtn);
    panelLeft.add(transactionHistoryBtn);
    panelLeft.add(addCustomerBtn);
    panelLeft.add(logOutBtn, BorderLayout.SOUTH);

    panelRight.add(header);
    return panel;
  }


  // Save account data to a CSV file
  private void addAccountToCSV(BankAccount account) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.csv", true))) {
      writer.write(account.generateAccountNumber() + "," + account.getName() + "," + account.getBalance());
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error saving account to CSV!");
    }
  }

}
