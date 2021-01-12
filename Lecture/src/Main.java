import java.util.Map;
import java.util.WeakHashMap;

public class Main {
    private static Map<Integer,Long> memo = new WeakHashMap<>();

    public static long fib (int n) {
        if (n < 2) return n;
        else return fib(n-1) + fib(n- 2);
    }

    public static long mfib (int n) {
        if (memo.containsKey(n)) return memo.get(n);
        /* original code but rename recursive calls */
        long res = 0;
        if (n < 2) res = n;
        else res = mfib(n-1) + mfib(n- 2);
        /* end of original code */
        memo.put(n,res);
        return res;
    }

    public static long mfib2 (int n) {
        return memo.computeIfAbsent(n, i -> {
            /* original code goes as an argument to computeIfAbsent */
            long res = 0;
            if (i < 2) res = n;
            else res = mfib2 (i-1) + mfib2 (i-2);
            return res;
        });
    }

    public static void main(String[] args) {
        System.out.println(mfib(500));
        System.out.println(mfib2(500));
        System.out.println(fib(5));
    }



    // Map.computeIfAbsent
}
