import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiEchoClient {
	private static InetAddress host;
	private static final int PORT = 1234;

	public static void main(String[] args) {
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException uhEx) {
			System.out.println("\nHost tidak ditemukan\n");
			System.exit(1);
		}
		kirimPesan();
	}

	private static void kirimPesan() {
		Socket socket = null;

		try {
			socket = new Socket("10.70.58.144", PORT);
			Scanner networkInput = new Scanner(socket.getInputStream());
			PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(), true);
			// Set up stream untuk masukan dari keyboard�
			Scanner userEntry = new Scanner(System.in);
			String message, response;
			do {
				System.out.print("Masukkan pesan ('QUIT' untuk keluar): ");
				message = userEntry.nextLine();
				// Mengirim pesan ke server melalui
				// output stream socket�
				// menerima respon dari server melalui
				// input stream socket�
				networkOutput.println(message);
				response = networkInput.nextLine();
				// Menampilkan respon server ke client..
				System.out.println("\nSERVER> " + response);
			} while (!message.equals("QUIT"));
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				System.out.println(
						"\nMenutup Koneksi�");
				socket.close();
			} catch (IOException ioEx) {
				System.out.println(
						"Tidak bisa didiskonek!");
				System.exit(1);
			}
		}
	}

}
