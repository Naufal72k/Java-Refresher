package singleLinkedList;

public class LinkedList {
    Node head;

    public LinkedList() {
        this.head = null;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void Display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void bubbleSort() {
        if (head == null || head.next == null) {
            return; // Daftar kosong atau hanya satu node, sudah terurut
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            Node prev = null;

            // Telusuri daftar untuk membandingkan dan menukar
            while (current != null && current.next != null) {
                if (current.data > current.next.data) {
                    // Tukar node
                    swapped = true;
                    Node nextNode = current.next;

                    // Update referensi next
                    current.next = nextNode.next;
                    nextNode.next = current;

                    // Update prev atau head
                    if (prev == null) {
                        head = nextNode;
                    } else {
                        prev.next = nextNode;
                    }

                    // Update prev untuk iterasi berikutnya
                    prev = nextNode;
                } else {
                    // Tidak perlu tukar, lanjutkan ke node berikutnya
                    prev = current;
                    current = current.next;
                }
            }
        } while (swapped); // Ulangi jika ada penukaran
    }
}