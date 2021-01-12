import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testPoint() {
        Point p1, p2, p3, p4,p5,p6;
        p1 = new Point(0, 0);
        p2 = new Point(3, 4);
        p3 = new Point(1, 2);
        p4 = new Point(1, 2);
        Assert.assertEquals(3, p2.getX());
        Assert.assertEquals(4, p2.getY());
        Assert.assertEquals(25, p1.distanceSquaredTo(p2));
        Assert.assertEquals(25, p2.distanceSquaredTo(p1));
        Assert.assertEquals(0, p1.distanceSquaredTo(p1));
        Assert.assertEquals(0, p2.distanceSquaredTo(p2));
        Assert.assertEquals(p3, p4);
        Assert.assertEquals(p3.hashCode(), p4.hashCode());
//mytest on points
        p1 = new Point(3, 2);
        p2 = new Point(5, 2);
        p3 = new Point(4, 5);
        p4 = new Point(4, 5);
        p5 = new Point(3, 2);
        p6 = new Point(5, 2);
        Assert.assertEquals(5, p2.getX());
        Assert.assertEquals(2, p2.getY());
        Assert.assertEquals(4, p3.getX());
        Assert.assertEquals(5, p3.getY());
        Assert.assertEquals(3, p1.getX());
        Assert.assertEquals(2, p1.getY());
        Assert.assertEquals(4, p1.distanceSquaredTo(p2));
        Assert.assertEquals(4, p2.distanceSquaredTo(p1));
        Assert.assertEquals(10,p2.distanceSquaredTo(p3));
        Assert.assertEquals(0, p1.distanceSquaredTo(p1));
        Assert.assertEquals(0, p2.distanceSquaredTo(p2));
        Assert.assertEquals(0,p3.distanceSquaredTo(p3));
        Assert.assertEquals(p3, p4);
        Assert.assertEquals(p3.hashCode(), p4.hashCode());
        Assert.assertEquals(p1, p5);
        Assert.assertEquals(p1.hashCode(), p5.hashCode());
        Assert.assertEquals(p2, p6);
        Assert.assertEquals(p2.hashCode(), p6.hashCode());
    }

    @Test
    public void testEdge() {
        Point p1, p2, p3, p4;
        p1 = new Point(0, 0);
        p2 = new Point(3, 4);
        p3 = new Point(1, 2);
        p4 = new Point(1, 2);

        Edge e1, e2, e3, e4,e5,e6,e7;
        e1 = new Edge(p1, p2);
        e2 = new Edge(p3, p3);
        e3 = new Edge(p2, p4);
        e4 = new Edge(p2, p3);

        Assert.assertEquals(e1.getFrom(), p1);
        Assert.assertEquals(e1.getTo(), p2);
        Assert.assertEquals(e2.getFrom(), p3);
        Assert.assertEquals(e2.getFrom(), p4);
        Assert.assertEquals(e2.getFrom(), e2.getTo());
        Assert.assertEquals(e3, e4);
        Assert.assertEquals(e3.hashCode(), e4.hashCode());
//mytest on edges
        p1 = new Point(1, 5);
        p2 = new Point(8, 9);
        p3 = new Point(3, 4);
        p4 = new Point(3, 4);
        e1 = new Edge(p1, p4);
        e2 = new Edge(p2, p2);
        e3 = new Edge(p3, p4);
        e4 = new Edge(p4, p3);
        e5 = new Edge(p4, p3);
        e6 = new Edge(p2, p2);
        e7 = new Edge(p3, p4);
        Assert.assertEquals(e1.getFrom(), p1);
        Assert.assertEquals(e1.getTo(), p4);
        Assert.assertEquals(e2.getFrom(), p2);
        Assert.assertEquals(e2.getTo(), p2);
        Assert.assertEquals(e3.getFrom(), p3);
        Assert.assertEquals(e1.getTo(), e3.getTo());
        Assert.assertEquals(e5, e4);
        Assert.assertEquals(e4.hashCode(), e5.hashCode());
        Assert.assertEquals(e6, e2);
        Assert.assertEquals(e6.hashCode(), e2.hashCode());
        Assert.assertEquals(e7, e3);
        Assert.assertEquals(e7.hashCode(), e3.hashCode());
        e4=e1.flip();
        e2=e2.flip();
        e3=e3.flip();
        Assert.assertEquals(e6.getTo(),e2.getFrom());
        Assert.assertEquals(e6.getFrom(),e2.getTo());
        Assert.assertEquals(e7.getTo(),e3.getFrom());
        Assert.assertEquals(e7.getFrom(),e3.getTo());
        Assert.assertEquals(p4,e4.getFrom());
        Assert.assertEquals(p1,e4.getTo());
    }

    @Test
    public void testRect() {
        Rect r1, r2, r3, r4, r5, r6, r7, r8;
        r1 = new Rect(0, 0, 4, 5);
        r2 = new Rect(1, 2, 3, 3);
        r3 = new Rect(2, 1, 5, 4);
        r4 = new Rect(6, 2, 7, 3);

        r5 = new Rect(0, 0, 2, 5);
        r6 = new Rect(2, 0, 4, 5);
        r7 = new Rect(0, 0, 4, 2);
        r8 = new Rect(0, 2, 4, 5);

        Assert.assertEquals(r3.getXmin(), 2);
        Assert.assertEquals(r3.getYmin(), 1);
        Assert.assertEquals(r3.getXmax(), 5);
        Assert.assertEquals(r3.getYmax(), 4);

        Point p = new Point(2, 2);
        Assert.assertEquals(r5, r1.leftOf(p));
        Assert.assertEquals(r6, r1.rightOf(p));
        Assert.assertEquals(r7, r1.underOf(p));
        Assert.assertEquals(r8, r1.aboveOf(p));

        Assert.assertTrue(r1.contains(p));
        Assert.assertTrue(r2.contains(p));
        Assert.assertTrue(r3.contains(p));
        Assert.assertFalse(r4.contains(p));

        Assert.assertTrue(r1.intersect(r1));
        Assert.assertTrue(r1.intersect(r2));
        Assert.assertTrue(r1.intersect(r3));
        Assert.assertFalse(r1.intersect(r4));

        Assert.assertTrue(r2.intersect(r1));
        Assert.assertTrue(r2.intersect(r2));
        Assert.assertTrue(r2.intersect(r3));
        Assert.assertFalse(r2.intersect(r4));

        Assert.assertTrue(r3.intersect(r1));
        Assert.assertTrue(r3.intersect(r2));
        Assert.assertTrue(r3.intersect(r3));
        Assert.assertFalse(r3.intersect(r4));

        Assert.assertFalse(r4.intersect(r1));
        Assert.assertFalse(r4.intersect(r2));
        Assert.assertFalse(r4.intersect(r3));
        Assert.assertTrue(r4.intersect(r4));

        Assert.assertEquals(0, r3.distanceSquaredTo(new Point(3, 1)));
        Assert.assertEquals(4, r3.distanceSquaredTo(new Point(0, 2)));
        Assert.assertEquals(1, r3.distanceSquaredTo(new Point(3, 0)));
        Assert.assertEquals(4, r3.distanceSquaredTo(new Point(7, 3)));
        Assert.assertEquals(36, r3.distanceSquaredTo(new Point(2, 10)));
        Assert.assertEquals(5, r3.distanceSquaredTo(new Point(0, 0)));
        Assert.assertEquals(5, r3.distanceSquaredTo(new Point(0, 5)));
        Assert.assertEquals(5, r3.distanceSquaredTo(new Point(7, 0)));
        Assert.assertEquals(2, r3.distanceSquaredTo(new Point(6, 5)));

        //my test on rect
        r1 = new Rect(0, 0, 7, 8);
        r2 = new Rect(2, 3, 4, 5);
        r3 = new Rect(1, 0, 5, 5);
        r4 = new Rect(10, 5, 12, 6);

        r5 = new Rect(0, 0, 2, 8);
        r6 = new Rect(2, 0, 7, 8);
        r7 = new Rect(0, 0, 7, 2);
        r8 = new Rect(0, 2, 7, 8);

        Assert.assertEquals(r3.getXmin(), 1);
        Assert.assertEquals(r3.getYmin(), 0);
        Assert.assertEquals(r3.getXmax(), 5);
        Assert.assertEquals(r3.getYmax(), 5);

        p = new Point(2, 2);
        Assert.assertEquals(r5, r1.leftOf(p));
        Assert.assertEquals(r6, r1.rightOf(p));
        Assert.assertEquals(r7, r1.underOf(p));
        Assert.assertEquals(r8, r1.aboveOf(p));

        Assert.assertTrue(r1.contains(p));
        Assert.assertFalse(r2.contains(p));
        Assert.assertTrue(r3.contains(p));
        Assert.assertFalse(r4.contains(p));

        Assert.assertTrue(r1.intersect(r1));
        Assert.assertTrue(r1.intersect(r2));
        Assert.assertTrue(r1.intersect(r3));
        Assert.assertFalse(r1.intersect(r4));

        Assert.assertTrue(r2.intersect(r1));
        Assert.assertTrue(r2.intersect(r2));
        Assert.assertTrue(r2.intersect(r3));
        Assert.assertFalse(r2.intersect(r4));

        Assert.assertTrue(r3.intersect(r1));
        Assert.assertTrue(r3.intersect(r2));
        Assert.assertTrue(r3.intersect(r3));
        Assert.assertFalse(r3.intersect(r4));

        Assert.assertFalse(r4.intersect(r1));
        Assert.assertFalse(r4.intersect(r2));
        Assert.assertFalse(r4.intersect(r3));
        Assert.assertTrue(r4.intersect(r4));

        Assert.assertEquals(0, r3.distanceSquaredTo(new Point(3, 1)));
        Assert.assertEquals(1, r3.distanceSquaredTo(new Point(0, 2)));
        Assert.assertEquals(0, r3.distanceSquaredTo(new Point(3, 0)));
        Assert.assertEquals(4, r3.distanceSquaredTo(new Point(7, 3)));
        Assert.assertEquals(25, r3.distanceSquaredTo(new Point(2, 10)));

    }

    @Test
    public void testRegion() {
        Region r1, r2;
        r1 = new Region();
        r2 = new Region();

        Assert.assertTrue(r1.isEmpty());
        Assert.assertTrue(r2.getDirs().isEmpty());

        r1.push(DIR.LEFT);
        Assert.assertFalse(r1.isEmpty());
        Assert.assertTrue(r1.getDirs().contains(DIR.LEFT));
        Assert.assertTrue(r2.getDirs().isEmpty());

        r1.push(DIR.UNDER);
        Assert.assertEquals(r1.getDirs().get(0), DIR.UNDER);
        Assert.assertEquals(r1.getDirs().get(1), DIR.LEFT);

        r1.push(DIR.RIGHT);
        r2.push(DIR.LEFT);
        r2.push(DIR.UNDER);
        r2.push(DIR.RIGHT);
        Assert.assertEquals(r1, r2);
        Assert.assertEquals(r1.hashCode(), r2.hashCode());
//mytest on region
        r1 = new Region();
        r2 = new Region();

        Assert.assertTrue(r1.isEmpty());
        Assert.assertTrue(r2.getDirs().isEmpty());

        r1.push(DIR.RIGHT);
        Assert.assertFalse(r1.isEmpty());
        Assert.assertTrue(r1.getDirs().contains(DIR.RIGHT));
        Assert.assertTrue(r2.getDirs().isEmpty());

        r1.push(DIR.ABOVE);
        Assert.assertEquals(r1.getDirs().get(0), DIR.ABOVE);
        Assert.assertEquals(r1.getDirs().get(1), DIR.RIGHT);

        r1.push(DIR.RIGHT);
        r2.push(DIR.RIGHT);
        r2.push(DIR.ABOVE);
        r2.push(DIR.RIGHT);
        Assert.assertEquals(r1, r2);
        Assert.assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testItem() {
        Point p1, p2, p3, p4, p5;
        Item<Point> pp1, pp2, pp3, pp4, pp5;

        p1 = new Point(0, 0);
        p2 = new Point(1, 1);
        p3 = new Point(2, 2);
        p4 = new Point(3, 3);
        p5 = new Point(4, 4);

        pp1 = new Item<>(p1, p1.toString(), 0);
        pp2 = new Item<>(p2, p2.toString(), 0);
        pp3 = new Item<>(p3, p3.toString(), 0);
        pp4 = new Item<>(p4, p4.toString(), 0);
        pp5 = new Item<>(p5, p5.toString(), 0);

        Assert.assertEquals(pp1, new Item<>(new Point(0, 0), "(0,0)", 0));
        Assert.assertEquals(pp1.hashCode(),
                new Item<>(new Point(0, 0), "(0,0)", 0).hashCode());

        pp1.setPrevious(pp2);
        pp2.setPrevious(pp3);
        pp3.setPrevious(pp4);
        pp4.setPrevious(pp5);

        ArrayList<Edge> path = Item.pathToSource(pp1);
        Assert.assertEquals(4, path.size());

        Edge e1, e2, e3, e4;
        e1 = path.get(0);
        e2 = path.get(1);
        e3 = path.get(2);
        e4 = path.get(3);

        Assert.assertEquals(e1.getFrom(), p1);
        Assert.assertEquals(e1.getTo(), p2);

        Assert.assertEquals(e2.getFrom(), p2);
        Assert.assertEquals(e2.getTo(), p3);

        Assert.assertEquals(e3.getFrom(), p3);
        Assert.assertEquals(e3.getTo(), p4);

        Assert.assertEquals(e4.getFrom(), p4);
        Assert.assertEquals(e4.getTo(), p5);
        //my test on Item
        p1 = new Point(5, 5);
        p2 = new Point(6, 6);
        p3 = new Point(7, 8);
        p4 = new Point(9, 9);
        p5 = new Point(10, 10);

        pp1 = new Item<>(p1, p1.toString(), 0);
        pp2 = new Item<>(p2, p2.toString(), 0);
        pp3 = new Item<>(p3, p3.toString(), 0);
        pp4 = new Item<>(p4, p4.toString(), 0);
        pp5 = new Item<>(p5, p5.toString(), 0);

        Assert.assertEquals(pp1, new Item<>(new Point(5, 5), "(5,5)", 0));
        Assert.assertEquals(pp1.hashCode(),
                new Item<>(new Point(5, 5), "(5,5)", 0).hashCode());
        Assert.assertEquals(pp2, new Item<>(new Point(6, 6), "(6,6)", 0));
        Assert.assertEquals(pp2.hashCode(),
                new Item<>(new Point(6, 6), "(6,6)", 0).hashCode());

        Assert.assertEquals(pp3, new Item<>(new Point(7, 8), "(7,8)", 0));
        Assert.assertEquals(pp3.hashCode(),
                new Item<>(new Point(7, 8), "(7,8)", 0).hashCode());


        pp4.setPrevious(pp2);
        pp1.setPrevious(pp3);
        pp2.setPrevious(pp4);
        pp3.setPrevious(pp5);
        path = Item.pathToSource(pp1);
        Assert.assertEquals(2, path.size());
        e1 = path.get(0);
        e2 = path.get(1);

        Assert.assertEquals(p1, e1.getFrom());
        Assert.assertEquals(e1.getTo(), p3);

        Assert.assertEquals(e2.getFrom(), p3);
        Assert.assertEquals(e2.getTo(), p5);
        path = Item.pathToSource(pp3);
        Assert.assertEquals(1, path.size());
        e1 = path.get(0);

        Assert.assertEquals(p3, e1.getFrom());
        Assert.assertEquals(e1.getTo(), p5);

    }

    @Test
    public void testBinaryHeap() {
        Point p1, p2, p3, p4, p5;
        Item<Point> pp1, pp2, pp3, pp4, pp5;

        p1 = new Point(0, 0);
        p2 = new Point(1, 1);
        p3 = new Point(2, 2);
        p4 = new Point(3, 3);
        p5 = new Point(4, 4);

        pp1 = new Item<>(p1, p1.toString(), 5);
        pp2 = new Item<>(p2, p2.toString(), 2);
        pp3 = new Item<>(p3, p3.toString(), 3);
        pp4 = new Item<>(p4, p4.toString(), 1);
        pp5 = new Item<>(p5, p5.toString(), 4);

        BinaryHeap<Point> h = new BinaryHeap<>();
        h.insert(pp1);
        h.insert(pp2);
        h.insert(pp3);
        h.insert(pp4);
        h.insert(pp5);

        ArrayList<Item<Point>> sorted;
        sorted = new ArrayList<>(Arrays.asList(pp4, pp2, pp3, pp1, pp5));
        Assert.assertEquals(sorted, h.getElems());
        Assert.assertEquals(pp4, h.extractMin());
        sorted = new ArrayList<>(Arrays.asList(pp2, pp5, pp3, pp1));
        Assert.assertEquals(sorted, h.getElems());
        h.updateKey(4, 1);
        sorted = new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp5));
        Assert.assertEquals(sorted, h.getElems());
        //my test on binary heap
        p1 = new Point(5, 8);
        p2 = new Point(6, 7);
        p3 = new Point(7, 6);
        p4 = new Point(9, 5);
        p5 = new Point(10, 4);

        pp1 = new Item<>(p1, p1.toString(), 9);
        pp2 = new Item<>(p2, p2.toString(), 3);
        pp3 = new Item<>(p3, p3.toString(), 6);
        pp4 = new Item<>(p4, p4.toString(), 4);
        pp5 = new Item<>(p5, p5.toString(), 1);
        h = new BinaryHeap<>();
        h.insert(pp1);
        h.insert(pp2);
        h.insert(pp3);
        h.insert(pp4);
        h.insert(pp5);

        sorted = new ArrayList<>(Arrays.asList(pp5, pp2, pp3,pp1, pp4));
        Assert.assertEquals(sorted, h.getElems());
        Assert.assertEquals(pp5, h.extractMin());
        sorted = new ArrayList<>(Arrays.asList(pp2, pp4, pp3, pp1));
        Assert.assertEquals(sorted, h.getElems());
        h.updateKey(4, 1);
        sorted = new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4));
        Assert.assertEquals(sorted, h.getElems());
    }

    @Test
    public void testKDTree() {
        ArrayList<Point> xPoints, yPoints;
        Point p1, p2, p3, p4, p5, p6, p7;
        p1 = new Point(1, 6);
        p2 = new Point(2, 2);
        p3 = new Point(3, 3);
        p4 = new Point(4, 1);
        p5 = new Point(5, 7);
        p6 = new Point(6, 4);
        p7 = new Point(7, 5);
        xPoints = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
        yPoints = new ArrayList<>(Arrays.asList(p4, p2, p3, p6, p7, p1, p5));
        XTree xtree = XTree.makeXTree(xPoints, yPoints, 0);
        /*

        TreePrinter.print(xtree);


                                              (4,1)
                            ┌───────────────────┴───────────────────┐
                          (3,3)                                   (7,5)
                  ┌─────────┴─────────┐                   ┌─────────┴─────────┐
                (2,2)               (1,6)               (6,4)               (5,7)
             ┌────┴────┐         ┌────┴────┐         ┌────┴────┐         ┌────┴────┐



        */

        Region r;

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.LEFT);
        Assert.assertEquals(r, xtree.findRegion(p2));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.LEFT);
        Assert.assertEquals(r, xtree.findRegion(p1));

        r = new Region();
        r.push(DIR.LEFT);
        Assert.assertEquals(r, xtree.findRegion(p3));

        r = new Region();
        Assert.assertEquals(r, xtree.findRegion(p4));

        r = new Region();
        r.push(DIR.RIGHT);
        Assert.assertEquals(r, xtree.findRegion(p7));

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.RIGHT);
        Assert.assertEquals(r, xtree.findRegion(p6));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.RIGHT);
        Assert.assertEquals(r, xtree.findRegion(p5));

        //mytest on kdtree

        p1 = new Point(10, 1);
        p2 = new Point(9, 2);
        p3 = new Point(8, 3);
        p4 = new Point(7, 4);
        p5 = new Point(6, 5);
        p6 = new Point(5, 6);
        p7 = new Point(4, 7);
        yPoints = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
        xPoints = new ArrayList<>(Arrays.asList(p7,p6,p5,p4,p3,p2,p1));
        xtree = XTree.makeXTree(xPoints, yPoints, 0);
        r = new Region();
        r.push(DIR.RIGHT);
        Assert.assertEquals(r, xtree.findRegion(p2));

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.RIGHT);
        Assert.assertEquals(r, xtree.findRegion(p1));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.RIGHT);
        Assert.assertEquals(r, xtree.findRegion(p3));

        r = new Region();
        Assert.assertEquals(r, xtree.findRegion(p4));

        r = new Region();
        r.push(DIR.ABOVE);
        r.push(DIR.LEFT);
        Assert.assertEquals(r, xtree.findRegion(p7));

        r = new Region();
        r.push(DIR.LEFT);
        Assert.assertEquals(r, xtree.findRegion(p6));

        r = new Region();
        r.push(DIR.UNDER);
        r.push(DIR.LEFT);
        Assert.assertEquals(r, xtree.findRegion(p5));

    }

    @Test
    public void testSpatialGraph() {
        Rect grid = new Rect(0, 0, 10, 8);

        Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
        p1 = new Point(2, 1);
        p2 = new Point(2, 2);
        p3 = new Point(2, 3);
        p4 = new Point(2, 5);
        p5 = new Point(3, 4);
        p6 = new Point(4, 3);
        p7 = new Point(5, 2);
        p8 = new Point(5, 1);
        p9 = new Point(7, 2);
        p10 = new Point(7, 1);
        p11 = new Point(8, 2);
        p12 = new Point(8, 1);

        ArrayList<Point> points =
                new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));

        Item<Point> pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12;
        pp1 = new Item<>(p1, p1.toString(), 0);
        pp2 = new Item<>(p2, p2.toString(), 0);
        pp3 = new Item<>(p3, p3.toString(), 0);
        pp4 = new Item<>(p4, p4.toString(), 0);
        pp5 = new Item<>(p5, p5.toString(), 0);
        pp6 = new Item<>(p6, p6.toString(), 0);
        pp7 = new Item<>(p7, p7.toString(), 0);
        pp8 = new Item<>(p8, p8.toString(), 0);
        pp9 = new Item<>(p9, p9.toString(), 0);
        pp10 = new Item<>(p10, p10.toString(), 0);
        pp11 = new Item<>(p11, p11.toString(), 0);
        pp12 = new Item<>(p12, p12.toString(), 0);

        ArrayList<Item<Point>> nodes =
                new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12));

        Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>());
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp2)));
        neighbors.put(pp4, new ArrayList<>());
        neighbors.put(pp5, new ArrayList<>(Arrays.asList(pp4)));
        neighbors.put(pp6, new ArrayList<>(Arrays.asList(pp3, pp5)));
        neighbors.put(pp7, new ArrayList<>(Arrays.asList(pp6, pp8, pp9, pp10)));
        neighbors.put(pp8, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp9, new ArrayList<>(Arrays.asList(pp11)));
        neighbors.put(pp10, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp11, new ArrayList<>(Arrays.asList(pp12)));
        neighbors.put(pp12, new ArrayList<>());

        Edge e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13;
        e1 = new Edge(p2, p1);
        e2 = new Edge(p3, p2);
        e3 = new Edge(p5, p4);
        e4 = new Edge(p6, p3);
        e5 = new Edge(p6, p5);
        e6 = new Edge(p7, p6);
        e7 = new Edge(p7, p8);
        e8 = new Edge(p7, p9);
        e9 = new Edge(p7, p10);
        e10 = new Edge(p8, p12);
        e11 = new Edge(p9, p11);
        e12 = new Edge(p10, p12);
        e13 = new Edge(p11, p12);

        Hashtable<Edge, Integer> weights = new Hashtable<>();
        weights.put(e1, 10);
        weights.put(e2, 10);
        weights.put(e3, 10);
        weights.put(e4, 10);
        weights.put(e5, 10);
        weights.put(e6, 10);
        weights.put(e7, 10);
        weights.put(e8, 10);
        weights.put(e9, 10);
        weights.put(e10, 2);
        weights.put(e11, 1);
        weights.put(e12, 1);
        weights.put(e13, 1);

        SpatialGraph g = new SpatialGraph(grid, points, nodes, neighbors, weights);

        // all shortest paths from p7
       g.allShortestPaths(pp7);

        Assert.assertTrue(pp1.isVisited());
        Assert.assertTrue(pp2.isVisited());
        Assert.assertTrue(pp3.isVisited());
        Assert.assertTrue(pp4.isVisited());
        Assert.assertTrue(pp5.isVisited());
        Assert.assertTrue(pp6.isVisited());
        Assert.assertTrue(pp7.isVisited());
        Assert.assertTrue(pp8.isVisited());
        Assert.assertTrue(pp9.isVisited());
        Assert.assertTrue(pp10.isVisited());
        Assert.assertTrue(pp11.isVisited());
        Assert.assertTrue(pp12.isVisited());

        Assert.assertEquals(pp2, pp1.getPrevious());
        Assert.assertEquals(pp3, pp2.getPrevious());
        Assert.assertEquals(pp6, pp3.getPrevious());
        Assert.assertEquals(pp5, pp4.getPrevious());
        Assert.assertEquals(pp6, pp5.getPrevious());
        Assert.assertEquals(pp7, pp6.getPrevious());
        Assert.assertEquals(pp7, pp8.getPrevious());
        Assert.assertEquals(pp7, pp9.getPrevious());
        Assert.assertEquals(pp7, pp10.getPrevious());
        Assert.assertEquals(pp9, pp11.getPrevious());
        Assert.assertEquals(pp10, pp12.getPrevious());
       // shortest path from p7 to p4
        g.shortestPath(pp7, pp4);

        Assert.assertFalse(pp1.isVisited());
        Assert.assertTrue(pp2.isVisited());
        Assert.assertTrue(pp3.isVisited());
        Assert.assertTrue(pp5.isVisited());
        Assert.assertTrue(pp6.isVisited());
        Assert.assertTrue(pp7.isVisited());
        Assert.assertTrue(pp8.isVisited());
        Assert.assertTrue(pp9.isVisited());
        Assert.assertTrue(pp10.isVisited());
        Assert.assertTrue(pp11.isVisited());
        Assert.assertTrue(pp12.isVisited());

        Assert.assertEquals(pp2, pp1.getPrevious());
        Assert.assertEquals(pp3, pp2.getPrevious());
        Assert.assertEquals(pp6, pp3.getPrevious());
        Assert.assertEquals(pp5, pp4.getPrevious());
        Assert.assertEquals(pp6, pp5.getPrevious());
        Assert.assertEquals(pp7, pp6.getPrevious());
        Assert.assertEquals(pp7, pp8.getPrevious());
        Assert.assertEquals(pp7, pp9.getPrevious());
        Assert.assertEquals(pp7, pp10.getPrevious());
        Assert.assertEquals(pp9, pp11.getPrevious());
        Assert.assertEquals(pp10, pp12.getPrevious());

        g.buildKDTree(1);
        System.out.printf("p1 Region %s%n", g.getKdtree().findRegion(p1));
        System.out.printf("p2 Region %s%n", g.getKdtree().findRegion(p2));
        System.out.printf("p3 Region %s%n", g.getKdtree().findRegion(p3));
        System.out.printf("p4 Region %s%n", g.getKdtree().findRegion(p4));
        System.out.printf("p5 Region %s%n", g.getKdtree().findRegion(p5));
        System.out.printf("p6 Region %s%n", g.getKdtree().findRegion(p6));
        System.out.printf("p7 Region %s%n", g.getKdtree().findRegion(p7));
        System.out.printf("p8 Region %s%n", g.getKdtree().findRegion(p8));
        System.out.printf("p9 Region %s%n", g.getKdtree().findRegion(p9));
        System.out.printf("p10 Region %s%n", g.getKdtree().findRegion(p10));
        System.out.printf("p11 Region %s%n", g.getKdtree().findRegion(p11));
        System.out.printf("p12 Region %s%n", g.getKdtree().findRegion(p12));

        TreePrinter.print(g.getKdtree());
        /*

                                      (5,2)
                    ┌───────────────────┴───────────────────┐
                  (4,3)                                   (8,1)
          ┌─────────┴─────────┐                   ┌─────────┴─────────┐
        (2,2)               (3,4)               (7,1)               (8,2)
     ┌────┴────┐         ┌────┴────┐         ┌────┴────┐         ┌────┴────┐




        */

        g.preprocess(0);

        System.out.printf("Regional edges:%n%s%n:", g.getRegionalEdges());

        Region r = g.getKdtree().findRegion(p4);
        Hashtable<Edge, HashSet<Region>> regionalEdges = g.getRegionalEdges();

        Assert.assertTrue(regionalEdges.get(e6).contains(r));
        Assert.assertFalse(regionalEdges.get(e7).contains(r));
        Assert.assertFalse(regionalEdges.get(e8).contains(r));
        Assert.assertFalse(regionalEdges.get(e9).contains(r));

        g.regionalShortestPath(pp7, pp4);
        Assert.assertFalse(pp1.isVisited());
        Assert.assertFalse(pp2.isVisited());
        Assert.assertFalse(pp3.isVisited());
        Assert.assertTrue(pp5.isVisited());
        Assert.assertTrue(pp6.isVisited());
        Assert.assertTrue(pp7.isVisited());
        Assert.assertFalse(pp8.isVisited());
        Assert.assertFalse(pp9.isVisited());
        Assert.assertFalse(pp10.isVisited());
        Assert.assertFalse(pp11.isVisited());
        Assert.assertFalse(pp12.isVisited());

        //test on SpatialGraph
        grid = new Rect(0, 0, 30, 30);

        p1 = new Point(3, 1);
        p2 = new Point(1, 2);
        p3 = new Point(5, 3);
        p4 = new Point(4, 2);
        p5 = new Point(6, 4);
        p6 = new Point(1, 5);
        p7 = new Point(3, 3);
        p8 = new Point(2, 1);
        p9 = new Point(9, 2);
        p10 = new Point(5, 7);
        p11 = new Point(9, 10);
        p12 = new Point(8, 1);
        points = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
        pp1 = new Item<>(p1, p1.toString(), 0);
        pp2 = new Item<>(p2, p2.toString(), 0);
        pp3 = new Item<>(p3, p3.toString(), 0);
        pp4 = new Item<>(p4, p4.toString(), 0);
        pp5 = new Item<>(p5, p5.toString(), 0);
        pp6 = new Item<>(p6, p6.toString(), 0);
        pp7 = new Item<>(p7, p7.toString(), 0);
        pp8 = new Item<>(p8, p8.toString(), 0);
        pp9 = new Item<>(p9, p9.toString(), 0);
        pp10 = new Item<>(p10, p10.toString(), 0);
        pp11 = new Item<>(p11, p11.toString(), 0);
        pp12 = new Item<>(p12, p12.toString(), 0);
        nodes = new ArrayList<>(Arrays.asList(pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, pp9, pp10, pp11, pp12));
        neighbors = new Hashtable<>();
        neighbors.put(pp1, new ArrayList<>(Arrays.asList(pp6)));
        neighbors.put(pp2, new ArrayList<>(Arrays.asList(pp1)));
        neighbors.put(pp3, new ArrayList<>(Arrays.asList(pp8)));
        neighbors.put(pp4, new ArrayList<>(Arrays.asList(pp2)));
        neighbors.put(pp5, new ArrayList<>(Arrays.asList(pp4)));
        neighbors.put(pp6, new ArrayList<>(Arrays.asList(pp9, pp5)));
        neighbors.put(pp7, new ArrayList<>(Arrays.asList(pp6)));
        neighbors.put(pp8, new ArrayList<>(Arrays.asList(pp2)));
        neighbors.put(pp9, new ArrayList<>(Arrays.asList(pp11)));
        neighbors.put(pp10, new ArrayList<>(Arrays.asList(pp7)));
        neighbors.put(pp11, new ArrayList<>(Arrays.asList(pp9)));
        neighbors.put(pp12, new ArrayList<>());
        e1 = new Edge(p1, p6);
        e2 = new Edge(p2, p1);
        e3 = new Edge(p3, p8);
        e4 = new Edge(p4, p2);
        e5 = new Edge(p5, p4);
        e6 = new Edge(p6, p9);
        e7 = new Edge(p6, p5);
        e8 = new Edge(p7, p6);
        e9 = new Edge(p8, p2);
        e10 = new Edge(p9, p11);
        e11 = new Edge(p10, p7);
        e12 = new Edge(p11, p9);

        weights= new Hashtable<>();
        weights.put(e1, 10);
        weights.put(e2, 8);
        weights.put(e3, 7);
        weights.put(e4, 9);
        weights.put(e5, 1);
        weights.put(e6, 5);
        weights.put(e7, 6);
        weights.put(e8, 7);
        weights.put(e9, 10);
        weights.put(e10, 2);
        weights.put(e11, 1);
        weights.put(e12, 5);
        weights.put(e13, 4);

        g = new SpatialGraph(grid, points, nodes, neighbors, weights);

        // all shortest paths from p7
        g.allShortestPaths(pp7);

        Assert.assertTrue(pp1.isVisited());
        Assert.assertTrue(pp2.isVisited());
        Assert.assertTrue(pp4.isVisited());
        Assert.assertTrue(pp5.isVisited());
        Assert.assertTrue(pp6.isVisited());
        Assert.assertTrue(pp7.isVisited());
        Assert.assertTrue(pp9.isVisited());
        Assert.assertTrue(pp11.isVisited());

        Assert.assertEquals(pp2, pp1.getPrevious());
        Assert.assertEquals(pp4, pp2.getPrevious());
        Assert.assertEquals(null, pp3.getPrevious());
        Assert.assertEquals(pp5, pp4.getPrevious());
        Assert.assertEquals(pp6, pp5.getPrevious());
        Assert.assertEquals(pp7, pp6.getPrevious());
        // shortest path from p7 to p4
        g.shortestPath(pp7, pp4);

        Assert.assertFalse(pp1.isVisited());
        Assert.assertTrue(pp5.isVisited());
        Assert.assertTrue(pp6.isVisited());
        Assert.assertTrue(pp7.isVisited());
        Assert.assertFalse(pp8.isVisited());
        Assert.assertTrue(pp9.isVisited());
        Assert.assertFalse(pp10.isVisited());
        Assert.assertTrue(pp11.isVisited());
        Assert.assertFalse(pp12.isVisited());

        Assert.assertEquals(null, pp1.getPrevious());
        Assert.assertEquals(pp9, pp11.getPrevious());
        Assert.assertEquals(null, pp8.getPrevious());
        Assert.assertEquals(pp6, pp5.getPrevious());
        Assert.assertEquals(pp7, pp6.getPrevious());

        g.buildKDTree(1);
        System.out.printf("p1 Region %s%n", g.getKdtree().findRegion(p1));
        System.out.printf("p2 Region %s%n", g.getKdtree().findRegion(p2));
        System.out.printf("p3 Region %s%n", g.getKdtree().findRegion(p3));
        System.out.printf("p4 Region %s%n", g.getKdtree().findRegion(p4));
        System.out.printf("p5 Region %s%n", g.getKdtree().findRegion(p5));
        System.out.printf("p6 Region %s%n", g.getKdtree().findRegion(p6));
        System.out.printf("p7 Region %s%n", g.getKdtree().findRegion(p7));
        System.out.printf("p8 Region %s%n", g.getKdtree().findRegion(p8));
        System.out.printf("p9 Region %s%n", g.getKdtree().findRegion(p9));
        System.out.printf("p10 Region %s%n", g.getKdtree().findRegion(p10));
        System.out.printf("p11 Region %s%n", g.getKdtree().findRegion(p11));
        System.out.printf("p12 Region %s%n", g.getKdtree().findRegion(p12));

        TreePrinter.print(g.getKdtree());
        g.preprocess(0);

        System.out.printf("Regional edges:%n%s%n:", g.getRegionalEdges());

        r = g.getKdtree().findRegion(p4);
     regionalEdges = g.getRegionalEdges();

        Assert.assertFalse(regionalEdges.get(e6).contains(r));
        Assert.assertTrue(regionalEdges.get(e7).contains(r));
        Assert.assertTrue(regionalEdges.get(e8).contains(r));
        Assert.assertTrue(regionalEdges.get(e9).contains(r));

        g.regionalShortestPath(pp7, pp4);
        Assert.assertFalse(pp1.isVisited());
        Assert.assertFalse(pp2.isVisited());
        Assert.assertFalse(pp3.isVisited());
        Assert.assertTrue(pp5.isVisited());
        Assert.assertTrue(pp6.isVisited());
        Assert.assertTrue(pp7.isVisited());
        Assert.assertFalse(pp8.isVisited());
        Assert.assertFalse(pp9.isVisited());
        Assert.assertFalse(pp10.isVisited());
        Assert.assertFalse(pp11.isVisited());
        Assert.assertFalse(pp12.isVisited());

    }

    static long testBigSpatialGraphSlow(Random r, SpatialGraph g) {
        ArrayList<Item<Point>> nodes = g.getNodes();
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            g.shortestPath(nodes.get(r.nextInt(1000)), nodes.get(r.nextInt(1000)));
        }
        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    static long testBigSpatialGraphFast(Random r, SpatialGraph g) {
        ArrayList<Item<Point>> nodes = g.getNodes();
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            g.regionalShortestPath(nodes.get(r.nextInt(1000)), nodes.get(r.nextInt(1000)));
        }
        long end = System.nanoTime();
        return (end - start) / 1000000;
    }
