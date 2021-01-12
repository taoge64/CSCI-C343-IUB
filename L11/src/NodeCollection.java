import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// ---------------------------------------------------------------------------

enum KIND {
    QUEUE,
    STACK,
    BINARY_HEAP,
    DHEAP_3
}

// ---------------------------------------------------------------------------

abstract class NodeCollection<E> {
    static <E> NodeCollection<E> make(KIND k) {
        switch (k) {
            case STACK:
                return new STACK_COLL<>();
            default:
                throw new Error("Unknown kind");
        }
    }

    abstract boolean isEmpty();

    abstract void setSource(ArrayList<Item<E>> nodes, Item<E> u);

    abstract void insert(Item<E> elem);

    void updateKey(int position, int value) {
        // not used in queue and stack classes; override in heap classes
    }

    abstract Item<E> extract();
}

class STACK_COLL<E> extends NodeCollection<E> {
    private Stack<Item<E>> stack;

    STACK_COLL() {
        this.stack = new Stack<>();
    }

    boolean isEmpty() {
        return stack.isEmpty();
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> u) {
        stack.push(u);
    }

    void insert(Item<E> elem) {
        stack.push(elem);
    }

    Item<E> extract() {
        return stack.pop();
    }

}
