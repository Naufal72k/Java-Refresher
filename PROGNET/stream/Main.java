package stream;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        FileInputStream myFile = null;

        try {
            myFile = new FileInputStream(
                    "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream");
            boolean eof = false;

            while (!eof) {
                int byteValue = myFile.read(null);

                System.out.println(byteValue + " ");
                if (byteValue == -1) {
                    eof = true;
                }

            }
        } catch (IOException e) {
            System.out.println("tidak bisa membaca file" + e.toString());
        } finally {
            if (myFile != null) {
                try {
                    myFile.close();
                } catch (Exception e1) {
                    e1.printStackTrace();

                }
            }
        }
    }
}
