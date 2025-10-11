package test2;

public class KaryawanTetap extends Karyawan {

    public KaryawanTetap(String nama, int jamKerja, int jlhLembur, int jlhAbsen) {
        super(nama, "Tetap", 25_000, jamKerja, jlhLembur, jlhAbsen);
    }

    public double hitungLembur(int jlhLembur) {
        return super.getBonusLembur() * jlhLembur;
    }

    public double hitungAbsen(int jlhAbsen) {
        return super.getPotonganAbsen() * jlhAbsen;
    }

}
