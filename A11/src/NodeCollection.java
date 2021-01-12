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
            case QUEUE:
                return new QUEUE_COLL<>();
            case STACK:
                return new STACK_COLL<>();
            case BINARY_HEAP:
                return new BINARY_HEAP_COLL<>();
            case DHEAP_3:
                return new DHEAP_3_COLL<>();
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

    public boolean equals(Object obj) {

        if (obj instanceof String) {
            String that = (String) obj;
            return that.equals(this);
        } else {
            return false;
        }
    }
}

// ---------------------------------------------------------------------------

class QUEUE_COLL<E> extends NodeCollection<E> {
    private Queue<Item<E>> queue;

    QUEUE_COLL() {
        this.queue = new LinkedList<>();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> u) {
        queue.add(u);
    }

    void insert(Item<E> elem) {
        queue.add(elem);
    }

    Item<E> extract() {
        return queue.poll();
    }

    public String toString(){
        String alpha="";
        while(!queue.isEmpty()){
            alpha+=queue.poll().toString();
        }
return alpha;
    }
}

// ---------------------------------------------------------------------------

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
    public String toString(){
        String alpha="";
        while(!stack.isEmpty()){
            alpha+=stack.pop().toString();
        }
        return alpha;
    }
}

// ---------------------------------------------------------------------------

class BINARY_HEAP_COLL<E> extends NodeCollection<E> {
    private BinaryHeap<E> heap;

    BINARY_HEAP_COLL() {
        this.heap = new BinaryHeap<>();
    }

    boolean isEmpty() {
       return heap.isEmpty();
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> u) {
        for (Item<E>i : nodes){

                i.setValue(Integer.MAX_VALUE);
        }
            u.setPosition(0);
            u.setValue(0);

        heap= new BinaryHeap<>(nodes);
    }

    void updateKey(int position, int value) {
        heap.updateKey(position,value);
    }

    void insert(Item<E> elem) {
        heap.insert(elem);
    }

    Item<E> extract() {
        return heap.extractMin();
    }
    public String toString(){
        String alpha="";
      for(int i=0;i<heap.getSize();i++){
          alpha+=heap.getKey(i);
      }
        return alpha;
    }
}


// ---------------------------------------------------------------------------

class DHEAP_3_COLL<E> extends NodeCollection<E> {
    private DHeap<E> heap;

    DHEAP_3_COLL() {
        this.heap = new DHeap<>(3);
    }

    boolean isEmpty() {
        return heap.isEmpty();
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> u) {
        for (Item<E>i : nodes){

                i.setValue(Integer.MAX_VALUE);

        }
        u.setPosition(0);
        u.setValue(0);
        heap= new DHeap<>(3,nodes);
    }

    void updateKey(int position, int value) {
        heap.updateKey(position,value);
    }

    void insert(Item<E> elem) {
        heap.insert(elem);
    }

    Item<E> extract(){
        return heap.extractMin();
    }
    public String toString(){
        String alpha="";
        for(int i=0;i<heap.getSize();i++){
            alpha+=heap.getKey(i);
        }
        return alpha;
    }

}

// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------

