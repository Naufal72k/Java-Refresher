package Kalkulator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kalkulator kalkul = new Kalkulator();
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan angka pertama: ");
        int a = input.nextInt();
        System.out.print("Masukkan angka kedua: ");
        int b = input.nextInt();

        kalkul.tambah(a, b);
        kalkul.kurang(a, b);
        kalkul.kali(a, b);

        input.close();
    }
}