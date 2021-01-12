import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

/**
 * In last week's assignment, you were asked to implement
 * relax() for both SP and SPD
 *
 * SP uses a Binary Heap, while SPD uses a D Heap. This
 * lab is more conceptual than practical. You will be
 * asked to time your implementations of SP and SPD and
 * compare the results. In order to get credit, you need
 * to:
 *
 * (1) complete large tests (the one listed is very
 * small and will not be sufficient for comparisons)
 *
 * (2) come up with the theoretical time complexities (in
 * terms of Big O) for SP and SPD.
 *
 * (3) decide which heap is best in practice
 *
 * (4) decide whether or not you can construct a graph for
 * which BinaryHeap is superior - if so, construct it,
 * otherwise, explain why.
 *
 * (5) decide whether or not you can construct a graph for
 * which DHeaps are better for various choices of d
 */

public class Main {
    public static void main(String[] args) {
        Item<String> a, b, c, d, e;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);

        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e));

        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b, c)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList()));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList()));

        Hashtable<Pair<Item<String>,Item<String>>,Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a,b),2);
        weights.put(new Pair<>(a,c),2);
        weights.put(new Pair<>(b,c),1);
        weights.put(new Pair<>(b,d),2);
        weights.put(new Pair<>(b,e),2);
        weights.put(new Pair<>(d,e),1);

        Graph<String> g;
        System.out.println("SP");
        g = new SP<>(neighbors,weights);
        g.setSource(nodes,a);

        long startTime = System.nanoTime();
        g.traverse();
        long endTime = System.nanoTime();
        long duration1 = (endTime - startTime) / 1000000;
        System.out.println("Time for SP: " + duration1);

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("SPD");
        g = new SPD<>(neighbors,weights);
        g.setSource(nodes,a);

        startTime = System.nanoTime();
        g.traverse();
        endTime = System.nanoTime();
        long duration2 = (endTime - startTime) / 1000000;
        System.out.println("Time for SPD: " + duration2);

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        /**
         * TODO 1: Make a big graph (~100-1000 nodes depending on your computer) to test these timings
         *
         * Hint: remember you can use Random to make the values of the weights. When you do this, make
         * sure the random numbers don't repeat- otherwise you might have repeated nodes and edges.
         * The hard part will be determining how to randomly assign neighbors to each node.
         *
         * Make sure to explain your strategy to your UIs.
         */

        Random r = new Random(1);
        ArrayList<Item<Integer>> ar = new ArrayList<>();
        Hashtable<Item<Integer>, ArrayList<Item<Integer>>> ht = new Hashtable<>();
        Hashtable<Pair<Item<Integer>, Item<Integer>>, Integer> htweights = new Hashtable<>();
        int max = 1000;
        int min = 1;
        for (int i = 0; i < 100; i++) {
            int num = r.nextInt(max) + min;
            Item<Integer> newit = new Item<>(num, "" + num, 0);
            while (ar.contains(newit)) {
                num = r.nextInt(max) + min;
                newit = new Item<>(num, "" + num, 0);
            }
            ar.add(newit);
        }

        for (int i =0; i < 100; i++) {
            int num2=r.nextInt(4-2+1)+2;
            ArrayList<Item<Integer>> neigh = new ArrayList<>();
            for (int j = 0; j < num2; j++) {
                int num3=r.nextInt(ar.size());
                Item<Integer> poss = ar.get(num3);
                while(neigh.contains(poss)) {
                    num3=r.nextInt(ar.size());
                    poss = ar.get(num3);
                }
                    neigh.add(poss);
                    int wei = r.nextInt(10);
                    Pair<Item<Integer>, Item<Integer>> pa = new Pair<>(ar.get(i), ar.get(num3));
                    htweights.put(pa, wei);
                }
            ht.put(ar.get(i), neigh);
        }
        /**
         * TODO 2: What are the theoretical complexities of SP and SPD? Demonstrate this empirically.
         *
         * You are free to test the timing of any of the heap methods in order to show that the
         * complexities you come up with are correct. This is an open-ended   question but in order to
         * earn full points you'll need to have a well-thought out answer with tests that support it.
         */
        /** deap will be faster O(log2(d) ) for sp, while spd will have O(logd(n))for spd
         *
         */
        Graph<Integer> gg;
        System.out.println("SP");
        gg = new SP<Integer>(ht,htweights);
        gg.setSource(ar,ar.get(0));

         startTime = System.nanoTime();
     gg.traverse();
         endTime = System.nanoTime();
         duration1 = (endTime - startTime) / 1000000;
        System.out.println("Time for SP: " + duration1);

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("SPD");
        gg = new SPD<Integer>(ht,htweights);
        gg.setSource(ar,ar.get(0));

        startTime = System.nanoTime();
       gg.traverse();
        endTime = System.nanoTime();
        duration2 = (endTime - startTime) / 1000000;
        System.out.println("Time for SPD: " + duration2);

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");
        /**
         * TODO 3: Which heap is better in practice?
         */
//Dheap is better in practice

        /**
         * TODO 4: Decide whether or not you can construct a graph for which BinaryHeap is superior
         * If so, construct it, otherwise, explain why.
         */
// to the big data binary heap will better to handle, to the small data , dheap will be better to handle .

        /**
         * TODO 5: Decide whether or not you can construct a graph for which DHeaps are better for various choices of d
         */
//  the d should not be too high or too small, since it will give a unnecessary complexiity in making for some graphs, 4 to 10 will be perfect
    }
}
