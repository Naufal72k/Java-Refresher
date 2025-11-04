import javax.swing.*;
import java.awt.*;

public class Jurnal {
    public static void main(String[] args) {
        JFrame frameUtama = new JFrame();
        frameUtama.setSize(800, 800);
        frameUtama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameUtama.setMinimumSize(new Dimension(800, 600));
        JPanel panelAtas = new JPanel();
        // panelAtas.setLayout(new BorderLayout());

        JPanel setPanel = new JPanel();
        setPanel.setBackground(Color.BLUE);
        panelAtas.add(setPanel, BorderLayout.CENTER);

        frameUtama.setVisible(true);
    }
}
