import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.stream.*;
import java.util.function.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LOTest {

  @Test
  public void triplicate() {
    List<Integer> l1 = new LinkedList<>();
    l1.add(1);l1.add(2);
    assertTrue((new Node<>(1, new Node<>(1, new Node<>(1, new Node<>(2, new Node<>(2, new Node<>(2, new Empty<>())))))))
               .equals(ListOperations.triplicate(l1)));
    assertEquals(6, ListOperations.triplicate(l1).length());
  }

  @Test
  public void square() {
    List<Integer> l1 = new LinkedList<>();
    l1.add(1);l1.add(2);l1.add(3);l1.add(4);l1.add(5);
    assertTrue((new Node<>(1, new Node<>(4, new Node<>(9, new Node<>(16, new Node<>(25, new Empty<>()))))))
               .equals(ListOperations.square(l1)));
  }

  @Test
  public void allTrue() {
    List<Integer> l1 = new LinkedList<>();
    l1.add(1);l1.add(2);l1.add(3);l1.add(4);l1.add(5);
    assertTrue(ListOperations.allTrue(l1, x -> x != 0));
    Predicate<Integer> pred = x -> x % 2 == 0;
    l1 = l1.stream().filter(pred).collect(Collectors.toList());
    assertTrue(ListOperations.allTrue(l1, pred));
  }

  @Test
  public void evens() {
    List<Integer> l1 = new LinkedList<>();
    l1.add(1);l1.add(2);l1.add(3);l1.add(4);l1.add(5);
    assertTrue((new Node<>(2, new Node<>(4, new Empty<>()))).equals(ListOperations.evens(l1)));
  }

  @Test
  public void product() {
    List<Integer> l1 = new LinkedList<>();
    l1.add(1);l1.add(2);l1.add(3);l1.add(4);l1.add(5);
    assertTrue(120 == ListOperations.product(l1));
  }

  @Test
  public void reverse() {
    List<Integer> l1 = new LinkedList<>();
    l1.add(1);l1.add(2);l1.add(3);l1.add(4);l1.add(5);
    assertTrue((new Node<>(5, new Node<>(4, new Node<>(3, new Node<>(2, new Node<>(1, new Empty<>())))))).equals(ListOperations.reverse(l1)));
  }

  @Test
  public void orderBy() {
    List<Integer> l1 = new ArrayList<>(); l1.add(1); l1.add(2);
    List<Integer> l2 = new ArrayList<>(); l2.add(2); l2.add(3);
    List<Integer> l3 = new ArrayList<>(); l3.add(3); l3.add(3);
    List<Integer> l4 = new ArrayList<>(); l4.add(1); l4.add(1);
    List<Integer> l5 = new ArrayList<>(); l5.add(2); l5.add(2);
    List<List<Integer>> nums = new LinkedList<>(); nums.add(l1);nums.add(l2);nums.add(l3);nums.add(l4);nums.add(l5);
    BiPredicate<List<Integer>, List<Integer>> f1 = (x, y) -> x.get(1) < y.get(1);
    BiPredicate<List<Integer>, List<Integer>> f2 = (x, y) -> x.get(0) < y.get(0);
    List<BiPredicate<List<Integer>, List<Integer>>> funcs = new LinkedList<>(); funcs.add(f1); funcs.add(f2);
    List<List<Integer>> cnums = new LinkedList<>(); cnums.add(l4);cnums.add(l1);cnums.add(l5);cnums.add(l2);cnums.add(l3);
    assertEquals(cnums, ListOperations.orderBy(nums, funcs));
    f1 = (x, y) -> y.get(1) < x.get(1);
    f2 = (x, y) -> y.get(0) < x.get(0);
    funcs = new LinkedList<>(); funcs.add(f1); funcs.add(f2);
    cnums = new LinkedList<>(); cnums.add(l3);cnums.add(l2);cnums.add(l5);cnums.add(l1);cnums.add(l4);
    assertEquals(cnums, ListOperations.orderBy(nums, funcs));

                  l1 = new ArrayList<>(); l1.add(1); l1.add(1); l1.add(2);
                  l2 = new ArrayList<>(); l2.add(1); l2.add(2); l2.add(1);
                  l3 = new ArrayList<>(); l3.add(1); l3.add(2); l3.add(0);
                  l4 = new ArrayList<>(); l4.add(2); l4.add(3); l4.add(1);
                  l5 = new ArrayList<>(); l5.add(2); l5.add(2); l5.add(9);
    List<Integer> l6 = new ArrayList<>(); l6.add(2); l6.add(3); l6.add(2);
    nums = new LinkedList<>(); nums.add(l1);nums.add(l2);nums.add(l3);nums.add(l4);nums.add(l5);nums.add(l6);

    f1 = (x, y) -> y.get(0) < x.get(0);
    f2 = (x, y) -> x.get(2) > y.get(2);
    BiPredicate<List<Integer>, List<Integer>> f3 = (x, y) -> x.get(1) < y.get(1);
    funcs = new LinkedList<>(); funcs.add(f1); funcs.add(f2); funcs.add(f3);
    cnums = new LinkedList<>(); cnums.add(l5);cnums.add(l6);cnums.add(l4);cnums.add(l1);cnums.add(l2);cnums.add(l3);
    assertEquals(cnums, ListOperations.orderBy(nums, funcs));
  }
}
