import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class EmptyTreeE extends Exception {
}

abstract class XTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract Point getPoint() throws EmptyTreeE;

    abstract YTree getLeftT() throws EmptyTreeE;

    abstract YTree getRightT() throws EmptyTreeE;

    abstract boolean isEmpty();

    //--------------------------
    // Insertion and queries
    //--------------------------

    abstract XTree insert(Point p);

    abstract boolean find(Point p);

    abstract Set<Point> rangeSearch(Rect range, Rect region);

    abstract Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;

    abstract Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;
}

abstract class YTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract Point getPoint() throws EmptyTreeE;

    abstract XTree getUnderT() throws EmptyTreeE;

    abstract XTree getAboveT() throws EmptyTreeE;

    abstract boolean isEmpty();

    //--------------------------
    // Insertion and queries
    //--------------------------

    abstract YTree insert(Point p);

    abstract boolean find(Point p);

    abstract Set<Point> rangeSearch(Rect range, Rect region);

    abstract Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE;

    abstract Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE;

}

//-----------------------------------------------------------------------
// Empty trees

class XEmpty extends XTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    YTree getLeftT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    YTree getRightT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    XTree insert(Point p) {
        return new XNode(p, new YEmpty(), new YEmpty());
    }

    boolean find(Point p) {
        return false;
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        return new HashSet<>();
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

}

class YEmpty extends YTree {

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return "";
    }

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    XTree getUnderT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    XTree getAboveT() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    YTree insert(Point p) {
        return new YNode(p, new XEmpty(), new XEmpty());
    }

    boolean find(Point p) {
        return false;
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        return new HashSet<>();
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        throw new EmptyTreeE();
    }
}

//-----------------------------------------------------------------------
// Non-Empty trees

class XNode extends XTree {
    private Point point;
    private YTree left, right;

    XNode(Point point, YTree left, YTree right) {
        this.point = point;
        this.left = left;
        this.right = right;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() {
        return point;
    }

    YTree getLeftT() {
        return left;
    }

    YTree getRightT() {
        return right;
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    XTree insert(Point p) {
        if (p.getX() < point.getX())
            return new XNode(point, left.insert(p), right);
        else
            return new XNode(point, left, right.insert(p));
    }

    boolean find(Point p) {
        if (p == point) return true;
        if (p.getX() < point.getX()) return left.find(p);
        else return right.find(p);
    }

    /*
     * We want to find all the points that lie inside the rectangle
     * 'range'. The parameter 'region' is the entire region
     * represented by this tree.
     *
     * The idea is to check if the region represented by this tree
     * intersects the range of interest. If there is no intersection,
     * we return an empty set. Otherwise, we search this tree in
     * detail as follows. We split the region represented by this tree
     * into a left region and a right region around the current point
     * and make recursive calls. The find set is the union of the the
     * two sets produced by the recursive calls additionally including the
     * current point if it is contained in the desired range.
     */
    Set<Point> rangeSearch(Rect range, Rect region) {
        if (!region.intersect(range)){
            return new HashSet<>();
        }
        else{
            Set<Point> left=this.getLeftT().rangeSearch(range,region.leftOf(this.point));
            Set<Point> right=this.getRightT().rangeSearch(range,region.rightOf(this.point));
            if (range.contains(this.point)){
                left.add(this.point);
            }
            left.addAll(right);
        return left;//how to combine two set(which is left and right)
        }
    }

    /*
     * This is the most complicated method in this assignment.
     *
     * The three parameters are as follows. We are looking for the
     * nearest neighbor of p in the given region but excluding every
     * point in the set 'excludes'.
     *
     * Here is the strategy I recommend:
     *
     * - start with a safe appoximation for the 'result' and
     *   'distance' by creating a new point at "infinity": use
     *   Integer.MAX_VALUE for the coordinate and use
     *   Integer.MAX_VALUE as the distance. We will now try to refine
     *   this approximation by trying to find a better point as
     *   'result' and update 'distance' accordingly. If however there
     *   are no more points, this bad approximation will be our final
     *   result. In other words, we may get the point at infinity as
     *   the nearest neighbor if no other points remain.
     *
     * - the first refinement we can try to 'result' and 'distance' is
     *   to check if we can use the current point (the root of the
     *   current tree). If that point is not in the 'excludes' set,
     *   then we can use and we can use it to update 'result' and
     *   'distance'.
     *
     * - The next refinement is to try to find a closer point by
     *   recursively visiting the subtrees. But which subtree to visit
     *   first? If the point p happens to be to the left of the
     *   current point, then it is more likely that its closest
     *   neighbor is to the left, so we formulate a plan to visit the
     *   left subtree first and then the right subtree. Otherwise we
     *   try to visit the right subtree first and then the left
     *   subtree.
     *
     * - Now we put our plan into action. We want to make a recursive
     *   call to one of the subtrees hoping to improve our
     *   approximation. However, sometimes we might be able to detect,
     *   before making the recursive call, that the call will be
     *   useless and can be avoided. This happens if the closest
     *   distance from p to the region represented by the subtree is
     *   greater than or equal to the current approximation. In other
     *   words, every point in that region is farther than our current
     *   approximation so there is no reason to visit that subtree at
     *   all.
     *
     * - If we decide that it is worth it to visit the subtree, then
     *    we making the recursive call, and update our approximation
     *    if the recursive call returns a point that is closer to p.
     *
     */
    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        int smallestdistance = Integer.MAX_VALUE;
        Point result = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
      if (!excludes.contains(this.getPoint())) {
            result = this.getPoint();
            smallestdistance = this.getPoint().distanceSquaredTo(p);
        }
        try {
            int firstregionDist;
            int secondregionDist ;
             Rect firstregion,secondregion;
            YTree first,second;



            if (p.getX() < this.getPoint().getX()) {
                first=this.getLeftT();
                second=this.getRightT();
                 firstregionDist = region.leftOf(this.point).distanceSquaredTo(p);
                 secondregionDist = region.rightOf(this.point).distanceSquaredTo(p);
                 firstregion=region.leftOf(this.point);
                 secondregion=region.rightOf(this.point);
            } else {
                second=this.getLeftT();
                first=this.getRightT();
                secondregionDist = region.leftOf(this.point).distanceSquaredTo(p);
                firstregionDist = region.rightOf(this.point).distanceSquaredTo(p);
                secondregion=region.leftOf(this.point);
                firstregion=region.rightOf(this.point);
            }
            if (firstregionDist<smallestdistance){
                Point p1=first.nearestNeighbor(p,firstregion,excludes);
                if(p1.distanceSquaredTo(p)<smallestdistance){
                    result=p1;
                    smallestdistance=p1.distanceSquaredTo(p);
                }
            }
            if (secondregionDist<smallestdistance){
                Point p2=second.nearestNeighbor(p,secondregion,excludes);
                if (p2.distanceSquaredTo(p)<smallestdistance){
                    result=p2;
                    smallestdistance=p2.distanceSquaredTo(p);
                }
            }

        }catch(EmptyTreeE e){
        }
        return result;
    }

    /*
     * If your nearestNeighbor works correctly, this method is
     * relatively easy. You repeatedly compute the nearest neighbor
     * making sure each successive call excludes the previous
     * neighbors from the search.
     */
//    Set<Point> exclude=new HashSet<>();
//    Point current;
//    Set<Point> result= new HashSet<>();
    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        Set<Point> excludes = new HashSet<>();

        if(k == 0)
            return new HashSet<>();
        for(int i = 0; i < k; i++){
            Point t = nearestNeighbor(p, region, excludes);
            excludes.add(t);
        }
        return excludes;
    }
}

