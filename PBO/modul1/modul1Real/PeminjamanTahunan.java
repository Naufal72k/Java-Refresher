public class PeminjamanTahunan extends Peminjam {

    public PeminjamanTahunan(String nama, String idPeminjam, double jumlahPinjaman, int lamaPinjaman) {
        super(nama, idPeminjam, "Tahunan", jumlahPinjaman, lamaPinjaman);
        setBunga(0.02);
        setRewardBunga(0.15);
        setDenda(0.1);
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