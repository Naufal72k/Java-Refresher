public class Main {
    public static void main(String[] args) {
        Pinjaman pinjaman = new Pinjaman();

        Peminjam p1 = new PeminjamanMingguan("Budi", "M001", 1_000_000, 4);
        Peminjam p2 = new PeminjamanBulanan("Siti", "B001", 5_000_000, 12);
        Peminjam p3 = new PeminjamanTahunan("Andi", "T001", 20_000_000, 2);
        PeminjamanMingguan p4 = new PeminjamanMingguan("Dewi", "M002", 3_000_000, 6);

        pinjaman.tambahPeminjam(p1);
        pinjaman.tambahPeminjam(p2);
        pinjaman.tambahPeminjam(p3);
        pinjaman.tambahPeminjam(p4);

        boolean[] statusPembayaran = { true, false, true, false };

        pinjaman.hitungSemuaAngsuran(statusPembayaran);
    }
}