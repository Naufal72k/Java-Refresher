import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimulasiKlien {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 6789;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SELAMAT DATANG DI BIOSKOP ADIAKSA =====");
            System.out.println("Pilih Mode Simulasi:");
            System.out.println("  1. Pesan 1 Kursi");
            System.out.println("  2. Simulasi Rebutan Kursi");
            System.out.println("  3. Keluar");
            System.out.print("Pilihan Anda: ");

            int menuPilihan;
            try {
                menuPilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, silakan masukkan angka.");
                continue;
            }

            if (menuPilihan == 1) {
                jalankanModeInteraktif(scanner);
                Thread.sleep(500);
            } else if (menuPilihan == 2) {
                jalankanModeStressTest(scanner);
                Thread.sleep(3000);
            } else if (menuPilihan == 3) {
                System.out.println("Terima kasih, simulasi selesai.");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }

    private static void jalankanModeInteraktif(Scanner scanner) {
        System.out.println("\n--- Mode: Pesan 1 Kursi ---");
        System.out.print("Masukkan nama Anda: ");
        String nama = scanner.nextLine();

        System.out.print("Pilih Mode Pemesanan [1] UNSAFE (Rawan Error) [2] SAFE (Aman): ");
        String mode = "SAFE";
        try {
            int modePilihan = Integer.parseInt(scanner.nextLine());
            if (modePilihan == 1) {
                mode = "UNSAFE";
            }
        } catch (NumberFormatException e) {
            System.out.println("Input mode tidak valid, menggunakan SAFE.");
        }

        System.out.print("Masukkan Nomor Kursi yang diinginkan (Contoh: 0-9): ");
        String kursi;
        try {
            int seatNum = Integer.parseInt(scanner.nextLine());
            kursi = String.valueOf(seatNum);
        } catch (NumberFormatException e) {
            System.out.println("Input kursi tidak valid, membatalkan.");
            return;
        }

        String request = mode + ":" + kursi;
        System.out.println("Mengirim request: " + nama + " memesan kursi " + kursi + " (Mode: " + mode + ")");
        new Thread(new ClientTask(nama, request)).start();
    }

    private static void jalankanModeStressTest(Scanner scanner) {
        System.out.println("\n--- Mode: Simulasi Rebutan Kursi ---");
        System.out.print("Pilih Mode Pemesanan [1] UNSAFE (Rawan Error) [2] SAFE (Aman): ");
        String mode = "SAFE";
        try {
            int modePilihan = Integer.parseInt(scanner.nextLine());
            if (modePilihan == 1) {
                mode = "UNSAFE";
            }
        } catch (NumberFormatException e) {
            System.out.println("Input mode tidak valid, menggunakan SAFE.");
        }

        int kursi;
        try {
            System.out.print("Nomor Kursi yang akan diperebutkan (0-9): ");
            kursi = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input kursi tidak valid, membatalkan.");
            return;
        }

        int jumlahUser;
        try {
            System.out.print("Jumlah user (thread) yang akan berebut: ");
            jumlahUser = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input jumlah tidak valid, membatalkan.");
            return;
        }

        System.out.println(
                "--- MEMULAI " + jumlahUser + " USER UNTUK MEREBUTKAN KURSI " + kursi + " (Mode: " + mode + ") ---");
        for (int i = 1; i <= jumlahUser; i++) {
            String clientName = "User-" + i;
            String request = mode + ":" + kursi;
            new Thread(new ClientTask(clientName, request)).start();
        }
    }

    static class ClientTask implements Runnable {
        private String clientName;
        private String request;

        public ClientTask(String clientName, String request) {
            this.clientName = clientName;
            this.request = request;
        }

        @Override
        public void run() {
            try (
                    Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println(clientName);
                out.println(request);

                String response = in.readLine();
                System.out.println("   [Respons Server untuk " + clientName + "]: " + response);

            } catch (IOException e) {
                System.err.println("   [ERROR] Client " + clientName + ": " + e.getMessage());
            }
        }
    }
}