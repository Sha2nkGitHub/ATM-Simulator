package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    private JButton withdraw, back;
    private JTextField amount;
    private String pinnumber;

    public Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(220, 70, 700, 24);
        add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBackground(Color.cyan);
        amount.setBounds(180, 113, 200, 34);
        add(amount);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(180, 250, 180, 34);
        back.addActionListener(this);
        add(back);

        withdraw = new JButton("Withdraw");
        withdraw.setBackground(Color.BLACK);
        withdraw.setForeground(Color.WHITE);
        withdraw.setFont(new Font("Raleway", Font.BOLD, 16));
        withdraw.setBounds(440, 250, 180, 34);
        withdraw.addActionListener(this);
        add(withdraw);

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
        if (ae.getSource() == withdraw) {
            String samount = amount.getText();
            Date date = new Date();
            if (samount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter amount you want to withdraw");
            } else {
                try {
                    Conn conn = new Conn();
                    ResultSet rs = conn.s.executeQuery("select * from bank where pin='" + pinnumber + "'");
                    int balance = 0;
                    while (rs.next()) {
                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if (balance > Integer.parseInt(samount)) {
                        String query = "insert bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '"
                                + samount
                                + "');";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Rs. " + samount + " Withdrawn Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Funds");
                    }
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String... args) {
        new Withdrawl("");
    }
}
