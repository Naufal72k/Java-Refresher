package Java_Oop.Encapsulation;

public class Main {
    public static void main(String[] args) {
        Person myObj = new Person();
        // biasanyakan kita seperti di bawah ini, tetapi karna di private kita gabisa
        // tahu nama deklarasi variablenya, mangkanya kita gunakannan setname untuk
        // merubah namanya

        // myObj.name = "nopal";

        myObj.setName("nopal");
        System.out.println(myObj.getName());
    }
}
