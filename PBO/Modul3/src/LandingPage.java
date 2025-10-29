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

                // =================================================================
                // 1. HEADER PANEL (Tetap sama, full-width)
                // =================================================================
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

                // =================================================================
                // 2. CONTENT PANEL (Panel utama untuk semua konten)
                // =================================================================
                JPanel contentPanel = new JPanel();
                contentPanel.setBackground(Color.BLACK);
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                // Beri padding untuk contentPanel agar tidak terlalu mepet
                contentPanel.setBorder(new EmptyBorder(20, 40, 40, 40));

                // --- HERO SECTION (topInfoPanel) ---
                JPanel topInfoPanel = new JPanel();
                topInfoPanel.setLayout(new BoxLayout(topInfoPanel, BoxLayout.X_AXIS));
                topInfoPanel.setBackground(Color.BLACK);
                topInfoPanel.setPreferredSize(new Dimension(0, 260));
                topInfoPanel.setMinimumSize(new Dimension(0, 260));
                topInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));
                topInfoPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

                // Siapkan Teks
                JLabel titleText = new JLabel("Main Seru, Harga Murah, Tanpa Ribet!");
                titleText.setFont(new Font("Roboto", Font.BOLD, 24));
                titleText.setForeground(Color.WHITE);
                JLabel subText = new JLabel("Ayo main di rental ps rijal, semua game ada!");
                subText.setFont(new Font("Roboto", Font.PLAIN, 16));
                subText.setForeground(Color.LIGHT_GRAY);

                JPanel textWrapperPanel = new JPanel();
                textWrapperPanel.setLayout(new BoxLayout(textWrapperPanel, BoxLayout.Y_AXIS));
                textWrapperPanel.setBackground(Color.BLACK);
                textWrapperPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                titleText.setAlignmentX(Component.LEFT_ALIGNMENT);
                subText.setAlignmentX(Component.LEFT_ALIGNMENT);
                textWrapperPanel.add(titleText);
                textWrapperPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                textWrapperPanel.add(subText);

                // Siapkan Gambar
                JLabel imageLabel = new JLabel();
                try {
                        ImageIcon heroIcon = new ImageIcon(
                                        getClass().getResource("/assets/images/img-hero-section.png"));
                        Image heroImg = heroIcon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(heroImg));
                        imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                } catch (Exception e) {
                        System.out.println("Hero image not found");
                }

                topInfoPanel.add(Box.createHorizontalGlue()); // (A) Lem Kiri
                topInfoPanel.add(textWrapperPanel); // (B) Teks
                topInfoPanel.add(Box.createRigidArea(new Dimension(50, 0))); // (C) Jarak
                topInfoPanel.add(imageLabel); // (D) Gambar
                topInfoPanel.add(Box.createHorizontalGlue()); // (E) Lem Kanan

                contentPanel.add(topInfoPanel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                // --- PAKET SECTION (paketCardsPanel) ---
                JLabel paketLabel = new JLabel("Pilihan Paket");
                paketLabel.setForeground(Color.WHITE);
                paketLabel.setFont(paketFont);
                paketLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(paketLabel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                JPanel paketCardsPanel = new JPanel();
                paketCardsPanel.setBackground(Color.BLACK);
                paketCardsPanel.setLayout(new GridLayout(1, 4, 15, 5));

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

                // --- GAME SECTION (gameCardsPanel) ---
                JLabel gameLabel = new JLabel("Daftar Game"); // Sesuai gambar
                gameLabel.setForeground(Color.WHITE);
                gameLabel.setFont(gameFont);
                gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(gameLabel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                // --- PERUBAHAN DIMULAI DI SINI ---

                // 1. Buat panel game dengan BoxLayout horizontal (kiri ke kanan)
                JPanel gameCardsPanel = new JPanel();
                gameCardsPanel.setLayout(new BoxLayout(gameCardsPanel, BoxLayout.X_AXIS));
                gameCardsPanel.setBackground(Color.BLACK);
                // Padding sedikit di atas dan bawah kartu
                gameCardsPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

                // Buat jarak antar kartu
                Component gameGap = Box.createRigidArea(new Dimension(15, 0));

                // 2. Tambahkan 8 game pertama (dari kode Anda)
                gameCardsPanel.add(
                                createGameCard("Red Dead Redemption 2", "Action-Adventure", "/assets/games/games1.png",
                                                "2018", "9.7", "Rockstar", "Western outlaw story.",
                                                "https://www.imdb.com/title/tt6161168/"));
                gameCardsPanel.add(gameGap);
                gameCardsPanel.add(createGameCard("The Last of Us Part II", "Action-Adventure",
                                "/assets/games/games2.png", "2020", "9.7", "Neil Druckmann",
                                "Emotional journey of Ellie.", "https://www.imdb.com/title/tt6298000/"));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("God of War Ragnarok", "Action-Adventure", "/assets/games/games3.png",
                                "2022", "9.5", "Eric Williams", "Petualangan epik Kratos dan Atreus.",
                                "https://www.imdb.com/title/tt5838588/"));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Cyberpunk 2077", "Action, RPG", "/assets/games/games4.png",
                                "2020", "7.9", "CD Projekt", "Futuristic dystopia adventure.",
                                "https://www.imdb.com/title/tt4466244/"));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Hogwarts Legacy", "Adventure", "/assets/games/games5.png",
                                "2023", "8.5", "Avalanche", "Wizarding school exploration.",
                                "https://www.imdb.com/title/tt8201380/"));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Baldur's Gate 3", "RPG", "/assets/games/games6.jpg", "2023", "9.9",
                                "Larian Studios", "Deep turn-based RPG.", "https://www.imdb.com/title/tt15498712/"));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Elden Ring", "Action-RPG", "/assets/games/games7.jpg", "2022",
                                "9.6", "FromSoftware", "Challenging open-world soulslike.",
                                "https://www.imdb.com/title/tt10340546/"));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Spider-Man 2", "Action-Adventure", "/assets/games/games8.jpg",
                                "2023", "9.2", "Insomniac Games", "Swing through New York as Peter and Miles.",
                                "https://www.imdb.com/title/tt1877830/"));

                // 3. Tambahkan 10 game lainnya (Saya gunakan path placeholder)
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Gran Turismo 7", "Racing", "/assets/games/games9.jpg", "2022", "8.5",
                                "Polyphony", "Racing sim", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("FIFA 23", "Sports", "/assets/games/games10.jpg", "2022", "7.8",
                                "EA Sports", "Soccer", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Call of Duty MWII", "Shooter", "/assets/games/games11.jpg", "2022",
                                "8.0",
                                "Infinity Ward", "Shooter", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(
                                createGameCard("Horizon Forbidden West", "Action", "/assets/games/games12.jpg", "2022",
                                                "9.0",
                                                "Guerilla", "Action-RPG", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Ratchet & Clank", "Platformer", "/assets/games/games13.jpg", "2021",
                                "8.7",
                                "Insomniac", "Platformer", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("RE4 Remake", "Horror", "/assets/games/games14.jpg", "2023", "9.5",
                                "Capcom", "Horror", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(
                                createGameCard("Street Fighter 6", "Fighting", "/assets/games/games15.jpg", "2023",
                                                "8.8",
                                                "Capcom", "Fighting", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Mortal Kombat 1", "Fighting", "/assets/games/games16.jpg", "2023",
                                "8.2",
                                "NetherRealm", "Fighting", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Jedi: Survivor", "Action", "/assets/games/games17.jpg", "2023",
                                "8.9",
                                "Respawn", "Action", "https://..."));
                gameCardsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
                gameCardsPanel.add(createGameCard("Diablo IV", "RPG", "/assets/games/diablo4.jpg", "2023", "8.3",
                                "Blizzard", "RPG", "https://..."));

                JScrollPane gameScrollPane = new JScrollPane(gameCardsPanel);
                gameScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                gameScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                gameScrollPane.setBorder(null);
                gameScrollPane.setBackground(Color.BLACK);
                gameScrollPane.getViewport().setBackground(Color.BLACK);
                int cardHeight = 230;
                gameScrollPane.setPreferredSize(new Dimension(0, cardHeight));
                gameScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, cardHeight));
                gameScrollPane.setMinimumSize(new Dimension(0, cardHeight));
                gameScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

                contentPanel.add(gameScrollPane);

                JPanel outerPanel = new JPanel(new GridBagLayout());
                outerPanel.setBackground(Color.BLACK);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.NORTH;
                gbc.weighty = 1.0;

                contentPanel.setMaximumSize(new Dimension(1300, Integer.MAX_VALUE));
                contentPanel.setPreferredSize(new Dimension(1300, contentPanel.getPreferredSize().height));

                outerPanel.add(contentPanel, gbc);

                JScrollPane scrollPane = new JScrollPane(outerPanel);
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
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
                card.setPreferredSize(new Dimension(200, 280));

                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setBackground(card.getBackground());
                textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

                JLabel nameLabel = new JLabel(name);
                nameLabel.setFont(titleFont);
                nameLabel.setForeground(Color.BLACK);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JTextArea descArea = new JTextArea(description);
                descArea.setFont(descFont);
                descArea.setForeground(Color.DARK_GRAY);
                descArea.setBackground(card.getBackground());
                descArea.setEditable(false);
                descArea.setWrapStyleWord(true);
                descArea.setLineWrap(true);
                descArea.setAlignmentX(Component.CENTER_ALIGNMENT);

                textPanel.add(nameLabel);
                textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                textPanel.add(descArea);

                card.add(textPanel, BorderLayout.CENTER);

                JButton bookingButton = new JButton("Booking Paket");
                bookingButton.setFont(new Font("Roboto", Font.BOLD, 14));
                bookingButton.setBackground(new Color(60, 90, 250));
                bookingButton.setForeground(Color.WHITE);
                bookingButton.setFocusPainted(false);
                bookingButton.setBorder(new EmptyBorder(10, 0, 10, 0));
                bookingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

                bookingButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                dispose();
                                new DaftarStation(name, duration, price).setVisible(true);
                        }
                });

                card.add(bookingButton, BorderLayout.SOUTH);
                return card;
        }

        private JPanel createGameCard(String name, String genre, String imgPath, String year, String rating,
                        String director, String description, String imdbUrl) {

                JPanel card = new JPanel();
                card.setLayout(new BorderLayout());
                card.setBackground(new Color(30, 30, 30));
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

                card.setPreferredSize(new Dimension(150, 220));
                card.setCursor(new Cursor(Cursor.HAND_CURSOR));

                try {
                        ImageIcon icon = new ImageIcon(getClass().getResource(imgPath));
                        Image img = icon.getImage().getScaledInstance(140, 170, Image.SCALE_SMOOTH);
                        JLabel imgLabel = new JLabel(new ImageIcon(img));
                        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        card.add(imgLabel, BorderLayout.CENTER);
                } catch (Exception e) {
                        JLabel placeholder = new JLabel("Image not found", SwingConstants.CENTER);
                        placeholder.setForeground(Color.WHITE);
                        placeholder.setPreferredSize(new Dimension(140, 170));
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