package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentPage extends JFrame {

    private String packageName;
    private int packageDuration; // jam
    private int packagePrice;
    private int stationNum;

    public PaymentPage(String packageName, int packageDuration, int packagePrice, int stationNum) {
        this.packageName = packageName;
        this.packageDuration = packageDuration;
        this.packagePrice = packagePrice;
        this.stationNum = stationNum;

        setTitle("Rental PS Rijal - Pembayaran");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // Header Panel (dengan Tombol Back)
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Panel Konten
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Teks "Proses Pembayaran Anda"
        JLabel titleLabel = new JLabel("Proses Pembayaran Anda");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, gbc);

        // Teks Detail Pesanan
        gbc.gridy = 1;
        String detailText = String.format("Station %d - ✓ %s - %d Jam", stationNum, packageName, packageDuration);
        JLabel detailLabel = new JLabel(detailText);
        detailLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        detailLabel.setForeground(Color.WHITE);
        mainPanel.add(detailLabel, gbc);

        // Gambar QR Code (Sesuai Gambar 4)
        gbc.gridy = 2;
        try {
            // Ganti path ini jika lokasi QR code berbeda
            ImageIcon qrIcon = new ImageIcon(getClass().getResource("/assets/images/qris-dummy.png"));
            // Scaling gambar
            Image qrImg = qrIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            JLabel qrLabel = new JLabel(new ImageIcon(qrImg));
            mainPanel.add(qrLabel, gbc);
        } catch (Exception e) {
            JLabel qrPlaceholder = new JLabel("QR Code Not Found");
            qrPlaceholder.setFont(new Font("Roboto", Font.BOLD, 20));
            qrPlaceholder.setForeground(Color.RED);
            mainPanel.add(qrPlaceholder, gbc);
        }

        // Tombol "Selesai"
        gbc.gridy = 3;
        JButton selesaiButton = new JButton("Selesai");
        selesaiButton.setFont(new Font("Roboto", Font.BOLD, 16));
        selesaiButton.setBackground(new Color(60, 90, 250)); // Biru
        selesaiButton.setForeground(Color.WHITE);
        selesaiButton.setFocusPainted(false);
        selesaiButton.setPreferredSize(new Dimension(120, 40));

        selesaiButton.addActionListener(e -> {
            // **AKSI UTAMA SAAT BAYAR SELESAI**

            // 1. Update status station global jadi 'Unavailable'
            long durationInSeconds = packageDuration * 3600L;
            DaftarStation.updateStationStatus(stationNum, false, durationInSeconds);

            // 2. Tutup window ini
            dispose();

            // 3. Buka ConfirmationPage (kirim info paket agar bisa kembali)
            new ConfirmationPage(packageName, packageDuration, packagePrice).setVisible(true);
        });

        mainPanel.add(selesaiButton, gbc);
        add(mainPanel, BorderLayout.CENTER);
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

        // Tombol Back (Sesuai Gambar 4)
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
            // Kembali ke DaftarStation (dengan info paket asli)
            new DaftarStation(packageName, packageDuration, packagePrice).setVisible(true);
        });

        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        return headerPanel;
    }
}