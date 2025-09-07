package bubbleSort;

public class SingleList {
    Node head;
    Node tail;

    public SingleList() {
        this.head = null;
        this.tail = null;
    }

    public void addData(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

    }

    public void Display() {
        Node temp = head;

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    // public void addLast(int data) {
    // Node newNode = new Node(data);
    // head.next = newNode;
    // tail = newNode;

}
