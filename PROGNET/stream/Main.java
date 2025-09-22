package stream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Program Pengarsip Catatan Mahasiswa (Teks -> ZIP)  ");
        System.out.println("=============================================");

        String tempatFile = "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream";
        String tempatZip = "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream\\anda.zip";

        System.out.println("Folder Sumber : " + tempatFile);
        System.out.println("File ZIP Tujuan: " + tempatZip);

        File sourceFolder = new File(tempatFile);

        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            System.err.println(
                    "\nError: Folder sumber tidak ditemukan atau path yang dimasukkan bukanlah sebuah folder.");
            System.err.println("Pastikan path '" + tempatFile + "' sudah benar dan merupakan sebuah folder.");
            return;
        }

        try {
            archiveFolder(tempatFile, tempatZip);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat proses pengarsipan: " + e.getMessage());
            e.printStackTrace();
        }

        String extractFolder = "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream\\extracted";

        System.out.println("Folder Ekstraksi: " + extractFolder);

        try {
            extractZip(tempatZip, extractFolder);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat proses ekstraksi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void archiveFolder(String tempatFile, String tempatZip) throws IOException {
        System.out.println("\nMemulai proses pengarsipan...");
        long startTime = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(tempatZip);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ZipOutputStream zos = new ZipOutputStream(bos);

        try (zos) {
            File sourceFolder = new File(tempatFile);
            File[] files = sourceFolder.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("Folder sumber kosong, tidak ada file yang diarsipkan.");
                return;
            }

            System.out.println("File TXT yang akan diarsipkan:");
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                    System.out.println("- " + file.getName());

                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    try (bis) {
                        byte[] buffer = new byte[1024];
                        int length;

                        while ((length = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    }

                    zos.closeEntry();
                }
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("\n=============================================");
            System.out.println("  PROSES SELESAI!  ");
            System.out.println("Folder berhasil diarsipkan ke: " + tempatZip);
            System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik.");
            System.out.println("=============================================");

        } catch (IOException e) {
            throw e;
        }
    }

    public static void extractZip(String tempatZip, String destFolderPath) throws IOException {
        System.out.println("\nMemulai proses ekstraksi...");
        long startTime = System.currentTimeMillis();

        File destFolder = new File(destFolderPath);
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(tempatZip))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(destFolderPath, entry.getName());

                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    file.getParentFile().mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(file);
                            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            bos.write(buffer, 0, length);
                        }
                    }
                }

                zis.closeEntry();
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\n=============================================");
        System.out.println("  EKSTRAKSI SELESAI!  ");
        System.out.println("File ZIP berhasil diekstrak ke: " + destFolderPath);
        System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik.");
        System.out.println("=============================================");
    }
}
