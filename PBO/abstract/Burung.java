public class Burung extends Hewan {
    public Burung() {
        super("null", 1, false);

    }

    @Override
    public void bersuara() {
        System.out.println("burung");
    }
}
