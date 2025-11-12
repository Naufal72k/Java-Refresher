package PemesananTiket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * created on 11/12/25
 * by naufal (modifikasi dari ramads)
 */

class Candidate {
    String nama;
    int suara;

    Candidate(String nama) {
        this.nama = nama;
        this.suara = 0;
    }
}

public class MultiServerVoting {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    public static List<Candidate> kandidatList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM VOTING ONLINE ===");
        System.out.print("Masukkan jumlah kandidat: ");
        int jumlahKandidat = input.nextInt();
        input.nextLine(); // clear buffer

        for (int i = 0; i < jumlahKandidat; i++) {
            System.out.print("Nama kandidat ke-" + (i + 1) + ": ");
            String nama = input.nextLine();
            kandidatList.add(new Candidate(nama));
        }

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("\nServer voting berjalan di port " + PORT + "...");

            // Tambahkan baris ini:
            String ipServer = java.net.InetAddress.getLocalHost().getHostAddress();
            System.out.println("Server aktif di IP: " + ipServer);

        } catch (IOException ioEx) {
            System.out.println("\nTidak bisa men-set port!");
            System.exit(1);
        }

        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("\nClient baru terhubung.");
            VotingClientHandler handler = new VotingClientHandler(client, kandidatList);
            handler.start();
        }
    }

    // Metode untuk menampilkan hasil terkini
    public static synchronized void tampilkanHasil() {
        System.out.println("\n--- Hasil Voting Sementara ---");
        for (Candidate c : kandidatList) {
            System.out.println(c.nama + " : " + c.suara + " suara");
        }
        System.out.println("------------------------------");
    }
}

class VotingClientHandler extends Thread {
    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private List<Candidate> kandidatList;

    public VotingClientHandler(Socket socket, List<Candidate> kandidatList) {
        this.client = socket;
        this.kandidatList = kandidatList;

        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private synchronized boolean beriSuara(String kandidatNama) {
        for (Candidate c : kandidatList) {
            if (c.nama.equalsIgnoreCase(kandidatNama)) {
                c.suara++;
                return true;
            }
        }
        return false;
    }

    public void run() {
        try {
            output.println("Selamat datang di Sistem Voting Online!");
            output.println("Kandidat yang tersedia:");
            for (Candidate c : kandidatList) {
                output.println("- " + c.nama);
            }
            output.println("Ketik nama kandidat yang ingin Anda pilih:");

            String pilihan = input.nextLine().trim();

            if (beriSuara(pilihan)) {
                output.println("Terima kasih! Anda telah memilih " + pilihan);
                System.out.println("Client memilih: " + pilihan);
            } else {
                output.println("Kandidat tidak ditemukan. Suara tidak tercatat.");
                System.out.println("Client memilih kandidat tidak valid: " + pilihan);
            }

            MultiServerVoting.tampilkanHasil();

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.close();
                    System.out.println("Koneksi client ditutup.\n");
                }
            } catch (IOException ioEx) {
                System.out.println("Gagal menutup koneksi!");
            }
        }
    }
}
