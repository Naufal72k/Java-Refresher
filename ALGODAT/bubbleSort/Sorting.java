package bubbleSort;

public class Sorting {
    Node head;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void bubbleSort() {
        if (head == null)
            return;
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    public void selectionSort() {
        for (Node current = head; current != null; current = current.next) {
            Node min = current;
            for (Node next = current.next; next != null; next = next.next) {
                if (next.data < min.data) {
                    min = next;
                }
            }
            int temp = current.data;
            current.data = min.data;
            min.data = temp;
        }
    }

    public void insertionSort() {
        if (head == null || head.next == null) {
            System.out.println("masi kosong");
        }

        else {
            Node sorted = null;
            Node current = head;
            while (current != null) {
                Node next = current.next;
                if (sorted == null || current.data < sorted.data) {
                    current.next = sorted;
                    sorted = current;
                } else {
                    Node temp = sorted;
                    while (temp.next != null && temp.next.data < current.data) {
                        temp = temp.next;
                    }
                    current.next = temp.next;
                    temp.next = current;
                }
                current = next;
            }
            head = sorted;
        }
    }
}
