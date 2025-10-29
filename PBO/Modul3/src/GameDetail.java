package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.awt.Desktop;

public class GameDetail extends JFrame {
    // Deklarasi field tetap sama
    private String gameTitle;
    private String genre;
    private String year;
    private String rating;
    private String director;
    private String description;
    private String posterPath;
    private String imdbUrl;

    // Constructor (sama)
    public GameDetail(String gameTitle, String genre, String year, String rating, String director, String description,
            String posterPath, String imdbUrl) {
        this.gameTitle = gameTitle;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.director = director;
        this.description = description;
        this.posterPath = posterPath;
        this.imdbUrl = imdbUrl;

        // Panggil UI baru
        initializeNewUI();
    }

    // *** UI LAMA DIHAPUS DAN DIGANTI DENGAN INI ***
    private void initializeNewUI() {
        setTitle("Game Detail - " + gameTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Hanya tutup window ini

        // 1. Konfigurasi Pop-up (bukan fullscreen)
        setUndecorated(true); // Menghilangkan title bar OS
        setSize(900, 550); // Ukuran pop-up yang pas
        setLocationRelativeTo(null); // Muncul di tengah layar

        // 2. Container Utama
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(Color.BLACK);
        // Border abu-abu tipis agar terlihat seperti window
        mainContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // 3. Custom Title Bar (Sesuai Gambar: [Judul] [X])
        JPanel titleBarPanel = new JPanel(new BorderLayout());
        titleBarPanel.setBackground(new Color(20, 20, 20)); // Abu-abu sangat gelap
        titleBarPanel.setBorder(new EmptyBorder(5, 10, 5, 5));

        JLabel titleLabel = new JLabel(gameTitle);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleBarPanel.add(titleLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Roboto", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Aksi untuk tombol "X" -> tutup window
        closeButton.addActionListener(e -> dispose());
        titleBarPanel.add(closeButton, BorderLayout.EAST);

        mainContainer.add(titleBarPanel, BorderLayout.NORTH); // Tambahkan title bar ke atas

        // 4. Panel Konten Utama (Layout Kiri & Kanan)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding

        // 5. Panel Kiri (Poster + Tombol IMDb)
        JPanel leftPanel = new JPanel(new BorderLayout(0, 15)); // Gap vertikal 15px
        leftPanel.setOpaque(false); // Transparan (ikut background hitam)

        // Poster
        JLabel posterLabel = new JLabel();
        try {
            ImageIcon posterIcon = new ImageIcon(getClass().getResource(posterPath));
            // Scaling gambar poster
            Image posterImg = posterIcon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            posterLabel.setIcon(new ImageIcon(posterImg));
        } catch (Exception e) {
            posterLabel.setText("Poster not found");
            posterLabel.setForeground(Color.WHITE);
        }
        leftPanel.add(posterLabel, BorderLayout.CENTER);

        // Tombol IMDb (Biru)
        JButton imdbButton = new JButton("View on IMDb"); // Teks sesuai gambar
        imdbButton.setFont(new Font("Roboto", Font.BOLD, 14));
        imdbButton.setBackground(new Color(60, 90, 250)); // Warna biru
        imdbButton.setForeground(Color.WHITE);
        imdbButton.setFocusPainted(false);
        imdbButton.setBorder(new EmptyBorder(10, 0, 10, 0)); // Padding atas-bawah
        imdbButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // **FUNGSI LINK KE IMDB (Sudah ada di kode lama Anda)**
        imdbButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(imdbUrl));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        leftPanel.add(imdbButton, BorderLayout.SOUTH); // Tambah tombol ke bawah

        contentPanel.add(leftPanel, BorderLayout.WEST); // Tambah panel kiri ke konten

        // 6. Panel Kanan (Semua Teks Info)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false); // Transparan
        rightPanel.setBorder(new EmptyBorder(0, 25, 0, 0)); // Padding kiri (jarak dari poster)

        // Definisikan Font
        Font labelFont = new Font("Roboto", Font.BOLD, 16);
        Font valueFont = new Font("Roboto", Font.PLAIN, 16);
        Font titleFont = new Font("Roboto", Font.BOLD, 28);

        // Judul Game (Besar)
        JLabel gameTitleLabel = new JLabel(gameTitle);
        gameTitleLabel.setFont(titleFont);
        gameTitleLabel.setForeground(Color.WHITE);
        gameTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(gameTitleLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Jarak

        // Baris Info (Genre, Year, Rating, Director)
        rightPanel.add(createInfoRow("Genre:", genre, labelFont, valueFont));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(createInfoRow("Year:", year, labelFont, valueFont));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(createInfoRow("Rating:", rating, labelFont, valueFont));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(createInfoRow("Director:", director, labelFont, valueFont));
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Deskripsi
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(labelFont);
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightPanel.add(descLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextArea descArea = new JTextArea(description);
        descArea.setFont(valueFont);
        descArea.setForeground(Color.LIGHT_GRAY); // Warna abu-abu
        descArea.setBackground(Color.BLACK); // Background hitam
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        descArea.setMaximumSize(new Dimension(450, 200)); // Batasi ukuran area teks

        rightPanel.add(descArea);

        // "Lem" vertikal untuk mendorong semua konten ke atas
        rightPanel.add(Box.createVerticalGlue());

        contentPanel.add(rightPanel, BorderLayout.CENTER); // Tambah panel kanan ke konten

        // 7. Finalisasi
        mainContainer.add(contentPanel, BorderLayout.CENTER);
        setContentPane(mainContainer); // Set container utama sebagai isi frame
    }

    // Helper method untuk membuat baris info (Label: Value)
    private JPanel createInfoRow(String label, String value, Font labelFont, Font valueFont) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setOpaque(false); // Transparan
        rowPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lbl = new JLabel(label);
        lbl.setFont(labelFont);
        lbl.setForeground(Color.WHITE);

        JLabel val = new JLabel(value);
        val.setFont(valueFont);
        val.setForeground(Color.LIGHT_GRAY);
        val.setBorder(new EmptyBorder(0, 10, 0, 0)); // Padding kiri

        rowPanel.add(lbl);
        rowPanel.add(val);
        return rowPanel;
    }

    // Hapus method setFullscreen() yang lama
    // private void setFullscreen() { ... }

    // main() method untuk testing (bisa dihapus jika tidak perlu)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Contoh data sesuai gambar
            new GameDetail("Red Dead Redemption II", "Action", "2018", "9.7", "Rockstar Games",
                    "Follows outlaw Arthur Morgan and his gang, led by the charismatic Dutch Van der Linde, as they struggle to cope with the loss of their way of life amidst the decline of the Wild West at the turn of the 20th century.",
                    "/assets/games/rdr2.jpg", "https://www.imdb.com/title/tt6161168/").setVisible(true);
        });
    }
}