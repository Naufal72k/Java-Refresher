import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel untuk Login dan Register
// [cite: 214-216]
public class LoginPanel extends JPanel {

    private Main mainApp;
    private JTextField usnField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginPanel(Main mainApp) {
        this.mainApp = mainApp;

        // Gunakan null layout untuk meniru mockup [cite: 29]
        setLayout(null);
        setBackground(Theme.COLOR_BACKGROUND);

        // Label Judul
        JLabel titleLabel = new JLabel("Welcome to Typing Dash");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.COLOR_TEXT);
        titleLabel.setBounds(250, 100, 400, 50); // (x, y, width, height)
        add(titleLabel);

        // Label Username
        JLabel usnLabel = new JLabel("Username:");
        usnLabel.setFont(Theme.FONT_BODY);
        usnLabel.setForeground(Theme.COLOR_TEXT);
        usnLabel.setBounds(250, 180, 100, 30);
        add(usnLabel);

        // Field Username
        usnField = new JTextField();
        usnField.setFont(Theme.FONT_INPUT);
        usnField.setBackground(Theme.COLOR_INPUT_BG);
        usnField.setForeground(Theme.COLOR_TEXT);
        usnField.setCaretColor(Theme.COLOR_PRIMARY);
        usnField.setBounds(360, 180, 190, 30);
        add(usnField);

        // Label Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(Theme.FONT_BODY);
        passLabel.setForeground(Theme.COLOR_TEXT);
        passLabel.setBounds(250, 230, 100, 30);
        add(passLabel);

        // Field Password
        passField = new JPasswordField();
        passField.setFont(Theme.FONT_INPUT);
        passField.setBackground(Theme.COLOR_INPUT_BG);
        passField.setForeground(Theme.COLOR_TEXT);
        passField.setCaretColor(Theme.COLOR_PRIMARY);
        passField.setBounds(360, 230, 190, 30);
        add(passField);

        // Tombol Login
        loginButton = createStyledButton("Login");
        loginButton.setBounds(250, 290, 300, 40);
        add(loginButton);

        // Tombol Register
        registerButton = createStyledButton("Register");
        registerButton.setBounds(250, 340, 300, 40);
        add(registerButton);

        // Action Listeners
        loginButton.addActionListener(e -> processLogin());
        registerButton.addActionListener(e -> processRegister());
    }

    // Utility method untuk style tombol
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Theme.COLOR_PRIMARY);
        button.setForeground(Theme.COLOR_BACKGROUND);
        button.setFont(Theme.FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    // Method untuk memproses login [cite: 216]
    private void processLogin() {
        String username = usnField.getText();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password tidak boleh kosong!", "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userId = KoneksiDatabase.loginUser(username, password);
        if (userId != -1) {
            // Login berhasil [cite: 43]
            JOptionPane.showMessageDialog(this, "Login berhasil!", "Message", JOptionPane.INFORMATION_MESSAGE);
            mainApp.onLoginSuccess(userId, username);
        } else {
            // Login gagal [cite: 56]
            JOptionPane.showMessageDialog(this, "Username atau password salah.", "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method untuk memproses registrasi [cite: 216]
    private void processRegister() {
        String username = usnField.getText();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password tidak boleh kosong!", "Registrasi Gagal",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = KoneksiDatabase.registerUser(username, password);
        if (success) {
            // Registrasi berhasil [cite: 64]
            JOptionPane.showMessageDialog(this, "Registrasi berhasil! Silakan login.", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
            resetFields();
        } else {
            // Registrasi gagal (username duplikat) [cite: 37, 38, 48]
            JOptionPane.showMessageDialog(this, "Registrasi gagal. Username mungkin sudah digunakan.",
                    "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mengosongkan field setelah register/logout
    public void resetFields() {
        usnField.setText("");
        passField.setText("");
    }
}