package pertama;

public class Main {
    public static void main(String[] args) {
        Perusahaan a = new Perusahaan();
        Karyawan b = new KaryawanKontrak("Budi", "K001", 1_000_000, 150);

        a.tambahKaryawan(b);
        a.tampilkanSemuaGaji();
    }
}
