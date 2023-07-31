package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    private JButton deposit, back;
    private JTextField amount;
    private String pinnumber;

    public Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(210, 70, 700, 24);
        add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBackground(Color.cyan);
        amount.setBounds(180, 158, 260, 36);
        add(amount);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(180, 250, 180, 34);
        back.addActionListener(this);
        add(back);

        deposit = new JButton("Deposit");
        deposit.setBackground(Color.BLACK);
        deposit.setForeground(Color.WHITE);
        deposit.setFont(new Font("Raleway", Font.BOLD, 16));
        deposit.setBounds(440, 250, 180, 34);
        deposit.addActionListener(this);
        add(deposit);

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
        if (ae.getSource() == deposit) {
            String samount = amount.getText();
            Date date = new Date();
            if (samount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter amount you want to deposit");
            } else {
                try {
                    Conn conn = new Conn();
                    String query = "insert bank values('" + pinnumber + "', '" + date + "', 'Deposit', '" + samount
                            + "');";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs. " + samount + " deposited Successfully");
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
        new Deposit("");
    }
}
