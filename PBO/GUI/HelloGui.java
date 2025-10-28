import java.lang.classfile.Label;

import javax.swing.*;

public class HelloGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("contoh GUI");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("masukan input");
        JButton button = new JButton("pencet saya");

        frame.add(label);
        frame.add(button);
        button.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setTitle("hello");
            dialog.setSize(200, 200);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });
        frame.setVisible(true);

    }
}
