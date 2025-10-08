public class Magang extends Karyawan {

    public Magang(String nama, int jamKerja, int jlhLembur, int jlhAbsen) {
        super(nama, "Magang", 15_000, jamKerja, jlhLembur, jlhAbsen);
    }

    public double hitungLembur(int jlhLembur) {
        return super.getBonusLembur() * jlhLembur;
    }

    public double hitungAbsen(int jlhAbsen) {
        return super.getPotonganAbsen() * jlhAbsen;
    }
}
