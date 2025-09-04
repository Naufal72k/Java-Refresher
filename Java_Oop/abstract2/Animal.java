package Java_Oop.abstract2;

// abstract class Animal {
//     public abstract void animalSound();

//     public void sleep() {
//         System.out.println("zzz");
//     }
// }

abstract class Animal {
    public abstract void animalSound();

    public void sleep() {
        System.out.println("zzzzzzzz");
    }
}
// Animal myObj = new Animal(); // gabisa kita langsung manggil kek obj biasanya

// class Pig extends Animal {
// public void animalSound() {
// System.out.println("the pig says: weeeeee");
// }
// }

class Pig extends Animal {
    public void animalSound() {
        System.out.println("The pig says : weeeeee");
    }
}

class Main {
    public static void main(String[] args) {
        Pig myPig = new Pig();
        myPig.animalSound();
        myPig.sleep();
    }
}