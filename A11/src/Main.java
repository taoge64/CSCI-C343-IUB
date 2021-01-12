import java.util.*;

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

        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 2);
        weights.put(new Pair<>(b, c), 1);
        weights.put(new Pair<>(b, d), 2);
        weights.put(new Pair<>(b, e), 2);
        weights.put(new Pair<>(d, e), 1);

        Graph<String> g;

        System.out.println("BFS");
        g = new BFS<>(neighbors);
        g.setSource(nodes, a);
        g.traverse();

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("DFS");
        g = new DFS<>(neighbors);
        g.setSource(nodes, a);
        g.traverse();

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("SP");
        g = new SP<>(neighbors, weights);
        g.setSource(nodes, a);
        g.traverse();

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("SPD");
        g = new SPD<>(neighbors, weights);
        g.setSource(nodes, a);
        g.traverse();

        nodes.forEach(Item::reset);
        System.out.printf("%n%n---------%n");

        System.out.println("MSP");
        g = new MSP<>(neighbors, weights);
        g.setSource(nodes, a);
        g.traverse();
    }
}

/* 
 * Expected output:
 * 
BFS
a[0]b[0]c[0]d[0]e[0]

---------
DFS
a[0]c[0]b[0]e[0]d[0]

---------
SP
a[0]b[2]c[2]d[4]e[4]

---------
SPD
a[0]b[2]c[2]d[4]e[4]

---------
MSP
a[0]b[2]c[1]d[2]e[1]


 *
 */
