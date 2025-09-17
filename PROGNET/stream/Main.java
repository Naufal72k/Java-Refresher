package stream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        // Menampilkan judul program
        System.out.println("=============================================");
        System.out.println("  Program Pengarsip Catatan Mahasiswa (Teks -> ZIP)  ");
        System.out.println("=============================================");

        // Path folder sumber yang berisi file-file untuk diarsipkan
        String tempatFile = "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream";

        // Path file ZIP tujuan hasil arsip
        String tempatZip = "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream\\anda.zip";

        System.out.println("Folder Sumber : " + tempatFile);
        System.out.println("File ZIP Tujuan: " + tempatZip);

        // Membuat objek File untuk folder sumber
        File sourceFolder = new File(tempatFile);

        // Validasi: cek apakah path ada dan merupakan folder
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            System.err.println(
                    "\nError: Folder sumber tidak ditemukan atau path yang dimasukkan bukanlah sebuah folder.");
            System.err.println("Pastikan path '" + tempatFile + "' sudah benar dan merupakan sebuah folder.");
            return; // Stop program jika folder tidak valid
        }

        // Proses kompresi folder menjadi ZIP
        try {
            archiveFolder(tempatFile, tempatZip);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat proses pengarsipan: " + e.getMessage());
            e.printStackTrace();
        }

        // Path folder tujuan untuk ekstraksi ZIP
        String extractFolder = "C:\\Users\\lenov\\OneDrive\\Desktop\\Github\\Java-Refresher\\PROGNET\\stream\\extracted";

        System.out.println("Folder Ekstraksi: " + extractFolder);

        // Proses ekstraksi file ZIP
        try {
            extractZip(tempatZip, extractFolder);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat proses ekstraksi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void archiveFolder(String tempatFile, String tempatZip) throws IOException {
        System.out.println("\nMemulai proses pengarsipan...");
        long startTime = System.currentTimeMillis(); // Hitung waktu mulai

        // Output stream untuk file ZIP tujuan
        FileOutputStream fos = new FileOutputStream(tempatZip);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ZipOutputStream zos = new ZipOutputStream(bos);

        // Gunakan try-with-resources agar otomatis menutup stream
        try (zos) {
            File sourceFolder = new File(tempatFile);

            // Ambil semua file di dalam folder
            File[] files = sourceFolder.listFiles();

            // Jika folder kosong
            if (files == null || files.length == 0) {
                System.out.println("Folder sumber kosong, tidak ada file yang diarsipkan.");
                return;
            }

            System.out.println("File TXT yang akan diarsipkan:");
            // Looping untuk setiap file
            for (File file : files) {
                // Hanya proses file (bukan folder) DAN hanya file berekstensi .txt
                if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                    System.out.println("- " + file.getName()); // Tampilkan nama file

                    // Membuat entri baru di dalam ZIP
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    // Membaca isi file sumber
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    try (bis) {
                        byte[] buffer = new byte[1024]; // Buffer untuk menampung data
                        int length;

                        // Baca file dan tulis ke dalam ZIP
                        while ((length = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    }

                    // Tutup entry setelah file selesai diproses
                    zos.closeEntry();
                }
            }

            long endTime = System.currentTimeMillis(); // Hitung waktu selesai
            long duration = endTime - startTime;

            // Informasi hasil
            System.out.println("\n=============================================");
            System.out.println("  PROSES SELESAI!  ");
            System.out.println("Folder berhasil diarsipkan ke: " + tempatZip);
            System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik.");
            System.out.println("=============================================");

        } catch (IOException e) {
            throw e; // lempar exception jika ada error
        }
    }

    /**
     * Method untuk mendekompres (mengekstrak) file ZIP ke folder tujuan.
     *
     * @param tempatZip      Path file ZIP yang akan diekstrak.
     * @param destFolderPath Path folder tujuan ekstraksi.
     * @throws IOException jika terjadi error I/O.
     */
    public static void extractZip(String tempatZip, String destFolderPath) throws IOException {
        System.out.println("\nMemulai proses ekstraksi...");
        long startTime = System.currentTimeMillis(); // waktu mulai

        // Pastikan folder tujuan ada, jika tidak buat folder baru
        File destFolder = new File(destFolderPath);
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        // Buka file ZIP untuk dibaca
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(tempatZip))) {
            ZipEntry entry;
            // Loop setiap entry (file/ folder) di dalam ZIP
            while ((entry = zis.getNextEntry()) != null) {
                // Lokasi file tujuan setelah ekstraksi
                File file = new File(destFolderPath, entry.getName());

                if (entry.isDirectory()) {
                    // Jika entry berupa folder, buat folder
                    file.mkdirs();
                } else {
                    // Jika entry berupa file, pastikan folder induk sudah ada
                    file.getParentFile().mkdirs();

                    // Tulis isi file dari ZIP ke lokasi tujuan
                    try (FileOutputStream fos = new FileOutputStream(file);
                            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            bos.write(buffer, 0, length);
                        }
                    }
                }

                // Tutup entry ZIP
                zis.closeEntry();
            }
        }

        long endTime = System.currentTimeMillis(); // waktu selesai
        long duration = endTime - startTime;

        // Informasi hasil ekstraksi
        System.out.println("\n=============================================");
        System.out.println("  EKSTRAKSI SELESAI!  ");
        System.out.println("File ZIP berhasil diekstrak ke: " + destFolderPath);
        System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik.");
        System.out.println("=============================================");
    }
}
