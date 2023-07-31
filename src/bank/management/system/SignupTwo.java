package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {
    private JTextField aadhar, pan;
    private JButton next;
    private JRadioButton syes, sno, eyes, eno;
    private JComboBox<String> religion, category, income, occupation, education;
    private String formNo;

    public SignupTwo(String formNo) {
        this.formNo = formNo;
        setLayout(null);

        add(Styles.createJLabel("Page 2 : Additional Details", 220, 60, 500, 35, new Font("Raleway", Font.BOLD, 30)));

        final Font LABEL_FONT = new Font("Raleway", Font.BOLD, 20);

        add(Styles.createJLabel("Religion : ", 100, 140, 200, 30, LABEL_FONT));

        String[] valReligion = new String[] { "Hindu", "Muslim", "Sikh", "Parsi", "Christian", "Other" };
        religion = new JComboBox<>(valReligion);
        religion.setBounds(320, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        add(Styles.createJLabel("Category : ", 100, 190, 200, 30, LABEL_FONT));

        String[] valCategory = new String[] { "General", "OBC", "SC", "ST", "Other" };
        category = new JComboBox<>(valCategory);
        category.setBounds(320, 190, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        add(Styles.createJLabel("Category : ", 100, 240, 200, 30, LABEL_FONT));

        String[] incomeValues = new String[] { "Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000" };
        income = new JComboBox<>(incomeValues);
        income.setBounds(320, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        add(Styles.createJLabel("Educational", 100, 295, 200, 30, LABEL_FONT));

        add(Styles.createJLabel("Qualification : ", 100, 325, 200, 30, LABEL_FONT));

        String[] educationValues = new String[] { "Non-Graduation", "Graduate", "Post Graduate", "Doctorate", "Other" };
        education = new JComboBox<>(educationValues);
        education.setBounds(320, 325, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        add(Styles.createJLabel("Occupation : ", 100, 390, 200, 30, LABEL_FONT));

        String[] occupationValues = new String[] { "Salaried", "Self-Employed", "Businessman", "Student", "Retired",
                "Other" };
        occupation = new JComboBox<>(occupationValues);
        occupation.setBounds(320, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        add(Styles.createJLabel("PAN Number : ", 100, 440, 200, 30, LABEL_FONT));

        pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD, 16));
        pan.setBounds(320, 440, 400, 30);
        add(pan);

        add(Styles.createJLabel("Aadhar Number : ", 100, 490, 200, 30, LABEL_FONT));

        aadhar = new JTextField();
        aadhar.setFont(new Font("Raleway", Font.BOLD, 16));
        aadhar.setBounds(320, 490, 400, 30);
        add(aadhar);

        add(Styles.createJLabel("Senior Citizen : ", 100, 540, 200, 30, LABEL_FONT));

        syes = Styles.createJRadioButton("Yes", 320, 540, 150, 30);
        add(syes);

        sno = Styles.createJRadioButton("No", 520, 540, 150, 30);
        add(sno);

        Styles.createButtonGroup(new JRadioButton[]{syes, sno});

        add(Styles.createJLabel("Existing Account : ", 100, 590, 200, 30, LABEL_FONT));

        eyes = Styles.createJRadioButton("Yes", 320, 590, 150, 30);
        add(eyes);

        eno = Styles.createJRadioButton("No", 520, 590, 150, 30);
        add(eno);

        Styles.createButtonGroup(new JRadioButton[]{eyes, eno});

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
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String seniorCitizen = null;
        if (syes.isSelected())
            seniorCitizen = "Yes";
        else if (sno.isSelected())
            seniorCitizen = "No";

        String existingAccount = null;
        if (eyes.isSelected())
            existingAccount = "Yes";
        else if (eno.isSelected())
            existingAccount = "No";

        String saadhar = aadhar.getText();
        String span = pan.getText();

        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo VALUES('" + formNo + "', '" + sreligion + "', '" + scategory + "', '"
                    + sincome + "', '" + seducation + "', '" + soccupation + "', '" + saadhar + "', '" + span
                    + "', '" + seniorCitizen + "', '" + existingAccount + "');";

            c.s.executeUpdate(query);

            setVisible(false);
            new SignupThree(formNo).setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String... args) {
        new SignupTwo("");
    }
}