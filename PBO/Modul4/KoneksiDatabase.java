import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Kelas untuk koneksi dan operasi database
// [cite: 241-255]
public class KoneksiDatabase {

    // =========================================================================
    // TODO: ISI DETAIL KONEKSI DATABASE KAMU DI BAWAH INI
    // =========================================================================
    private static final String DB_NAME = "typing_dash"; // Nama database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root"; // Username XAMPP/MySQL kamu
    private static final String DB_PASS = ""; // Password XAMPP/MySQL kamu
    // =========================================================================

    // Method untuk mendapatkan koneksi [cite: 248]
    public static Connection getConnection() {
        try {
            // Load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Inisialisasi: Membuat tabel jika belum ada [cite: 167]
    public static void initialize() {
        String createUsers = "CREATE TABLE IF NOT EXISTS users (" +
                "user_id INT(11) NOT NULL AUTO_INCREMENT," +
                "username VARCHAR(50) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "PRIMARY KEY (user_id)" +
                ");";

        String createSoal = "CREATE TABLE IF NOT EXISTS soal (" +
                "soal_id INT(11) NOT NULL AUTO_INCREMENT," +
                "data TEXT NOT NULL," +
                "PRIMARY KEY (soal_id)" +
                ");";

        String createScores = "CREATE TABLE IF NOT EXISTS scores (" +
                "score_id INT(11) NOT NULL AUTO_INCREMENT," +
                "user_id INT(11) NOT NULL," +
                "wpm DOUBLE NOT NULL," +
                "accuracy DOUBLE NOT NULL," +
                "timestamp DATETIME NOT NULL," +
                "PRIMARY KEY (score_id)," +
                "FOREIGN KEY (user_id) REFERENCES users(user_id)" +
                ");";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            if (conn == null) {
                System.err.println("Tidak bisa inisialisasi database, koneksi null.");
                return;
            }
            stmt.execute(createUsers);
            stmt.execute(createSoal);
            stmt.execute(createScores);
            System.out.println("Inisialisasi database berhasil.");
        } catch (SQLException e) {
            System.err.println("Error saat inisialisasi tabel: " + e.getMessage());
        }
    }

    // Registrasi user baru [cite: 251]
    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password); // TODO: Idealnya password di-hash
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Error code 1062 = Duplicate entry (username unik) [cite: 37, 38]
            System.err.println("Error register: " + e.getMessage());
            return false;
        }
    }

    // Login user [cite: 252]
    public static int loginUser(String username, String password) {
        String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id"); // Login sukses, kembalikan user_id
                }
            }
        } catch (SQLException e) {
            System.err.println("Error login: " + e.getMessage());
        }
        return -1; // Login gagal
    }

    // Mengambil semua soal dari database [cite: 254]
    public static List<String> getSoal() {
        List<String> daftarSoal = new ArrayList<>();
        String sql = "SELECT data FROM soal";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                daftarSoal.add(rs.getString("data"));
            }
        } catch (SQLException e) {
            System.err.println("Error mengambil soal: " + e.getMessage());
        }
        return daftarSoal;
    }

    // Menyimpan skor game [cite: 253]
    public static boolean saveScore(int userId, double wpm, double accuracy) {
        String sql = "INSERT INTO scores (user_id, wpm, accuracy, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setDouble(2, wpm);
            pstmt.setDouble(3, accuracy);
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error menyimpan skor: " + e.getMessage());
            return false;
        }
    }

    // Mengambil top 10 leaderboard [cite: 255]
    public static List<Object[]> getLeaderboard() {
        List<Object[]> leaderboardData = new ArrayList<>();
        // Ambil top 10, diurutkan berdasarkan WPM [cite: 128]
        String sql = "SELECT u.username, s.wpm, s.accuracy, s.timestamp " +
                "FROM scores s " +
                "JOIN users u ON s.user_id = u.user_id " +
                "ORDER BY s.wpm DESC " +
                "LIMIT 10";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                leaderboardData.add(new Object[] {
                        rs.getString("username"),
                        rs.getDouble("wpm"),
                        rs.getDouble("accuracy"),
                        rs.getTimestamp("timestamp")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error mengambil leaderboard: " + e.getMessage());
        }
        return leaderboardData;
    }
}