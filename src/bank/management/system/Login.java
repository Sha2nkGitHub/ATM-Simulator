package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private JButton login, signup, clear, exit;
    private JTextField cardTextField;
    private JPasswordField pinTextField;

    public Login() {
        setUndecorated(true);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(150, 15, 90, 90);
        add(label);

        add(Styles.createJLabel("Welcome to ATM", 280, 40, 400, 40, (new Font("Oswald", Font.BOLD, 38))));

        add(Styles.createJLabel("Card No. : ", 130, 140, 200, 40, (new Font("Raleway", Font.BOLD, 28))));

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 140, 300, 40);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 18));
        add(cardTextField);

        add(Styles.createJLabel("  PIN  : ", 130, 190, 200, 40, (new Font("Raleway", Font.BOLD, 28))));

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 190, 300, 40);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 18));
        add(pinTextField);

        login = Styles.createJButton("SIGN IN", 300, 300, 130, 40, Color.BLACK);
        login.addActionListener(this);
        add(login);

        signup = Styles.createJButton("SIGN UP", 450, 300, 130, 40, Color.BLACK);
        signup.addActionListener(this);
        add(signup);

        clear = Styles.createJButton("CLEAR", 300, 360, 130, 40, Color.BLACK);
        clear.addActionListener(this);
        add(clear);

        exit = Styles.createJButton("EXIT", 450, 360, 130, 40, Color.BLACK);
        exit.addActionListener(this);
        add(exit);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setLocation(350, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = new String(pinTextField.getPassword());
            String query = "SELECT * FROM login WHERE cardnumber = '" + cardnumber + "' AND pin='" + pinnumber + "';";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        } else if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String... args) {
        new Login();
    }
}