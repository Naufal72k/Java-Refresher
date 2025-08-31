package Java_Oop.superKeyword;

class Animals {
    Animals() {
        System.out.println("Animal is created");
    }
}

class Dogs extends Animals {
    Dogs() {
        super();
        System.out.println("Dog is created");
    }
}

public class Second {
    public static void main(String[] args) {
        Dogs myDog = new Dogs();
    }
}
