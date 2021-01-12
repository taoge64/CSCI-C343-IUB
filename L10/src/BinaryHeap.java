import java.util.List;

class NoParentE extends Exception {}
class NoLeftChildE extends Exception {}
class NoRightChildE extends Exception {}

interface BinaryHeap<E> {
    // constructor BinaryHeap()
    boolean isEmpty();
    int getSize();
    Item<E> findMin();
    List<Item<E>> getElems();
    int getParentIndex(int i) throws NoParentE;
    int getLeftChildIndex(int i) throws NoLeftChildE;
    int getRightChildIndex(int i) throws NoRightChildE;
    void swap(int i, int j);
    int getKey(int i);
    void updateKey(int i, int value); // new value < existing value
    void moveUp(int i);
    void insert(Item<E> ek);
    int minChildIndex(int i) throws NoLeftChildE;
    void moveDown(int i);
    Item<E> extractMin();
}