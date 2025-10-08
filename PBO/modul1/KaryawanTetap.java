public class KaryawanTetap extends Karyawan {
    public KaryawanTetap(String nama, int jamKerja, int jlhLembur, int jlhAbsen) {
        super(nama, "tetap", 2_500_000, jamKerja, jlhLembur, jlhAbsen);
    }

    public double hitungLembur(int jlhLembur) {
        return jlhLembur;
    }

    public double hitungAbsen(int jlhAbsen) {

        return jlhAbsen;
    }
}
