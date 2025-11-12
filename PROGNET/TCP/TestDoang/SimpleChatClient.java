package TestDoang;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClient {
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;

	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
	}

	public void go() {
		JFrame frame = new JFrame("Simple Client Chat");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15, 30);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Kirim");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		setUpNetworking();

		// membuat thread baru menggunakan class yg dibuat sebagai Runnable utk thread
		// (IncomingReader)
		// thread bertugas untuk membaca pesan dari stream socket server, menampilkannya
		// di text area client
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	} // tutup go

	// mensetup koneksi dg server, menggunakan socket utk input dan output stream.
	private void setUpNetworking() {
		try {
			sock = new Socket("localhost", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Koneksi berhasil dibuat");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // tutup setUpNetworking

	// ketika mengklik tombol kirim, method ini mengirim isi text field ke server
	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(outgoing.getText());
				writer.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();

		}

	} // tutup inner class

	public class IncomingReader implements Runnable {
		public void run() {
			String pesan;
			try {
				while ((pesan = reader.readLine()) != null) {
					System.out.println("Baca " + pesan);
					incoming.append(pesan + "\n");

				} // tutup while
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}// tutup run
	} // tutup inner class
} // tutup outer class
