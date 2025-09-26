package URL;

import java.io.*;
import java.net.*;

public class DownloadFile {

    public static void main(String args[]) {
        InputStream in = null;
        FileOutputStream fOut = null;
        try {
            URL remoteFile = new URL(
                    "http://dl.vafamusic.com/Full%20Album/bts/320/BTS-Boy-WithLuv%20%28VafaMusic%29.mp3");
            URLConnection fileStream = remoteFile.openConnection();
            // membuka input dan output stream
            fOut = new FileOutputStream("BTS1.mp3");
            in = fileStream.getInputStream();

            // menyimpan file
            int data;
            while ((data = in.read()) != -1) {
                fOut.write(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("File telah berhasil didownload");
            try {
                in.close();
                fOut.flush();
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
