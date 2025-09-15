package Nilai;

public class CekNilai {
    public String getGrade(int nilai) {
        if (nilai >= 85 && nilai <= 100) {
            return "A";
        } else if (nilai >= 70 && nilai <= 84) {
            return "B";
        } else if (nilai >= 60 && nilai <= 69) {
            return "C";
        } else if (nilai >= 50 && nilai <= 59) {
            return "D";
        } else
            return "E";
    }
}