package TestDoang;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleChatServer {
	ArrayList clientOutputStream;

	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;

		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // tutup constructor

		public void run() {
			String pesan;
			try {
				while ((pesan = reader.readLine()) != null) {
					System.out.println("Baca " + pesan);
					tellEveryone(pesan);
				} // tutup while
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // tutup run
	} // tutup inner class

	public static void main(String[] args) {
		new SimpleChatServer().go();
	}

	public void go() {
		clientOutputStream = new ArrayList();
		try {
			ServerSocket serverSock = new ServerSocket(5000);

			while (true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStream.add(writer);

				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("Sudah terkoneksi");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // tutup go

	public void tellEveryone(String pesan) {
		Iterator it = clientOutputStream.iterator();
		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(pesan);
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