static long mycomparing(Random r,SpatialGraph g){
    ArrayList<Item<Point>> nodes = g.getNodes();
    long start = System.nanoTime();
    for (int i = 0; i < 15000; i++) {
        g.regionalShortestPath(nodes.get(r.nextInt(700)), nodes.get(r.nextInt(700)));
    }
    long end = System.nanoTime();
    long Spatialtime= (end - start) / 1000000;
    nodes = g.getNodes();
     start = System.nanoTime();
    for (int i = 0; i < 15000; i++) {
        g.shortestPath(nodes.get(r.nextInt(700)), nodes.get(r.nextInt(700)));
    }
    end = System.nanoTime();
    long shortestpathtime= (end - start) / 1000000;
    return shortestpathtime-Spatialtime;
}
    @Test
    public void testTime() {
        Random r = new Random(1);
        SpatialGraph g = new SpatialGraph(r, 1000, 10, 20, 100);
        long start = System.nanoTime();
        g.preprocess(10);
        long end = System.nanoTime();
        System.out.printf("Preprocessing took %d%n", (end - start) / 1000000);

        long fast = testBigSpatialGraphFast(r, g);
        System.out.printf("Regional search took %d%n", fast);

        long slow = testBigSpatialGraphSlow(r, g);
        System.out.printf("Regular search took %d%n", slow);
        long alpha=mycomparing(r,g);
        System.out.println("my time test");
        System.out.printf("the difference for shortestpath and Regional search is %d%n",alpha);
         start = System.nanoTime();
        g.preprocess(40);
         end = System.nanoTime();
        System.out.printf(" my Preprocessing took %d%n", (end - start) / 1000000);

    }

}
