package Java_Oop.superKeyword;

class Animal {
    String type = "Animl";

}

class Dog extends Animal {
    String type = "Dog";

    public void printType() {
        System.out.println(super.type);
    }
}

public class Main2 {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.printType();

    }
}
