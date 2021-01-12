import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class List2Test {

    @Test
    public void get() {
        List2<Integer> a= new List2<>();
        a.add(5);
        assertEquals((Object)5, a.get(0));
        List2<String> b= new List2<>();
        b.add("A");
        assertEquals("A",b.get(0));
        List2<Character> c= new List2<>();
        c.add('A');
        assertEquals((Character) 'A',c.get(0));

    }

    @Test
    public void triplicate() {
        List2<Integer> a = new List2<>();
        a.add(5);
        a.add(6);
        a.triplicate();
        assertEquals((Object)5,a.get(0));
        assertEquals((Object)5,a.get(1));
        assertEquals((Object)5,a.get(2));
        assertEquals((Object)6,a.get(3));
        assertEquals((Object)6,a.get(4));
        assertEquals((Object)6,a.get(5));
        List2<String> b = new List2<>();
        b.add("aa");
        b.add("bb");
        b.triplicate();
        assertEquals("aa",b.get(0));
        assertEquals("aa",b.get(1));
        assertEquals("aa",b.get(2));
        assertEquals("bb",b.get(3));
        assertEquals("bb",b.get(4));
        assertEquals("bb",b.get(5));

    }
    @Test
    public void sum() {
        List2<Integer> a = new List2<>();
        a.add(5);
        a.add(6);
        assertEquals((Object)11,a.sum(0,(c,d)->c+d));
        List2<String> b = new List2<>();
        b.add("aa");
        b.add("bb");
        assertEquals("aabb",b.sum("",(c,d)->c+d));



    }
    @Test
    public void reverse() {
    List2<Integer> a= new List2<>();
    a.add(5);
    a.add(3);
    a.add(2);
    a.reverse();
    assertEquals((Object)2,a.get(0));
    assertEquals((Object)3,a.get(1));
    assertEquals((Object)5,a.get(2));
    List2<String> c= new List2<>();
    c.add("aa");
    c.add("bb");
    c.reverse();
    assertEquals("bb",c.get(0));
    assertEquals("aa",c.get(1));
    }
    @Test
    public void  countOccurrences(){
        List2<Integer> a= new List2<>();
        a.add(5);
        a.add(3);
        a.add(2);
        assertEquals(1, a.countOccurrences((e->(e>3))));
        List2<String> aa= new List2<>();
        aa.add("a");
        aa.add("ba");
        aa.add("bbbb");
        assertEquals(1, aa.countOccurrences((e->(e.length()>3))));

    }
    @Test
    public void makeintlist2(){
        List2<Integer> a=List2.makeIntList2(10,10);
        assertEquals(true,a.length()<=10);
        for(int i=0;i<10;i++){
            assertEquals(true,a.get(i)<=10);
        }



    }
}