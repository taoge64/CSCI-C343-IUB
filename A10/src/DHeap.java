import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

class NoChildE extends Exception {
}

class DHeap<E> {
    private int d;
    private int size;
    private ArrayList<Item<E>> elems;

    DHeap(int d) {
        size=0;
        this.elems=new ArrayList<>();
        elems.add(0,null);
        this.d=d;
    }

    DHeap(int d, ArrayList<Item<E>> es) {
        this.d=d;
        size=es.size();
        this.elems=new ArrayList<>();
        elems.add(0,null);
        for (int i=0;i<es.size();i++){
            es.get(i).setPosition(i+1);
            elems.add(es.get(i));
        }
        for (int i=size/d;i>0;i--){
            this.moveDown(i);
        }



    }

    boolean isEmpty() {
        if(size==0){
            return true;
        }else{
            return false;
        }
    }

    int getSize() {
        return size;
    }

    Item<E> findMin() {
        if (size==0){
            return null;
        }else{
            return elems.get(1);
        }
    }

    List<Item<E>> getElems() {
        return elems.subList(1,elems.size());
    }

    int getParentIndex(int i) throws NoParentE {
        if (i==1){
            throw new NoParentE();
        }
        else{
            return (i+d-2)/d;
        }
    }

    int getChildIndex(int i, int j) throws NoChildE {
        if( i*d-(d-2)+j-1<=size){
            return i*d-(d-2)+j-1;
        }
        else{
            throw new NoChildE();
        }
    }

    void swap(int i, int j) {
        Item<E> a=elems.get(i);
        Item<E> b=elems.get(j);
        a.setPosition(j);
        b.setPosition(i);
        elems.set(i,b);
        elems.set(j,a);
    }

    int getKey(int i) {
        if (i > 0 && i <= size) {
            return elems.get(i).getValue();
        }
        else{
            return -1;
        }
    }

    void updateKey(int i, int value) {
        if(i<=0||i>size){
            return;
        }
        elems.get(i).setValue(value);
        moveUp(i);
    }

    void moveUp(int i) {
        try{
            int parent=getParentIndex(i);
            if(getKey(i)<getKey(parent)){
                swap(i,parent);
                moveUp(parent);
            }
        }catch( NoParentE e){
        }
    }

    void insert(Item<E> ek) {
        size += 1;
        elems.add(ek);
        ek.setPosition(size);
        moveUp(size);
    }
    /*
     * Returns the index of the smallest child.
     */
    int minChildIndex(int i) throws NoChildE {
            int result=i*d-(d-2);
            int current= Integer.MAX_VALUE;
           for(int begin=1;begin<=d;begin++){
               if (elems.get(getChildIndex(i,begin)).getValue()<current){
                   current=elems.get(getChildIndex(i,begin)).getValue();
                   result=begin;
               }
           }
        return getChildIndex(i,result);
    }

    void moveDown(int i) {
        try{
            int minchild=minChildIndex(i);
            if(getKey(i)>getKey(minchild)){
                swap(i,minchild);
                moveDown(minchild);
            }
        }catch( NoChildE e){
        }
    }

    Item<E> extractMin() {
        Item minItem = elems.get(1);
        swap(1,size);
        elems.remove(size);
        this.size -= 1;
        moveDown(1);
        return minItem;
    }

    public String toString() {
        return getElems().toString();
    }

}

