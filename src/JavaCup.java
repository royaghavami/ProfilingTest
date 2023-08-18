import java.util.ArrayList;
import java.util.Scanner;

public class JavaCup {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press number1: ");
        int i = scanner.nextInt();
        System.out.println("Press number2: ");
        int j = scanner.nextInt();
        System.out.println("Press number3: ");
        int k = scanner.nextInt();
        temp();
        eval(i, j, k);
    }

    public static void eval(int i, int j, int k) {
        int iSquared = i * i;
        int jSquared = j * j;
        int kSquared = k * k;

        boolean condition1 = iSquared + jSquared == kSquared;
        boolean condition2 = iSquared == jSquared + kSquared;
        boolean condition3 = jSquared == iSquared + kSquared;

        StringBuilder output = new StringBuilder();
        if (condition1 && condition2 && condition3) {
            output.append("YES");
        } else {
            output.append("NO");
        }
        System.out.println(output.toString());
    }


    public static void temp() {
        ArrayList a = new ArrayList<>();
        int totalNumbers = 20000 * 10000;
        a.ensureCapacity(totalNumbers);
        for (int i = 0; i < totalNumbers; i++) {
            a.add(i % 20000 + i / 20000);
        }
    }

}
