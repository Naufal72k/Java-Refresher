import java.awt.Dimension;
import java.awt.Panel;

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
    }
}
