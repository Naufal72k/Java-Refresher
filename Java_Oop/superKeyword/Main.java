package Java_Oop.superKeyword;

class Animal {
    public void animalSound() {
        System.out.println("The animas makes a sound");
    }
}

class Dog extends Animal {
    public void animalSound() {
        super.animalSound();
        System.out.println("The dog say: bow wow");
    }

    static void AnimeSound() {
        System.out.println("hahahah");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        Dog.AnimeSound();// karna static void harus di panggil menggunakan class namenya bukan sebagaia
                         // objek
        myDog.animalSound();
    }
}
