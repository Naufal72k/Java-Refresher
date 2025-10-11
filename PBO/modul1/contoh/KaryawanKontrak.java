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
    public void tampilDetail() {
        System.out.println("======================================================");
        System.out.println("Nama\t\t : " + super.getNama());
        System.out.println("ID Karyawan\t : " + super.getId());
        System.out.println("Status\t\t : Kontrak");
        System.out.println("Upah per jam\t : " + this.upahPerJam);
        System.out.println("Jam Kerja\t : " + this.jumlahJamKerja);
        System.out.println("Total Gaji\t : " + this.hitungGaji());
        System.out.println("======================================================");

    }
}
