package praktikum2;

public class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name) {
        super(name);
    }

    public void makeSound() {
        System.out.println("wkekweakekwekwewk");

    }

    public void swim() {
        System.out.println("can swim");
    }

    public void fly() {
        System.out.println("can fly");

    }

}
