import static org.junit.Assert.*;
import java.util.Random;
public class Tests {

    @org.junit.Test
    public void easyRight() {
        BinTree t1, t11, t2, t22, t3, t33;
        t1 = BinTree.makeLeaf(10).insert(9).insert(8);
        t11 = BinTree.makeLeaf(9).insert(10).insert(8);
        assertEquals(t11, t1.easyRight());
        t2 = BinTree.makeLeaf(10).insert(7).insert(8);
        t22 = BinTree.makeLeaf(7).insert(10).insert(8);
        assertEquals(t22, t2.easyRight());
        t3 = BinTree.makeLeaf(21).insert(22).insert(23).insert(19);
        t33 = BinTree.makeLeaf(19).insert(21).insert(22).insert(23);
        assertEquals(t33, t3.easyRight());


    }

    @org.junit.Test
    public void easyLeft() {
        BinTree t1, t11, t2, t22, t3, t33;
        t1 = BinTree.makeLeaf(10).insert(11).insert(12);
        t11 = BinTree.makeLeaf(11).insert(10).insert(12);
        assertEquals(t11, t1.easyLeft());
        t2 = BinTree.makeLeaf(10).insert(11).insert(8);
        t22 = new Node(11, BinTree.makeLeaf(10), BinTree.makeLeaf(8));
        assertEquals(t22, t2.easyLeft());
        t3 = BinTree.makeLeaf(21).insert(22).insert(23).insert(19);
        t33 = BinTree.makeLeaf(22).insert(21).insert(19).insert(23);
        assertEquals(t33, t3.easyLeft());

    }

    @org.junit.Test
    public void RotateRight() {
        BinTree t1, t11, t2, t22, t3, t33;
        t1 = BinTree.makeLeaf(40).insert(20).insert(50).insert(10).insert(30).insert(25);
        t2 = BinTree.makeLeaf(4).insert(1).insert(2);
        t3 = BinTree.makeLeaf(20).insert(21).insert(19).insert(18).insert(17);
        t11 = new Node(30, BinTree.makeLeaf(20).insert(10).insert(25), BinTree.makeLeaf(40).insert(50));
        t22 = new Node(2, BinTree.makeLeaf(1), BinTree.makeLeaf(4));
        t33 = new Node(19, BinTree.makeLeaf(18).insert(17), BinTree.makeLeaf(20).insert(21));
        assertEquals(t11, t1.rotateRight());
        assertEquals(t22, t2.rotateRight());
        assertEquals(t33, t3.rotateRight());
    }

    @org.junit.Test
    public void RotateLeft() {
        BinTree t1, t11, t2, t22, t3, t33;
        t1 = BinTree.makeLeaf(40).insert(20).insert(50).insert(10).insert(30).insert(60);
        t2 = BinTree.makeLeaf(4).insert(20).insert(9);
        t3 = BinTree.makeLeaf(20).insert(21).insert(22).insert(23).insert(17);
        t11 = BinTree.makeLeaf(50).insert(40).insert(60).insert(20).insert(10).insert(30);
        t22 = new Node(9, BinTree.makeLeaf(4), BinTree.makeLeaf(20));
        t33 = BinTree.makeLeaf(21).insert(20).insert(22).insert(23).insert(17);
        assertEquals(t11, t1.rotateLeft());
        assertEquals(t22, t2.rotateLeft());
        assertEquals(t33, t3.rotateLeft());
    }

    @org.junit.Test
    public void InsertB() {
        BinTree t1, t11, t2, t22, t3, t33;
        t1 = BinTree.makeLeaf(40).insertB(20).insertB(5).insertB(10).insertB(30).insertB(60);
        t2 = BinTree.makeLeaf(4).insertB(20).insertB(9);
        t3 = BinTree.makeLeaf(20).insertB(21).insertB(22).insertB(19).insertB(17);
        t11 = BinTree.makeLeaf(20).insert(5).insert(40).insert(10).insert(10).insert(30).insert(60);
        t22 = new Node(9, BinTree.makeLeaf(4), BinTree.makeLeaf(20));
        t33 = BinTree.makeLeaf(21).insert(19).insert(22).insert(17).insert(20);
        assertEquals(t11, t1);
        assertEquals(t22, t2);
        assertEquals(t33, t3);
    }

    @org.junit.Test
    public void deleteB() {
        BinTree t1, t11, t2, t22, t3, t33;
        try {
            t1 = BinTree.makeLeaf(40).insertB(20).insertB(5).insertB(10).insertB(30).insertB(60).deleteB(40);
            t11 = BinTree.makeLeaf(20).insert(5).insert(5).insert(30).insert(30).insert(60).delete(10);
            t22 = BinTree.makeLeaf(2).insert(9).insert(1).insert(3);
            t2 = BinTree.makeLeaf(2).insertB(9).insertB(1).insertB(3).insertB(10).deleteB(10);
            t33 = BinTree.makeLeaf(21).insert(19).insert(22).insert(20);
            t3 = BinTree.makeLeaf(20).insertB(21).insertB(22).insertB(19).insertB(17).deleteB(17);
            assertEquals(t11, t1);
            assertEquals(t22, t2);
            assertEquals(t33, t3);
        } catch (EmptyTreeE e) {
            new Error("test fails, either inner error or can't delete");
        }
    }
}