package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LandingPage extends JFrame {

    public LandingPage() {

        Font titleFont = new Font("Roboto", Font.BOLD, 32);
        Font infoFont = new Font("Roboto", Font.PLAIN, 14);
        Font paketFont = new Font("Roboto", Font.PLAIN, 20);
        Font gameFont = new Font("Roboto", Font.PLAIN, 20);
        Font paketTitleFont = new Font("Roboto", Font.BOLD, 14);
        Font fasilitasFont = new Font("Roboto", Font.PLAIN, 12);
        Font gameNameFont = new Font("Roboto", Font.PLAIN, 12);
        Font genreFont = new Font("Roboto", Font.ITALIC, 10);

        setTitle("Rental PS Rijal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(135, 206, 250));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1000, 60));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("RENTAL PS RIJAL", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(20, 40, 40, 40));

        JPanel topInfoPanel = new JPanel(new BorderLayout());
        topInfoPanel.setBackground(Color.BLACK);

        JTextArea infoText = new JTextArea(
                "Main Seru, Harga Murah, Tanpa Ribet!\nAyo main di rental ps rijal, semua game ada!");
        infoText.setFont(infoFont);
        infoText.setForeground(Color.WHITE);
        infoText.setBackground(Color.BLACK);
        infoText.setEditable(false);
        infoText.setBorder(new EmptyBorder(0, 0, 0, 30));
        topInfoPanel.add(infoText, BorderLayout.WEST);

        ImageIcon heroIcon = new ImageIcon(getClass().getResource("/assets/images/img-hero-section.png"));
        Image heroImg = heroIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(heroImg));
        topInfoPanel.add(imageLabel, BorderLayout.EAST);

        contentPanel.add(topInfoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel paketLabel = new JLabel("Pilihan Paket");
        paketLabel.setForeground(Color.WHITE);
        paketLabel.setFont(paketFont);
        paketLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(paketLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel paketCardsPanel = new JPanel();
        paketCardsPanel.setBackground(Color.BLACK);
        paketCardsPanel.setLayout(new GridLayout(1, 4, 15, 5));

        paketCardsPanel.add(createPaketCard("Paket Bronze (1 Jam)",
                "Fasilitas:\n• 1 jam bermain\n• Bebas pilih game\n• Konsol PS5\n• Headset & sofa nyaman\n*Cocok buat kamu yang ingin main sebentar atau sekadar mengisi waktu luang.\nRp12.000",
                paketTitleFont, fasilitasFont));
        paketCardsPanel.add(createPaketCard("Paket Silver (3 Jam)",
                "Fasilitas:\n• 3 jam bermain\n• Ganti game kapan saja\n• Bonus 1 snack ringan\n• Wi-Fi gratis & ruangan ber-AC\n*Pilihan pas untuk kamu yang ingin bermain lebih lama tanpa khawatir cepat habis.\nRp30.000",
                paketTitleFont, fasilitasFont));
        paketCardsPanel.add(createPaketCard("Paket Gold (5 Jam)",
                "Fasilitas:\n• 5 jam bermain\n• Akses semua game premium\n• 1 minuman gratis\n• Prioritas room (bisa pilih ruangan)\n*Buat kamu yang mau puas main game favorit tanpa batas waktu yang ngepot!\nRp50.000",
                paketTitleFont, fasilitasFont));
        paketCardsPanel.add(createPaketCard("Paket Malam",
                "Fasilitas:\n• Bermain nonstop jam 00:00-06:00\n• Snack & minuman gratis\n• Bisa main ramai-ramai\n• Diskon tambahan untuk member\n*Spesial untuk gamer malam yang mau suasana tenang dan bebas antri.\nRp60.000",
                paketTitleFont, fasilitasFont));

        contentPanel.add(paketCardsPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel gameLabel = new JLabel("Daftar Game");
        gameLabel.setForeground(Color.WHITE);
        gameLabel.setFont(gameFont);
        gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(gameLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel gameListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        gameListPanel.setBackground(Color.BLACK);

        gameListPanel.add(createGameCard("Red Dead Redemption II", "Action, Adventure", "/assets/games/games1.png",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("The Last of Us: Part I", "Survival, Drama", "/assets/games/games2.png",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("God of War: Ragnarok", "Action, Adventure", "/assets/games/games3.png",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("Metal Gear Solid", "Action, Mystery", "/assets/games/games4.png",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("Final Fantasy VII", "Sci-Fi, Fantasy", "/assets/games/games5.png",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("Baldur's Gate III", "Action, Adventure", "/assets/games/games6.jpg",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("Elden Ring", "Dark Fantasy, Adventure", "/assets/games/games7.jpg",
                gameNameFont, genreFont));
        gameListPanel.add(createGameCard("Grand Theft Auto V", "Action, Crime", "/assets/games/games8.jpg",
                gameNameFont, genreFont));

        contentPanel.add(gameListPanel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createGameCard(String name, String genre, String imgPath, Font gameNameFont, Font genreFont) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(120, 180));

        ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        panel.add(imgLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(gameNameFont);
        panel.add(nameLabel, BorderLayout.CENTER);

        JLabel genreLabel = new JLabel(genre, SwingConstants.CENTER);
        genreLabel.setForeground(Color.LIGHT_GRAY);
        genreLabel.setFont(genreFont);
        panel.add(genreLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPaketCard(String title, String fasilitasText, Font paketTitleFont, Font fasilitasFont) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setPreferredSize(new Dimension(220, 280));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(paketTitleFont);
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextArea fasilitasArea = new JTextArea(fasilitasText);
        fasilitasArea.setFont(fasilitasFont);
        fasilitasArea.setEditable(false);
        fasilitasArea.setOpaque(false);
        fasilitasArea.setBorder(new EmptyBorder(0, 10, 10, 10));
        fasilitasArea.setLineWrap(true);
        fasilitasArea.setWrapStyleWord(true);

        JButton bookingBtn = new JButton("Booking Paket");
        bookingBtn.setBackground(new Color(0, 120, 255));
        bookingBtn.setForeground(Color.WHITE);
        bookingBtn.setFocusPainted(false);
        bookingBtn.setBorder(new EmptyBorder(10, 80, 10, 80));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(bookingBtn);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(fasilitasArea, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LandingPage frame = new LandingPage();
            frame.setVisible(true);
        });
    }
}
