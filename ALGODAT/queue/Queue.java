public class Queue {
    Node head;
    Node tail;

    public Queue() {
        this.head = null;
        this.tail = null;
    }

    public void enQueue(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public int peek() {
        if (head == null) {
            System.out.println("blom ada apa apa");
            return -1;
        } else {

            return head.data;
        }
    }

    public int deQueue() {
        if (head == null) {
            System.out.println("blom ada apa apa");
            return -1;
        } else {
            int value = head.data;
            head = head.next;
            return value;
        }
    }

    public void display() {
        Node temp = head;
        if (head == null) {
            System.out.println("blom ada apa apa");
        } else {
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }
}
