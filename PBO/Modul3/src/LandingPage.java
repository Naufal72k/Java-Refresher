package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.awt.Desktop;

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
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true); // Sesuai desain
                setLocationRelativeTo(null);
                setLayout(new BorderLayout());

                JPanel headerPanel = new JPanel() {
                        private Image backgroundImage;
                        {
                                try {
                                        ImageIcon icon = new ImageIcon(
                                                        getClass().getResource("/assets/images/img-header.png"));
                                        backgroundImage = icon.getImage();
                                } catch (Exception e) {
                                        System.out.println("Header image not found");
                                        setBackground(new Color(135, 206, 250)); // Fallback color
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
                headerLabel.setFont(titleFont);
                headerLabel.setForeground(Color.WHITE);
                headerPanel.add(headerLabel, BorderLayout.CENTER);

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

                try {
                        ImageIcon heroIcon = new ImageIcon(
                                        getClass().getResource("/assets/images/img-hero-section.png"));
                        Image heroImg = heroIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                        JLabel imageLabel = new JLabel(new ImageIcon(heroImg));
                        topInfoPanel.add(imageLabel, BorderLayout.EAST);
                } catch (Exception e) {
                        System.out.println("Hero image not found");
                }

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

                // **PERHATIKAN DURASI (PENTING UNTUK TIMER): 1, 3, 5, 6 jam**
                paketCardsPanel.add(createPaketCard("Paket Bronze (1 Jam)", 1, 12000,
                                "Fasilitas:\n• 1 jam bermain\n• Bebas pilih game\n• Konsol PS5\n• Headset & sofa nyaman\n*Cocok buat kamu yang ingin main sebentar atau sekadar mengisi waktu luang.\nRp12.000",
                                paketTitleFont, fasilitasFont));
                paketCardsPanel.add(createPaketCard("Paket Silver (3 Jam)", 3, 30000,
                                "Fasilitas:\n• 3 jam bermain\n• Ganti game kapan saja\n• Bonus 1 snack ringan\n• Wi-Fi gratis & ruangan ber-AC\n*Pilihan pas untuk kamu yang ingin bermain lebih lama tanpa khawatir cepat habis.\nRp30.000",
                                paketTitleFont, fasilitasFont));
                paketCardsPanel.add(createPaketCard("Paket Gold (5 Jam)", 5, 50000,
                                "Fasilitas:\n• 5 jam bermain\n• Akses semua game premium\n• 1 minuman gratis\n• Prioritas room (bisa pilih ruangan)\n*Buat kamu yang mau puas main game favorit tanpa batas waktu yang ngepot!\nRp50.000",
                                paketTitleFont, fasilitasFont));
                paketCardsPanel.add(createPaketCard("Paket Malam", 6, 60000,
                                "Fasilitas:\n• Bermain nonstop jam 00:00 - 06:00\n• Akses semua game\n• 2 snack & 2 minuman gratis\n• Ruangan VIP dengan layar besar\n*Paket spesial untuk kamu yang ingin main semalaman tanpa batas!\nRp60.000",
                                paketTitleFont, fasilitasFont));

                contentPanel.add(paketCardsPanel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                JLabel gameLabel = new JLabel("Pilihan Game");
                gameLabel.setForeground(Color.WHITE);
                gameLabel.setFont(gameFont);
                gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(gameLabel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                JPanel gameCardsPanel = new JPanel();
                gameCardsPanel.setBackground(Color.BLACK);
                gameCardsPanel.setLayout(new GridLayout(3, 6, 15, 5)); // 3 baris untuk 18 game

                // Game asli (8) - Sesuai kode asli Anda
                gameCardsPanel.add(createGameCard("God of War Ragnarok", "Action-Adventure", "/assets/games/gowr.jpg",
                                "2022", "9.5", "Eric Williams", "Petualangan epik Kratos dan Atreus.",
                                "https://www.imdb.com/title/tt5838588/"));
                gameCardsPanel.add(createGameCard("Spider-Man 2", "Action-Adventure", "/assets/games/spiderman2.jpg",
                                "2023", "9.2", "Insomniac Games", "Swing through New York as Peter and Miles.",
                                "https://www.imdb.com/title/tt1877830/"));
                gameCardsPanel.add(createGameCard("The Last of Us Part II", "Action-Adventure",
                                "/assets/games/tlou2.jpg", "2020", "9.7", "Neil Druckmann",
                                "Emotional journey of Ellie.", "https://www.imdb.com/title/tt6298000/"));
                gameCardsPanel.add(createGameCard("Gran Turismo 7", "Racing", "/assets/games/gt7.jpg", "2022", "8.5",
                                "Kazunori Yamauchi", "Ultimate racing simulation.",
                                "https://www.imdb.com/title/tt12561702/"));
                gameCardsPanel.add(createGameCard("FIFA 23", "Sports", "/assets/games/fifa23.jpg", "2022", "7.8",
                                "EA Sports", "Soccer action on the pitch.", "https://www.imdb.com/title/tt1234567/"));
                gameCardsPanel.add(createGameCard("Call of Duty Modern Warfare II", "Shooter",
                                "/assets/games/codmw2.jpg", "2022", "8.0", "Infinity Ward",
                                "Intense multiplayer shooter.", "https://www.imdb.com/title/tt6073624/"));
                gameCardsPanel.add(createGameCard("Horizon Forbidden West", "Action-Adventure", "/assets/games/hfw.jpg",
                                "2022", "9.0", "Mathijs de Jonge", "Explore a post-apocalyptic world.",
                                "https.imdb.com/title/tt12496904/"));
                gameCardsPanel.add(createGameCard("Ratchet & Clank: Rift Apart", "Platformer",
                                "/assets/games/ratchet.jpg", "2021", "8.7", "Insomniac Games",
                                "Fun platforming adventure.", "https.imdb.com/title/tt12792418/"));

                // **10 Game BARU (Sesuai Permintaan di Alur)**
                gameCardsPanel.add(createGameCard("Cyberpunk 2077", "Action, RPG", "/assets/games/cp2077.jpg",
                                "2020", "7.9", "CD Projekt", "Futuristic dystopia adventure.",
                                "https.imdb.com/title/tt4466244/"));
                gameCardsPanel.add(createGameCard("Hogwarts Legacy", "Adventure", "/assets/games/hogwarts.jpg",
                                "2023", "8.5", "Avalanche", "Wizarding school exploration.",
                                "https.imdb.com/title/tt8201380/"));
                gameCardsPanel.add(createGameCard("Resident Evil 4 Remake", "Horror", "/assets/games/re4.jpg",
                                "2023", "9.5", "Capcom", "Remake of the classic horror.",
                                "https.imdb.com/title/tt12561704/"));
                gameCardsPanel.add(createGameCard("Street Fighter 6", "Fighting", "/assets/games/sf6.jpg",
                                "2023", "8.8", "Capcom", "New era of fighting games.",
                                "https.imdb.com/title/tt18652204/"));
                gameCardsPanel.add(createGameCard("Mortal Kombat 1", "Fighting", "/assets/games/mk1.jpg",
                                "2023", "8.2", "NetherRealm", "A new Mortal Kombat universe.",
                                "https.imdb.com/title/tt27818228/"));
                gameCardsPanel.add(
                                createGameCard("Star Wars Jedi: Survivor", "Action", "/assets/games/jedi_survivor.jpg",
                                                "2023", "8.9", "Respawn", "Cal Kestis continues his journey.",
                                                "https.imdb.com/title/tt19842048/"));
                gameCardsPanel.add(createGameCard("Diablo IV", "RPG", "/assets/games/diablo4.jpg",
                                "2023", "8.3", "Blizzard", "Return to the world of Sanctuary.",
                                "https.imdb.com/title/tt11311540/"));
                gameCardsPanel.add(createGameCard("Assassin’s Creed Mirage", "Action", "/assets/games/ac_mirage.jpg",
                                "2023", "7.5", "Ubisoft", "A new adventure in Baghdad.",
                                "https.imdb.com/title/tt21915386/"));
                gameCardsPanel.add(createGameCard("Alan Wake 2", "Horror", "/assets/games/alan_wake_2.jpg",
                                "2023", "9.0", "Remedy", "A story of supernatural horror.",
                                "https.imdb.com/title/tt14867160/"));
                gameCardsPanel.add(createGameCard("Elden Ring", "Action-RPG", "/assets/games/eldenring.jpg", "2022",
                                "9.6", "FromSoftware", "Challenging open-world soulslike.",
                                "https.imdb.com/title/tt10340546/"));

                contentPanel.add(gameCardsPanel);

                JScrollPane scrollPane = new JScrollPane(contentPanel);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);
                scrollPane.setBorder(null);
                scrollPane.addMouseWheelListener(new MouseWheelListener() {
                        @Override
                        public void mouseWheelMoved(MouseWheelEvent e) {
                                JScrollBar bar = scrollPane.getVerticalScrollBar();
                                bar.setValue(bar.getValue() + e.getWheelRotation() * 50);
                        }
                });

                add(scrollPane, BorderLayout.CENTER);
        }

        private JPanel createPaketCard(String name, int duration, int price, String description, Font titleFont,
                        Font descFont) {
                JPanel card = new JPanel();
                card.setLayout(new BorderLayout());
                card.setBackground(new Color(30, 30, 30));
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                card.setPreferredSize(new Dimension(200, 250));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));

                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setBackground(card.getBackground());
                textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel nameLabel = new JLabel(name);
                nameLabel.setFont(titleFont);
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JTextArea descArea = new JTextArea(description);
                descArea.setFont(descFont);
                descArea.setForeground(Color.WHITE);
                descArea.setBackground(card.getBackground());
                descArea.setEditable(false);
                descArea.setWrapStyleWord(true);
                descArea.setLineWrap(true);
                descArea.setAlignmentX(Component.CENTER_ALIGNMENT);

                textPanel.add(nameLabel);
                textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                textPanel.add(descArea);

                card.add(textPanel, BorderLayout.CENTER);

                card.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                dispose(); // Tutup LandingPage
                                // Buka DaftarStation dengan info paket
                                new DaftarStation(name, duration, price).setVisible(true);
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                                card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                        }
                });

                return card;
        }

        private JPanel createGameCard(String name, String genre, String imgPath, String year, String rating,
                        String director, String description, String imdbUrl) {
                JPanel card = new JPanel();
                card.setLayout(new BorderLayout());
                card.setBackground(new Color(30, 30, 30));
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                card.setPreferredSize(new Dimension(150, 200));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));

                try {
                        ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));
                        // Scaling gambar agar pas
                        Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                        JLabel imgLabel = new JLabel(new ImageIcon(img));
                        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        card.add(imgLabel, BorderLayout.CENTER);
                } catch (Exception e) {
                        JLabel placeholder = new JLabel("Image not found", SwingConstants.CENTER);
                        placeholder.setForeground(Color.WHITE);
                        card.add(placeholder, BorderLayout.CENTER);
                }

                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setBackground(card.getBackground());
                textPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

                JLabel nameLabel = new JLabel(name);
                nameLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                nameLabel.setForeground(Color.WHITE);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel genreLabel = new JLabel(genre);
                genreLabel.setFont(new Font("Roboto", Font.PLAIN, 10));
                genreLabel.setForeground(Color.GRAY);
                genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                textPanel.add(nameLabel);
                textPanel.add(genreLabel);

                card.add(textPanel, BorderLayout.SOUTH);

                card.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                // **PERUBAHAN:** Tidak dispose(), tapi buka window baru
                                new GameDetail(name, genre, year, rating, director, description, imgPath, imdbUrl)
                                                .setVisible(true);
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                                card.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                        }
                });

                return card;
        }
}