package PemesananTiket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * created on 11/17/24 22:11
 * by ramads
 */

public class MultiTicketClient {
    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        kirimPesan();
    }

    private static void kirimPesan() {
        Socket socket = null;

        try {
            socket = new Socket("10.70.1.38", PORT);
            Scanner networkInput = new Scanner(socket.getInputStream());
            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(), true);

            // Set up stream untuk masukan dari keyboard
            Scanner userEntry = new Scanner(System.in);
            String nama = "";
            int jumlah = 0;
            System.out.print("Masukkan nama Anda: ");
            nama = userEntry.nextLine();
            System.out.print("Mau pesan tiket berapa: ");
            jumlah = userEntry.nextInt();

            networkOutput.println(nama + " pesan:" + jumlah);
            String response = networkInput.nextLine();

            // Menampilkan respon server ke client..
            System.out.println("\nSERVER> " + response);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\nMenutup Koneksi");
                socket.close();
            } catch (IOException ioEx) {
                System.out.println("Tidak bisa didiskonek!");
                System.exit(1);
            }
        }
    }
}
