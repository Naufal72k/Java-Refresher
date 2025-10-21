package praktikum2;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void makeSound();

    public void eat() {
        System.out.println("can eat");
    };

    public String getName() {
        return name;
    }
}
