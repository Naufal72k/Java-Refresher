package URL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;

public class ImageViewerFromURL extends JFrame {

    private JTextField urlField;
    private JButton loadButton, saveButton;
    private JLabel imageLabel;
    private BufferedImage loadedImage;

    public ImageViewerFromURL() {
        setTitle("Image Viewer from URL");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel atas: input URL + tombol Load
        JPanel topPanel = new JPanel(new BorderLayout());
        urlField = new JTextField(
                "https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png");
        loadButton = new JButton("Load");
        topPanel.add(urlField, BorderLayout.CENTER);
        topPanel.add(loadButton, BorderLayout.EAST);

        // Panel bawah: tombol Save
        JPanel bottomPanel = new JPanel();
        saveButton = new JButton("Save Image");
        saveButton.setEnabled(false); // default nonaktif
        bottomPanel.add(saveButton);

        // Label untuk menampilkan gambar
        imageLabel = new JLabel("Image akan muncul di sini", SwingConstants.CENTER);

        // Tambahkan ke frame
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(imageLabel), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action tombol Load
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String imageUrl = urlField.getText();
                    URL url = new URL(imageUrl);

                    // Buka koneksi dengan User-Agent
                    URLConnection conn = url.openConnection();
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream in = conn.getInputStream();

                    // Coba baca gambar
                    loadedImage = ImageIO.read(in);
                    in.close();

                    if (loadedImage != null) {
                        ImageIcon icon = new ImageIcon(loadedImage);
                        imageLabel.setIcon(icon);
                        imageLabel.setText("");
                        saveButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Format gambar tidak didukung.\nCoba gunakan JPG/PNG.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Action tombol Save
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loadedImage != null) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Simpan gambar");
                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        try {
                            // default simpan ke PNG
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
            new ImageViewerFromURL().setVisible(true);
        });
    }
}
