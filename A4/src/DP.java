import javax.swing.text.GapContent;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

class DP {

    /**
     * Trivial example to show the pattern: fib
     * First write the normal recursive solution
     */
    static long fib (int n) {
        if (n < 2) return n;
        else return fib(n-1) + fib (n- 2);
    }

    /**
     * Create a hash table: please call it fibMemo as we will refer
     * to it from the test suite.
     */
    static Map<Integer,Long> fibMemo = new WeakHashMap<>();

    /**
     * Use the method computeIfAbsent passing the naive recursive
     * computation as an argument. Do not forget to rename the
     * recursive calls to refer to the memoized version.
     *
     * There will typically be small additional tweaks to do. In
     * this case, I had to explicitly cast from int to long
     * in the return clause for the base case.
     */
    static long mfib (int n1) {
        return fibMemo.computeIfAbsent(n1, n -> {
            if (n < 2) return (long)n;
            else return mfib(n - 1) + mfib(n - 2);
        });
    }

    /**
     * For fun... Compute fib using the golden ratio.
     */
    static long ffib (int n) {
        double gold1=(1+Math.sqrt(5))/2;
        double gold2=(1-Math.sqrt(5))/2;
	return (long)((Math.pow(gold1,n)-Math.pow(gold2,n))/Math.sqrt(5));
    }

    // -----------------------------------------------------------------------------
    // Coin changing...
    // -----------------------------------------------------------------------------

    /**
     * Given a list of possible coins that must include a penny,
     * and a number of pennies 'n', in how many ways can we use the
     * coins to make change for 'n'.
     */
    static int coinChange (List<Coin> coins, int n) {
        try {
            if (n < 0) return 0; // no way to make change
            else if (n == 0) return 1; // previous choices succeeded
            else return coinChange(coins.getRest(), n) +
                        coinChange(coins,n - coins.getFirst().getValue());
        }
        catch (EmptyListE e) {
            return 0; // no way to make change
        }
    }

    /**
     * Again we create a hash table making sure it is called
     * coinChangeMemo. But here we have to think a bit. Each
     * subproblem is determined by the list of coins and by the
     * desired sum.  That combination should be the key used
     * in hashing.
     */
    static Map<Pair<List<Coin>,Integer>,Integer> coinChangeMemo = new WeakHashMap<>();

    /**
     * We again want to use computeIfAbsent. When we communicate with
     * the hash table, we combine all the information into a key and
     * take back apart when we extract from the hash table.
     */
     static int mcoinChange (List<Coin> coins1, int n1) {
        return coinChangeMemo.computeIfAbsent(new Pair<>(coins1,n1), p -> {
            List<Coin> coins = p.getFirst();
            int n = p.getSecond();
            try {
                if (n < 0) return 0; // no way to make change
                else if (n == 0) return 1; // previous choices succeeded
                else return mcoinChange(coins.getRest(), n) +
                            mcoinChange(coins, n - coins.getFirst().getValue());
            }
            catch (EmptyListE e) {
                return 0; // no way to make change
            }
        });
    }

    // -----------------------------------------------------------------------------
    // Partition ...
    // -----------------------------------------------------------------------------

    /**
     * Partition: take a list, a desired sum 's', and return
     * T/F depending on whether it is possible to find a
     * subsequence of the list whose sum is exactly 's'
     */
    static boolean partition (List<Integer> s, int sum) {
        try {
            if (sum==0){
                return true;
            }
            else if (s.isEmpty()){
                return false;
            }

            else if ((s.length()==1)&&(s.getFirst()!=sum)){
                return false;
            }
            else{
                return partition(s.getRest(), sum - s.getFirst())||partition(s.getRest(), sum) ;
        }
        }
        catch (EmptyListE e) {
            return false; // no way to make change
        }
    }
    static Map<Pair<List<Integer>,Integer>,Boolean> partitionMemo = new WeakHashMap<>();
    static boolean mpartition (List<Integer> s1, int sum1) {
        return partitionMemo.computeIfAbsent( new Pair<>(s1,sum1),sss -> {
            List<Integer> s= sss.getFirst();
            int sum=sss.getSecond();

                    try {
                        if (sum == 0) {
                            return true;
                        } else if (s.isEmpty()) {
                            return false;
                        } else {
                            return mpartition(s.getRest(), sum - s.getFirst()) || mpartition(s.getRest(), sum);
                        }
                    }
                    catch (EmptyListE e) {
                        return false; // no way to make change
                    }
    });
    }
    // -----------------------------------------------------------------------------
    // Min distance ...
    // -----------------------------------------------------------------------------

