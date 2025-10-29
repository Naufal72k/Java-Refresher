package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.awt.Desktop;

public class GameDetail extends JFrame {
    private JPanel mainPanel;
    private String gameTitle;
    private String genre;
    private String year;
    private String rating;
    private String director;
    private String description;
    private String posterPath;
    private String imdbUrl;

    // Constructor dengan data game
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
        initializeUI();
        setFullscreen();
    }

    private void initializeUI() {
        setTitle("Game Detail - " + gameTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Poster
        ImageIcon posterIcon = new ImageIcon(getClass().getResource(posterPath));
        if (posterIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            posterIcon = new ImageIcon(); // Fallback if not found
        }
        JLabel posterLabel = new JLabel(posterIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        mainPanel.add(posterLabel, gbc);

        // Judul
        JLabel titleLabel = new JLabel("Judul: " + gameTitle);
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        // Genre
        JLabel genreLabel = new JLabel("Genre: " + genre);
        gbc.gridy = 1;
        mainPanel.add(genreLabel, gbc);

        // Tahun
        JLabel yearLabel = new JLabel("Tahun: " + year);
        gbc.gridy = 2;
        mainPanel.add(yearLabel, gbc);

        // Rating
        JLabel ratingLabel = new JLabel("Rating: " + rating);
        gbc.gridy = 3;
        mainPanel.add(ratingLabel, gbc);

        // Direktur
        JLabel directorLabel = new JLabel("Direktur: " + director);
        gbc.gridy = 4;
        mainPanel.add(directorLabel, gbc);

        // Deskripsi
        JTextArea descArea = new JTextArea(description);
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setPreferredSize(new Dimension(300, 100));
        gbc.gridy = 5;
        mainPanel.add(descScroll, gbc);

        // IMDB Button
        JButton imdbButton = new JButton("Buka IMDB");
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
        gbc.gridy = 6;
        mainPanel.add(imdbButton, gbc);

        add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    private void setFullscreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Contoh data
            new GameDetail("Game Title", "Action", "2020", "9.0", "Director Name", "Description here",
                    "/assets/poster.png", "https://www.imdb.com/title/example").setVisible(true);
        });
    }
}