package stream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Program Pengarsip Catatan Mahasiswa (Teks -> ZIP)  ");
        System.out.println("=============================================");

        String sourceFolderPath = "ganti/dengan/path/folder/sumber/anda";

        String zipFilePath = "ganti/dengan/path/file/zip/tujuan/anda.zip";

        System.out.println("Folder Sumber : " + sourceFolderPath);
        System.out.println("File ZIP Tujuan: " + zipFilePath);

        File sourceFolder = new File(sourceFolderPath);

        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            System.err.println(
                    "\nError: Folder sumber tidak ditemukan atau path yang dimasukkan bukanlah sebuah folder.");
            System.err.println("Pastikan path '" + sourceFolderPath + "' sudah benar dan merupakan sebuah folder.");
            return; // Menghentikan program jika folder tidak valid
        }

        try {
            archiveFolder(sourceFolderPath, zipFilePath);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat proses pengarsipan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method untuk mengompres (mengarsipkan) sebuah folder menjadi file ZIP.
     *
     * @param sourceFolderPath Path folder yang akan diarsipkan.
     * @param zipFilePath      Path file ZIP hasil arsip.
     * @throws IOException jika terjadi error I/O.
     */
    public static void archiveFolder(String sourceFolderPath, String zipFilePath) throws IOException {
        System.out.println("\nMemulai proses pengarsipan...");
        long startTime = System.currentTimeMillis(); // Fitur tambahan: Menghitung waktu proses

        FileOutputStream fos = new FileOutputStream(zipFilePath);

        BufferedOutputStream bos = new BufferedOutputStream(fos);

        ZipOutputStream zos = new ZipOutputStream(bos);

        try (zos) {
            File sourceFolder = new File(sourceFolderPath);

            File[] files = sourceFolder.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("Folder sumber kosong, tidak ada file yang diarsipkan.");
                return;
            }

            System.out.println("File yang akan diarsipkan:");
            // Looping untuk setiap file di dalam folder
            for (File file : files) {
                // Hanya proses file, bukan sub-folder
                if (file.isFile()) {
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
            System.out.println("Folder berhasil diarsipkan ke: " + zipFilePath);
            System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik.");
            System.out.println("=============================================");

        } catch (IOException e) {
            throw e;
        }

    }
}