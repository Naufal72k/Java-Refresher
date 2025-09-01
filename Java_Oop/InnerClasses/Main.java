package Java_Oop.InnerClasses;

class OutherClass {
    int x = 10;

    class InnerClass {
        int y = 5;
    }
};

public class Main {
    public static void main(String[] args) {
        OutherClass myOuther = new OutherClass();
        OutherClass.InnerClass myInner = myOuther.new InnerClass();
        System.out.println(myOuther.x);
        System.out.println(myInner.y);

    }
}
