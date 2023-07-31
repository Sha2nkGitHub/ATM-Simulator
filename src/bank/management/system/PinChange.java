package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
    private JButton change, back;
    private JPasswordField pin, repin;
    private String pinnumber;

    public PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel text = new JLabel("Change your PIN");
        text.setFont(new Font("System", Font.BOLD, 20));
        text.setBounds(250, 70, 300, 24);
        add(text);

        JLabel pintext = new JLabel("New PIN : ");
        pintext.setFont(new Font("System", Font.BOLD, 18));
        pintext.setBounds(200, 113, 300, 34);
        add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(400, 113, 200, 34);
        pin.setBackground(Color.cyan);
        add(pin);

        JLabel repintext = new JLabel("Re-enter PIN : ");
        repintext.setFont(new Font("System", Font.BOLD, 18));
        repintext.setBounds(180, 158, 300, 34);
        add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(400, 158, 200, 34);
        repin.setBackground(Color.cyan);
        add(repin);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 16));
        back.setBounds(180, 250, 180, 34);
        back.addActionListener(this);
        add(back);

        change = new JButton("Change");
        change.setBackground(Color.BLACK);
        change.setForeground(Color.WHITE);
        change.setFont(new Font("Raleway", Font.BOLD, 16));
        change.setBounds(440, 250, 180, 34);
        change.addActionListener(this);
        add(change);

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
        if (ae.getSource() == change) {
            try {
                String newpin = new String(pin.getPassword());
                String newrepin =new String(repin.getPassword());

                if (!newpin.equals(newrepin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (newpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                if (newrepin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re enter new PIN");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "update bank set pin='" + newpin + "' where pin='" + pinnumber + "'";
                String query2 = "update login set pin='" + newpin + "' where pin='" + pinnumber + "'";
                String query3 = "update signupthree set pin='" + newpin + "' where pin='" + pinnumber + "'";
                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(newpin).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String... args) {
        new PinChange("").setVisible(true);
    }
}
