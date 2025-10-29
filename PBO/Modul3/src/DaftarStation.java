package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder; // Import yang mungkin kurang
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class DaftarStation extends JFrame {
    private String selectedPackage;
    private int packageDuration; // dalam jam
    private int packagePrice;

    // Status global yang dibagikan antar semua instance window
    private static Map<Integer, StationInfo> globalStationStatus = new HashMap<>();

    // Timer global untuk countdown
    private static Timer countdownTimer;

    private JPanel stationsPanel; // Panel untuk menampung card station
    private Font stationFont = new Font("Roboto", Font.BOLD, 20);
    private Font statusFont = new Font("Roboto", Font.PLAIN, 12);

    // Class internal untuk menyimpan info station
    private static class StationInfo {
        boolean available;
        long remainingTime; // dalam detik

        StationInfo(boolean available, long remainingTime) {
            this.available = available;
            this.remainingTime = remainingTime;
        }
    }

    public DaftarStation(String packageName, int duration, int price) {
        this.selectedPackage = packageName;
        this.packageDuration = duration;
        this.packagePrice = price;

        initializeStationStatus();
        setupUI();
        startGlobalTimer(); // Mulai timer jika belum berjalan
    }

    // Inisialisasi status station jika belum ada
    private void initializeStationStatus() {
        if (globalStationStatus.isEmpty()) {
            for (int i = 1; i <= 12; i++) {
                globalStationStatus.put(i, new StationInfo(true, 0)); // Default semua available
            }
        }
    }

    // Setup Timer global (hanya sekali)
    private void startGlobalTimer() {
        if (countdownTimer == null) {
            countdownTimer = new Timer(1000, e -> updateTimers()); // Tick setiap 1 detik
            countdownTimer.start();
        }
    }

    // Logika yang dijalankan timer setiap detik
    private void updateTimers() {
        boolean needsRefresh = false;
        for (Map.Entry<Integer, StationInfo> entry : globalStationStatus.entrySet()) {
            StationInfo info = entry.getValue();
            if (!info.available && info.remainingTime > 0) {
                info.remainingTime--; // Kurangi waktu
                needsRefresh = true;
                if (info.remainingTime == 0) {
                    info.available = true; // Waktu habis, station available lagi
                }
            }
        }

        // Jika window ini sedang terbuka (visible) dan ada update, refresh UI
        if (needsRefresh && this.isVisible()) {
            refreshStationCards();
        }
    }

    // *** INI ADALAH METHOD YANG DIPERBAIKI (setupUI) ***
    private void setupUI() {
        Font titleFont = new Font("Roboto", Font.BOLD, 24);

        setTitle("Rental PS Rijal - Daftar Station");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Panel (dengan Tombol Back)
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // --- PERUBAHAN LAYOUT DIMULAI DI SINI ---

        // Main Panel (Hitam)
        // Gunakan GridBagLayout untuk menengahkan konten
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();

        // Judul "Daftar Station"
        JLabel titleLabel = new JLabel("Daftar Station");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);

        // Atur posisi Judul
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1; // Beri sedikit bobot ke atas
        gbc.anchor = GridBagConstraints.PAGE_END; // Tempel di bawah selnya
        gbc.insets = new Insets(30, 0, 20, 0); // Padding (top, left, bottom, right)
        mainPanel.add(titleLabel, gbc);

        // Panel untuk grid station
        stationsPanel = new JPanel();
        stationsPanel.setBackground(Color.BLACK);
        // Padding tidak perlu besar, GBC akan menengahkan
        stationsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        stationsPanel.setLayout(new GridLayout(3, 4, 30, 30)); // Grid 3x4 dengan gap

        // Buat dan tampilkan card station
        refreshStationCards();

        // Atur posisi Grid Station
        gbc.gridy = 1; // Baris kedua
        gbc.weighty = 0.9; // Beri sisa ruang ke bawah
        gbc.anchor = GridBagConstraints.PAGE_START; // Tempel di atas selnya
        gbc.insets = new Insets(0, 0, 0, 0); // Reset insets

        // Tambahkan stationsPanel ke mainPanel
        mainPanel.add(stationsPanel, gbc);

        // Tambahkan mainPanel (yang sudah berisi title dan grid) ke JFrame
        add(mainPanel, BorderLayout.CENTER);

        // --- PERUBAHAN LAYOUT SELESAI ---
    }

    // Method untuk (re)create semua card station
    private void refreshStationCards() {
        stationsPanel.removeAll(); // Hapus card lama
        for (int i = 1; i <= 12; i++) {
            final int stationNum = i;
            JPanel stationCard = createStationCard(stationNum, stationFont, statusFont);
            stationsPanel.add(stationCard);
        }
        stationsPanel.revalidate(); // Validasi ulang layout
        stationsPanel.repaint(); // Gambar ulang
    }

    // *** INI ADALAH METHOD YANG DIPERBAIKI (createStationCard) ***
    private JPanel createStationCard(int stationNum, Font stationFont, Font statusFont) {
        StationInfo info = globalStationStatus.get(stationNum);

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5)); // Gap antar komponen (dikecilkan)

        // PERBAIKAN: Kecilkan ukuran card agar sesuai gambar
        card.setPreferredSize(new Dimension(160, 120));
        card.setBackground(info.available ? new Color(50, 205, 50) : new Color(255, 69, 69)); // Hijau/Merah
        card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel numLabel = new JLabel(String.valueOf(stationNum), SwingConstants.CENTER);
        // PERBAIKAN: Kecilkan font angka
        numLabel.setFont(stationFont.deriveFont(40f));
        numLabel.setForeground(Color.WHITE);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setOpaque(false); // Transparan
        // PERBAIKAN: Kecilkan border
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel statusLabel = new JLabel(info.available ? "Available" : "Unavailable", SwingConstants.CENTER);
        // PERBAIKAN: Kecilkan font status
        statusLabel.setFont(statusFont.deriveFont(Font.BOLD, 14f));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel(formatTime(info.remainingTime), SwingConstants.CENTER);
        // PERBAIKAN: Kecilkan font timer
        timeLabel.setFont(statusFont.deriveFont(12f));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusPanel.add(statusLabel);
        statusPanel.add(timeLabel);

        card.add(numLabel, BorderLayout.CENTER);
        card.add(statusPanel, BorderLayout.SOUTH);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleStationClick(stationNum);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
        });

        return card;
    }

    private void handleStationClick(int stationNum) {
        StationInfo info = globalStationStatus.get(stationNum);

        if (!info.available) {
            // Sesuai Gambar 3
            JOptionPane.showMessageDialog(this, "Station " + stationNum + " masih occupied, tidak bisa dipilih!",
                    "Station Occupied", JOptionPane.WARNING_MESSAGE);
        } else {
            // Lanjut ke PaymentPage
            dispose();
            // Kirim info paket DAN nomor station
            new PaymentPage(selectedPackage, packageDuration, packagePrice, stationNum).setVisible(true);
        }
    }

    // Format detik ke HH:MM:SS
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    // Method public static untuk update status dari luar (dari PaymentPage)
    public static void updateStationStatus(int stationNum, boolean available, long remainingTimeInSeconds) {
        if (globalStationStatus.containsKey(stationNum)) {
            globalStationStatus.put(stationNum, new StationInfo(available, remainingTimeInSeconds));
        }
    }

    // Helper untuk membuat header konsisten
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel() {
            private Image backgroundImage;
            {
                try {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/assets/images/img-header.png"));
                    backgroundImage = icon.getImage();
                } catch (Exception e) {
                    setBackground(new Color(135, 206, 250)); // Fallback
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 100);
            }
        };
        headerPanel.setLayout(new BorderLayout());

        // Tombol Back (Sesuai Gambar 2)
        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Roboto", Font.BOLD, 32));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            dispose(); // Tutup window ini
            new LandingPage().setVisible(true); // Kembali ke LandingPage
        });

        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        return headerPanel;
    }
}