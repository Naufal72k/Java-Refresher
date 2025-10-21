package praktikum1;

public class Sapi extends Hewan {
    public Sapi() {
        super("null", 1, false);
    }

    @Override
    public void bersuara() {
        System.out.println("haiii");
    }
}
