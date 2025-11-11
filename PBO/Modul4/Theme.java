import java.awt.Color;
import java.awt.Font;

// Kelas untuk menyimpan konstanta styling (warna dan font)
// [cite: 242-261]
public class Theme {
    
    // Warna
    public static final Color COLOR_BACKGROUND = new Color(30, 30, 30); // Hitam gelap
    public static final Color COLOR_PRIMARY = new Color(245, 171, 0); // Kuning/Gold
    public static final Color COLOR_TEXT = Color.WHITE;
    public static final Color COLOR_CORRECT = new Color(76, 175, 80); // Hijau [cite: 95]
    public static final Color COLOR_WRONG = new Color(244, 67, 54); // Merah [cite: 102]
    public static final Color COLOR_INPUT_BG = new Color(50, 50, 50);

    // Font
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 28);
    public static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 18);
    public static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 16);
    public static final Font FONT_INPUT = new Font("Monospaced", Font.PLAIN, 20);
    public static final Font FONT_TIMER = new Font("Monospaced", Font.BOLD, 24);
}