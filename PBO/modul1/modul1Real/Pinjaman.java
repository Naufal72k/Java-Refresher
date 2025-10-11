import java.util.ArrayList;

public class Pinjaman {
    private ArrayList<Peminjam> daftarPeminjam;

    public Pinjaman() {
        daftarPeminjam = new ArrayList<>();
    }

    public void tambahPeminjam(Peminjam peminjam) {
        daftarPeminjam.add(peminjam);
    }

    public void hitungSemuaAngsuran(boolean[] statusPembayaran) {
        for (int i = 0; i < daftarPeminjam.size(); i++) {
            Peminjam p = daftarPeminjam.get(i);
            boolean tepatWaktu = statusPembayaran[i];

            System.out.println("==========================================================");
            System.out.println("Nama: " + p.getNama());
            System.out.println("ID: " + p.getIdPeminjam());
            System.out.println("Jumlah Pinjaman: " + String.format("%.0f", p.getJumlahPinjaman()));
            System.out.println("Status : " + p.getStatus());

            if (p instanceof PeminjamanMingguan) {
                System.out.println("Tenor: " + p.getLamaPinjaman() + " periode");
                System.out.println("Angsuran Pokok per Periode: " + String.format("%.0f", p.hitungAngsuranPokok()));
                System.out.println("Angsuran + Biaya admin: " + String.format("%.0f", p.hitungAngsuranPokok(10000)));
            } else if (p instanceof PeminjamanBulanan) {
                System.out.println("Tenor: " + p.getLamaPinjaman() + " bulan");
                System.out.println("Angsuran Pokok per Periode: " + String.format("%.0f", p.hitungAngsuranPokok()));
                System.out.println("Angsuran Pokok per Bulan: " + String.format("%.2f", p.hitungAngsuranPokok()));
            } else if (p instanceof PeminjamanTahunan) {
                System.out.println("Tenor: " + p.getLamaPinjaman() + " tahun");
                System.out.println("Angsuran Pokok per Periode: " + String.format("%.0f", p.hitungAngsuranPokok()));
                System.out.println("Angsuran Pokok per Tahun: " + String.format("%.0f", p.hitungAngsuranPokok()));
            }
            System.out.println("Bunga: " + String.format("%.0f", p.hitungBunga()));
            System.out.println("Status: " + (tepatWaktu ? "Tepat waktu" : "Terlambat"));

            if (tepatWaktu) {
                System.out.println("Reward: " + String.format("%.0f", p.getRewardBunga()));
            } else {
                System.out.println("Denda: " + String.format("%.0f", p.getDenda()));
            }

            System.out.println("Total Bayar: " + String.format("%.0f", p.hitungTotalBayar(tepatWaktu)));
            System.out.println("==========================================================");
            System.out.println();
        }
    }
}
