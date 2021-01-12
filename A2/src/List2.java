import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class List2<E> {
    private LinkedList<E> deleg;

    List2() {
        this.deleg = new LinkedList<>();
    }

    E get (int index) {
        return deleg.get(index);
    }

    int length () {
        return deleg.size();
    }

    void add (E elem) {
        deleg.add(elem);
    }

    /**
       This is the same method from the class List1. Your solution must use 
       the method listIterator to get an iterator for the list.
     */
    void  triplicate () {
        ListIterator<E> it = this.deleg.listIterator();
        LinkedList<E> a= new LinkedList<>();
        while(it.hasNext()){
            E e= it.next();
            a.add(e);
            a.add(e);
            a.add(e);

        }
        this.deleg=a;




    }

    /**
       This is the method sum we wrote in the Jan. 15 lecture for the
       List1 class.  Your solution for this class must use the method
       listIterator to get an iterator for the list.
     */
    E sum (E base, BiFunction<E,E,E> acc) {
        Iterator<E> it = deleg.listIterator();
        E e=acc.apply(base,it.next());
        while (it.hasNext()) {
            E basement = it.next();

            e=acc.apply(e, basement);
        }
        return e;
    }

    /**
       The following method reverses the list using iterators. A
       simple idea is to the 'descendingIterator' method to traverse
       the list backwards and build the reversed list.
     */
    void reverse () {
        Iterator<E> it=deleg.descendingIterator();
        LinkedList<E> a= new LinkedList<>();
        while(it.hasNext()){
            E e=it.next();
            a.add(e);
        }
        deleg=a;
    }

    /**
       The following method takes a predicate on values type E and
       returns the number of list elements that satisfy the
       predicate. Again use an iterator to traverse the list.
     */
    int countOccurrences (Predicate<E> pred) {
        Iterator<E> it = deleg.listIterator();
        int count=0;
        while (it.hasNext()){
            E e= it.next();
            if (pred.test(e)){
                count+=1;

            }
        }
        return count;
    }

    void sort (Comparator<E> c) {
        deleg.sort(c);
    }

    /**
       This is similar to the method in List1. 
     */
    static List2<Integer> makeIntList2 (int size, int bound) {
        List2<Integer> Listty = new List2<>();
        while (Listty.length()<size){
            Random r= new Random();
            Listty.add(r.nextInt(bound));
        }
        return Listty;
    }

}
