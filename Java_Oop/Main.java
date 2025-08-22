package Java_Oop;

public class Main {
    int x = 10;

    public static void main(String[] args) {
        Main myObj1 = new Main();
        Main myObj2 = new Main();
        myObj2.x = 40;

        System.out.println(myObj1.x);
        System.out.println(myObj2.x);
    }

}
