package bubbleSort;

public class Sorting {
    Node head;

    public Sorting() {
        this.head = null;
    }

    public void add(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;

            }
            temp.next = newNode;
        }
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

    }

    public void bubbleSort() {
        if (head == null) {
            System.out.println("blom ada apa apa");

        } else {
            boolean swap;

            do {
                swap = false;
                Node temp = head;

                while (temp.next != null) {
                    if (temp.data > temp.next.data) {
                        int simpan = temp.data;
                        temp.data = temp.next.data;
                        temp.next.data = simpan;
                        swap = true;

                    }
                    temp = temp.next;

                }

            } while (swap);
        }
    }

}
