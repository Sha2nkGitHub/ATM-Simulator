package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    public MiniStatement(String pinnumber) {
        setTitle("Mini Statement");

        setLayout(null);

        JLabel mini = new JLabel("");

        add(mini);

        add(Styles.createJLabel("JSB Bank", 130, 40, 200, 25, new Font("Arial", Font.BOLD, 25)));

        JLabel card = Styles.createJLabel("", 30, 100, 300, 25, new Font("Raleway", Font.BOLD, 16));
        add(card);

        JLabel balance = Styles.createJLabel("Balance", 70, 400, 300, 30, new Font("Raleway", Font.BOLD, 18));
        add(balance);
        int bal = 0;
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin='" + pinnumber + "';");
            while (rs.next()) {
                card.setText("Card Number : " + rs.getString("cardnumber").substring(0, 4) + "-XXXX-XXXX-"
                        + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin='" + pinnumber + "';");
            while (rs.next()) {
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rs. "
                        + rs.getString("amount") + "<br><br><html>");
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Current Balance : Rs. " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }

        mini.setBounds(30, 120, 400, 250);

        setSize(400, 580);
        setLocation(900, 150);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String... args) {
        new MiniStatement("6515");
    }
}