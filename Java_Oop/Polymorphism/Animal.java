package Java_Oop.Polymorphism;

class Animal {
    public void animalSound() {
        System.out.println("The animal makes a sound");
    }
}

class Pig extends Animal {
    public void animalSound() {
        System.out.println("The pig says: wee wee");
    }
}

class Dog extends Animal {
    public void animalSound() {
        System.out.println("The dog says: bow wow");
    }
}

class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal(); // Create a Animal object
        Animal myPig = new Pig(); // Create a Pig object
        Animal myDog = new Dog(); // Create a Dog object
        myAnimal.animalSound();
        myPig.animalSound();
        myDog.animalSound();

        // jika polimorisme bisa dia di jadiin satu objek, entr masuk kedalam array,
        // kalo ga pake poli nanti lama kita nulisnya
        Animal[] myAnimals = new Animal[3];
        myAnimals[0] = new Animal();
        myAnimals[1] = new Pig();
        myAnimals[2] = new Dog();

        for (Animal animal : myAnimals) {
            animal.animalSound();
        }
    }
}
