import org.junit.Test;

public class List1Test {

    @Test
    public void getFirst() throws EmptyListE {
        List1<Integer> a= new Node<>(5,new Node<>(3,new Node<>(2,new Node<>(1,new Empty<>()))));
        assertEquals((Object)5, a.getFirst());
    }

    @Test
    public void getSecond() throws EmptyListE {

        List1<Integer> b= new Node<>(1,new Node<>(2,new Node<>(3,new Node<>(4,new Node<>(5,new Empty<>())))));
        assertEquals((Object)2, b.getSecond());


    }
    public void getRest() throws EmptyListE{
        List1<Integer> a= new Node<>(5,new Node<>(3,new Node<>(2,new Node<>(1,new Empty<>()))));
        assertEquals(new Node<>(3,new Node<>(2,new Node<>(1,new Empty<>()))), a.getRest());
    }
    @Test
    public void triplicate(){
List1<Integer> a= new Node<>(1,new Node<>(2,new Empty<>()));
List1<Integer> b= new Node<>(1,new Node<>(1,new Node<>(1,new Node<>(2,new Node<>(2,new Node<>(2,new Empty<>()))))));
assertEquals(b,a.triplicate());
List1<Integer> c= new Empty<>();
assertEquals(new Empty<>(),c.triplicate());
List1<String> f= new Node<>("ab",new Node<>("ab",new Node<>("ab",new Node<>("bc",new Node<>("bc",new Node<>("bc",new Empty<>()))))));
List1<String> e= new Node<>("ab",new Node<>("bc",new Empty<>()));
assertEquals(f,e.triplicate());
    }

    @Test
    public void filter() {
        List1<Integer> a = new Node<>(3, new Node<>(2, new Node<>(1, new Node<>(4, new Empty<>()))));
        List1<Integer> b = new Node<>(4, new Empty<>());
        assertEquals(b, a.filter(e -> (e > 3)));
        assertEquals(new Empty<>(),a.filter(e->(e>6)));
        List1<String> c= new Node<>("ab",new Node<>("b", new Empty<>()));
        List1<String> d= new Node<>("ab",new Empty<>());
        assertEquals(d,c.filter(e->(e.length()>1)));
    }

    @Test
public void sort2(){
        List1<Integer> a= new Node<>(2,new Node<>(3,new Empty<>()));
        List1<String> f= new Node<>("a",new Node<>("b",new Empty<>()));
        Comparator<Integer> c=Integer::compareTo;
        Comparator<String> d=String::compareTo;
        assertEquals(a,List1.sort2(3,2,c));
        assertEquals(f,List1.sort2("a","b",d));

}
    @Test
    public void quicksort()throws EmptyListE{

        List1<Integer> b= new Node<>(3,new Node<>(2,new Node<>(1,new Node<>(4,new Empty<>()))));
            List1.quickSort(b,Integer::compareTo);
        List1<Integer> c= new Node<>(1,new Node<>(2,new Node(3,new Node<>(4,new Empty<>()))));
            assertEquals( List1.quickSort(b,Integer::compareTo),c);
        List1<String> bb= new Node<>("e",new Node<>("c",new Node<>("b",new Node<>("a",new Empty<>()))));
        List1.quickSort(bb,String::compareTo);
        List1<String> cc= new Node<>("a",new Node<>("b",new Node("c",new Node<>("e",new Empty<>()))));
        assertEquals( List1.quickSort(bb,String::compareTo),cc);



        }



    



}