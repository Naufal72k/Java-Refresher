import java.net.*;

import java.io.*;

public class MediaStreamerUI {

    public static void main(String args[]) {

        String nextLine;

        URL url = null;

        URLConnection urlConn = null;

        InputStreamReader inStream = null;

        BufferedReader buff = null;

        try {

            // index.html adalah nama file default dari URL

            url = new URL("http://www.google.com");

            urlConn = url.openConnection();

            inStream = new InputStreamReader(urlConn.getInputStream(), "UTF8");

            buff = new BufferedReader(inStream);

            // Membaca dan mencetak tiap baris dari index.html

            while (true) {

                nextLine = buff.readLine();

                if (nextLine != null) {

                    System.out.println(nextLine);

                } else {

                    break;

                }

            }

        } catch (MalformedURLException e) {

            System.out.println("Cek kembali URL:" + e.toString());

        } catch (IOException e1) {

            System.out.println("Tidak dapat membaca dari Internet: " +

                    e1.toString());

        } finally {

            if (inStream != null) {

                try {

                    inStream.close();

                    buff.close();

                } catch (IOException e1) {

                    System.out.println("Tidak dapat menutup stream: " +

                            e1.getMessage());

                }

            }

        }

    }

}

b)

Mendownload File
dari suatu
web tertentu
.

import java.io.*;

import java.net.*;

public class DownloadFile {

  public static void main (String args []){  

    InputStream in=null;  

    FileOutputStream fOut=null;  

    try { 

    URL remoteFile = new URL 

("http://dl.vafamusic.com/Full%20Album/bts/320/BTS-Boy-With

Luv%20%28VafaMusic%29.mp3"); 

     URLConnection fileStream = remoteFile.openConnection();  

     //membuka input dan output stream  

     fOut = new FileOutputStream("BTS1.mp3");  

     in = fileStream.getInputStream();  

      

     //menyimpan file  

     int data;  

    while ((data=in.read())!=-1){  

      fOut.write(data);  

      }  

    }  

   catch (Exception ex){  

     ex.printStackTrace();  

    }  

    finally {  

     System.out.println("File telah berhasil didownload");  

    try {  

      in.close();   

fOut.flush();  

fOut.close(); 

}  

catch (Exception e){  

e.printStackTrace();  

} 

}  

}

} 
