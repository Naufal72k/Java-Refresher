public class Main {
    public static void main(String[] args) {
        KaryawanTetap karyawan1 = new KaryawanTetap("Agus", 320, 5, 2);
        KaryawanTetap karyawan2 = new KaryawanTetap("Mlynar", 320, 4, 1);
        KaryawanKontrak karyawan3 = new KaryawanKontrak("Surti", 320, 7, 3);
        Magang karyawan4 = new Magang("Vina", 320, 2, 0);

        Gaji gaji = new Gaji();
        gaji.tambahKaryawan(karyawan1);
        gaji.tambahKaryawan(karyawan2);
        gaji.tambahKaryawan(karyawan3);
        gaji.tambahKaryawan(karyawan4);

        gaji.hitungSemuaGaji();

        System.out.println("Semoga gaji kita bisa sampai 3 digit ke atas.....Aamiin ya Allah.");
    }
}