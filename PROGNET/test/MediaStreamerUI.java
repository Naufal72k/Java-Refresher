package test;

import javax.swing.*;
import java.awt.*;

public class MediaStreamerUI extends JFrame {

    // Panel custom dengan rounded corner
    static class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color bgColor;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            this.cornerRadius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
    }

    public MediaStreamerUI() {
        setTitle("Media Streamer");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background gradasi
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(79, 70, 229),
                        getWidth(), getHeight(), new Color(147, 51, 234));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(new GridBagLayout());

        // Card putih rounded
        RoundedPanel card = new RoundedPanel(30, Color.WHITE);
        card.setPreferredSize(new Dimension(360, 500));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Logo M
        RoundedPanel logoPanel = new RoundedPanel(20, new Color(99, 102, 241));
        logoPanel.setPreferredSize(new Dimension(70, 70));
        logoPanel.setMaximumSize(new Dimension(70, 70));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel logoLabel = new JLabel("M", SwingConstants.CENTER);
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        logoPanel.setLayout(new GridBagLayout());
        logoPanel.add(logoLabel);
        card.add(logoPanel);

        // Judul
        card.add(Box.createVerticalStrut(10));
        JLabel title = new JLabel("Media Streamer", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(51, 51, 51));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        JLabel subtitle = new JLabel("Masukkan URL audio/video untuk diputar");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(new Color(100, 100, 100));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subtitle);

        // Input URL
        card.add(Box.createVerticalStrut(20));
        JLabel urlLabel = new JLabel("Media URL:");
        urlLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        urlLabel.setForeground(new Color(80, 80, 80));
        JTextField urlField = new JTextField("https://example.com/media.mp3");
        urlField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        urlField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        card.add(urlLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(urlField);

        // Tombol Play utama
        card.add(Box.createVerticalStrut(15));
        JButton playButton = new JButton("▶️ Play");
        playButton.setBackground(new Color(99, 102, 241));
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        card.add(playButton);

        // Panel kontrol
        card.add(Box.createVerticalStrut(25));
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        controls.setOpaque(false);
        JButton btnPlay = new JButton("▶️");
        JButton btnPause = new JButton("⏸");
        JButton btnStop = new JButton("⏹");
        controls.add(btnPlay);
        controls.add(btnPause);
        controls.add(btnStop);
        card.add(controls);

        // Slider progress
        JSlider progressSlider = new JSlider(0, 100, 30);
        JLabel timeLabel = new JLabel("0:30 / 3:45", SwingConstants.CENTER);
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        timeLabel.setForeground(new Color(120, 120, 120));
        card.add(progressSlider);
        card.add(timeLabel);

        // Volume slider
        card.add(Box.createVerticalStrut(10));
        JPanel volumePanel = new JPanel(new BorderLayout(5, 5));
        volumePanel.setOpaque(false);
        JLabel volumeLabel = new JLabel("🔊");
        JSlider volumeSlider = new JSlider(0, 100, 70);
        volumePanel.add(volumeLabel, BorderLayout.WEST);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);
        card.add(volumePanel);

        bgPanel.add(card);
        add(bgPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MediaStreamerUI().setVisible(true));
    }
}
