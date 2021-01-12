/**
 * PART 1
 * TODO
 * Convert IndList to a generic class.
 * (Currently, IndList is a list of integers. After
 *  conversion, you should able to make a list of anything!)
 */
import java.util.function.BiFunction;
abstract class IndList<E> {
    abstract int length ();
    abstract boolean isEmpty();
    abstract IndList<E> append (IndList<E> xs);
    abstract IndList<E> reverse ();
}

class Empty<E> extends IndList<E> {
    int length () { return 0; }
    boolean isEmpty () { return true; }
    IndList<E> append (IndList<E> ys) { return ys; }

    public boolean equals(Object other) {
      return other instanceof Empty;
    }

    IndList<E> reverse() {
      return new Empty();
    }
}

class Node<E> extends IndList<E> {
    private E data;
    private IndList<E> rest;

    Node(E data, IndList<E> rest) {
        this.data = data;
        this.rest = rest;
    }

    int length() {
        return 1 + rest.length();
    }

    boolean isEmpty() {
        return false;
    }

    IndList <E> append(IndList<E> ys) {
        return new Node<>(this.data, rest.append(ys));
    }

    IndList<E> reverse() {
      return rest.reverse().append(new Node<>(data, new Empty()));
    }

    public boolean equals(Object other) {
        if (other instanceof Node){
            Node xs = (Node) other;
            return this.data.equals(xs.data) && this.rest.equals(xs.rest);
        }
        else return false;
    }
}


