
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class FileDownloaderGUI extends JFrame {
    private JTextField urlField;
    private JTextField fileNameField;
    private JButton downloadButton;
    private JProgressBar progressBar;
    private JTextArea logArea;

    public FileDownloaderGUI() {
        setTitle("File Downloader");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        urlField = new JTextField(30);
        fileNameField = new JTextField(20);
        downloadButton = new JButton("Download");
        progressBar = new JProgressBar();
        logArea = new JTextArea(15, 40);

        logArea.setEditable(false);
        progressBar.setStringPainted(true);

        // Layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // URL panel
        JPanel urlPanel = new JPanel(new FlowLayout());
        urlPanel.add(new JLabel("URL: "));
        urlPanel.add(urlField);

        // Filename panel
        JPanel filePanel = new JPanel(new FlowLayout());
        filePanel.add(new JLabel("Save as: "));
        filePanel.add(fileNameField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(downloadButton);

        // Progress panel
        JPanel progressPanel = new JPanel(new FlowLayout());
        progressPanel.add(progressBar);

        // Add all panels
        mainPanel.add(urlPanel);
        mainPanel.add(filePanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(progressPanel);
        mainPanel.add(new JScrollPane(logArea));

        add(mainPanel);

        // Event handler
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                downloadFile();
            }
        });
    }

    private void downloadFile() {
        String url = urlField.getText().trim();
        String fileName = fileNameField.getText().trim();

        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan URL!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fileName.isEmpty()) {
            // Ambil nama file dari URL
            try {
                fileName = url.substring(url.lastIndexOf('/') + 1);
                if (fileName.isEmpty() || !fileName.contains(".")) {
                    fileName = "download.tmp";
                }
            } catch (Exception ex) {
                fileName = "download.tmp";
            }
        }

        // Set filename ke field
        fileNameField.setText(fileName);

        // Disable tombol saat download
        downloadButton.setEnabled(false);
        progressBar.setValue(0);
        logArea.setText("");

        // Download di thread terpisah
        new Thread(new DownloadTask(url, fileName)).start();
    }

    private class DownloadTask implements Runnable {
        private String url;
        private String fileName;

        public DownloadTask(String url, String fileName) {
            this.url = url;
            this.fileName = fileName;
        }

        public void run() {
            InputStream in = null;
            FileOutputStream fOut = null;

            try {
                appendLog("Memulai download dari: " + url);

                // Encode URL untuk menangani spasi dan karakter khusus
                String encodedUrl = encodeURL(url);
                appendLog("URL ter-encode: " + encodedUrl);

                URL remoteFile = new URL(encodedUrl);
                URLConnection connection = remoteFile.openConnection();

                // Set user agent dan properties lain untuk menghindari 403/400 error
                connection.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
                connection.setRequestProperty("Accept", "*/*");
                connection.setRequestProperty("Connection", "keep-alive");

                int fileSize = connection.getContentLength();
                appendLog("Ukuran file: " + (fileSize > 0 ? fileSize + " bytes" : "Tidak diketahui"));

                in = connection.getInputStream();
                fOut = new FileOutputStream(fileName);

                byte[] buffer = new byte[1024];
                int bytesRead;
                int totalBytes = 0;

                while ((bytesRead = in.read(buffer)) != -1) {
                    fOut.write(buffer, 0, bytesRead);
                    totalBytes += bytesRead;

                    if (fileSize > 0) {
                        final int progress = (int) ((totalBytes * 100L) / fileSize);
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                progressBar.setValue(progress);
                                progressBar.setString(progress + "%");
                            }
                        });
                    }
                }

                appendLog("Download selesai!");
                appendLog("File disimpan sebagai: " + fileName);
                appendLog("Total bytes: " + totalBytes);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar.setValue(100);
                        progressBar.setString("Selesai");
                    }
                });

            } catch (Exception ex) {
                appendLog("Error: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (fOut != null) {
                        fOut.flush();
                        fOut.close();
                    }
                } catch (IOException e) {
                    appendLog("Error menutup file: " + e.getMessage());
                }

                // Enable tombol kembali
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        downloadButton.setEnabled(true);
                    }
                });
            }
        }

        private void appendLog(final String message) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    logArea.append(message + "\n");
                    logArea.setCaretPosition(logArea.getDocument().getLength());
                }
            });
        }
    }

    // Method untuk encode URL dengan benar
    private String encodeURL(String url) {
        try {
            // Split URL menjadi bagian-bagian
            URL urlObj = new URL(url);
            String protocol = urlObj.getProtocol();
            String host = urlObj.getHost();
            int port = urlObj.getPort();
            String path = urlObj.getPath();
            String query = urlObj.getQuery();

            // Encode path
            String[] pathParts = path.split("/");
            StringBuilder encodedPath = new StringBuilder();
            for (String part : pathParts) {
                if (!part.isEmpty()) {
                    encodedPath.append("/").append(URLEncoder.encode(part, "UTF-8"));
                }
            }

            // Rebuild URL
            StringBuilder result = new StringBuilder();
            result.append(protocol).append("://").append(host);
            if (port != -1) {
                result.append(":").append(port);
            }
            result.append(encodedPath.toString());
            if (query != null) {
                result.append("?").append(query);
            }

            return result.toString();
        } catch (Exception e) {
            // Jika gagal encode, coba replace spasi dengan %20
            return url.replace(" ", "%20");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Pakai default look and feel
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileDownloaderGUI().setVisible(true);
            }
        });
    }
}