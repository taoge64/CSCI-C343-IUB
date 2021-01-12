import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import java.util.function.*;
import java.util.stream.*;

/**
 * PART 2 (PART 1 should be complete before this part is attempted).
 * All exercises below should be done by first converting list to a stream.
 * Note: Feel free to use the toIndList() method to convert a Stream to an IndList.
 */
public class ListOperations {

  /**
   * TODO
   * Repeats every item three times.
   * 
   * @param l Input list
   * @return IndList with every item in l repeated three times.
   */
  public static <E> IndList<E> triplicate(List<E> l) {
    Stream<IndList<E>> acc= l.stream().map(a -> new Node<>(a,new Node<>(a,new Node<>(a,new Empty<>()))));


  return acc.reduce(new Empty(),((accd,x)->(accd.append(x))));
  }

  /**
   * TODO
   * Squares every number.
   * 
   * @param l List of Integers.
   * @return IndList with every number in l squared.
   */
  public static IndList<Integer> square(List<Integer> l) {
    Stream<Integer> acc= l.stream().map(a->a*a);
    return  ListOperations.toIndList(acc) ;}
  /**
   * TODO
   * Checks if pred is satisfied by every item.
   * 
   * @param l Input list.
   * @param pred Predicate function 
   * @return true iff pred(x) == true for every item `x` in l, else false.
   */
  public static <E> boolean allTrue(List<E> l, Predicate<? super E> pred) {

    Stream<E> A=l.stream().filter(x -> pred.test(x));
    return ListOperations.toIndList(A);
  }

  /**
   * TODO
   * Checks if every integer is even.
   *
   * @param l List of Integer
   * @return IndList with all even Integers from l.
   */
  public static IndList<Integer> evens(List<Integer> l) {

   Stream<Integer> acc= l.stream().filter(a->a%2==0);
   if acc

    return
  }

  /**
   * TODO
   * Mulitplies all integers.
   *
   * @param l List of Integer.
   * @return Product of all integers in l.
   */
  public static Integer product(List<Integer> l) {
l   Stream a= l.stream().reduce(1,(ac,x)->acc*x);
    return a.
  }

  /**
   * TODO
   * Reverses the given list. (HINT: Use reduction)
   * DO NOT USE INDLIST.REVERSE()
   * You should reverse the given list using stream operations.
   * @param l Input list.
   * @return Indlist representation of reversed l.
   *
   * NOTE: You might have to help Java with the Types (à la type casting) in this method.
   */
  public static <E> IndList<E> reverse(List<Integer> l) {
    return null;
  }

  /*******************************************
                 Challenge Problem
   This can be really tricky so attempt only if
   rest of the lab is 100% complete.
   There is no extra credit. This is just for fun :)
   *******************************************/

  /**
   * Sorts the items in order of the given BiPredicates.
   * Example 0: lets say we have the following list:
   * ["grape", "passionfruit", "banana", "mango", "orange", "raspberry", "apple", "blueberry"]
   * and we wish to sort it first with respect to their lengths, and then sort it in
   * alphabetical order. Then, the list looks as follows:
   * ["apple", "grape", "mango", "banana", "orange", "blueberry", "raspberry", "passionfruit"]
   * Note that it doesn't sort the whole list first by length, and then sort the whole list again
   * by alphabets. If we did that, we would get the following list.
   * ["apple", "banana", "blueberry", "grape", "mango", "orange", "passionfruit", "raspberry"]
   *
   * Example 1: lets say we have the following list:
   * [[1, 1], [2, 2], [3, 3], [1, 2], [2, 3]]
   * and we wish to first sort the items in ascending order with repect to the first element,
   * and then in descending order with respect to the second element. 
   * Then, the result will look something like the following list:
   * [[1, 2], [1, 1], [2, 3], [2, 2], [3, 3]].
   *
   * Example 2: lets say we have the following list:
   * [[1, 5, 3], [2, 2, 0], [3, 2, 1], [1, 1, 1], [5, 0, 0], [2, 3, 0], [1, 3, 1], [2, 2, 1], [1, 4, 0]].
   * and we wish to first sort the items in descending order with 
   * respect to the first element, then ascending order with respect
   * to the second element, and finally, in descending order with respect
   * to the third element. Then, the result will look something like:
   * [[5, 0, 0], [3, 2, 1], [2, 2, 1], [2, 2, 0], [2, 3, 0], [1, 1, 1], [1, 3, 1], [1, 4, 0], [1, 5, 3]]
   *
   * @param l List to be sorted.
   * @param orderingList l is sorted first by the first function, then the second function, and so on....
   *        For proper usage, the comparisons done should be strict (eg, <, > are okay, but <=, >= will not work).
   * @return l sorted with respect to the ordering in orderingList.
   */
  public static <E> List<E> orderBy(List<E> l, List<BiPredicate<E, E>> orderingList) {
    BiPredicate<E, E> compFunc = orderingFunction(orderingList);
    Comparator<E> comp = (x, y) -> compFunc.test(x, y)? -1 : 1;
    return null /*TODO*/;
  }

  /**
   * TODO
   * HINT: This method plays a big part in this problem. You might need
   *       a helper function to complete this method.
   *
   * @param orderingList Functions used for ordered sorting in orderBy().
   * @return A BiPredicate function that encapsulates the ordering 
   *         defined by all the functions in orderingList.
   */ 
  private static <E> BiPredicate<E, E> orderingFunction(List<BiPredicate<E, E>> orderingList) {
    return null ;
  }

  /**
   * Function to convert a stream to IndList
   * @param s Stream of E
   * @return IndList of E.
   */ 
  public static <E> IndList<E> toIndList(Stream<E> s) {
    BiFunction<IndList<E>, E, IndList<E>> f = (y, x) -> new Node(x, y);
    return (s.reduce(new Empty<>(), f, (y, x) -> new Node(x, y))).reverse();
  }

}
