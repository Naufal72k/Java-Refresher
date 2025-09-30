
import java.net.*;
import java.io.*;

public class ReadWebsite {
    public static void main(String[] args) {
        String nextLine;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;

        try {
            url = new URL("https://www.wikipedia.org/");
            urlConn = url.openConnection();

            // 👉 Tambahkan identitas biar dianggap user (browser)
            urlConn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                            + "AppleWebKit/537.36 (KHTML, like Gecko) "
                            + "Chrome/120.0.0.0 Safari/537.36");

            urlConn.setRequestProperty("Accept", "text/html");
            urlConn.setRequestProperty("Accept-Language", "en-US,en;q=0.9");

            inStream = new InputStreamReader(urlConn.getInputStream(), "UTF8");
            buff = new BufferedReader(inStream);

            while ((nextLine = buff.readLine()) != null) {
                System.out.println(nextLine);
            }
        } catch (MalformedURLException e) {
            System.out.println("cek kembali URl" + e.toString());
        } catch (IOException e1) {
            System.out.println("tidak dapat membaca dari internet: " + e1.getMessage());
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                    if (buff != null)
                        buff.close();
                } catch (IOException e1) {
                    System.out.println("tidak dapat menutup stream: " + e1.getMessage());
                }
            }
        }
    }
}
