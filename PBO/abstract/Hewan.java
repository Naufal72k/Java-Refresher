public abstract class Hewan {
    private String nama;
    private int jumlahKaki;
    private boolean bisaTerbang;

    public Hewan(String nama, int jumlahKaki, boolean bisaTerbang) {
        this.nama = nama;
        this.jumlahKaki = jumlahKaki;
        this.bisaTerbang = bisaTerbang;
    }

    public void isHewan() {
        System.out.println("hewan");

    }

    public abstract void bersuara();

}