    final static int GAP = 2;
    final static int MATCH = 0;
    final static int MISMATCH = 1;

    enum BASE { A, T, G, C }

    static int minDistance (List<BASE> dna1, List<BASE> dna2) {
        if (dna1.isEmpty()){
            return GAP*dna2.length();
        }
        if (dna2.isEmpty()){
            return GAP*dna1.length();
        }
        try {
            BASE a= dna1.getFirst();
            BASE b= dna2.getFirst();

            if (a.equals(b)){
                return  MATCH+minDistance(dna1.getRest(), dna2.getRest());}
                int gap=GAP + Math.min(minDistance(dna1, dna2.getRest()), minDistance(dna1.getRest(), dna2));

                int mismatch=MISMATCH+minDistance(dna1.getRest(),dna2.getRest());
                return Math.min(gap,mismatch);//, GAP+ minDistance(dna1.getRest(), dna2));}
               } catch (EmptyListE e) {
                return -1;


        }
    }
    static Map<Pair<List<BASE>,List<BASE>>,Integer> minDistanceMemo = new WeakHashMap<>();
    //static Object minDistanceMemo = null;

    static int mminDistance (List<BASE> dna11, List<BASE> dna21) {
        return minDistanceMemo.computeIfAbsent(new Pair<>(dna11, dna21), costbdistance -> {
            List<BASE> dna111 = costbdistance.getFirst();
            List<BASE> dna222 = costbdistance.getSecond();
            if (dna111.isEmpty()){
                return GAP*dna222.length();
            }
            if (dna222.isEmpty()){
                return GAP*dna111.length();
            }
            try {
                BASE a= dna111.getFirst();
                BASE b= dna222.getFirst();

                if (a.equals(b)){
                    return  MATCH+mminDistance(dna111.getRest(), dna222.getRest());}
                int gap=GAP + Math.min(mminDistance(dna111, dna222.getRest()), mminDistance(dna111.getRest(), dna222));

                int mismatch=MISMATCH+mminDistance(dna111.getRest(),dna222.getRest());
                return Math.min(gap,mismatch);//, GAP+ minDistance(dna1.getRest(), dna2));}
            } catch (EmptyListE e) {
                return -1;


            }

        });
    }

    // -----------------------------------------------------------------------------
    // Longest common subsequence ...
    // -----------------------------------------------------------------------------

    static List<Character> lcs (List<Character> cs1, List<Character> cs2) {
        try{
            Character a= cs1.getFirst();
            Character b= cs2.getFirst();
            if (a.equals(b)){
                return new Node<>(a,lcs(cs1.getRest(),cs2.getRest()));
            }
            else{
                List<Character> l1=lcs(cs1.getRest(),cs2);
                List<Character> l2=lcs(cs1,cs2.getRest());
                if (l2.length()>l1.length())
                    return l2;
                return l1;

            }
        }
        catch (EmptyListE   e){
            return new Empty<>();
        }
    }

    //static Object lcsMemo = null;
    static Map<Pair<List<Character>,List<Character>>,List<Character>> lcsMemo = new WeakHashMap<>();
    static List<Character> mlcs (List<Character> cs11, List<Character> cs21) {
        return lcsMemo.computeIfAbsent(new Pair<>(cs11, cs21), cs -> {
            List<Character> cs111 = cs.getFirst();
            List<Character> cs222 = cs.getSecond();
            try{
                Character a= cs111.getFirst();
                Character b= cs222.getFirst();
                if (a.equals(b)){
                    return new Node<>(a,mlcs(cs111.getRest(),cs222.getRest()));
                }
                else{
                    List<Character> l1=mlcs(cs111.getRest(),cs222);
                    List<Character> l2=mlcs(cs111,cs222.getRest());
                    if (l2.length()>l1.length())
                        return l2;
                    return l1;

                }
            }
            catch (EmptyListE   e){
                return new Empty<>();
            }
        });
    }
}
