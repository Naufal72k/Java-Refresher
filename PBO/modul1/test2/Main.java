package test2;

public class Main {
    public static void main(String[] args) {
        Karyawan karyawan1 = new KaryawanTetap("Agus", 320, 5, 2);
        Karyawan karyawan2 = new KaryawanTetap("Mlynar", 320, 4, 1);
        Karyawan karyawan3 = new KaryawanKontrak("surti", 320, 7, 3);
        Karyawan karyawan4 = new Magang("VIna", 320, 2, 0);

        Gaji gaji = new Gaji();
        gaji.tambahKaryawan(karyawan1);
        gaji.tambahKaryawan(karyawan2);
        gaji.tambahKaryawan(karyawan3);
        gaji.tambahKaryawan(karyawan4);

        gaji.hitungSemuaGaji();
    }
}
