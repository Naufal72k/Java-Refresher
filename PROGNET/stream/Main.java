package stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
// import java.util.Scanner; // Scanner sudah tidak digunakan lagi
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Kelas ini berfungsi untuk membuat aplikasi sederhana yang dapat mengarsipkan
 * semua file dari sebuah folder ke dalam satu file ZIP.
 * Path folder sumber dan file ZIP tujuan diatur secara statis di dalam kode.
 * Dibuat agar mudah dipahami oleh pemula.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Program Pengarsip Catatan Mahasiswa (Teks -> ZIP)  ");
        System.out.println("=============================================");

        // --- PENGATURAN STATIS ---
        // Ganti path folder dan nama file zip di bawah ini sesuai kebutuhan Anda.
        // PENTING: Pastikan folder sumber (sourceFolderPath) sudah ada sebelum program
        // dijalankan.
        // Contoh untuk Windows: "C:/Users/Nama/Documents/CatatanKuliah"
        // Contoh untuk MacOS/Linux: "/home/nama/Documents/CatatanKuliah"
        String sourceFolderPath = "ganti/dengan/path/folder/sumber/anda";

        // Contoh untuk Windows: "D:/Arsip/Catatan.zip"
        // Contoh untuk MacOS/Linux: "/home/nama/Arsip/Catatan.zip"
        String zipFilePath = "ganti/dengan/path/file/zip/tujuan/anda.zip";
        // -------------------------

        System.out.println("Folder Sumber : " + sourceFolderPath);
        System.out.println("File ZIP Tujuan: " + zipFilePath);

        // Membuat objek File dari path yang diberikan
        File sourceFolder = new File(sourceFolderPath);

        // Validasi folder sumber
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            System.err.println(
                    "\nError: Folder sumber tidak ditemukan atau path yang dimasukkan bukanlah sebuah folder.");
            System.err.println("Pastikan path '" + sourceFolderPath + "' sudah benar dan merupakan sebuah folder.");
            return; // Menghentikan program jika folder tidak valid
        }

        try {
            // Memanggil method untuk melakukan proses pengarsipan
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

        // Membuat FileOutputStream untuk menulis data ke file ZIP tujuan.
        FileOutputStream fos = new FileOutputStream(zipFilePath);

        // (Stream Layering) Membungkus FileOutputStream dengan BufferedOutputStream
        // untuk efisiensi penulisan.
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        // (Stream Layering) Membungkus BufferedOutputStream dengan ZipOutputStream
        // untuk menulis data dalam format ZIP.
        ZipOutputStream zos = new ZipOutputStream(bos);

        // Menggunakan try-with-resources untuk memastikan stream tertutup secara
        // otomatis.
        try (zos) {
            File sourceFolder = new File(sourceFolderPath);
            // Mengambil semua file yang ada di dalam folder sumber
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

                    // 1. Membuat entri baru di dalam file ZIP
                    // ZipEntry merepresentasikan satu file di dalam arsip ZIP.
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    // 2. Membaca data dari file sumber
                    // Membuat FileInputStream untuk membaca file sumber.
                    FileInputStream fis = new FileInputStream(file);

                    // (Stream Layering) Membungkus FileInputStream dengan BufferedInputStream untuk
                    // efisiensi pembacaan.
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    // Menggunakan try-with-resources untuk FileInputStream dan BufferedInputStream
                    try (bis) {
                        // Membuat buffer (penampung sementara) untuk membaca data
                        byte[] buffer = new byte[1024];
                        int length;
                        // Membaca data dari file sumber ke buffer, lalu menulisnya ke ZipOutputStream
                        while ((length = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    }

                    // 3. Menutup entri ZIP saat ini
                    zos.closeEntry();
                }
            }

            long endTime = System.currentTimeMillis(); // Mengambil waktu selesai
            long duration = endTime - startTime; // Menghitung durasi

            System.out.println("\n=============================================");
            System.out.println("  PROSES SELESAI!  ");
            System.out.println("Folder berhasil diarsipkan ke: " + zipFilePath);
            System.out.println("Waktu yang dibutuhkan: " + duration + " milidetik.");
            System.out.println("=============================================");

        } catch (IOException e) {
            // Melempar kembali exception agar bisa ditangani oleh pemanggil method
            throw e;
        }
        // Tidak perlu blok finally untuk menutup zos, fos, bos karena sudah menggunakan
        // try-with-resources pada zos.
        // Saat zos ditutup, stream yang dibungkusnya (bos dan fos) akan ikut tertutup
        // secara otomatis.
    }
}