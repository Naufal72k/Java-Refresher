package perulangan;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan jumlah pengulangan: ");
        int n = input.nextInt();

        System.out.println("\n=== Perulangan FOR ===");
        for (int i = 1; i <= n; i++) {
            System.out.println("Nopal ganteng ke-" + i);
        }

        System.out.println("\n=== Perulangan WHILE ===");
        int j = 1;
        while (j <= n) {
            System.out.println("Nopal ganteng ke-" + j);
            j++;
        }

        System.out.println("\n=== Perulangan DO-WHILE ===");
        int k = 1;
        do {
            System.out.println("Nopal ganteng ke-" + k);
            k++;
        } while (k <= n);
    }
}