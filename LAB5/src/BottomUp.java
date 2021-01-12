/**
 * Implement a bottom-up solution to the Longest Increasing Subsequence
 * problem, following the algorithm described in lab.
 */

/**
 * A subproblem is to compute the length of the longest increasing
 * subsequence that ends with the i-th element in the array. The
 * result of solving such a subproblem is the score (i.e., the length
 * of the sequence) and the parent (i.e., the index of element in the
 * array that precedes the i-th one in the identified sequence).
 */

class Result {
    int score;
    int parent;

    Result(int score, int parent) {
        this.score = score;
        this.parent = parent;
    }

    public String toString() {
        return String.format("(%d,%d)", score, parent);
    }
}

public class BottomUp {

    /**
     * Returns an array consisting of the longest increasing subsequence
     * in a. Your solution must follow the code sketch given in lab.
     */

    public static int[] lis(int[] a) {
        int n = a.length;
if (n==0){
    return new int[0];
}
        // TODO: What is the base case you should check for?

        Result[] cache = new Result[n];
        cache[0]= new Result(1,-1);
        // TODO: What is the first entry in the cache?
        // Hint: All other entries in the table build off of this
        // Remember, an entry in the cache is a Result object, which has two pieces.
        for (int i=1; i<a.length;i++){
            int current= a[i];
            int currentscore=1;
            int currentparents=-1;
            for ( int j=0; j<i;j++){
                if ((current>a[j])
                    &&(cache[j].score>currentscore)){
                    currentscore=cache[j].score;
                    currentparents=j;
                }
            }
            cache[i]=new Result(currentscore,currentparents);
        }

        // TODO: Now, build the table.
        // Some steps:
        // 1. You know you'll need to loop through every index i in the table, and for each one,
        //    calculate a score (the LIS that ends at that index) and the parent (the previous
        //    element in that LIS)
        // 2. To calculate the score, you need the MAX SCORE over ALL indices (j) leading up to i
        //    -> So, you need to think about how to find the index j with the "best" score
        // 3. Once you find the best score, create a Result containing that score and the corresponding
        //    parent, and set the cache at index i
        int greatlocation=0;
        int checkpoint=cache[0].score;
        for (int i=0;i<cache.length;i++){

        if (cache[i].score>checkpoint){
            greatlocation=i;
        }
}
        int[] result= new int[cache[greatlocation].score];
        int length=cache[greatlocation].score;

        for ( int j=0; j<1;j++){
            result[j]=a[cache[greatlocation].parent];}
        return result;
        // TODO: Traceback.
        // Now that you have the cache with all the information, you need to traceback to get the
        // actual sequence.
        // 1. Start by finding the MAX SCORE in the cache. This is where you will start tracing back.
        //    Keep in mind, this could be anywhere in the cache.
        // 2. Start at the "best" score, then trace back, jumping to the index that is the parent of the
        //    previous. Return this sequence.

    }

    /**
     * Simple testing.
     */

    public static void main(String... args) {
        int[] a, b;
        a = new int[] { 5, 6, 1, 2, 9, 3, 4, 7, 4, 3 };
        b = lis(a);
        System.out.println(b);
        /*
        assert 5 == b.length;
        assert 1 == b[0];
        assert 2 == b[1];
        assert 3 == b[2];
        assert 4 == b[3];
        assert 7 == b[4];
        a = new int[] { 2, 1, 5, 3, 6, 4, 2, 7, 9, 11, 8, 10 };
        b = lis(a);
        assert 6 == b.length;
        assert 2 == b[0];
        assert 5 == b[1];
        assert 6 == b[2];
        assert 7 == b[3];
        assert 9 == b[4];
        assert 11 == b[5];
        a = new int[] { 1, 2, 3, 4, 5 };
        b = lis(a);
        assert a.length == b.length;
        a = new int[] { 5, 4, 3, 2, 1 };
        b = lis(a);
        assert 1 == b.length;
        System.out.println("All tests passed...");
    */
    }
}
