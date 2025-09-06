package singleLinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add(9);
        list.add(1);
        list.add(8);
        list.add(2);
        list.add(5);

        System.out.println("Sebelum sorting:");
        list.Display(); // Output: 5 -> 2 -> 8 -> 1 -> 9 -> null

        // Lakukan Bubble Sort
        list.bubbleSort();

        System.out.println("Setelah Bubble Sort:");
        list.Display(); // Output: 1 -> 2 -> 5 -> 8 -> 9 -> null
    }
}
