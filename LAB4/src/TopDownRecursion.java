/**
 * This is your first task.
 *
 * Solve this problem recursively.
 */

public class TopDownRecursion {
    public static int countWays(int coins[], int l, int n){
      if (n<0){
          return 0;
      }
      else if(n==0){
          return 1;
      }
      else if (l<=0){
          return 0;
      }
      else{
          return countWays(coins,l,n-coins[l-1])+countWays(coins,l-1,n);
      }
    }

    /**
     * Simple main method tests are given, but you should write
     * additional tests.
     */
    public static void main(String[] args) {
        int coins[] = {1, 5, 10, 25};
        int l = coins.length;
        assert(countWays(coins, l, 30) == 18);

        // how does this do with n = 5000?
    }
}
