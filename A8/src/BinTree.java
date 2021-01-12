import java.nio.channels.Pipe;
import java.util.function.BiFunction;

class EmptyTreeE extends Exception {
}

//-----------------------------------------------------------------------
// Abstract tree class

abstract class BinTree implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------
    public boolean equals(Object obj) {
        try{
        if (obj instanceof BinTree) {
            BinTree that = (BinTree) obj;
            return this.getData() == that.getData() && this.getLeftT().equals(that.getLeftT())&&this.getRightT().equals(that.getRightT());
        }else{
        return false;}
        }
        catch (EmptyTreeE e){
            return true;
        }
    }
    static EmptyTreeE ETExc = new EmptyTreeE();

    static BinTree EMPTY = new Empty();

    static BinTree makeLeaf(int elem) {
        return new Node(elem, EMPTY, EMPTY);
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int getData() throws EmptyTreeE;

    abstract BinTree getLeftT() throws EmptyTreeE;

    abstract BinTree getRightT() throws EmptyTreeE;

    abstract int getHeight();

    abstract boolean isEmpty();

    abstract boolean isLeaf();

    //--------------------------
    // reduce either left or right traversal pattern
    //--------------------------

    abstract <R> R reduceLeft(R base, BiFunction<Integer, R, R> f);

    abstract <R> R reduceRight(R base, BiFunction<Integer, R, R> f);

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    abstract boolean find (int key);

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    abstract BinTree insert(int key);

    abstract BinTree delete(int key) throws EmptyTreeE;

    abstract Pair<Integer, BinTree> deleteLeftMostLeaf() throws EmptyTreeE;

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    abstract BinTree insertB(int key);

    abstract BinTree easyRight();

    abstract BinTree rotateRight();

    abstract BinTree easyLeft();

    abstract BinTree rotateLeft();

    abstract BinTree deleteB(int key) throws EmptyTreeE;

    abstract Pair<Integer, BinTree> shrink() throws EmptyTreeE;
}

//-----------------------------------------------------------------------
// Empty tree cases

class Empty extends BinTree {
    public boolean equals(Object obj) {
        try{
            if (obj instanceof BinTree) {
                BinTree that = (BinTree) obj;
                return this.getData() == that.getData() && this.getLeftT().equals(that.getLeftT())&&this.getRightT().equals(that.getRightT());
            }else{
                return false;}
        }
        catch (EmptyTreeE e){
            return true;
        }
    }
    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int getData() throws EmptyTreeE {
        throw ETExc;
    }

    BinTree getLeftT() throws EmptyTreeE {
        throw ETExc;
    }

    BinTree getRightT() throws EmptyTreeE {
        throw ETExc;
    }

    int getHeight() {
        return 0;
    }

    boolean isEmpty() {
        return true;
    }

    boolean isLeaf() {
        return false;
    }

    //--------------------------
    // reduce either left or right traversal pattern
    //--------------------------

    <R> R reduceLeft(R base, BiFunction<Integer, R, R> f) {
        return base;
    }

    <R> R reduceRight(R base, BiFunction<Integer, R, R> f) {
        return base;
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    boolean find (int key) {
        return false;
    }

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    BinTree insert(int key) {
        return makeLeaf(key);
    }

    BinTree delete(int key) throws EmptyTreeE {
        throw ETExc;
    }

    Pair<Integer, BinTree> deleteLeftMostLeaf() throws EmptyTreeE {
        throw ETExc;
    }

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    BinTree insertB(int key) {
        return makeLeaf(key);
    }

    BinTree easyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    BinTree rotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    BinTree easyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    BinTree rotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    BinTree deleteB(int key) throws EmptyTreeE {
        throw ETExc;
    }

    Pair<Integer, BinTree> shrink() throws EmptyTreeE {
        throw ETExc;
    }
}

//-----------------------------------------------------------------------
// Non-empty tree case

class Node extends BinTree {
    private int data;
    private BinTree left, right;
    private int height;

    Node(int data, BinTree left, BinTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.getHeight(), right.getHeight());
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return getLeftT();
    }

    public TreePrinter.PrintableNode getRight() {
        return getRightT();
    }

