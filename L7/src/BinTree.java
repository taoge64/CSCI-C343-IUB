import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;

//-----------------------------------------------------------------------

class EmptyTreeE extends Exception {}

//-----------------------------------------------------------------------

abstract class BinTree<E> {
    abstract E getData () throws EmptyTreeE;
    abstract BinTree<E> getLeft () throws EmptyTreeE;
    abstract BinTree<E> getRight () throws EmptyTreeE;

    abstract boolean isEmpty ();
    abstract int size();
    abstract int height ();
    abstract int width ();
    abstract BinTree<E> mirror ();
    abstract boolean isBalanced ();

    static <E> BinTree<E> makeLeaf (E elem) {
        return new TreeNode<>(elem, new EmptyTree<>(), new EmptyTree<>());
    }
}

//-----------------------------------------------------------------------

class EmptyTree<E> extends BinTree<E> {
    E getData() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    BinTree<E> getLeft() throws EmptyTreeE {
        throw new EmptyTreeE();
    }


    BinTree<E> getRight() throws EmptyTreeE {
        throw new EmptyTreeE();
    }

    boolean isEmpty() {
        return true;
    }

    int size() {
        return 0;
    }

    int height () {
        return 0;
    }

    int width () {
        return 1;
    }

    BinTree<E> mirror () { return this; }

    boolean isBalanced () { return true; }
}

//-----------------------------------------------------------------------

class TreeNode<E> extends BinTree<E> {
    private E data;
    private BinTree<E> left, right;


    TreeNode (E data, BinTree<E> left, BinTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    E getData() {
        return data;
    }

    BinTree<E> getLeft() {
        return left;
    }


    BinTree<E> getRight() {
        return right;
    }

    boolean isEmpty () { return false; }

    int size () { return 1 + left.size() + right.size(); }

    int height () {
        return 1 + Math.max(left.height(), right.height());
    }

    int width () {
        return left.width() + right.width();
    }

    BinTree<E> mirror () {
        return new TreeNode<>(data, right.mirror(), left.mirror());
    }

    boolean isBalanced () {
        int lh = left.height();
        int rh = right.height();
        boolean hcheck = Math.abs(lh - rh) <= 1;
        return left.isBalanced() && right.isBalanced() && hcheck;

    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
