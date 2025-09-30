package URL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;

public class ImageViewerFromURL_Sederhana extends JFrame {

    private JTextField urlField;
    private JButton loadButton;
    private JButton saveButton;
    private JLabel imageLabel;
    private BufferedImage loadedImage;

    public ImageViewerFromURL_Sederhana() {
        setTitle("🔎 Image Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen

        // 🎨 Palet warna lebih cerah
        Color bgLight = new Color(245, 247, 250);
        Color bgCard = new Color(255, 255, 255);
        Color bgInput = new Color(250, 250, 250);
        Color borderGray = new Color(200, 200, 200);
        Color textDark = new Color(50, 50, 55);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgCard);
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("🔎 Image Viewer", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(37, 99, 235)); // biru cerah

        JLabel subtitle = new JLabel("Paste URL gambar untuk melihat preview", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(textDark);

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headerPanel.setBackground(bgCard);
        headerPanel.add(title);
        headerPanel.add(subtitle);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        topPanel.setBackground(bgCard);

        urlField = new JTextField(40);
        urlField.setText("https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png");
        urlField.setBackground(bgInput);
        urlField.setForeground(textDark);
        urlField.setCaretColor(textDark);
        urlField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        urlField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        loadButton = new JButton("Load") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(0, 0, new Color(59, 130, 246), getWidth(), getHeight(),
                        new Color(96, 165, 250));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        loadButton.setContentAreaFilled(false);
        loadButton.setOpaque(false);
        loadButton.setForeground(Color.WHITE);
        loadButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loadButton.setFocusPainted(false);
        loadButton.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));

        topPanel.add(urlField, BorderLayout.CENTER);
        topPanel.add(loadButton, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        // ======================
        // Area Preview Gambar
        // ======================
        imageLabel = new JLabel("[ Preview gambar ]", SwingConstants.CENTER);
        imageLabel.setForeground(textDark);
        imageLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(bgInput);
        imageLabel.setPreferredSize(new Dimension(1000, 600));
        imageLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JScrollPane scrollPane = new JScrollPane(imageLabel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(bgInput);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(bgCard);

        saveButton = new JButton("💾 Save Image") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(0, 0, new Color(16, 185, 129), getWidth(), getHeight(),
                        new Color(5, 150, 105));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        saveButton.setContentAreaFilled(false);
        saveButton.setOpaque(false);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        saveButton.setEnabled(false);

        bottomPanel.add(saveButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(bgLight); // latar belakang terang
        wrapper.add(mainPanel);

        setContentPane(wrapper);

        // ======================
        // Logic tombol Load
        // ======================
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String imageUrl = urlField.getText();
                    URL url = new URL(imageUrl);
                    URLConnection conn = url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream in = conn.getInputStream();

                    loadedImage = ImageIO.read(in);
                    in.close();

                    if (loadedImage != null) {
                        imageLabel.setIcon(new ImageIcon(loadedImage));
                        imageLabel.setText("");
                        imageLabel.revalidate();
                        saveButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Format gambar tidak didukung.\nGunakan JPG atau PNG.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // ======================
        // Logic tombol Save
        // ======================
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loadedImage != null) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Simpan gambar");
                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        try {
                            ImageIO.write(loadedImage, "png", fileToSave);
                            JOptionPane.showMessageDialog(null, "Gambar berhasil disimpan!");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error menyimpan gambar: " + ex.getMessage());
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageViewerFromURL_Sederhana().setVisible(true);
        });
    }
}
