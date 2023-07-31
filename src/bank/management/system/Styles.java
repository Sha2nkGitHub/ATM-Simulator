package bank.management.system;

import java.awt.*;
import javax.swing.*;

public class Styles {
    public static JLabel createJLabel(String text, int x, int y, int w, int h, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font);
        return label;
    }

    public static JButton createJButton(String text, int x, int y, int w, int h, Color color) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    public static JTextField createJTextField(int x, int y, int w, int h, Font font){
        JTextField textField = new JTextField();
        textField.setBounds(x,y,w,h);
        textField.setFont(font);
        return textField;
    }

    public static void createButtonGroup(JRadioButton[] btns){
        ButtonGroup grp = new ButtonGroup();
        for(JRadioButton btn : btns){
            grp.add(btn);
        }
    }

    public static JRadioButton createJRadioButton(String text, int x, int y, int w, int h){
        JRadioButton btn = new JRadioButton(text);
        btn.setBounds(x,y,w,h);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        return btn;
    }

    public static JCheckBox createJCheckBox(String text, int x, int y, int w, int h){
        JCheckBox box = new JCheckBox(text);
        box.setBounds(x,y,w,h);
        box.setFont(new Font("Arial", Font.PLAIN, 18));
        box.setBackground(Color.WHITE);
        return box;
    }
}
