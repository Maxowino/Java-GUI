/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guilab;

/**
 *
 * @author USER
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpActionListener implements ActionListener {
    private SignUpForm form;

    public SignUpActionListener(SignUpForm form) {
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String fullName = form.getFullNameField().getText();
        String phoneNumber = form.getPhoneNumberField().getText();
        String username = form.getUsernameField().getText();
        String email = form.getEmailField().getText();
        String password = new String(form.getPasswordField().getPassword());

        if (fullName.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (username.length() < 5) {
            JOptionPane.showMessageDialog(form, "Username must be at least 5 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(form, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(form, "Password must be at least 8 characters long and contain uppercase.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection conn = dbconnection.getInstance().getConnection();
                String sql = "INSERT INTO users (full_name, phone_number, username, email, password) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, fullName);
                pstmt.setString(2, phoneNumber);
                pstmt.setString(3, username);
                pstmt.setString(4, email);
                pstmt.setString(5, password);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(form, "Sign up successful!\nWelcome, " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(form, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }
}