class YNode extends YTree {
    private Point point;
    private XTree under, above;

    YNode(Point point, XTree under, XTree above) {
        this.point = point;
        this.under = under;
        this.above = above;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public String getText() {
        return point.toString();
    }

    public TreePrinter.PrintableNode getLeft() {
        return under;
    }

    public TreePrinter.PrintableNode getRight() {
        return above;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    Point getPoint() {
        return point;
    }

    XTree getUnderT() {
        return under;
    }

    XTree getAboveT() {
        return above;
    }

    boolean isEmpty() {
        return false;
    }

    //--------------------------
    // Insertion and queries
    //--------------------------

    YTree insert(Point p) {
        if (p.getY() < point.getY())
            return new YNode(point, under.insert(p), above);
        else
            return new YNode(point, under, above.insert(p));
    }

    boolean find(Point p) {
        if (p == point) return true;
        if (p.getY() < point.getY()) return under.find(p);
        else return above.find(p);
    }

    Set<Point> rangeSearch(Rect range, Rect region) {
        if (!region.intersect(range)){
            return new HashSet<>();
        }
        else{
            Set<Point> above=this.getAboveT().rangeSearch(range,region.aboveOf(this.point));
            Set<Point> under=this.getUnderT().rangeSearch(range,region.underOf(this.point));
            if (range.contains(this.point)){
                above.add(this.point);

            }
            above.addAll(under);
            return above;
        }
    }

    Point nearestNeighbor(Point p, Rect region, Set<Point> excludes) throws EmptyTreeE {
        int smallestdistance = Integer.MAX_VALUE;
        Point result = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        if (!excludes.contains(this.getPoint())) {
            result = this.getPoint();
            smallestdistance = this.getPoint().distanceSquaredTo(p);
        }
        try {
            int firstDist;
            int secondDist;
            XTree first,second;
            Rect firstregion,secondregion;
            if (p.getY() < this.getPoint().getY()) {
                first=this.getUnderT();
                second=this.getAboveT();
                firstDist = region.underOf(this.point).distanceSquaredTo(p);
                secondDist=region.aboveOf(this.point).distanceSquaredTo(p);
                firstregion=region.underOf(this.point);
                secondregion=region.aboveOf(this.point);
            } else {
                first=this.getAboveT();
                second=this.getUnderT();
                firstDist = region.aboveOf(this.point).distanceSquaredTo(p);
                secondDist=region.underOf(this.point).distanceSquaredTo(p);
                secondregion=region.underOf(this.point);
                firstregion=region.aboveOf(this.point);
            }
            if (firstDist<smallestdistance) {
                Point p1 = first.nearestNeighbor(p, firstregion, excludes);
                if (p1.distanceSquaredTo(p) < smallestdistance) {
                    result = p1;
                    smallestdistance = p1.distanceSquaredTo(p);
                }
            }
            if (secondDist<smallestdistance){
                Point p2=second.nearestNeighbor(p,secondregion,excludes);
                if (p2.distanceSquaredTo(p)<smallestdistance){
                    result=p2;
                    smallestdistance=p2.distanceSquaredTo(p);
                }
            }


        }catch(EmptyTreeE e){
        }
        return result;
    }
    Set<Point> nearestKNeighbors(Point p, int k, Rect region) throws EmptyTreeE {
        Set<Point> excludes = new HashSet<>();

        if(k == 0)
            return new HashSet<>();
        for(int i = 0; i < k; i++){
            Point t = nearestNeighbor(p, region, excludes);
            excludes.add(t);
        }
        return excludes;
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------


