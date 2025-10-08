package test2;

public class Karyawan {
    private String nama;
    private String status;
    private double gajiPerJam;
    private int jamKerja;
    private int jlhLembur;
    private int jlhAbsen;
    private double bonusLembur;
    private double potonganAbsen;

    public Karyawan(String nama, String status, double gajiPerJam, int jamKerja, int jlhLembur, int jlhAbsen) {
        this.nama = nama;
        this.status = status;
        this.gajiPerJam = gajiPerJam;
        this.jamKerja = jamKerja;
        this.jlhLembur = jlhLembur;
        this.jlhAbsen = jlhAbsen;
    }

    public double hitungGaji(int jamKerja) {
        return jamKerja;
    }

    public double hitungLembur(int jlhLembur) {
        return jlhLembur;
    }

    public double hitungAbsen(int jlhAbsen) {
        return jlhAbsen;
    }

    public String getNama() {
        return this.nama;
    }

    public String getStatus() {
        return this.status;
    }

    public int getJamKerja() {
        return this.jamKerja;
    }

    public int getJlhLembur() {
        return this.jlhLembur;
    }

    public int getJlhAbsen() {
        return this.jlhAbsen;
    }

    public double getBonusLembur() {
        return this.bonusLembur;
    }

    public double getPotonganAbsen() {
        return this.potonganAbsen;
    }

}
