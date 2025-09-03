package simpleProject;

public class sort {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(64);
        list.add(34);
        list.add(25);
        list.add(12);
        list.add(22);
        list.add(11);
        list.add(90);
        list.add(5);

        list.bubbleSort();
        list.printList();
    }
}
