package bubbleSort;

public class Main {
    public static void main(String[] args) {
        Sorting a = new Sorting();

        a.add(5);
        a.add(6);
        a.add(3);
        a.add(9);
        a.add(11);
        a.add(8);
        a.bubbleSort();
        a.display();
    }
}
