import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ConsoleServer {

    private static final int PORT = 8888;
    private static VotingSession currentSession = null;
    private static boolean serverRunning = true;

    public static void main(String[] args) {
        new Thread(ConsoleServer::startNetworkListener).start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SERVER E-VOTING (CONSOLE) ===");
        System.out.println("Port: " + PORT);
        System.out.println("Perintah: buat, stop, cek, keluar");

        while (serverRunning) {
            System.out.print("ADMIN> ");
            String cmd = scanner.nextLine();

            if (cmd.equalsIgnoreCase("buat")) {
                System.out.print("Judul Voting: ");
                String title = scanner.nextLine();
                System.out.print("Kandidat (pisahkan koma): ");
                String candStr = scanner.nextLine();
                currentSession = new VotingSession(title, candStr.split(","));
                System.out.println("Sesi dibuat: " + title);

            } else if (cmd.equalsIgnoreCase("stop")) {
                if (currentSession != null) {
                    currentSession.endSession();
                    System.out.println("Sesi diakhiri.");
                } else {
                    System.out.println("Belum ada sesi.");
                }

            } else if (cmd.equalsIgnoreCase("cek")) {
                if (currentSession != null) {
                    System.out.println("--- HASIL SEMENTARA ---");
                    currentSession.getResults().forEach((k, v) -> System.out.println(k + ": " + v));
                } else {
                    System.out.println("Belum ada sesi.");
                }

            } else if (cmd.equalsIgnoreCase("keluar")) {
                serverRunning = false;
                System.exit(0);
            }
        }
    }

    private static void startNetworkListener() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (serverRunning) {
                Socket client = serverSocket.accept();
                new Thread(new ClientHandler(client)).start();
            }
        } catch (IOException e) {
            if (serverRunning)
                e.printStackTrace();
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
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
                if (currentSession == null || !currentSession.isActive()) {
                    out.writeUTF("CLOSED|Maaf, tidak ada sesi voting aktif saat ini.");
                    return;
                }

                out.writeUTF("OPEN|" + currentSession.getTitle() + "|" + currentSession.getCandidatesStr());

                String response = in.readUTF();
                if (response.startsWith("VOTE|")) {
                    String choice = response.split("\\|")[1];
                    boolean success = currentSession.addVote(choice);
                    if (success) {
                        out.writeUTF("SUCCESS|Terima kasih, suara masuk.");
                    } else {
                        out.writeUTF("FAIL|Gagal, kandidat tidak valid atau sesi berakhir.");
                    }
                }

            } catch (IOException e) {
            }
        }
    }
}