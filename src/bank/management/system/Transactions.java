package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    private JButton deposit, withdrawl, fastcash, ministatement, pinchange, balanceenquiry, exit;
    private String pinnumber;

    public Transactions(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel text = new JLabel("Please select your Transaction ");
        text.setFont(new Font("System", Font.BOLD, 20));
        text.setBounds(250, 70, 700, 24);
        add(text);

        deposit = new JButton("Deposit");
        styleButtons(deposit, 180, 113, 180, 34);

        withdrawl = new JButton("Cash Withdrawl");
        styleButtons(withdrawl, 440, 113, 180, 34);

        fastcash = new JButton("Fast Cash");
        styleButtons(fastcash, 180, 158, 180, 34);

        ministatement = new JButton("Mini Statement");
        styleButtons(ministatement, 440, 158, 180, 34);

        pinchange = new JButton("PIN Change");
        styleButtons(pinchange, 180, 205, 180, 34);

        balanceenquiry = new JButton("Balance Enquiry");
        styleButtons(balanceenquiry, 440, 205, 180, 34);

        exit = new JButton("EXIT");
        styleButtons(exit, 440, 250, 180, 34);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 800, 800);
        add(image);

        setSize(800, 790);
        setLocation(350, 15);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            //System.exit(0);
            setVisible(false);
            new Login();
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new Withdrawl(pinnumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        } else if (ae.getSource() == pinchange) {
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        } else if (ae.getSource() == balanceenquiry) {
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        } else if (ae.getSource() == ministatement) {
            new MiniStatement(pinnumber).setVisible(true);
        }
    }

    private void styleButtons(JButton btn, int x, int y, int w, int h) {
        btn.setBounds(x, y, w, h);
        btn.setForeground(Color.WHITE);
        btn.setBackground(Color.BLACK);
        btn.addActionListener(this);
        btn.setFont(new Font("Raleway", Font.BOLD, 16));
        add(btn);
    }

    public static void main(String... args) {
        new Transactions("");
    }
}