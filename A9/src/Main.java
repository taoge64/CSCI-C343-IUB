import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {
    /* 
     * See main.png for expected answers to the examples in this file.
     */

    public static void main(String[] args) throws EmptyTreeE {
        XTree t = new XEmpty();
        t = t.insert(new Point(7, 2)).insert(new Point(5, 4)).insert(new Point(2, 3)).
                insert(new Point(4, 7)).insert(new Point(9, 6));
        System.out.println();
        TreePrinter.print(t);

        Rect region = new Rect(0, 0, 10, 10);

        Set<Point> s = t.rangeSearch(region, region);
        System.out.printf("Full range search = %s%n", s);

        Rect range = new Rect(0, 0, 3, 4);
        s = t.rangeSearch(range, region);
        System.out.printf("Range search in %s = %s%n", range, s);

        range = new Rect(4, 1, 8, 5);
        s = t.rangeSearch(range, region);
        System.out.printf("Range search in %s = %s%n", range, s);

        range = new Rect(3, 1, 8, 8);
        s = t.rangeSearch(range, region);
        System.out.printf("Range search in %s = %s%n", range, s);

        Point point = new Point(5, 5);
        Point c = t.nearestNeighbor(point, region, new HashSet<>());
        System.out.printf("Closest to %s is %s%n", point, c);
        Set<Point> neighbors = new HashSet<>();
        neighbors = t.nearestKNeighbors(point, 3, region);
        System.out.printf("Closest %d neighbors to %s are %s%n", 3, point, neighbors);
        neighbors = new HashSet<>();
        neighbors = t.nearestKNeighbors(point, 4, region);
        System.out.printf("Closest %d neighbors to %s are %s%n", 4, point, neighbors);
        for (int i = 0; i < 7; i++) {
            neighbors = t.nearestKNeighbors(point, i, region);
            System.out.printf("Closest %d neighbors to %s are %s%n", i, point, neighbors);
        }

        // Make big tree
        Random r = new Random(1);
        int bound = 10000;
        int numpoints = 1000000;
        region = new Rect(0, 0, bound, bound);
        t = new XEmpty();
        for (int i = 0; i < numpoints; i++)
            t = t.insert(new Point(r.nextInt(bound), r.nextInt(bound)));
        point = new Point(r.nextInt(bound), r.nextInt(bound));
        neighbors = t.nearestKNeighbors(point, 5, region);
        System.out.printf("Closest 5 neighbors to %s are %s%n", point, neighbors);

    }
}
