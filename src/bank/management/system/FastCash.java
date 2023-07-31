package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    private JButton cash1, cash2, cash3, cash4, cash5, cash6, back;
    private String pinnumber;

    public FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel text = new JLabel("Select amount you want to withdraw");
        text.setFont(new Font("System", Font.BOLD, 20));
        text.setBounds(250, 70, 700, 24);
        add(text);

        cash1 = new JButton("Rs. 100");
        cash1.setBackground(Color.BLACK);
        cash1.setForeground(Color.WHITE);
        cash1.setFont(new Font("Raleway", Font.BOLD, 16));
        cash1.setBounds(180, 113, 180, 34);
        cash1.addActionListener(this);
        add(cash1);

        cash2 = new JButton("Rs. 500");
        cash2.setBackground(Color.BLACK);
        cash2.setForeground(Color.WHITE);
        cash2.setFont(new Font("Raleway", Font.BOLD, 16));
        cash2.setBounds(440, 113, 180, 34);
        cash2.addActionListener(this);
        add(cash2);

        cash3 = new JButton("Rs. 1000");
        cash3.setBackground(Color.BLACK);
        cash3.setForeground(Color.WHITE);
        cash3.setFont(new Font("Raleway", Font.BOLD, 16));
        cash3.setBounds(180, 158, 180, 34);
        cash3.addActionListener(this);
        add(cash3);

        cash4 = new JButton("Rs. 2000");
        cash4.setBackground(Color.BLACK);
        cash4.setForeground(Color.WHITE);
        cash4.setFont(new Font("Raleway", Font.BOLD, 16));
        cash4.setBounds(440, 158, 180, 34);
        cash4.addActionListener(this);
        add(cash4);

        cash5 = new JButton("Rs. 5000");
        cash5.setBackground(Color.BLACK);
        cash5.setForeground(Color.WHITE);
        cash5.setFont(new Font("Raleway", Font.BOLD, 16));
        cash5.setBounds(180, 205, 180, 34);
        cash5.addActionListener(this);
        add(cash5);

        cash6 = new JButton("Rs. 10000");
        cash6.setBackground(Color.BLACK);
        cash6.setForeground(Color.WHITE);
        cash6.setFont(new Font("Raleway", Font.BOLD, 16));
        cash6.setBounds(440, 205, 180, 34);
        cash6.addActionListener(this);
        add(cash6);

        back = new JButton("BACK");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(440, 250, 180, 34);
        back.addActionListener(this);
        add(back);

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
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount = (String) ((JButton) ae.getSource()).getText().substring(4);
            Conn conn = new Conn();
            try {
                ResultSet rs = conn.s.executeQuery("select * from bank where pin='" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource() != back && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds");
                    return;
                }

                Date date = new Date();
                String query = "insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + amount
                        + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String... args) {
        new FastCash("");
    }
}
