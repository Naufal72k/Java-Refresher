package stack;

public class Main {
    public static void main(String[] args) {
        Stack a = new Stack();

        a.push(5);
        a.push(6);
        a.push(7);
        // a.display();

        // System.out.println(" ");
        // a.pop();
        // a.display();

        int b = a.peek();
        System.out.println(b);

    }
}
