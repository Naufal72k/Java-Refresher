public class PeminjamanBulanan extends Peminjam {

    public PeminjamanBulanan(String nama, String idPeminjam, double jumlahPinjaman, int lamaPinjaman) {
        super(nama, idPeminjam, "Bulanan", jumlahPinjaman, lamaPinjaman);
        setBunga(0.01);
        setRewardBunga(0.1);
        setDenda(0.05);
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