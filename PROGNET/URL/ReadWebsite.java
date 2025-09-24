package URL;

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
            inStream = new InputStreamReader(urlConn.getInputStream(), "UTF8");
            buff = new BufferedReader(inStream);
            while ((true)) {
                nextLine = buff.readLine();
                if (nextLine != null) {
                    System.out.println(nextLine);
                } else {
                    break;
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("cek kembali URl" + e.toString());
        } catch (IOException e1) {
            System.out.println("tidak dapat membaca dari internet" + e1.getMessage());

        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                    buff.close();
                } catch (IOException e1) {
                    System.out.println("tidak dapat menutup stream" + e1.getMessage());
                }
            }
        }
    }
}
