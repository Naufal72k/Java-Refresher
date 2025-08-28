package Java_Oop.Modifier;

public class Modifiers {
    final int x = 10;
    final double pi = 3.14;

    static int myfun() {
        return 5;
    }// kalo static void maupun int dia ga perlu buat Main myObj = new Main();, dia
     // ga perlu , tinggal panggil aja. kalo obj baru perlu misal ke di bawah ni

    public void myPublicMethod() {
        System.out.println("ini public methode");
    }

    public static void main(String[] args) {
        Modifiers myObj = new Modifiers();
        // int a = myObj.x = 50; // gabakal keganti
        // myObj.pi = 25;
        System.out.println(myObj.x);
        int a = myfun();
        System.out.println(a);
        myObj.myPublicMethod();

    }
}