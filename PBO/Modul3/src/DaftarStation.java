package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class DaftarStation extends JFrame {
    private String selectedPackage;
    private int packageDuration;
    private int packagePrice;

    private static Map<Integer, StationInfo> globalStationStatus = new HashMap<>();

    private static Timer countdownTimer;

    private JPanel stationsPanel;
    private Font stationFont = new Font("Roboto", Font.BOLD, 20);
    private Font statusFont = new Font("Roboto", Font.PLAIN, 12);

    private static class StationInfo {
        boolean available;
        long remainingTime;

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
        startGlobalTimer();
    }

    private void initializeStationStatus() {
        if (globalStationStatus.isEmpty()) {
            for (int i = 1; i <= 12; i++) {
                globalStationStatus.put(i, new StationInfo(true, 0));
            }
        }
    }

    private void startGlobalTimer() {
        if (countdownTimer == null) {
            countdownTimer = new Timer(1000, e -> updateTimers());
            countdownTimer.start();
        }
    }

    private void updateTimers() {
        boolean needsRefresh = false;
        for (Map.Entry<Integer, StationInfo> entry : globalStationStatus.entrySet()) {
            StationInfo info = entry.getValue();
            if (!info.available && info.remainingTime > 0) {
                info.remainingTime--;
                needsRefresh = true;
                if (info.remainingTime == 0) {
                    info.available = true;
                }
            }
        }

        if (needsRefresh && this.isVisible()) {
            refreshStationCards();
        }
    }

    private void setupUI() {
        Font titleFont = new Font("Roboto", Font.BOLD, 24);

        setTitle("Rental PS Rijal - Daftar Station");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        JLabel titleLabel = new JLabel("Daftar Station");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(30, 0, 20, 0);
        mainPanel.add(titleLabel, gbc);

        stationsPanel = new JPanel();
        stationsPanel.setBackground(Color.BLACK);
        stationsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        stationsPanel.setLayout(new GridLayout(3, 4, 30, 30));
        refreshStationCards();
        gbc.gridy = 1;
        gbc.weighty = 0.9;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(0, 0, 0, 0);

        mainPanel.add(stationsPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

    }

    private void refreshStationCards() {
        stationsPanel.removeAll();
        for (int i = 1; i <= 12; i++) {
            final int stationNum = i;
            JPanel stationCard = createStationCard(stationNum, stationFont, statusFont);
            stationsPanel.add(stationCard);
        }
        stationsPanel.revalidate();
        stationsPanel.repaint();
    }

    private JPanel createStationCard(int stationNum, Font stationFont, Font statusFont) {
        StationInfo info = globalStationStatus.get(stationNum);

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5));

        card.setPreferredSize(new Dimension(160, 120));
        card.setBackground(info.available ? new Color(50, 205, 50) : new Color(255, 69, 69));
        card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel numLabel = new JLabel(String.valueOf(stationNum), SwingConstants.CENTER);

        numLabel.setFont(stationFont.deriveFont(40f));
        numLabel.setForeground(Color.WHITE);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setOpaque(false);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel statusLabel = new JLabel(info.available ? "Available" : "Unavailable", SwingConstants.CENTER);

        statusLabel.setFont(statusFont.deriveFont(Font.BOLD, 14f));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel(formatTime(info.remainingTime), SwingConstants.CENTER);

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

            JOptionPane.showMessageDialog(this, "Station " + stationNum + " masih occupied, tidak bisa dipilih!",
                    "Station Occupied", JOptionPane.WARNING_MESSAGE);
        } else {
            dispose();
            new PaymentPage(selectedPackage, packageDuration, packagePrice, stationNum).setVisible(true);
        }
    }

    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public static void updateStationStatus(int stationNum, boolean available, long remainingTimeInSeconds) {
        if (globalStationStatus.containsKey(stationNum)) {
            globalStationStatus.put(stationNum, new StationInfo(available, remainingTimeInSeconds));
        }
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel() {
            private Image backgroundImage;
            {
                try {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/assets/images/img-header.png"));
                    backgroundImage = icon.getImage();
                } catch (Exception e) {
                    setBackground(new Color(135, 206, 250));
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

        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Roboto", Font.BOLD, 32));
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            dispose();
            new LandingPage().setVisible(true);
        });

        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel headerLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        return headerPanel;
    }
}