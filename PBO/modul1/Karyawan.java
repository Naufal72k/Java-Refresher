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
        return gajiPerJam * jamKerja;
    }

    public double hitungLembur(int jlhLembur) {
        return bonusLembur * jlhLembur;
    }

    public double hitungAbsen(int jumlahAbsen) {
        return potonganAbsen * jumlahAbsen;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public int getJamKerja() {
        return jamKerja;
    }

    public int getJlhLembur() {
        return jlhLembur;
    }

    public int getJlmAbsen() {
        return jlhAbsen;
    }

    public double getBonusLembur() {
        return bonusLembur;
    }

    public double getPotonganAbsen() {
        return potonganAbsen;
    }
}
