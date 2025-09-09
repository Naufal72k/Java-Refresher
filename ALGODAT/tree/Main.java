package tree;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(50);
        tree.insert(30);

        tree.insert(20);

        System.out.print("Isi tree (Inorder): ");
        tree.inorder();

    }
}
