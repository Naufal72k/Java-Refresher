package praktikum2;

public class Dog extends Animal implements Swimmable {

    public Dog(String name) {
        super(name);
    }

    public void makeSound() {
        System.out.println("wkekweakekwekwewk");

    }

    public void swim() {
        System.out.println("can swim");
    }
}
