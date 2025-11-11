import javax.swing.*;
import java.awt.*;

// Panel untuk Main Menu
// [cite: 217-221]
public class MainMenuPanel extends JPanel {

    private Main mainApp;
    private JLabel welcomeLabel;
    private JButton startButton;
    private JButton leaderboardButton;
    private JButton logoutButton;

    public MainMenuPanel(Main mainApp) {
        this.mainApp = mainApp;
        setLayout(null);
        setBackground(Theme.COLOR_BACKGROUND);

        // Label Welcome
        welcomeLabel = new JLabel("Selamat datang, ");
        welcomeLabel.setFont(Theme.FONT_TITLE);
        welcomeLabel.setForeground(Theme.COLOR_TEXT);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(100, 100, 600, 50);
        add(welcomeLabel);

        // Tombol Mulai Main
        startButton = createStyledButton("Mulai Main");
        startButton.setBounds(250, 200, 300, 50);
        add(startButton);

        // Tombol Lihat Leaderboard
        leaderboardButton = createStyledButton("Lihat Leaderboard");
        leaderboardButton.setBounds(250, 270, 300, 50);
        add(leaderboardButton);

        // Tombol Logout
        logoutButton = createStyledButton("Logout");
        logoutButton.setBounds(250, 340, 300, 50);
        add(logoutButton);

        // Action Listeners
        startButton.addActionListener(e -> mainApp.showPanel("Game"));
        leaderboardButton.addActionListener(e -> mainApp.showPanel("Leaderboard"));
        logoutButton.addActionListener(e -> mainApp.logout());
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

    // Method untuk set pesan selamat datang [cite: 221]
    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }
}