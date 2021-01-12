import java.util.HashMap;
import java.util.Map;

/**
 * Implement a top-down memoized solution to the Longest Increasing
 * Subsequence problem, following the recursive algorithm described in 
 * lecture and practiced on A4.
 *
 * All of the work that you are to do is in the implementation of the
 * TopDown.lisHelper() function. However, read through the SubProblem
 * class before you start.
 */

/**
 * A SubProblem corresponds to one node in the decision tree. It is
 * described by two pieces of information: the index in the array of
 * the element under consideration (for inclusion in the sequence
 * being constructed), and the largest element in the sequence so far.
 */

class SubProblem {
    int pos;  // the index of the element under consideration
    int cap;  // the largest value in the sequence already selected

    /**
     * Constructs a problem from the given position and cap.
     */

    SubProblem(int pos, int cap) {
        this.pos = pos;
        this.cap = cap;
    }

    /**
     * Returns true iff the given object equals this object, field for field.
     * If we don't override this method, then the hash map will not be able to
     * find previously stored problems.
     */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SubProblem) {
            SubProblem that = (SubProblem) obj;
            return this.pos == that.pos && this.cap == that.cap;
        }
        return false;
    }

    /**
     * Returns a nicely packed version of this Subproblem. This promotes good
     * behavior of a hash map that uses Subproblems as keys.
     */

    @Override
    public int hashCode() {
        return pos << 16 | cap;
    }

    /**
     * Returns a sensible textual version of this Subproblem.
     */

    public String toString() {
        return String.format("(%d, %d)", pos, cap);
    }
}

public class TopDown {

    private static Map<SubProblem, Integer> cache;

    /**
     * Returns the length of the longest increasing subsequence in the
     * array a.
     */

    public static int lis(int[] a) {

        if(a.length == 0)
            return 0;

        cache = new HashMap<>(a.length);
        // Initially, we consider the element at position 0 and our cap,
        // so far, is 0.
        SubProblem p = new SubProblem(0, 0);
        return lisHelper(a, p);
    }

    /**
     * Returns the result of solving the SubProblem described by prob.
     */

    public static int lisHelper(int[] a, SubProblem prob) {
        Integer ans;
        // TODO: Check to see if the problem has previously been solved.
        if (cache.containsKey(prob)){
            ans=cache.get(prob);
            return ans;
        }
        // If so, return it.

        ans = 0;
        if (prob.pos < a.length) {
            int with = 0, without = 0;
            if(a[prob.pos]>prob.cap){
                SubProblem p= new SubProblem(prob.pos+1,a[prob.pos]);
                with=1+lisHelper(a,p);

            }


                SubProblem pp= new SubProblem(prob.pos+1,prob.cap);
                without=lisHelper(a,pp);

            // TODO: Recur to find the "with" and "without" results, so
            // we can use those results to build the answer ans.
            // Make sure to set ans equal to the results of with ans
            // without here.
            ans=Math.max(with,without);
        }

         cache.put(prob,ans);
        // TODO: Store this problem/answer association in the cache for the future

        return ans;
    }

    /**
     * Simple testing.
     */

    public static void main(String... args) {
        //System.out.println(new SubProblem(1, 2).equals("1"));
        int[] a;
        a = new int[] { 5, 6, 1, 2, 9, 3, 4, 7, 4, 3 };
        assert 5 == lis(a);

        System.out.println(cache);
        a = new int[] { 2, 1, 5, 3, 6, 4, 2, 7, 9, 11, 8, 10 };
        assert 6 == lis(a);
        a = new int[100];
        for (int i = 0; i < a.length; i++)
            a[i] = i + 1;
        assert a.length == lis(a);
        System.out.println("All tests passed...");
    }
}