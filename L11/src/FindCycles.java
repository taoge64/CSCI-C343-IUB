import java.util.*;

/**
 * Lab 11
 *
 * Today's lab will be focused on finding cycles in a
 * graph.
 *
 * You will implement findCycles() in the Graph class and use
 * the results from that to find the first 10 cycles
 * from the graph and print them.
 *
 */

public class FindCycles{
    public static void main(String[] args) {
        smallgraphnocycle();
        smallgraphcycle();
    }

    static void smallgraphnocycle () {
        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        ArrayList<Item<String>> nodes = new ArrayList<>(Arrays.asList(a, b, c));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a,new ArrayList<>(Arrays.asList(b,c)));
        neighbors.put(b,new ArrayList<>(Arrays.asList(c)));
        neighbors.put(c,new ArrayList<>(Arrays.asList()));

        DFS<String> g = new DFS<>(neighbors);
        g.setSource(nodes, a);
        System.out.printf("%n%nTraversing...%n");
        g.findCycles();

        nodes.forEach(Item::reset);
    }

    static void smallgraphcycle () {
        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        Item<String> d = new Item<>("d", "d", 0);
        Item<String> e = new Item<>("e", "e", 0);
        Item<String> g = new Item<>("g", "g", 0);
        Item<String> h = new Item<>("h", "h", 0);
        Item<String> i = new Item<>("i", "i", 0);
        Item<String> j = new Item<>("j", "j", 0);
        Item<String> k = new Item<>("k", "k", 0);
        Item<String> f = new Item<>("f", "f", 0);
        ArrayList<Item<String>> nodes = new ArrayList<>(Arrays.asList(a, b, c, d, e,g,h,i,j,k,f));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();

        // TODO: Write a test with a cycle

        neighbors.put(a,new ArrayList<>(Arrays.asList(c)));
        neighbors.put(c,new ArrayList<>(Arrays.asList(b)));
        neighbors.put(b,new ArrayList<>(Arrays.asList(g)));
        neighbors.put(g,new ArrayList<>(Arrays.asList(h)));
        neighbors.put(h,new ArrayList<>(Arrays.asList(i)));
        neighbors.put(i,new ArrayList<>(Arrays.asList(d)));
        neighbors.put(d,new ArrayList<>(Arrays.asList(c)));
        neighbors.put(d,new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e,new ArrayList<>(Arrays.asList(f)));
        neighbors.put(f,new ArrayList<>(Arrays.asList(j)));
        neighbors.put(j,new ArrayList<>(Arrays.asList(a)));
        DFS<String> gg = new DFS<>(neighbors);
        gg.setSource(nodes, c);
        System.out.printf("%n%nTraversing...%n");
        gg.findCycles();

        nodes.forEach(Item::reset);
    }

    static void largegraph() {
        ArrayList<Item<Integer>> nodes = new ArrayList<>(Arrays.asList());
        Random nodeGen = new Random(133);
        Item<String> a = new Item<>("a", "a", 0);
        Item<String> b = new Item<>("b", "b", 0);
        Item<String> c = new Item<>("c", "c", 0);
        Item<String> d = new Item<>("d", "d", 0);
        Item<String> e = new Item<>("e", "e", 0);


    }
}
