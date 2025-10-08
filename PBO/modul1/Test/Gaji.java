package Test;

import java.util.ArrayList;

public class Gaji {
    private ArrayList<Karyawan> DaftarKaryawan = new ArrayList<>();

    public void tambahKaryawan(Karyawan karyawan) {
        DaftarKaryawan.add(karyawan);
    }

    public void hitungSemuaGaji() {
        for (Karyawan karyawan : DaftarKaryawan) {
            double tempGaji = karyawan.hitungGaji(karyawan.getJamKerja());
            double tempLembur = karyawan.hitungLembur(karyawan.getJlhLembur());
            double tempAbsen = karyawan.hitungAbsen(karyawan.getJlhAbsen());
            double totalGaji = tempGaji + tempLembur - tempAbsen;
            System.out.println("==================================================");
            System.out.println("Nama Karyawan\t\t: " + karyawan.getNama());
            System.out.println("Status Karyawan\t\t: " + karyawan.getStatus());
            System.out.println("Jumlah jam kerja\t: " + karyawan.getJamKerja());
            System.out.println("Gaji\t\t\t: " + tempGaji);
            System.out.println("Bonus lembur\t\t: " + tempLembur);
            System.out.println("Potongan absen\t\t: " + tempAbsen);
            System.out.println("Gaji akhir\t\t: " + String.format("%.0f", totalGaji));
            System.out.println("==================================================");
            System.out.println();

        }
    }
}
