package ketiga;

public class Peminjam {
    private String nama;
    private String idPeminjam;
    private String status;
    private double jumlahPinjaman;
    private int lamaPinjaman;
    private double bunga;
    private double rewardBunga;
    private double denda;

    public Peminjam(String nama, String idPeminjam, String status, double jumlahPinjaman, int lamaPinjaman) {
        this.nama = nama;
        this.idPeminjam = idPeminjam;
        this.status = status;
        this.jumlahPinjaman = jumlahPinjaman;
        this.lamaPinjaman = lamaPinjaman;
    }

    public double hitungAngsuranPokok() {
        return 1;

    }
}
