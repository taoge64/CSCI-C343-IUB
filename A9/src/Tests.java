import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;
public class Tests {

    @org.junit.Test
    public void leftOf() {
        Rect alpha= new Rect(1,2,10,12);
        Rect beta= new Rect(1,2,3,12);
        Rect celta= new Rect(1,2,5,12);
        Rect delta=new Rect(1,2,7,12);
    Assert.assertEquals(beta,alpha.leftOf(new Point(3,4)));
    Assert.assertEquals(celta, alpha.leftOf(new Point(5,6)));
    Assert.assertEquals(delta, alpha.leftOf(new Point(7,10)));
    }

    @org.junit.Test
    public void rightOf() {
        Rect alpha= new Rect(1,2,10,12);
        Rect beta= new Rect(3,2,10,12);
        Rect celta= new Rect(5,2,10,12);
        Rect delta=new Rect(7,2,10,12);
        Assert.assertEquals(beta,alpha.rightOf(new Point(3,4)));
        Assert.assertEquals(celta, alpha.rightOf(new Point(5,6)));
        Assert.assertEquals(delta, alpha.rightOf(new Point(7,10)));
    }

    @org.junit.Test
    public void underOf() {
        Rect alpha= new Rect(1,2,10,12);
        Rect beta= new Rect(1,2,10,4);
        Rect celta= new Rect(1,2,10,6);
        Rect delta=new Rect(1,2,10,10);
        Assert.assertEquals(beta,alpha.underOf(new Point(3,4)));
        Assert.assertEquals(celta, alpha.underOf(new Point(5,6)));
        Assert.assertEquals(delta, alpha.underOf(new Point(7,10)));
    }

    @org.junit.Test
    public void aboveOf() {
        Rect alpha= new Rect(1,2,10,12);
        Rect beta= new Rect(1,4,10,12);
        Rect celta= new Rect(1,6,10,12);
        Rect delta=new Rect(1,10,10,12);
        Assert.assertEquals(beta,alpha.aboveOf(new Point(3,4)));
        Assert.assertEquals(celta, alpha.aboveOf(new Point(5,6)));
        Assert.assertEquals(delta, alpha.aboveOf(new Point(7,10)));
    }

    @org.junit.Test
    public void contains() {
        Rect alpha= new Rect(1,2,10,12);

        Assert.assertTrue(alpha.contains(new Point(1,7)));
        Assert.assertTrue(alpha.contains(new Point(7,12)));
        Assert.assertFalse(alpha.contains(new Point(11,13)));

    }

    @org.junit.Test
    public void intersect() {
        Rect alpha= new Rect(1,2,10,12);
        Rect beta= new Rect(1,4,10,12);
        Rect celta= new Rect(7,6,19,29);
        Rect delta=new Rect(20,30,40,50);
        Assert.assertTrue(alpha.intersect(beta));
        Assert.assertTrue(alpha.intersect(celta));
        Assert.assertFalse(alpha.intersect(delta));

    }

    @org.junit.Test
    public void distanceSquaredTo() {
        Rect alpha= new Rect(1,2,10,12);
        Assert.assertEquals(100,alpha.distanceSquaredTo(new Point(-9,6)));
        Assert.assertEquals(100,alpha.distanceSquaredTo(new Point(20,6)));
        Assert.assertEquals(0,alpha.distanceSquaredTo(new Point(1,10)));
        Assert.assertEquals(10,alpha.distanceSquaredTo(new Point(11,15)));

    }
    @org.junit.Test
    public void rangesearch() {
        XTree t = new XEmpty();
        t = t.insert(new Point(2, 2)).insert(new Point(4, 5)).insert(new Point(1,1 ));
        Rect region = new Rect(0, 0, 10, 10);
        Set<Point> s = t.rangeSearch(region, region);
        Set<Point> ss=new HashSet<>();
        ss.add(new Point(4,5));
        ss.add(new Point(1,1));
        ss.add(new Point(2,2));
        Assert.assertTrue(ss.toString().equals(s.toString()));
        XTree t1 = new XEmpty();
        t1 = t1.insert(new Point(0, 0)).insert(new Point(3, 4)).insert(new Point(2,3)).insert(new Point(7,6)).insert(new Point(2,4)).insert(new Point(10,10));
        s = t1.rangeSearch(new Rect(1,1,5,5), region);
        ss=new HashSet<>();
        ss.add(new Point(2,3));
        ss.add(new Point(3,4));
        ss.add(new Point(2,4));
        Assert.assertEquals(ss.toString(),s.toString());
        XTree t2 = new XEmpty();
        t2 = t2.insert(new Point(0, 0)).insert(new Point(3, 4)).insert(new Point(2,3)).insert(new Point(7,6)).insert(new Point(2,4)).insert(new Point(10,10));
        s = t2.rangeSearch(new Rect(15,15,22,22), region);
        ss=new HashSet<>();
       // Assert.assertTrue(ss.toString().equals(s.toString()));

    }
    @org.junit.Test
    public void nearestNeighbor() {
        XTree t = new XEmpty();
        try {
            t = t.insert(new Point(2, 2)).insert(new Point(4, 5)).insert(new Point(1, 1));
            Rect region = new Rect(0, 0, 10, 10);
            // Assert.assertTrue(ss.toString().equals(s.toString()));
            Assert.assertEquals(new Point(2, 2), t.nearestNeighbor(new Point(2, 3), new Rect(0, 0, 10, 10), new HashSet<>()));
            Assert.assertEquals(new Point(1, 1), t.nearestNeighbor(new Point(1, 1), new Rect(0, 0, 10, 10), new HashSet<>()));
            Assert.assertEquals(new Point(4,5 ), t.nearestNeighbor(new Point(10, 10), new Rect(0, 0, 100, 100), new HashSet<>()));
        }catch (EmptyTreeE e){
            System.out.println("it should not happened");
        }

    }
    @org.junit.Test
    public void nearestNeighbors() {
        XTree t= new XEmpty();
        try {
            t = t.insert(new Point(2, 2)).insert(new Point(4, 5)).insert(new Point(1, 1));
            Rect region = new Rect(0, 0, 20, 20);
            // Assert.assertTrue(ss.toString().equals(s.toString()));
            Set<Point> set= new HashSet();
            set.add(new Point(2,2));
            set.add(new Point(4,5));
            set.add(new Point(1,1));
            XTree t1= new XEmpty();
            t1 = t1.insert(new Point(2, 2)).insert(new Point(4, 5)).insert(new Point(1, 1)).insert(new Point(10, 2)).insert(new Point(101, 111)).insert(new Point(2, 9)).insert(new Point(3, 12)).insert(new Point(12, 13));
            Assert.assertEquals(set.toString(), t.nearestKNeighbors(new Point(2,3),3,region).toString());
            Assert.assertEquals(new HashSet<>().toString(),  t.nearestKNeighbors(new Point(5,5),0,region).toString());
            Set<Point> set2= new HashSet();
            set2.add(new Point(2,9));
            set2.add(new Point(2,2));
            set2.add(new Point(4,5));
            set2.add(new Point(1,1));
            Assert.assertEquals(set2.toString(),t1.nearestKNeighbors(new Point(5,5),4,region).toString());
        }catch (EmptyTreeE e){
            System.out.println("it should not happened");
        }
    }
}