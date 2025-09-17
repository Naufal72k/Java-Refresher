import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class coba {

    public static void archiveFolder(String tempatFile, String tempatZip) throws IOException {
        System.out.println("\nMemulai proses pengarsipan...");
        long waktuMulai = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(tempatZip);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ZipOutputStream zos = new ZipOutputStream(bos);

        try (zos) {
            File sourceFolder = new File(tempatFile);

            File[] files = sourceFolder.listFiles();

            if (files.length == 0) {
                System.out.println("Folder sumber kosong, tidak ada file yang diarsipkan.");
                return;
            }

            System.out.println("ini filenya");

            for (File file : files) {

                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println("- " + file.getName());

                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    try (bis) {
                        byte[] buffer = new byte[1024];

                        int length;
                        length = bis.read(buffer);

                        while (length > 0) {
                            zos.write(buffer, 0, length);
                            length = bis.read(buffer);
                        }
                    }

                    zos.closeEntry();
                }
            }

            long waktuAkhir = System.currentTimeMillis();
            long durasi = waktuAkhir - waktuMulai;

            // Informasi hasil
            System.out.println("\n=============================================");
            System.out.println("  PROSES SELESAI!  ");
            System.out.println("Folder berhasil diarsipkan ke: " + tempatZip);
            System.out.println("Waktu yang dibutuhkan: " + durasi + " milidetik.");
            System.out.println("=============================================");

        } catch (IOException e) {
            throw e;
        }
    }

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
    }
}
