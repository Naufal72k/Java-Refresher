package pertemuanArray;

public class array {
    public static void main(String[] args) {
        String[][] negaraIbukota = {
                { "indonesia", "Jakarta" },
                { "jepang", "tokyo" },
                { "Amerika", "washington Dc" }
        };

        for (int i = 0; i < negaraIbukota.length; i++) {
            System.out.println(" ibu kota " + negaraIbukota[i][0] + " adalah " + negaraIbukota[i][1]);

        }
    }
}