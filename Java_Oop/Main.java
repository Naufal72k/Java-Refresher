package Java_Oop;

public class Main {
    // String fname = "John";
    // String lname = "Doe";
    // int age = 24;

    // public static void main(String[] args) {
    // Main myObj = new Main();

    // System.out.println("Name: " + myObj.fname + " " + myObj.lname);
    // System.out.println("Age: " + myObj.age);
    // }
    static void myStaticMethod() {
        System.out.println("ini static");

    }

    public void myPublicMethode() {
        System.out.println("ini public method");
    }

    public static void main(String[] args) {
        Main myObj = new Main();

        myObj.myPublicMethode();
    }
}
