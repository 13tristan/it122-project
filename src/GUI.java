import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import models.BankAccount;

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
    JPanel signUpPage = createSignUpPage();
    JPanel homePage = createHomepage();

    main.add(loginPage, "LOG IN");
    main.add(signUpPage, "SIGN UP");
    main.add(homePage, "HOME");

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

    JButton signUpBtn = new JButton("Create Account");
    signUpBtn.setPreferredSize(new Dimension(300, 80));
    signUpBtn.setFont(new Font("Arial", Font.BOLD, 18));
    signUpBtn.addActionListener(e -> {
      mainLayout.show(main, "SIGN UP");});

    JButton exitBtn = new JButton("Exit");
    exitBtn.setPreferredSize(new Dimension(300, 80));
    exitBtn.setFont(new Font("Arial", Font.BOLD, 18));

    exitBtn.addActionListener(e -> System.exit(0));

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(255, 255, 255));
    panelRight.setBorder(BorderFactory.createEmptyBorder(50, 10, 200, 10));
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    JLabel greeting = new JLabel("Welcome");
    greeting.setFont(new Font("Arial", Font.BOLD, 36));
    greeting.setAlignmentX(CENTER_ALIGNMENT);

    //USERNAME
    JLabel accountNumber = new JLabel("USERNAME:");
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



    JLabel accountPasswd = new JLabel("PASSWORD:");
    accountPasswd.setFont(new Font("Arial", Font.BOLD, 18));
    accountPasswd.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 168));

    JPanel accPassPanel = new JPanel();
    accPassPanel.setBackground(new Color(255, 255, 255));
    accPassPanel.setSize(new Dimension(700, 90));
    accPassPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20 ,50));

    JTextField inputAccPasswd= new JTextField(20);
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
    loginBtn.addActionListener(e -> {
      mainLayout.show(main, "HOME");
    });



    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(signUpBtn);
    panelLeft.add(exitBtn);

    panelRight.add(greeting);
    panelRight.add(accountNumber);
    panelRight.add(accNumPanel);
    panelRight.add(accountPasswd);
    panelRight.add(accPassPanel);
    panelRight.add(loginBtnPanel);
    return panel;
  }

  //CREATING ACCOUNT
  private JPanel createSignUpPage(){
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
    JLabel accountNumber = new JLabel("USERNAME:");
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



    JLabel accountPasswd = new JLabel("PASSWORD:");
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
      mainLayout.show(main, "HOME");
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
  }


  private JPanel createHomepage(){
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
    bankSubtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

    JButton homeBtn = new JButton("Home");
    homeBtn.setPreferredSize(new Dimension(300, 70));

    JButton manageUsersBtn = new JButton("Manage Users");
    manageUsersBtn.setPreferredSize(new Dimension(300, 70));

    JButton addUsersBtn = new JButton("Add Users");
    addUsersBtn.setPreferredSize(new Dimension(300, 70));

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel(new BorderLayout());
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(161, 159, 159));
    panelRight.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));

    JPanel insidePanelRight = new JPanel(new GridLayout());
    insidePanelRight.setSize(new Dimension(700, 600));
    insidePanelRight.setBackground(new Color(255, 255, 255));
    insidePanelRight.setAlignmentX(Component.CENTER_ALIGNMENT);
    insidePanelRight.setAlignmentY(Component.CENTER_ALIGNMENT);
    panelRight.add(insidePanelRight);

    JPanel greetingsPanel = new JPanel();
    greetingsPanel.setBackground(new Color(232, 232, 232));
    greetingsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    JLabel greetings = new JLabel("Welcome Admin");
    greetingsPanel.add(greetings);





    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(homeBtn);
    panelLeft.add(manageUsersBtn);
    panelLeft.add(addUsersBtn);
    panelLeft.add(logOutBtn);

    insidePanelRight.add(greetingsPanel);

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
