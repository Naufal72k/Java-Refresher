import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioskopServer {

    // Shared resource: Daftar kursi
    private static final int TOTAL_SEATS = 10;
    private static boolean[] seats = new boolean[TOTAL_SEATS];
    private static final int PORT = 6789;

    public static void main(String[] args) throws IOException {
        System.out.println("===================================");
        System.out.println("Server Bioskop Adiaksa Dimulai...");
        System.out.println("Mendengarkan di port " + PORT);
        System.out.println("Total Kursi Tersedia: " + TOTAL_SEATS);
        System.out.println("===================================");

        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String clientName = in.readLine();
                String request = in.readLine();

                String[] parts = request.split(":");
                String mode = parts[0];
                int seatNumber = Integer.parseInt(parts[1]);

                System.out.println("[Request Diterima] " + clientName + " ingin memesan kursi " + seatNumber
                        + " (Mode: " + mode + ")");

                if ("UNSAFE".equals(mode)) {
                    bookSeatUnsafe(clientName, seatNumber, out);
                } else if ("SAFE".equals(mode)) {
                    bookSeatSafe(clientName, seatNumber, out);
                } else {
                    out.println("GAGAL: Mode tidak dikenal");
                }

            } catch (IOException | InterruptedException e) {
                System.err.println("Error pada client handler: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    // Abaikan
                }
            }
        }

        /**
         * 🚨 METODE BERBAHAYA: Rawan Race Condition 🚨
         */
        private void bookSeatUnsafe(String clientName, int seatNumber, PrintWriter out) throws InterruptedException {
            if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
                out.println("GAGAL: Nomor kursi tidak valid (0-" + (TOTAL_SEATS - 1) + ")");
                return;
            }

            // 1. CEK
            if (!seats[seatNumber]) {
                System.out.println("   (UNSAFE) " + clientName + " menemukan kursi " + seatNumber + " kosong.");
                // 2. SIMULASI DELAY (Celah kritis)
                Thread.sleep(150);
                // 3. UBAH
                seats[seatNumber] = true;
                System.out.println("   (UNSAFE) " + clientName + " >> BERHASIL << memesan kursi " + seatNumber);
                out.println("BERHASIL: Anda mendapatkan kursi " + seatNumber);
            } else {
                System.out.println("   (UNSAFE) " + clientName + " GAGAL, kursi " + seatNumber + " sudah terisi.");
                out.println("GAGAL: Maaf, kursi " + seatNumber + " sudah terisi");
            }
        }

        /**
         * ✅ METODE AMAN: Menggunakan Sinkronisasi ✅
         */
        private void bookSeatSafe(String clientName, int seatNumber, PrintWriter out) throws InterruptedException {
            if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
                out.println("GAGAL: Nomor kursi tidak valid (0-" + (TOTAL_SEATS - 1) + ")");
                return;
            }

            // `synchronized` block mengunci objek 'seats'
            synchronized (seats) {
                // 1. CEK
                if (!seats[seatNumber]) {
                    System.out.println("   (SAFE) " + clientName + " menemukan kursi " + seatNumber + " kosong.");
                    // 2. SIMULASI DELAY
                    Thread.sleep(150);
                    // 3. UBAH
                    seats[seatNumber] = true;
                    System.out.println("   (SAFE) " + clientName + " >> BERHASIL << memesan kursi " + seatNumber);
                    out.println("BERHASIL: Anda mendapatkan kursi " + seatNumber);
                } else {
                    System.out.println("   (SAFE) " + clientName + " GAGAL, kursi " + seatNumber + " sudah terisi.");
                    out.println("GAGAL: Maaf, kursi " + seatNumber + " sudah terisi");
                }
            }
        }
    }
}