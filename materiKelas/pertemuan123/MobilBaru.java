package materiKelas.pertemuan123;

public class MobilBaru {
    public static void main(String[] args) {
        Mobil myMobil = new Mobil();
        myMobil.merk = "Toyota";
        myMobil.warna = " merah";
        myMobil.tahunProduksi = 2024;
        myMobil.harga = 80000;

        for (int i = 0; i < 5; i++) {
            System.out.println(myMobil.harga);
        }
    }
}
