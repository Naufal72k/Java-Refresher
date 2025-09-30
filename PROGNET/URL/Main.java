
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;

public class Main extends JFrame {

    private JTextField kolomURL;
    private JButton tombolSearch;
    private JButton tombolSimpan;
    private JLabel labelGambar;
    private BufferedImage muatGambar;

    public Main() {
        setTitle("🔎 Image Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Color warnaAbu = new Color(245, 247, 250);
        Color warnaGelap = new Color(33, 33, 33);
        Color warnaPutih = new Color(255, 255, 255);
        Color warnaTeks = new Color(50, 50, 55);

        JPanel panel1 = new JPanel();
        panel1.setBackground(warnaPutih);
        panel1.setLayout(new BorderLayout(20, 20));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelAtas = new JPanel();
        panelAtas.setBackground(warnaPutih);
        panelAtas.setLayout(new BorderLayout(10, 0));

        kolomURL = new JTextField(40);
        kolomURL.setText("https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png");
        kolomURL.setBackground(warnaPutih);
        kolomURL.setForeground(warnaTeks);
        kolomURL.setCaretColor(warnaTeks);
        kolomURL.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(warnaGelap, 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        kolomURL.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        tombolSearch = new JButton();
        tombolSearch.setText("Search");
        tombolSearch.setBackground(new Color(59, 130, 246));
        tombolSearch.setForeground(Color.WHITE);
        tombolSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tombolSearch.setFocusPainted(false);
        tombolSearch.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));

        panelAtas.add(kolomURL, BorderLayout.CENTER);
        panelAtas.add(tombolSearch, BorderLayout.EAST);

        panel1.add(panelAtas, BorderLayout.NORTH);

        labelGambar = new JLabel("~ Tempat Gambar Muncul ~", SwingConstants.CENTER);
        labelGambar.setForeground(warnaTeks);
        labelGambar.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelGambar.setOpaque(true);
        labelGambar.setBackground(warnaPutih);
        labelGambar.setPreferredSize(new Dimension(1000, 600));
        labelGambar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(warnaGelap, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panel1.add(labelGambar, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel();
        panelBawah.setBackground(warnaPutih);
        panelBawah.setLayout(new FlowLayout(FlowLayout.CENTER));

        tombolSimpan = new JButton();
        tombolSimpan.setText("💾 Simpan Gambar");
        tombolSimpan.setBackground(new Color(16, 185, 129));
        tombolSimpan.setForeground(Color.WHITE);
        tombolSimpan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tombolSimpan.setFocusPainted(false);
        tombolSimpan.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        tombolSimpan.setEnabled(false);

        panelBawah.add(tombolSimpan);
        panel1.add(panelBawah, BorderLayout.SOUTH);

        JPanel container = new JPanel();
        container.setBackground(warnaAbu);
        container.setLayout(new GridBagLayout());
        container.add(panel1);

        setContentPane(container);

        tombolSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String alamatGambar = kolomURL.getText();
                    URL url = new URL(alamatGambar);
                    URLConnection koneksi = url.openConnection();
                    koneksi.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream dataGambar = koneksi.getInputStream();

                    muatGambar = ImageIO.read(dataGambar);
                    dataGambar.close();

                    if (muatGambar != null) {
                        labelGambar.setIcon(new ImageIcon(muatGambar));
                        labelGambar.setText("");
                        labelGambar.revalidate();
                        tombolSimpan.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Format gambar tidak didukung.\nCoba pakai JPG atau PNG.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ada masalah: " + ex.getMessage());
                }
            }
        });

        tombolSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (muatGambar != null) {
                    JFileChooser pilihFile = new JFileChooser();
                    pilihFile.setDialogTitle("Simpan Gambar");
                    int pilihanUser = pilihFile.showSaveDialog(null);

                    if (pilihanUser == JFileChooser.APPROVE_OPTION) {
                        File fileSimpan = pilihFile.getSelectedFile();
                        try {
                            ImageIO.write(muatGambar, "png", fileSimpan);
                            JOptionPane.showMessageDialog(null, "Gambar berhasil disimpan!");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Gagal menyimpan gambar: " + ex.getMessage());
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}