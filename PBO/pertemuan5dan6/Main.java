package pertemuan5dan6;

public class Main {
    public double panjang;
    public double lebar;

    public void setLebar(double l) {
        this.lebar = l;
    }

    public void setPanjang(double p) {
        this.panjang = p;
    }

    public double hitungLuas() {
        return panjang * lebar;
    }

    public double hitungKeliling() {
        return 2 * (panjang + lebar);
    }

    public static void main(String[] args) {
        Main a = new Main();
        a.setLebar(2);
        a.setPanjang(3);

        double b = a.hitungLuas();
        System.out.println(b);

    }
}
