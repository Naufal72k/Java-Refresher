import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class BelajarPanel {
    public static void main(String[] args) {
        new BelajarPanel().MainGui();
    }

    private void MainGui() {
        JFrame frameUtama = new JFrame("Buku");
        frameUtama.setSize(800, 600);
        frameUtama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameUtama.setMinimumSize(new Dimension(800, 600));
        frameUtama.setLocationRelativeTo(null);
        frameUtama.setVisible(true);

        JPanel panelUtama = new JPanel();
        panelUtama.setBackground(Color.BLACK);

        panelUtama.setLayout(null);
        JLabel labelJudul = new JLabel("Perpustakaan");
        labelJudul.setForeground(Color.WHITE);
        labelJudul.setBounds(350, 50, 200, 30);
        ImageIcon img = new ImageIcon(getClass().getResource("/image.png"));
        JLabel image = new JLabel(img);

        // image.setBounds(300, 100, 300, 300);

        int heightSize = frameUtama.getHeight() / 2;
        int widhtSize = frameUtama.getWidth() / 2;
        image.setBounds(heightSize, widhtSize, 100, 100);

        panelUtama.add(image);

        frameUtama.setContentPane(panelUtama);
        frameUtama.add(panelUtama);
    }
}
