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
        return this.jumlahPinjaman / this.lamaPinjaman;
    }

    public double hitungAngsuranPokok(double biayaAdmin) {
        return biayaAdmin + (this.jumlahPinjaman / this.lamaPinjaman);
    }

    public double hitungBunga() {
        return jumlahPinjaman * bunga;
    }

    public double hitungTotalBayar(boolean tepatWaktu) {
        double total = (hitungAngsuranPokok() * lamaPinjaman);
        total += hitungBunga();
        if (tepatWaktu) {
            total -= (jumlahPinjaman * rewardBunga);
        } else {
            total += (jumlahPinjaman * denda);
        }
        if (this instanceof PeminjamanMingguan) {
            total += 10000 * lamaPinjaman;
        }
        return total;
    }

    public String getNama() {
        return this.nama;
    }

    public String getIdPeminjam() {
        return this.idPeminjam;
    }

    public String getStatus() {
        return this.status;
    }

    public double getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public int getLamaPinjaman() {
        return lamaPinjaman;
    }

    public double getRewardBunga() {
        return jumlahPinjaman * rewardBunga;
    }

    public double getDenda() {
        return jumlahPinjaman * denda;
    }

    protected void setRewardBunga(double rewardBunga) {
        this.rewardBunga = rewardBunga;
    }

    protected void setDenda(double denda) {
        this.denda = denda;
    }

    protected void setBunga(double bunga) {
        this.bunga = bunga;
    }
}
