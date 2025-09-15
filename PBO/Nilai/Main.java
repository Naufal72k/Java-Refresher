package Nilai;

public class Main {
    public static void main(String[] args) {
        CekNilai cek = new CekNilai();
        java.util.Scanner input = new java.util.Scanner(System.in);

        System.out.print("Masukkan nilai (0-100): ");
        int nilai = input.nextInt();

        if (nilai >= 60) {
            System.out.println("Lulus");
        } else {
            System.out.println("Tidak Lulus");
        }

        String grade = cek.getGrade(nilai);
        System.out.println("Grade: " + grade);

        input.close();
    }
}