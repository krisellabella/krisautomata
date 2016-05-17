import java.util.ArrayList;
import java.util.Scanner;
public class Rule30 {
    public static void main (String[] args) {
        int count = 50;
        int size = 50;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of iterations:");
        count = scan.nextInt();
        System.out.println("Enter size of a line:");
        size = scan.nextInt();
        System.out.println("------------- RULE 30 -----------");
        ArrayList line = new ArrayList();
        ArrayList base = new ArrayList();

        for (int i = 0; i <= size; i++) {
            base.add(0);
            line.add(0);
        }

        base.set((size / 2), 1);
        for (int j = 0; j < size; j++) {
            System.out.print(base.get(j));
        }

        System.out.println();

        for (int k = 0; k < count - 1; k++) {
            for (int m = 1; m < size; m++) {
                if (base.get(m - 1).equals(0) == true && base.get(m).equals(0) == true && base.get(m + 1).equals(1) == true
                        || base.get(m - 1).equals(0) == true && base.get(m).equals(1) == true && base.get(m + 1).equals(0) == true
                        || base.get(m - 1).equals(0) == true && base.get(m).equals(1) == true && base.get(m + 1).equals(1) == true
                        || base.get(m - 1).equals(1) == true && base.get(m).equals(0) == true && base.get(m + 1).equals(0) == true) {
                        line.set(m, 1);
                } else {
                    line.set(m, 0);
                }
            }

            for (int n = 0; n < size; n++) {
                System.out.print(line.get(n));
                base.set(n, line.get(n));
            }

            System.out.println();
        }
        System.out.println("---------------------------------");

    }
}