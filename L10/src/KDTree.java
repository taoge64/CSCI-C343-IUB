import java.util.Set;

class EmptyTreeE extends Exception {}

interface KDTree extends TreePrinter.PrintableNode {
    Point getPoint() throws EmptyTreeE;
    KDTree getChild1() throws EmptyTreeE;
    KDTree getChild2() throws EmptyTreeE;
    boolean isEmpty();
    KDTree insert(Point p);
    boolean find(Point p);
    Rect getRegion1(Rect region) throws EmptyTreeE;
    Rect getRegion2(Rect region) throws EmptyTreeE;
    Set<Point> rangeSearch(Rect range, Rect region);
    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;
    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;
}