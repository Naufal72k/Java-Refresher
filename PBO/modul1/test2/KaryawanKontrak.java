package test2;

public class KaryawanKontrak extends Karyawan {

    public KaryawanKontrak(String nama, int jamKerja, int jlhLembur, int jlhAbsen) {
        super(nama, "kontrak", 20_000, jamKerja, jlhLembur, jlhAbsen);
    }

}