    public String getText() {
        return String.valueOf(getData());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int getData() {
        return data;
    }

    BinTree getLeftT() {
        return left;
    }

    BinTree getRightT() {
        return right;
    }

    int getHeight() {
        return height;
    }

    boolean isEmpty() {
        return false;
    }

    boolean isLeaf() {
        return left.isEmpty() && right.isEmpty();
    }

    //--------------------------
    // reduce either left or right traversal pattern
    //--------------------------

    <R> R reduceLeft(R base, BiFunction<Integer, R, R> f) {
        return f.apply(data, left.reduceLeft(base, f));
    }

    <R> R reduceRight(R base, BiFunction<Integer, R, R> f) {
        return f.apply(data, right.reduceRight(base, f));
    }

    //--------------------------
    // search assuming tree respects BST order
    //--------------------------

    boolean find(int key) {
        if (data == key) return true;
        else if (key < data) return left.find(key);
        else return right.find(key);
    }

    //--------------------------
    // BST insert/delete (and helper) without balancing
    //--------------------------

    BinTree insert(int key) {
        if (key < data) return new Node(data, left.insert(key), right);
        else return new Node(data, left, right.insert(key));
    }

    BinTree delete(int key) throws EmptyTreeE {
        if (key < data) return new Node(data, left.delete(key), right);
        else if (key > data) return new Node(data, left, right.delete(key));
        else {
            try {
                Pair<Integer, BinTree> dr = right.deleteLeftMostLeaf();
                return new Node(dr.getFirst(), left, dr.getSecond());
            } catch (EmptyTreeE e) {
                return left;
            }
        }
    }

    Pair<Integer, BinTree> deleteLeftMostLeaf() throws EmptyTreeE {
        try {
            Pair<Integer, BinTree> dl = left.deleteLeftMostLeaf();
            return new Pair<>(dl.getFirst(), new Node(data, dl.getSecond(), right));
        } catch (EmptyTreeE e) {
            return new Pair<>(data, right);
        }
    }

    //--------------------------
    // BST insert/delete (and helpers) with balancing
    //--------------------------

    BinTree insertB(int key) {

        if (key < data) {
             BinTree newLeft = left.insertB(key);
            BinTree newNode = new Node(data,newLeft,right);
            if (newLeft.getHeight() > right.getHeight() + 1){
                return newNode.rotateRight();}
            else {return newNode;}
        }
        else {
            BinTree newRight = right.insertB(key);
            BinTree newNode = new Node(data,left,newRight);
            if (newRight.getHeight() > left.getHeight() + 1){
               return newNode.rotateLeft();}
            else {return newNode;}
        }
    }

    BinTree easyRight() {

        try {

            int ld = left.getData();
            BinTree ll = this.getLeftT().getLeftT();
            BinTree lr = this.getLeftT().getRightT();
            return new Node(ld, ll, new Node(data,lr,right));}
        catch (EmptyTreeE e) {throw new Error("Internal bug!");}
    }

    BinTree rotateRight() {
        try {
            if (left.getRightT().getHeight() > left.getLeftT().getHeight()) {
                return new Node(data, left.easyLeft(), right).easyRight();
            } else {
                return this.easyRight();
            }
        } catch (EmptyTreeE emptyTreeE) {throw new Error("Internal bug!");}
    }

    BinTree easyLeft() {
        try {int rd = right.getData();
            BinTree rl = right.getLeftT();
            BinTree rr = right.getRightT();
            return new Node(rd, new Node(data,left,rl),rr);}
        catch (EmptyTreeE e) {throw new Error("Internal bug!");}
    }

    BinTree rotateLeft() {
        try {
            if (right.getLeftT().getHeight() > right.getRightT().getHeight()) {
                return new Node(data, left, right.easyRight()).easyLeft();
            } else {
                return this.easyLeft();
            }
        } catch (EmptyTreeE emptyTreeE) {throw new Error("Internal bug!");}
    }

    BinTree deleteB(int key) throws EmptyTreeE {
        try{
            if (key<this.data){
                BinTree newLeft = left.deleteB(key);
                BinTree newNode = new Node(data,newLeft,right);
                if (newLeft.getHeight() > right.getHeight() + 1){
                    return newNode.rotateRight();}
                else if(right.getHeight()>newLeft.getHeight()+1){
                    return newNode.rotateLeft();
                }
                else {return newNode;}
            }
            else if(key>this.data){
                BinTree newRight = right.deleteB(key);
                BinTree newNode = new Node(data,left,newRight);
                if (newRight.getHeight() > left.getHeight() + 1){
                    return newNode.rotateLeft();}
                else if (left.getHeight()>newRight.getHeight()+1){
                    return newNode.rotateRight();
                }
                else {return newNode;}
            }
            else{
                Pair<Integer, BinTree> shrunk = left.shrink();

                BinTree newRoot= new Node(shrunk.getFirst(),shrunk.getSecond(),right);
                if (right.getHeight()>shrunk.getSecond().getHeight()+1){
                    return newRoot.rotateLeft();
                }
                else if (right.getHeight()+1<shrunk.getSecond().getHeight()){
                    return newRoot.rotateRight();
                }
                else{
                    return newRoot;
                }

            }

        }
        catch(EmptyTreeE e){
            return right;
        }
    }
    Pair<Integer, BinTree> shrink() {
        try {
            Pair<Integer, BinTree> dl = this.right.shrink();
            BinTree newSecond=new Node(this.data,this.left,dl.getSecond());
            if (this.left.getHeight() > dl.getSecond().getHeight() + 1){
                return new Pair<>(dl.getFirst(),newSecond.rotateRight());}
            else
            {
                return new Pair<>(dl.getFirst(),newSecond);}
        }catch(EmptyTreeE e){
            return new Pair<>(data,this.left);
        }
    }

}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------


