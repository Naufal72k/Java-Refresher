package kedua;

public class Pelanggan {
    private String nama;
    private boolean isMember;
    private int point;

    public Pelanggan(String nama, boolean isMember) {
        this.nama = nama;
        this.isMember = isMember;
    }

    public String getNama() {
        return nama;
    }

    public boolean isMember() {
        return false;
    }

    public int getPoint() {
        return point;
    }

    public void tambahPoint(int jumlahPoint) {

    }
}
