package PemesananTiket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class VotingClient {
    public static void main(String[] args) {
        // Ganti IP ini sesuai IP server kamu yang tampil di terminal
        String serverIP = "10.70.57.140"; // langsung hubungkan otomatis
        int serverPort = 1234;

        try (Socket socket = new Socket(serverIP, serverPort);
                Scanner input = new Scanner(socket.getInputStream());
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            while (input.hasNextLine()) {
                String pesan = input.nextLine();
                System.out.println(pesan);

                if (pesan.contains("Ketik nama kandidat")) {
                    String pilihan = scanner.nextLine();
                    output.println(pilihan);
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Gagal terhubung ke server " + serverIP + ":" + serverPort);
            System.out.println("Pastikan server aktif dan Wi-Fi sama!");
        }
    }
}
