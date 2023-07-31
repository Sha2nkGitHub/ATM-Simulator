package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class SignupOne extends JFrame implements ActionListener {
    private long num;
    private JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField,
            stateTextField, pinTextField;

    private JButton next;
    private JRadioButton male, female, other, married, unmarried;
    private JDateChooser dateChooser;

    public SignupOne() {
        setLayout(null);
        Random rand = new Random();
        num = Math.abs(rand.nextLong() % 9000l) + 1000l;

        add(Styles.createJLabel("APPLICATION FORM NO. : " + num, 160, 20, 600, 40, new Font("Raleway", Font.BOLD, 35)));
        add(Styles.createJLabel("Page 1 : Personal Details", 220, 80, 500, 35, new Font("Raleway", Font.BOLD, 30)));

        final Font LABEL_FONT = new Font("Raleway", Font.BOLD, 20);

        add(Styles.createJLabel("Name : ", 100, 140, 200, 30, LABEL_FONT));

        final Font TEXTFIELD_FONT = new Font("Raleway", Font.BOLD, 16);

        nameTextField = Styles.createJTextField(320, 140, 400, 30, TEXTFIELD_FONT);
        add(nameTextField);

        add(Styles.createJLabel("Father's Name : ", 100, 190, 200, 30, LABEL_FONT));

        fnameTextField = Styles.createJTextField(320, 190, 400, 30, TEXTFIELD_FONT);
        add(fnameTextField);

        add(Styles.createJLabel("Date of Birth : ", 100, 240, 200, 30, LABEL_FONT));

        dateChooser = new JDateChooser();
        dateChooser.setBounds(320, 240, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);

        add(Styles.createJLabel("Gender : ", 100, 290, 200, 30, LABEL_FONT));

        male = Styles.createJRadioButton("Male", 320, 290, 100, 30);
        add(male);

        female = Styles.createJRadioButton("Female", 520, 290, 100, 30);
        add(female);

        Styles.createButtonGroup(new JRadioButton[] { male, female });

        add(Styles.createJLabel("Email : ", 100, 340, 200, 30, LABEL_FONT));

        emailTextField = Styles.createJTextField(320, 340, 400, 30, TEXTFIELD_FONT);
        add(emailTextField);

        add(Styles.createJLabel("Marital Status : ", 100, 390, 200, 30, LABEL_FONT));

        married = Styles.createJRadioButton("Married", 320, 390, 100, 30);
        add(married);

        unmarried = Styles.createJRadioButton("Unmarried", 470, 390, 110, 30);
        add(unmarried);

        other = Styles.createJRadioButton("Other", 640, 390, 100, 30);
        add(other);

        Styles.createButtonGroup(new JRadioButton[] { married, unmarried, other });

        add(Styles.createJLabel("Address : ", 100, 440, 200, 30, LABEL_FONT));

        addressTextField = Styles.createJTextField(320, 440, 400, 30, TEXTFIELD_FONT);
        add(addressTextField);

        add(Styles.createJLabel("City : ", 100, 490, 200, 30, LABEL_FONT));

        cityTextField = Styles.createJTextField(320, 490, 400, 30, TEXTFIELD_FONT);
        add(cityTextField);

        add(Styles.createJLabel("State : ", 100, 540, 200, 30, LABEL_FONT));

        stateTextField = Styles.createJTextField(320, 540, 400, 30, TEXTFIELD_FONT);
        add(stateTextField);

        add(Styles.createJLabel("Pin Code : ", 100, 590, 200, 30, LABEL_FONT));

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Raleway", Font.BOLD, 16));
        pinTextField.setBounds(320, 590, 400, 30);
        add(pinTextField);

        JButton back = Styles.createJButton("Back", 320, 650, 100, 40, Color.BLACK);
        back.addActionListener(event -> {
            setVisible(false);
            new Login();
        });
        add(back);

        next = Styles.createJButton("Next", 620, 650, 100, 40, Color.BLACK);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(830, 720);
        setLocation(350, 40);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String formNo = Long.toString(num);
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }

        String email = emailTextField.getText();
        String marital = null;
        if (married.isSelected())
            marital = "Married";
        else if (unmarried.isSelected())
            marital = "Unmarried";
        else if (other.isSelected())
            marital = "Other";

        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pin = pinTextField.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is required");
            } else {
                Conn c = new Conn();
                String query = "insert into signup values('" + formNo + "', '" + name + "', '" + fname + "', '" + dob
                        + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city
                        + "', '" + state + "', '" + pin + "');";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignupTwo(formNo).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String... args) {
        new SignupOne();
    }
}