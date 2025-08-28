package Java_Oop.Modifier;

public class Day4 {
    int modelYear;
    String modelName;

    public Day4(String modelName) {
        this(2020, modelName);
    }

    public Day4(int modelYear, String modelName) {
        this.modelYear = modelYear;
        this.modelName = modelName;
    }

    public void printInfo() {
        System.out.println(modelYear + " " + modelName);
    }

    public static void main(String[] args) {
        Day4 myObj = new Day4("nopal");
        Day4 myObj1 = new Day4(20, "dael");
        myObj.printInfo();
        myObj1.printInfo();
    }
}
