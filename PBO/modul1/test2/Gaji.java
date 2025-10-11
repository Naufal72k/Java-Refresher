package test2;

import java.util.ArrayList;

public class Gaji {
    private ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();

    public void tambahKaryawan(Karyawan karyawan) {
        daftarKaryawan.add(karyawan);
    }

    public void hitungSemuaGaji() {
        for (Karyawan karyawan : daftarKaryawan) {
            double gaji = karyawan.hitungGaji(karyawan.getJamKerja());
            double bonusLembur = karyawan.hitungLembur(karyawan.getJlhLembur());
            double potonganAbsen = karyawan.hitungAbsen(karyawan.getJlhAbsen());
            double totalGaji = gaji + bonusLembur - potonganAbsen;
            System.out.println("===============================================");
            System.out.println("Nama Karyawan : " + karyawan.getNama());
            System.out.println("Status Karyawan : " + karyawan.getStatus());
            System.out.println("Jumlah jam kerj : " + karyawan.getJamKerja());
            System.out.println("Gaji : " + gaji);
            System.out.println("Bonus Lembur : " + bonusLembur);
            System.out.println("potongan absen : " + potonganAbsen);
            System.out.println("Gaji Akhir : " + totalGaji);
            System.out.println("===============================================");

        }
    }
}
