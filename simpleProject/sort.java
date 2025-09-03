package simpleProject;

public class sort {
    public static void main(String[] args) {
        int[] mylist = { 64, 34, 25, 12, 22, 11, 90, 5 };

        int n = mylist.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mylist[j] > mylist[j + 1]) {

                    int temp = mylist[j];
                    mylist[j] = mylist[j + 1];
                    mylist[j + 1] = temp;
                }
            }
        }

        for (int num : mylist) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
