package tree;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    // method untuk insert data
    public void insert(int data) {
        // kalau root masih kosong, langsung isi
        if (root == null) {
            root = new Node(data);
        } else {
            insertRec(root, data);
        }
    }

    // rekursif untuk cari tempat yang tepat
    private void insertRec(Node current, int data) {
        // kalau data lebih kecil, masuk ke kiri
        if (data < current.data) {
            if (current.left == null) {
                current.left = new Node(data);
            } else {
                insertRec(current.left, data);
            }
        }
        // kalau data lebih besar, masuk ke kanan
        else if (data > current.data) {
            if (current.right == null) {
                current.right = new Node(data);
            } else {
                insertRec(current.right, data);
            }
        }
        // kalau sama, kita abaikan (opsional)
    }

    // tampilkan inorder (kiri - root - kanan)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node current) {
        if (current != null) {
            inorderRec(current.left);
            System.out.print(current.data + " ");
            inorderRec(current.right);
        }
    }
}
