import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class Tests {
    @org.junit.Test
    public void setSource(){
        Item<String> a, b, c, d, e,f,j;
        a = new Item<>("a", "a", 0);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 0);
        d = new Item<>("d", "d", 0);
        e = new Item<>("e", "e", 0);
        f = new Item<>("f", "f", 0);
        j = new Item<>("j", "j", 0);
        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e,f,j));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b,f)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(c, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(d)));
        neighbors.put(d, new ArrayList<>(Arrays.asList(e)));
        neighbors.put(e, new ArrayList<>(Arrays.asList(f)));
        neighbors.put(f, new ArrayList<>(Arrays.asList(j)));
        neighbors.put(j, new ArrayList<>(Arrays.asList(d)));
        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, c), 4);
        weights.put(new Pair<>(b, c), 3);
        weights.put(new Pair<>(b, e), 1);
        weights.put(new Pair<>(c, d), 2);
        weights.put(new Pair<>(d, e), 7);
        weights.put(new Pair<>(e, f), 5);
        weights.put(new Pair<>(f, j), 2);
        weights.put(new Pair<>(j, d), 2);
        Graph<String> g;

        g = new BFS<>(neighbors);
        g.setSource(nodes, a);
        Item test= g.ncoll.extract();
        Assert.assertEquals(a,test);
        g.setSource(nodes, b);
        Item test0= g.ncoll.extract();
        Assert.assertEquals(b,test0);
        g.setSource(nodes, j);
        Item test00= g.ncoll.extract();
        Assert.assertEquals(j,test00);

        g = new DFS<>(neighbors);
        g.setSource(nodes, a);
        Item test1= g.ncoll.extract();
        Assert.assertEquals(a,test1);
        g.setSource(nodes, b);
        Item test11= g.ncoll.extract();
        Assert.assertEquals(b,test11);
        g.setSource(nodes, j);
        Item test111= g.ncoll.extract();
        Assert.assertEquals(j,test111);

        g = new SP<>(neighbors,weights);
        g.setSource(nodes, a);
        Item test2= g.ncoll.extract();
        Assert.assertEquals(a,test2);
        g.setSource(nodes, b);
        Item test22= g.ncoll.extract();
        Assert.assertEquals(b,test22);
        g.setSource(nodes, j);
        Item test222= g.ncoll.extract();
        Assert.assertEquals(j,test222);

        g = new SPD<>(neighbors,weights);
        g.setSource(nodes, a);
        Item test3= g.ncoll.extract();
        Assert.assertEquals(a,test3);
        g.setSource(nodes, b);
        Item test33= g.ncoll.extract();
        Assert.assertEquals(b,test33);
        g.setSource(nodes, j);
        Item test333= g.ncoll.extract();
        Assert.assertEquals(j,test333);

        g = new MSP<>(neighbors,weights);
        g.setSource(nodes, a);
        Item test4= g.ncoll.extract();
        Assert.assertEquals(a,test4);
        g.setSource(nodes, b);
        Item test44= g.ncoll.extract();
        Assert.assertEquals(b,test44);
        g.setSource(nodes, j);
        Item test444= g.ncoll.extract();
        Assert.assertEquals(j,test444);
    }
    @org.junit.Test
    public void relax() {
        Item<String> a, b, c, d, e,f,j;
        a = new Item<>("a", "a", 1);
        b = new Item<>("b", "b", 0);
        c = new Item<>("c", "c", 4);
        d = new Item<>("d", "d", 5);
        e = new Item<>("e", "e", 6);
        f = new Item<>("f", "f", 2);
        j = new Item<>("j", "j", 1);
        ArrayList<Item<String>> nodes =
                new ArrayList<>(Arrays.asList(a, b, c, d, e,f,j));
        Hashtable<Item<String>, ArrayList<Item<String>>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(b,f,d)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(d, e)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(d)));
        neighbors.put(d, new ArrayList<>(Arrays.asList()));
        neighbors.put(e, new ArrayList<>(Arrays.asList(f,d,j)));
        neighbors.put(f, new ArrayList<>(Arrays.asList(j,b)));
        neighbors.put(j, new ArrayList<>(Arrays.asList(d,b)));
        Hashtable<Pair<Item<String>, Item<String>>, Integer> weights = new Hashtable<>();
        weights.put(new Pair<>(a, b), 2);
        weights.put(new Pair<>(a, f), 4);
        weights.put(new Pair<>(a, d), 4);
        weights.put(new Pair<>(b, d), 3);
        weights.put(new Pair<>(b, e), 1);
        weights.put(new Pair<>(c, d), 2);
        weights.put(new Pair<>(e, f), 5);
        weights.put(new Pair<>(e, d), 3);
        weights.put(new Pair<>(e, j), 2);
        weights.put(new Pair<>(f, b), 4);
        weights.put(new Pair<>(f, j), 2);
        weights.put(new Pair<>(j, d), 2);
        weights.put(new Pair<>(j, b), 1);
        Graph<String> g,g1,g2;

        g = new BFS<>(neighbors);
        g.setSource(nodes, a);
        g.relax(b,c);
        Assert.assertEquals(a,g.ncoll.extract());
        g.relax(a,b);
        Assert.assertEquals(c,g.ncoll.extract());
        g.relax(a,e);
        Assert.assertEquals(b,g.ncoll.extract());

        g1 = new DFS<>(neighbors);
        g1.setSource(nodes, c);
        g1.relax(b,c);
        Assert.assertEquals(c,g1.ncoll.extract());
        g1.relax(a,b);
        Assert.assertEquals(b,g1.ncoll.extract());
        g1.relax(a,e);
        Assert.assertEquals(e,g1.ncoll.extract());

        g2= new SP<>(neighbors,weights);
        g2.setSource(nodes, e);
        g2.relax(e,f);
        Assert.assertEquals(0,g2.ncoll.extract().getValue());
        g2.relax(e,d);
        Assert.assertEquals(3,g2.ncoll.extract().getValue());
        g2.relax(e,j);
        Assert.assertEquals(2,g2.ncoll.extract().getValue());

        g2= new SPD<>(neighbors,weights);
        g2.setSource(nodes, e);
        g2.relax(e,f);
        Assert.assertEquals(0,g2.ncoll.extract().getValue());
        g2.relax(e,d);
        Assert.assertEquals(3,g2.ncoll.extract().getValue());
        g2.relax(e,j);
        Assert.assertEquals(2,g2.ncoll.extract().getValue());
        g2= new MSP<>(neighbors,weights);
        g2.setSource(nodes, e);
        g2.relax(e,f);
        Assert.assertEquals(0,g2.ncoll.extract().getValue());
        g2.relax(b,d);
        Assert.assertEquals(3,g2.ncoll.extract().getValue());
        g2.relax(e,j);
        Assert.assertEquals(2,g2.ncoll.extract().getValue());




    }
}