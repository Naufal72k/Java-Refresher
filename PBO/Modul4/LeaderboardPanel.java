import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Panel untuk Leaderboard
// [cite: 211-213]
public class LeaderboardPanel extends JPanel {

    private Main mainApp;
    private JTable tabelLeaderboard;
    private DefaultTableModel modelTabel;

    public LeaderboardPanel(Main mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());
        setBackground(Theme.COLOR_BACKGROUND);

        // Judul
        JLabel titleLabel = new JLabel("Top 10 Leaderboard");
        titleLabel.setFont(Theme.FONT_TITLE);
        titleLabel.setForeground(Theme.COLOR_TEXT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Kolom tabel [cite: 129]
        String[] columnNames = { "Username", "WPM", "Akurasi (%)", "Waktu" };
        modelTabel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat tabel read-only
            }
        };
        tabelLeaderboard = new JTable(modelTabel);

        // Styling Tabel
        tabelLeaderboard.setBackground(Theme.COLOR_INPUT_BG);
        tabelLeaderboard.setForeground(Theme.COLOR_TEXT);
        tabelLeaderboard.setFont(Theme.FONT_BODY);
        tabelLeaderboard.setRowHeight(30);
        tabelLeaderboard.getTableHeader().setFont(Theme.FONT_BUTTON);
        tabelLeaderboard.getTableHeader().setBackground(Theme.COLOR_PRIMARY);
        tabelLeaderboard.getTableHeader().setForeground(Theme.COLOR_BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(tabelLeaderboard);
        scrollPane.getViewport().setBackground(Theme.COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        add(scrollPane, BorderLayout.CENTER);

        // Tombol Kembali
        JButton backButton = new JButton("Kembali ke Menu");
        backButton.setBackground(Theme.COLOR_PRIMARY);
        backButton.setForeground(Theme.COLOR_BACKGROUND);
        backButton.setFont(Theme.FONT_BUTTON);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Theme.COLOR_BACKGROUND);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener
        backButton.addActionListener(e -> mainApp.showPanel("MainMenu"));
    }

    // Method ini dipanggil saat panel ditampilkan [cite: 213]
    public void onPanelShown() {
        // Hapus data lama
        modelTabel.setRowCount(0);

        // Ambil data baru dari database
        List<Object[]> leaderboardData = KoneksiDatabase.getLeaderboard();

        // Masukkan data ke tabel
        for (Object[] row : leaderboardData) {
            // Format WPM dan Akurasi ke 2 desimal
            double wpm = (double) row[1];
            double accuracy = (double) row[2];
            row[1] = String.format("%.2f", wpm);
            row[2] = String.format("%.2f", accuracy);
            modelTabel.addRow(row);
        }
    }
}