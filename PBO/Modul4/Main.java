import javax.swing.*;
import java.awt.*;

// Ini adalah frame utama yang memegang semua panel (Login, Menu, Game, etc.)
// [cite: 179-195]
public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanelContainer;
    private LoginPanel loginPanel;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;
    private LeaderboardPanel leaderboardPanel;

    private int currentUserId = -1;
    private String currentUsername = "";

    public Main() {
        setTitle("Typing Dash: Adu kecepatan jari dalam mengetik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Tampilkan di tengah layar

        cardLayout = new CardLayout();
        mainPanelContainer = new JPanel(cardLayout);

        // Inisialisasi semua panel
        loginPanel = new LoginPanel(this);
        mainMenuPanel = new MainMenuPanel(this);
        gamePanel = new GamePanel(this);
        leaderboardPanel = new LeaderboardPanel(this);

        // Tambahkan panel ke CardLayout
        mainPanelContainer.add(loginPanel, "Login");
        mainPanelContainer.add(mainMenuPanel, "MainMenu");
        mainPanelContainer.add(gamePanel, "Game");
        mainPanelContainer.add(leaderboardPanel, "Leaderboard");

        add(mainPanelContainer);

        // Mulai dengan panel login
        showPanel("Login");
    }

    // Method untuk berpindah panel [cite: 190]
    public void showPanel(String panelName) {
        if (panelName.equals("Leaderboard")) {
            leaderboardPanel.onPanelShown(); // Refresh data leaderboard
        }
        if (panelName.equals("Game")) {
            gamePanel.onPanelShown(); // Mulai persiapan game
        }
        cardLayout.show(mainPanelContainer, panelName);
    }

    // Dipanggil dari LoginPanel setelah login sukses [cite: 191]
    public void onLoginSuccess(int userId, String username) {
        this.currentUserId = userId;
        this.currentUsername = username;
        mainMenuPanel.setWelcomeMessage("Selamat datang, " + username);
        showPanel("MainMenu");
    }

    // Method untuk logout [cite: 192]
    public void logout() {
        this.currentUserId = -1;
        this.currentUsername = "";
        loginPanel.resetFields();
        showPanel("Login");
    }

    // Getter untuk data user [cite: 193, 194]
    public int getCurrentUserId() {
        return currentUserId;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    // Main method untuk menjalankan aplikasi [cite: 195]
    public static void main(String[] args) {
        // Inisialisasi database (membuat tabel jika belum ada)
        KoneksiDatabase.initialize();

        // Jalankan GUI di Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}