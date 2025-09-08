package tree;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    // tambah data (insert)
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // rekursif untuk insert
    private Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    // traversal inorder (hasilnya ascending)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // traversal preorder
    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // traversal postorder
    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // tambah data otomatis (tidak manual)
        int[] values = { 50, 30, 70, 20, 40, 60, 80 };
        for (int val : values) {
            tree.insert(val);
        }

        System.out.print("Inorder   : ");
        tree.inorder(); // hasil terurut naik

        System.out.print("Preorder  : ");
        tree.preorder();

        System.out.print("Postorder : ");
        tree.postorder();
    }
}
