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
    JPanel manageAccPage = createManageAccPage();
    JPanel addAccPage = createAddAccPage();

    main.add(loginPage, "LOG IN");
    main.add(signUpPage, "SIGN UP");
    main.add(homePage, "HOME");
    main.add(manageAccPage, "MANAGE ACCOUNT");
    main.add(addAccPage, "ADD ACCOUNT");

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

    JButton manageAccsBtn = new JButton("Manage Users");
    manageAccsBtn.setPreferredSize(new Dimension(300, 70));
    manageAccsBtn.addActionListener(e -> {
      mainLayout.show(main, "MANAGE ACCOUNT");
    });

    JButton addAccBtn = new JButton("Add Users");
    addAccBtn.setPreferredSize(new Dimension(300, 70));
    addAccBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD ACCOUNT" );
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));
    panelRight.setBackground(new Color(161, 159, 159));
    panelRight.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
    panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

    JPanel insidePanelRight = new JPanel();
    insidePanelRight.setSize(new Dimension(700, 600));
    insidePanelRight.setBackground(new Color(255, 255, 255));
    insidePanelRight.setAlignmentX(Component.CENTER_ALIGNMENT);
    insidePanelRight.setAlignmentY(Component.CENTER_ALIGNMENT);
    insidePanelRight.setLayout(new BoxLayout(insidePanelRight, BoxLayout.Y_AXIS));
    panelRight.add(insidePanelRight);

    JPanel greetingsPanel = new JPanel(new BorderLayout());
    greetingsPanel.setBackground(new Color(232, 232, 232));
    greetingsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    JLabel greetings = new JLabel("Welcome Admin");
    greetingsPanel.add(greetings,BorderLayout.NORTH);

    JPanel boxesPanel = new JPanel();

    //******************************************************************************************************************************
    ImageIcon mingming = new ImageIcon("C:\\Users\\Mercedes Jade Lopez\\Pictures\\ad739bd4d256684c46ca24f13e71347c.jpg");
    Image origPic = mingming.getImage();
    Image scaledPic = origPic.getScaledInstance(500, 400, Image.SCALE_SMOOTH);
    ImageIcon mingmingpic = new ImageIcon(scaledPic);
    JLabel mingmingPic = new JLabel(mingmingpic);

    //****************************************************************************************************************************






    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(homeBtn);
    panelLeft.add(manageAccsBtn);
    panelLeft.add(addAccBtn);
    panelLeft.add(logOutBtn);

    insidePanelRight.add(greetingsPanel);
    insidePanelRight.add(boxesPanel);
    insidePanelRight.add(mingmingPic);
    return panel;
  }

  private JPanel createManageAccPage(){
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
    homeBtn.addActionListener(e -> {
      mainLayout.show(main, "HOME");
    });

    JButton manageAccsBtn = new JButton("Manage Users");
    manageAccsBtn.setPreferredSize(new Dimension(300, 70));

    JButton addAccBtn = new JButton("Add Users");
    addAccBtn.setPreferredSize(new Dimension(300, 70));
    addAccBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD ACCOUNT" );
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));

    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);
    panelRight.setBackground(new Color(161, 159, 159));

    //******************************************************************************************************************************
    ImageIcon mingming = new ImageIcon("C:\\Users\\Mercedes Jade Lopez\\Pictures\\70dfbc3bcd2eaa749265e86d23d8f20d.jpg");
    Image origPic = mingming.getImage();
    Image scaledPic = origPic.getScaledInstance(700, 600, Image.SCALE_SMOOTH);
    ImageIcon mingmingpic = new ImageIcon(scaledPic);
    JLabel mingmingPic = new JLabel(mingmingpic);
    mingmingPic.setHorizontalAlignment(JLabel.CENTER);
    panelRight.add(mingmingPic);
    //****************************************************************************************************************************

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(homeBtn);
    panelLeft.add(manageAccsBtn);
    panelLeft.add(addAccBtn);
    panelLeft.add(logOutBtn);

    return panel;
  }

  private JPanel createAddAccPage(){
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
    homeBtn.addActionListener(e -> {
      mainLayout.show(main, "HOME");
    });

    JButton manageAccsBtn = new JButton("Manage Users");
    manageAccsBtn.setPreferredSize(new Dimension(300, 70));
    manageAccsBtn.addActionListener(e -> {
      mainLayout.show(main, "MANAGE ACCOUNT");
    });

    JButton addAccBtn = new JButton("Add Users");
    addAccBtn.setPreferredSize(new Dimension(300, 70));
    addAccBtn.addActionListener(e -> {
      mainLayout.show(main, "ADD ACCOUNT" );
    });

    JButton logOutBtn = new JButton("Log Out");
    logOutBtn.setPreferredSize(new Dimension(300, 70));
    logOutBtn.addActionListener(e -> {
      mainLayout.show(main, "LOG IN");
    });

    JPanel panelRight = new JPanel();
    panelRight.setSize(new Dimension(700, 600));

    panel.add(panelLeft, BorderLayout.WEST);
    panel.add(panelRight, BorderLayout.CENTER);

    panelLeft.add(bankTitle);
    panelLeft.add(bankSubtitle);
    panelLeft.add(homeBtn);
    panelLeft.add(manageAccsBtn);
    panelLeft.add(addAccBtn);
    panelLeft.add(logOutBtn);
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
