public class PeminjamanMingguan extends Peminjam {

    public PeminjamanMingguan(String nama, String idPeminjam, double jumlahPinjaman, int lamaPinjaman) {
        super(nama, idPeminjam, "Mingguan", jumlahPinjaman, lamaPinjaman);
        setBunga(0.005);
        setRewardBunga(0.05);
        setDenda(0.02);
    }

    public double hitungAngsuranPokok() {
        return super.getJumlahPinjaman() / super.getLamaPinjaman();
    }

    public double hitungBunga() {
        return super.hitungBunga();
    }

    public double hitungTotalBayar(boolean tepatWaktu) {
        return super.hitungTotalBayar(tepatWaktu);
    }
}