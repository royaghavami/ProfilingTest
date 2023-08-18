
import java.util.HashMap;

public class NewFibonacciSequence {
    private static final HashMap memo = new HashMap<>();

    public static void main(String[] args) {
        int n = 50;
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return (int) memo.get(n);
        }
        int fib = fibonacci(n - 1) + fibonacci(n - 2);
        memo.put(n, fib);
        return fib;
    }
}
