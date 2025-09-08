package stack;

public class Stack {
    Node top;

    public Stack() {
        this.top = null;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;

    }

    public int pop() {
        if (top == null) {
            System.out.println("masih kosong");
            return -1;
        } else {
            int value = top.data;
            top = top.next;
            return value;
        }

    }

    public int peek() {
        if (top == null) {
            System.out.println("masih kosong");
            return -1;

        } else {

            return top.data;
        }
    }

    public void display() {
        if (top == null) {
            System.out.println("blom ada stack disini");

        } else {
            Node temp = top;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
        }
    }
}
