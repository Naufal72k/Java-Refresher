public class Karyawan {
    private String nama;
    private String id;
    private double gajiPokok;

    public Karyawan(String nama, String id, double gajiPokok) {
        this.nama = nama;
        this.id = id;
        this.gajiPokok = gajiPokok;
    }

    public String getNama() {
        return this.nama;
    }

    public String getId() {
        return this.id;
    }

    public double hitungGaji() {
        return 1;
    }

    public void tampilDetail() {

    }
}
