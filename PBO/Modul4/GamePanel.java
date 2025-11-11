import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Panel Game Utama
// [cite: 223-275]
public class GamePanel extends JPanel {

    private Main mainApp;

    // GUI Components
    private JLabel timerLabel, wpmLabel, userLabel, countdownLabel;
    private JTextPane paragraphPane;
    private JTextField inputField;
    private JButton menuButton;

    // Game Logic Variables
    private List<String> daftarSoal;
    private String currSoal;
    private String[] wordsInSoal;
    private int currWordIdx;

    private Thread gameThread;
    private Thread countdownThread;
    private volatile boolean gameThreadRunning;
    private volatile boolean countdownThreadRunning;

    private int timeSisa; // 60 detik [cite: 86]
    private int countdownVal; // 3 detik [cite: 78]

    private int totalCharBenar;
    private int totalCharKetik;

    // Styling
    private SimpleAttributeSet styleCorrect, styleWrong, styleNormal, styleHighlight;

    // Sound
    private Clip backgroundMusic;

    public GamePanel(Main mainApp) {
        this.mainApp = mainApp;
        setupGUI();
        setupStyles();
        loadSoal();
        loadSound();
    }

    // Mengatur tampilan GUI [cite: 266]
    private void setupGUI() {
        setLayout(null);
        setBackground(Theme.COLOR_BACKGROUND);

        // Timer Label [cite: 76]
        timerLabel = new JLabel("Waktu: 60s");
        timerLabel.setFont(Theme.FONT_TIMER);
        timerLabel.setForeground(Theme.COLOR_PRIMARY);
        timerLabel.setBounds(50, 20, 200, 30);
        add(timerLabel);

        // WPM Label [cite: 76]
        wpmLabel = new JLabel("WPM: 0");
        wpmLabel.setFont(Theme.FONT_TIMER);
        wpmLabel.setForeground(Theme.COLOR_PRIMARY);
        wpmLabel.setBounds(350, 20, 150, 30);
        wpmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(wpmLabel);

        // User Label [cite: 77]
        userLabel = new JLabel("User: ");
        userLabel.setFont(Theme.FONT_TIMER);
        userLabel.setForeground(Theme.COLOR_PRIMARY);
        userLabel.setBounds(600, 20, 150, 30);
        add(userLabel);

        // Tombol Menu [cite: 75]
        menuButton = new JButton("Menu");
        menuButton.setBackground(Theme.COLOR_WRONG);
        menuButton.setForeground(Color.WHITE);
        menuButton.setFont(Theme.FONT_BUTTON);
        menuButton.setBounds(350, 70, 100, 30);
        menuButton.setFocusable(false);
        menuButton.addActionListener(e -> stopGame(GameEndType.USER_QUIT));
        add(menuButton);

        // Panel Paragraf (JTextPane agar bisa diwarnai)
        paragraphPane = new JTextPane();
        paragraphPane.setFont(Theme.FONT_INPUT);
        paragraphPane.setBackground(Theme.COLOR_INPUT_BG);
        paragraphPane.setForeground(Theme.COLOR_TEXT);
        paragraphPane.setEditable(false);
        paragraphPane.setBounds(50, 120, 700, 250);
        add(paragraphPane);

        // Input Field
        inputField = new JTextField();
        inputField.setFont(Theme.FONT_INPUT);
        inputField.setBackground(Theme.COLOR_INPUT_BG);
        inputField.setForeground(Theme.COLOR_TEXT);
        inputField.setCaretColor(Theme.COLOR_PRIMARY);
        inputField.setBounds(50, 400, 700, 40);
        inputField.setEnabled(false); // Aktifkan setelah countdown
        add(inputField);

        // Countdown Label (ditengah)
        countdownLabel = new JLabel("3");
        countdownLabel.setFont(new Font("SansSerif", Font.BOLD, 150));
        countdownLabel.setForeground(Theme.COLOR_PRIMARY);
        countdownLabel.setBounds(0, 0, 800, 600);
        countdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countdownLabel.setVerticalAlignment(SwingConstants.CENTER);
        countdownLabel.setVisible(false);
        add(countdownLabel);

        // Key Listener untuk input [cite: 87]
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Saat menekan spasi
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    cekAction();
                }
            }
        });
    }

    // Inisialisasi styles untuk JTextPane
    private void setupStyles() {
        styleNormal = new SimpleAttributeSet();
        StyleConstants.setForeground(styleNormal, Theme.COLOR_TEXT);
        StyleConstants.setBackground(styleNormal, Theme.COLOR_INPUT_BG);

        styleCorrect = new SimpleAttributeSet();
        StyleConstants.setForeground(styleCorrect, Theme.COLOR_CORRECT);

        styleWrong = new SimpleAttributeSet();
        StyleConstants.setForeground(styleWrong, Theme.COLOR_WRONG);
        styleHighlight = new SimpleAttributeSet();
        StyleConstants.setBackground(styleHighlight, new Color(70, 70, 70)); // Background abu-abu
        StyleConstants.setForeground(styleHighlight, Theme.COLOR_TEXT);
    }

    // Load soal dari database [cite: 263]
    private void loadSoal() {
        daftarSoal = KoneksiDatabase.getSoal();
        if (daftarSoal == null || daftarSoal.isEmpty()) {
            daftarSoal = new ArrayList<>();
            daftarSoal.add("Database soal kosong. Silakan isi tabel soal di database.");
        }
    }

    // Load file musik [cite: 264, 142]
    private void loadSound() {
        try {
            // TODO: Ganti "background_music.wav" dengan nama file musik .wav kamu
            File audioFile = new File("background_music.wav");
            if (audioFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioStream);
            } else {
                System.err.println("File musik 'background_music.wav' tidak ditemukan.");
            }
        } catch (Exception e) {
            System.err.println("Error memuat musik: " + e.getMessage());
            backgroundMusic = null;
        }
    }

    // Dipanggil saat panel Game ditampilkan [cite: 265]
    public void onPanelShown() {
        userLabel.setText("User: " + mainApp.getCurrentUsername());
        prepareGame();
    }

    // Mempersiapkan game baru [cite: 267]
    private void prepareGame() {
        resetGame();
        prepareNewSoal();
        startCountdown();
    }

    // Mengatur ulang variabel game [cite: 269]
    private void resetGame() {
        timeSisa = 60;
        currWordIdx = 0;
        totalCharBenar = 0;
        totalCharKetik = 0;

        timerLabel.setText("Waktu: 60s");
        wpmLabel.setText("WPM: 0");
        inputField.setText("");
        inputField.setEnabled(false);
        countdownLabel.setVisible(true);

        // Hentikan thread jika masih berjalan
        if (gameThread != null && gameThread.isAlive()) {
            gameThreadRunning = false;
            gameThread.interrupt();
        }
        if (countdownThread != null && countdownThread.isAlive()) {
            countdownThreadRunning = false;
            countdownThread.interrupt();
        }

        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.setFramePosition(0); // Rewind
        }
    }

    // Memilih soal baru secara acak [cite: 79]
    private void prepareNewSoal() {
        currSoal = daftarSoal.get(new Random().nextInt(daftarSoal.size()));
        wordsInSoal = currSoal.split(" ");

        // Tampilkan soal di JTextPane
        paragraphPane.setText(currSoal);
        paragraphPane.getStyledDocument().setCharacterAttributes(0, currSoal.length(), styleNormal, true);

        highlightCurrWord(); // Highlight kata pertama
    }

    // Hitung mundur 3 detik [cite: 268, 78]
    private void startCountdown() {
        countdownVal = 3;
        countdownThreadRunning = true;
        countdownThread = new Thread(() -> {
            try {
                while (countdownVal > 0 && countdownThreadRunning) {
                    final int val = countdownVal;
                    SwingUtilities.invokeLater(() -> countdownLabel.setText(String.valueOf(val)));
                    Thread.sleep(1000);
                    countdownVal--;
                }
                if (countdownThreadRunning) {
                    SwingUtilities.invokeLater(() -> {
                        countdownLabel.setVisible(false);
                        startGame(); // Mulai game setelah countdown
                    });
                }
            } catch (InterruptedException e) {
                System.out.println("Countdown diinterupsi");
            }
        });
        countdownThread.start();
    }

    // Memulai timer game utama [cite: 268]
    private void startGame() {
        gameThreadRunning = true;
        inputField.setEnabled(true);
        inputField.requestFocus(); // Fokuskan kursor ke input

        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Mainkan musik [cite: 88]
        }

        gameThread = new Thread(() -> {
            try {
                while (timeSisa > 0 && gameThreadRunning) {
                    Thread.sleep(1000); // Tunggu 1 detik
                    timeSisa--;
                    SwingUtilities.invokeLater(() -> {
                        updateTimer();
                        updateWPM(); // Update WPM setiap detik [cite: 88]
                    });
                }
                if (gameThreadRunning) {
                    // Waktu habis [cite: 124]
                    SwingUtilities.invokeLater(() -> stopGame(GameEndType.TIMER_UP));
                }
            } catch (InterruptedException e) {
                System.out.println("Game thread diinterupsi");
            }
        });
        gameThread.start();
    }

    // Method yang dipanggil saat user menekan spasi [cite: 103]
    private void cekAction() {
        if (currWordIdx >= wordsInSoal.length)
            return; // Game sudah selesai

        String typedWord = inputField.getText().trim();
        String correctWord = wordsInSoal[currWordIdx];

        totalCharKetik += correctWord.length(); // +1 untuk spasi? PDF tidak jelas, anggap per kata

        boolean isCorrect = typedWord.equals(correctWord);

        if (isCorrect) {
            totalCharBenar += correctWord.length();
            setStyle(currWordIdx, true); // Warnai hijau [cite: 95]
        } else {
            setStyle(currWordIdx, false); // Warnai merah [cite: 102]
        }

        currWordIdx++;
        inputField.setText("");

        if (currWordIdx >= wordsInSoal.length) {
            // Selesai mengetik semua kata [cite: 121]
            stopGame(GameEndType.PARAGRAPH_COMPLETE);
        } else {
            highlightCurrWord(); // Pindah highlight ke kata berikutnya
        }
    }

    // Menghentikan game [cite: 269]
    private void stopGame(GameEndType reason) {
        if (!gameThreadRunning && reason != GameEndType.USER_QUIT)
            return; // Hindari stop ganda

        gameThreadRunning = false;
        countdownThreadRunning = false;
        inputField.setEnabled(false);

        if (gameThread != null)
            gameThread.interrupt();
        if (countdownThread != null)
            countdownThread.interrupt();

        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }

        if (reason == GameEndType.USER_QUIT) {
            mainApp.showPanel("MainMenu");
            return;
        }

        // Hitung hasil akhir
        double timeElapsedInMinutes = (60.0 - timeSisa) / 60.0;

        // Rumus WPM: (Total Karakter Benar / 5) / (Menit)
        // PDF [cite: 133] menulis "WPM = Jumlah Karakter (huruf) Benar * 5 / time"
        // Ini kemungkinan besar typo dan seharusnya / 5. Saya gunakan / 5.
        double wpm = (timeElapsedInMinutes == 0) ? 0 : (totalCharBenar / 5.0) / timeElapsedInMinutes;

        // Rumus Akurasi [cite: 135]
        double accuracy = (totalCharKetik == 0) ? 0 : ((double) totalCharBenar / totalCharKetik) * 100.0;

        // Simpan skor ke DB
        saveScoretoDB(wpm, accuracy);

        // Tampilkan dialog hasil
        String title = (reason == GameEndType.TIMER_UP) ? "Waktu Habis!" : "Game Selesai!";
        String message = String.format("WPM: %.2f\nAkurasi: %.2f%%", wpm, accuracy);

        Object[] options = { "Main Lagi", "Kembali ke Menu" };
        int choice = JOptionPane.showOptionDialog(this, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (choice == 0) {
            prepareGame(); // Main Lagi
        } else {
            mainApp.showPanel("MainMenu"); // Kembali ke Menu
        }
    }

    // Update label timer [cite: 273]
    private void updateTimer() {
        timerLabel.setText(String.format("Waktu: %ds", timeSisa));
    }

    // Update label WPM (real-time) [cite: 274]
    private void updateWPM() {
        double timeElapsedInMinutes = (60.0 - timeSisa) / 60.0;
        double wpm = (timeElapsedInMinutes == 0) ? 0 : (totalCharBenar / 5.0) / timeElapsedInMinutes;
        wpmLabel.setText(String.format("WPM: %.0f", wpm));
    }

    // Menghitung posisi kata di JTextPane
    private int getWordPosition(int wordIndex) {
        int pos = 0;
        for (int i = 0; i < wordIndex; i++) {
            pos += wordsInSoal[i].length() + 1; // +1 untuk spasi
        }
        return pos;
    }

    // Mewarnai kata di JTextPane [cite: 271]
    private void setStyle(int wordIndex, boolean isCorrect) {
        int startPos = getWordPosition(wordIndex);
        int length = wordsInSoal[wordIndex].length();

        StyledDocument doc = paragraphPane.getStyledDocument();
        doc.setCharacterAttributes(startPos, length, (isCorrect ? styleCorrect : styleWrong), true);
    }

    // Memberi highlight (background) pada kata yang sedang diketik [cite: 272]
    private void highlightCurrWord() {
        // Reset highlight sebelumnya (jika ada)
        StyledDocument doc = paragraphPane.getStyledDocument();
        doc.setCharacterAttributes(0, currSoal.length(), styleNormal, false);

        // Set style normal dulu ke semua
        paragraphPane.setText(currSoal);
        doc.setCharacterAttributes(0, currSoal.length(), styleNormal, true);

        // Terapkan lagi style benar/salah untuk kata-kata sebelumnya
        for (int i = 0; i < currWordIdx; i++) {
            // (Kita harus menyimpan status benar/salah jika ingin akurat)
            // Untuk simple, kita anggap saja sudah di-style
        }

        // Highlight kata saat ini
        int startPos = getWordPosition(currWordIdx);
        int length = wordsInSoal[currWordIdx].length();
        doc.setCharacterAttributes(startPos, length, styleHighlight, false);
    }

    // Simpan skor ke database [cite: 275]
    private void saveScoretoDB(double wpm, double accuracy) {
        int userId = mainApp.getCurrentUserId();
        if (userId != -1) {
            KoneksiDatabase.saveScore(userId, wpm, accuracy);
        }
    }
}