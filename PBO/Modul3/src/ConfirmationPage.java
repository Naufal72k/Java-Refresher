package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ConfirmationPage extends JFrame {

    private String packageName;
    private int packageDuration;
    private int packagePrice;

    public ConfirmationPage(String packageName, int packageDuration, int packagePrice) {
        this.packageName = packageName;
        this.packageDuration = packageDuration;
        this.packagePrice = packagePrice;

        setTitle("Rental PS Rijal - Konfirmasi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Kode Anda");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        JLabel codeLabel = new JLabel(generateRandomCode());
        codeLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        codeLabel.setForeground(Color.WHITE);
        mainPanel.add(codeLabel, gbc);

        gbc.gridy = 2;
        JButton selesaiButton = new JButton("Selesai");
        selesaiButton.setFont(new Font("Roboto", Font.BOLD, 16));
        selesaiButton.setBackground(new Color(60, 90, 250));
        selesaiButton.setForeground(Color.WHITE);
        selesaiButton.setFocusPainted(false);
        selesaiButton.setPreferredSize(new Dimension(120, 40));

        selesaiButton.addActionListener(e -> {
            dispose();
            new DaftarStation(packageName, packageDuration, packagePrice).setVisible(true);
        });

        mainPanel.add(selesaiButton, gbc);
        add(mainPanel, BorderLayout.CENTER);
    }

    private String generateRandomCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 6) {
            int index = (int) (rnd.nextFloat() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
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

        JLabel headerLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Roboto", Font.BOLD, 32));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        return headerPanel;
    }
}