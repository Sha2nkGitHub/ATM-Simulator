package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SignupThree extends JFrame implements ActionListener {
    private JRadioButton r1, r2, r3, r4;
    private JCheckBox c1, c2, c3, c4, c5, c6, c7;
    private JButton submit;
    private String formNo;

    public SignupThree(String formNo) {
        this.formNo = formNo;
        setLayout(null);

        add(Styles.createJLabel("Page 3 : Account Details", 220, 30, 500, 35, new Font("Raleway", Font.BOLD, 30)));

        add(Styles.createJLabel("Account Type", 100, 90, 200, 30, new Font("Raleway", Font.BOLD, 20)));

        r1 = Styles.createJRadioButton("Saving Account", 120, 130, 300, 30);
        add(r1);

        r2 = Styles.createJRadioButton("Fixed Deposit Account", 450, 130, 300, 30);
        add(r2);

        r3 = Styles.createJRadioButton("Current Account", 120, 170, 300, 30);
        add(r3);

        r4 = Styles.createJRadioButton("Recurring Deposit Account", 450, 170, 300, 30);
        add(r4);

        Styles.createButtonGroup(new JRadioButton[] { r1, r2, r3, r4 });

        add(Styles.createJLabel("Card Number : ", 100, 220, 200, 30, new Font("Raleway", Font.BOLD, 20)));

        add(Styles.createJLabel("XXXX-XXXX-XXXX-4184", 350, 220, 300, 30, new Font("Raleway", Font.BOLD, 20)));

        add(Styles.createJLabel("* Your 16 Digit Card Number", 100, 260, 300, 18, new Font("Raleway", Font.BOLD, 14)));

        add(Styles.createJLabel("PIN : ", 100, 310, 300, 30, new Font("Raleway", Font.BOLD, 20)));

        add(Styles.createJLabel("XXXX", 350, 310, 300, 30, new Font("Raleway", Font.BOLD, 20)));

        add(Styles.createJLabel("** Your 4 Digit PIN", 100, 340, 300, 18, new Font("Raleway", Font.BOLD, 14)));

        add(Styles.createJLabel("Services Required", 100, 390, 300, 30, new Font("Raleway", Font.BOLD, 20)));

        c1 = Styles.createJCheckBox("ATM Card", 120, 430, 200, 30);
        add(c1);

        c2 = Styles.createJCheckBox("Internet Banking", 400, 430, 200, 30);
        add(c2);

        c3 = Styles.createJCheckBox("Mobile Banking", 120, 470, 200, 30);
        add(c3);

        c4 = Styles.createJCheckBox("Email and SMS Alerts", 400, 470, 200, 30);
        add(c4);

        c5 = Styles.createJCheckBox("Cheque Book", 120, 510, 200, 30);
        add(c5);

        c6 = Styles.createJCheckBox("E-Statement", 400, 510, 200, 30);
        add(c6);

        c7 = Styles.createJCheckBox(
                "I Hereby declare that the above entered details are correct to the best of my knowledge", 100, 570,
                600, 30);
        c7.setFont(new Font("Oswald", Font.BOLD, 13));
        add(c7);

        JButton cancel = Styles.createJButton("Cancel", 120, 620, 120, 40, Color.BLACK);
        cancel.addActionListener(event -> {
            setVisible(false);
            new Login().setVisible(true);
        });
        add(cancel);

        submit = Styles.createJButton("Submit", 500, 620, 120, 40, Color.BLACK);
        submit.addActionListener(this);
        add(submit);

        setSize(830, 720);
        setLocation(350, 40);
        getContentPane().setBackground(Color.WHITE);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Saving Account";
            } else if (r2.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                accountType = "Current Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            Random rand = new Random();
            String cardnumber = Long.toString(Math.abs(rand.nextLong() % 90_000_000l + 5040936000000000l));
            String pinnumber = Long.toString(Math.abs(rand.nextLong() % 9000l + 1000l));
            String facility = "";
            if (c1.isSelected())
                facility += " ATM CARD";
            if (c2.isSelected())
                facility += " Internet Banking";
            if (c3.isSelected())
                facility += " Mobile Banking";
            if (c4.isSelected())
                facility += " Email and SMS Alerts";
            if (c5.isSelected())
                facility += " Cheque Book";
            if (c6.isSelected())
                facility += " E-Statement";

            try {
                if (accountType.equals("")) {
                    JOptionPane.showMessageDialog(null, "Account Type is required");
                } else {
                    Conn conn = new Conn();
                    String query1 = "INSERT INTO signupthree VALUES('" + formNo + "', '" + accountType + "', '"
                            + cardnumber + "', '" + pinnumber + "', '" + facility + "');";

                    String query2 = "INSERT INTO login VALUES('" + formNo + "', '" + cardnumber + "', '" + pinnumber
                            + "');";

                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            JOptionPane.showMessageDialog(null, "Card Number : " + cardnumber + "\nPIN : " + pinnumber);
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        }
    }

    public static void main(String... args) {
        new SignupThree("");
    }
}