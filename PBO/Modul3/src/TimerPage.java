package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerPage extends JFrame {
    private JLabel timeLabel;
    private Timer timer;
    private long remainingTime;
    private String stationName;
    private int stationNum;

    public TimerPage(String stationName, long durationInSeconds) {
        this.stationName = stationName;
        this.remainingTime = durationInSeconds;
        this.stationNum = Integer.parseInt(stationName.replace("Station ", ""));

        initializeUI();
        startTimer();
    }

    private void initializeUI() {
        setTitle("Timer - " + stationName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel() {
            private Image backgroundImage;

            {
                try {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/assets/images/img-header.png"));
                    backgroundImage = icon.getImage();
                } catch (Exception e) {
                    System.out.println("Header image not found");
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

        JLabel headerLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel stationLabel = new JLabel("Station: " + stationName, SwingConstants.CENTER);
        stationLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        stationLabel.setForeground(Color.WHITE);
        mainPanel.add(stationLabel, gbc);

        gbc.gridy = 1;
        timeLabel = new JLabel(formatTime(remainingTime), SwingConstants.CENTER);
        timeLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        timeLabel.setForeground(Color.GREEN);
        mainPanel.add(timeLabel, gbc);

        gbc.gridy = 2;
        JButton stopButton = new JButton("Stop Timer");
        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.WHITE);
        stopButton.addActionListener(e -> stopTimer());
        mainPanel.add(stopButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (remainingTime > 0) {
                    remainingTime--;
                    SwingUtilities.invokeLater(() -> timeLabel.setText(formatTime(remainingTime)));
                } else {
                    timer.cancel();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(TimerPage.this, "Waktu habis!");
                        DaftarStation.updateStationStatus(stationNum, true, 0);
                        dispose();
                        new LandingPage().setVisible(true);
                    });
                }
            }
        }, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        DaftarStation.updateStationStatus(stationNum, true, 0);
        dispose();
        new LandingPage().setVisible(true);
    }

    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TimerPage("Station 1", 3600).setVisible(true);
        });
    }
}