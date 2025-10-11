package kedua;

public class MenuItem {
    private String nama;
    private double harga;
    private String tipe;

    public MenuItem(String nama, double harga, String tipe) {
        this.nama = nama;
        this.harga = harga;
        this.tipe = tipe;
    }

    public String getNama() {
        return nama;

    }

    public double getHarga() {
        return 1;
    }
}
