package Java_Oop;

public class Day4 {
    int modelYear;
    String modelName;

    public Day4(int x, String y) {
        modelYear = x;
        modelName = y;
    }

    public static void main(String[] args) {
        Day4 myObj = new Day4(5, "Nopal");

        System.out.println(myObj.modelName + " dan " + myObj.modelYear);
    }
}
