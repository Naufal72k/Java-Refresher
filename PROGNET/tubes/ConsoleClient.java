import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ConsoleClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    public static void main(String[] args) {
        System.out.println("=== CLIENT E-VOTING TERMINAL ===");

        try (Socket socket = new Socket(HOST, PORT);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in)) {

            String serverMsg = in.readUTF();
            String[] parts = serverMsg.split("\\|");

            if (parts[0].equals("CLOSED")) {
                System.out.println("SERVER: " + parts[1]);
                return;
            }

            if (parts[0].equals("OPEN")) {
                String title = parts[1];
                String[] candidates = parts[2].split(",");

                System.out.println("Topik: " + title);
                System.out.println("Daftar Kandidat:");
                for (String c : candidates) {
                    System.out.println("- " + c);
                }

                System.out.print("\nMasukkan nama kandidat pilihan Anda: ");
                String myVote = scanner.nextLine();

                out.writeUTF("VOTE|" + myVote);

                String resultMsg = in.readUTF();
                System.out.println("STATUS: " + resultMsg.split("\\|")[1]);
            }

        } catch (IOException e) {
            System.out.println("Gagal terhubung ke server (Pastikan Server sudah jalan di Port " + PORT + ").");
        }
    }
}