/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guilab;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author USER
 */
import java.awt.GridLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SignUpForm extends JFrame {
    private JTextField fullNameField;
    private JTextField phoneNumberField;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel passwordStrengthLabel;
    private JButton submitButton;
    private JButton clearButton;

    public SignUpForm() {
        setTitle("Sign Up Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5)); 

        panel.add(new JLabel("Full Name:"));
        fullNameField = new JTextField();
        panel.add(fullNameField);

        panel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        passwordStrengthLabel = new JLabel("Password Strength: ");
        panel.add(passwordStrengthLabel);

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePasswordStrength();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePasswordStrength();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePasswordStrength();
            }
            private void updatePasswordStrength() {
                String password = new String(passwordField.getPassword());
                passwordStrengthLabel.setText("Password Strength: " + checkPasswordStrength(password));
            }
        });
        
        
        

        submitButton = new JButton("Sign Up");
        panel.add(submitButton);

        clearButton = new JButton("Clear");
        panel.add(clearButton);

        submitButton.addActionListener(new SignUpActionListener(this));
        clearButton.addActionListener(e -> clearForm());

        add(panel); 
        pack();
        setSize(500, 300); 
        setVisible(true);
    }

    private void clearForm() {
        fullNameField.setText("");
        phoneNumberField.setText("");
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }

    private String checkPasswordStrength(String password) {
        int strengthPoints = 0;
        if (password.length() >= 8) strengthPoints++;
        if (password.matches(".*[a-z].*")) strengthPoints++;
        if (password.matches(".*[A-Z].*")) strengthPoints++;
        if (password.matches(".*[\\d].*")) strengthPoints++;
        if (password.matches(".*[^\\w].*")) strengthPoints++;

        switch (strengthPoints) {
            case 0: return "Very Weak";
            case 1: return "Weak";
            case 2: return "Fair";
            case 3: return "Good";
            case 4: return "Strong";
            case 5: return "Very Strong";
            default: return "Very Weak";
        }
    }
    
    public JTextField getFullNameField() {
        return fullNameField;
    }

    public JTextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run(){
                new SignUpForm();
            }
        
        });
    }
}