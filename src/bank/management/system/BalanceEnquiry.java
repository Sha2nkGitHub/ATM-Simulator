package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    private JButton back;
    private String pinnumber;
    private int balance;

    public BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        back = new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(440, 250, 180, 34);
        back.addActionListener(this);
        add(back);

        Conn conn = new Conn();
        try {
            ResultSet rs = conn.s.executeQuery("select * from bank where pin='" + pinnumber + "'");
            balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        JLabel text = new JLabel("Current Account Balance = Rs. " + balance);
        text.setFont(new Font("System", Font.BOLD, 20));
        text.setBounds(230, 90, 700, 24);
        add(text);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 800, 800);
        add(image);

        setSize(800, 800);
        setLocation(350, 15);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    public static void main(String... args) {
        new BalanceEnquiry("");
    }
}
