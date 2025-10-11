public class Manager extends Karyawan {
    private double tunjangan;

    public Manager(String nama, String id, double gajiPokok, double tunjangan) {
        super(nama, id, gajiPokok);
        this.tunjangan = tunjangan;
    }

    public double hitungGaji() {
        return 1;
    }

    public void tampilDdetail() {

    }

}
