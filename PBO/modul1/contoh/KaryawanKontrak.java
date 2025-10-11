public class KaryawanKontrak extends Karyawan {
    private double upahPerJam;
    private int jumlahJamKerja;

    public KaryawanKontrak(String nama, String id, double upahPerJam, int jumlahJamKerja) {
        super(nama, id, 0);
        this.upahPerJam = upahPerJam;
        this.jumlahJamKerja = jumlahJamKerja;
    }

    @Override
    public double hitungGaji() {
        return upahPerJam * jumlahJamKerja;
    }

    @Override
    public void tampilkanDetail() {

    }
}
