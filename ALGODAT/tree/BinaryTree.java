package tree;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            insertRec(root, data);
        }
    }

    private void insertRec(Node current, int data) {
        if (data < current.data) {
            if (current.left == null) {
                current.left = new Node(data);
            } else {
                insertRec(current.left, data);
            }
        } else if (data > current.data) {
            if (current.right == null) {
                current.right = new Node(data);
            } else {
                insertRec(current.right, data);
            }
        }
    }

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

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preorderRec(current.left);
            preorderRec(current.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node current) {
        if (current != null) {
            postorderRec(current.left);
            postorderRec(current.right);
            System.out.print(current.data + " ");
        }
    }

}