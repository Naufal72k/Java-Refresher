package PemesananTiket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * created on 11/17/24 22:13
 * by ramads
 */

class Ticket {
    Ticket(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }

    String nama = "";
    int jumlah = 0;
}

public class MultiServerWarTicket {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    public static Ticket ticket;

    public static void main(String[] args) throws IOException {
        Scanner inputTicket = new Scanner(System.in);
        System.out.println("Nama Tiket: ");
        String namaTiket = inputTicket.nextLine();

        System.out.println("Jumlah Tiket: ");
        int jmlTiket = inputTicket.nextInt();
        ticket = new Ticket(namaTiket, jmlTiket);

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ioEx) {
            System.out.println("\nTidak bisa men-set port!");
            System.exit(1);
        }

        do {
            // menunggu client
            Socket client = serverSocket.accept();
            System.out.println("\nClient baru diterima\n");

            // Membuat sebuah thread untuk menangani komunikasi dengan
            // client ini dan melewatkan constructor untuk thread ini
            // sebuah reference untuk socket yang relevan
            WarTicketClientHandler handler = new WarTicketClientHandler(client, ticket);
            handler.start();// Seperti biasa, method memanggil run .
        } while (true);
    }
}

class WarTicketClientHandler extends Thread {
    private Socket client;
    private Scanner input;
    private PrintWriter output;
    private Ticket ticket;

    public WarTicketClientHandler(Socket socket, Ticket ticket) {
        // Set up reference ke socket yang terkait
        this.ticket = ticket;
        client = socket;
        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private boolean pesanTiket(int jumlahPesan) {
        if (ticket.jumlah >= jumlahPesan) {
            ticket.jumlah = ticket.jumlah - jumlahPesan;
            return true;
        }
        return false;
    }

    public void run() {
        String received;
        // menerima pesan dari client melalui
        // input stream socket
        received = input.nextLine();
        String[] infoPesan = received.split("pesan:");
        String klien = infoPesan[0];
        int jumlah = Integer.parseInt(infoPesan[1]);

        if (pesanTiket(jumlah)) {
            System.out.println(klien + " berhasil pesan " + jumlah + " tiket " + ticket.nama);
            output.println("Selamat tiket " + ticket.nama + " berhasil dipesan!");
        } else {
            System.out.println(klien + " gagal pesan " + jumlah + " tiket " + ticket.nama);
            output.println("Maaf tiket " + ticket.nama + " sudah habis!");
        }

        try {
            if (client != null) {
                System.out.println(
                        "Closing down connection");
                client.close();
            }
        } catch (IOException ioEx) {
            System.out.println("Unable to disconnect!");
        }
    }
}
