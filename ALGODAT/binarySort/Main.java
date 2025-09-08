package binarySort;

class LinkedList {
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

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void bubbleSort() {
        if (head == null || head.next == null)
            return;

        boolean swapped;
        do {
            swapped = false;
            Node prev = null;
            Node current = head;

            while (current != null && current.next != null) {
                if (current.data > current.next.data) {

                    Node nextNode = current.next;
                    current.next = nextNode.next;
                    nextNode.next = current;

                    if (prev == null) {
                        head = nextNode;
                    } else {
                        prev.next = nextNode;
                    }

                    prev = nextNode;
                    swapped = true;
                } else {
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped);
    }

    private Node getMiddle(Node start, Node end) {
        if (start == null)
            return null;

        Node slow = start;
        Node fast = start.next;

        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node binarySearch(int key) {
        Node start = head;
        Node end = null;

        while (start != end) {
            Node mid = getMiddle(start, end);
            if (mid == null)
                return null;

            if (mid.data == key) {
                return mid;
            } else if (mid.data < key) {
                start = mid.next;
            } else {
                end = mid;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Tambah data random (belum urut)
        list.add(9);
        list.add(2);
        list.add(7);
        list.add(1);
        list.add(5);

        System.out.print("Sebelum sorting: ");
        list.printList();

        // Step 1: Sorting dulu
        list.bubbleSort();
        System.out.print("Sesudah sorting: ");
        list.printList();

        // Step 2: Binary Search
        int key = 7;
        Node result = list.binarySearch(key);

        if (result != null) {
            System.out.println("Data " + key + " ditemukan!");
        } else {
            System.out.println("Data " + key + " tidak ditemukan.");
        }
    }
}
