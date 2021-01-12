
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void emptytree () {
        Random r = new Random();
        Tree<Integer> t1 = Tree.makeTreeNodes(0,2, r, 10);
        assertEquals(2, t1.getBranchingFactor());
        assertEquals(0, t1.getHeight());
        assertEquals(0, t1.getNumberOfNodes());
        assertTrue(t1.isEmpty());
        assertFalse(t1.isLeaf());
        assertEquals(0, t1.numberOfInternalNodes());
        assertEquals(0, t1.numberOfLeaves());
        //my test on empty tree

        Tree<Integer> t2 = Tree.makeTreeNodes(0,5, r, 10);
        assertEquals(5, t2.getBranchingFactor());
        assertEquals(0, t2.getHeight());
        assertEquals(0, t2.getNumberOfNodes());
        assertTrue(t2.isEmpty());
        assertFalse(t2.isLeaf());
        assertEquals(0, t2.numberOfInternalNodes());
        assertEquals(0, t2.numberOfLeaves());

        Tree<Integer> t3 = Tree.makeTreeNodes(0,1, r, 10);
        assertEquals(1, t3.getBranchingFactor());
        assertEquals(0, t3.getHeight());
        assertEquals(0, t3.getNumberOfNodes());
        assertTrue(t3.isEmpty());
        assertFalse(t3.isLeaf());
        assertEquals(0, t3.numberOfInternalNodes());
        assertEquals(0, t3.numberOfLeaves());
        
        Tree<Integer> t4 = Tree.makeTreeNodes(0,0, r, 10);
        assertEquals(0, t4.getBranchingFactor());
        assertEquals(0, t4.getHeight());
        assertEquals(0, t4.getNumberOfNodes());
        assertTrue(t4.isEmpty());
        assertFalse(t4.isLeaf());
        assertEquals(0, t4.numberOfInternalNodes());
        assertEquals(0, t4.numberOfLeaves());
    }

    @Test
    public void leaf () {
        Random r = new Random();
        Tree<Integer> t1 = Tree.makeLeaf(5, 8);
        assertEquals(8, t1.getBranchingFactor());
        assertEquals(1, t1.getHeight());
        assertEquals(1, t1.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertTrue(t1.isLeaf());
        assertEquals(0, t1.numberOfInternalNodes());
        assertEquals(1, t1.numberOfLeaves());
        //my test on leaf
        Tree<Integer> t2 = Tree.makeLeaf(10, 4);
        assertEquals(4, t2.getBranchingFactor());
        assertEquals(1, t2.getHeight());
        assertEquals(1, t2.getNumberOfNodes());
        assertFalse(t2.isEmpty());
        assertTrue(t2.isLeaf());
        assertEquals(0, t2.numberOfInternalNodes());
        assertEquals(1, t2.numberOfLeaves());
        Tree<Integer> t3 = Tree.makeLeaf(4, 3);
        assertEquals(3, t3.getBranchingFactor());
        assertEquals(1, t3.getHeight());
        assertEquals(1, t3.getNumberOfNodes());
        assertFalse(t3.isEmpty());
        assertTrue(t3.isLeaf());
        assertEquals(0, t3.numberOfInternalNodes());
        assertEquals(1, t3.numberOfLeaves());
        Tree<Integer> t4 = Tree.makeLeaf(12, 13);
        assertEquals(13, t4.getBranchingFactor());
        assertEquals(1, t4.getHeight());
        assertEquals(1, t4.getNumberOfNodes());
        assertFalse(t4.isEmpty());
        assertTrue(t4.isLeaf());
        assertEquals(0, t4.numberOfInternalNodes());
        assertEquals(1, t4.numberOfLeaves());
    }


    @Test
    public void tree1 () {
        Random r = new Random();
        Tree<Integer> t1;

        t1 = Tree.makeTreeNodes(1+3+9+27,3,r,10);
        assertEquals(3, t1.getBranchingFactor());
        assertEquals(4, t1.getHeight());
        assertEquals(40, t1.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t1.isLeaf());
        assertEquals(13, t1.numberOfInternalNodes());
        assertEquals(27, t1.numberOfLeaves());

        t1 = Tree.makeTreeInternals(13,3,r,10);
        assertEquals(3, t1.getBranchingFactor());


        assertEquals(4, t1.getHeight());
        assertEquals(40, t1.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t1.isLeaf());
        assertEquals(13, t1.numberOfInternalNodes());
        assertEquals(27, t1.numberOfLeaves());

        t1 = Tree.makeTreeLeaves(27,3,r,10);
        assertEquals(3, t1.getBranchingFactor());
        assertEquals(4, t1.getHeight());
        assertEquals(40, t1.getNumberOfNodes());
        assertFalse(t1.isEmpty());
        assertFalse(t1.isLeaf());
        assertEquals(13, t1.numberOfInternalNodes());
        assertEquals(27, t1.numberOfLeaves());
//Mytest on tree 1
        Tree<Integer> t2;
        t2 = Tree.makeTreeNodes(1+4+16+64,4,r,10);
        assertEquals(4, t2.getBranchingFactor());
        assertEquals(5, t2.getHeight());
        assertEquals(1+4+16+64, t2.getNumberOfNodes());
        assertFalse(t2.isEmpty());
        assertFalse(t2.isLeaf());
        assertEquals(21, t2.numberOfInternalNodes());
        assertEquals(64, t2.numberOfLeaves());

        t2 = Tree.makeTreeInternals(21,4,r,10);
        assertEquals(4, t1.getBranchingFactor());


        assertEquals(5, t2.getHeight());
        assertEquals(1+4+16+64, t2.getNumberOfNodes());
        assertFalse(t2.isEmpty());
        assertFalse(t2.isLeaf());
        assertEquals(21, t2.numberOfInternalNodes());
        assertEquals(64, t2.numberOfLeaves());

        t2 = Tree.makeTreeLeaves(64,4,r,10);
        assertEquals(4, t2.getBranchingFactor());
        assertEquals(5, t2.getHeight());
        assertEquals(1+4+16+64, t2.getNumberOfNodes());
        assertFalse(t2.isEmpty());
        assertFalse(t2.isLeaf());
        assertEquals(21, t2.numberOfInternalNodes());
        assertEquals(64, t2.numberOfLeaves());

        Tree<Integer> t3;
        t3 = Tree.makeTreeNodes(1+2+4,2,r,10);
        assertEquals(2, t3.getBranchingFactor());
        assertEquals(3, t3.getHeight());
        assertEquals(1+2+4, t3.getNumberOfNodes());
        assertFalse(t3.isEmpty());
        assertFalse(t3.isLeaf());
        assertEquals(3, t3.numberOfInternalNodes());
        assertEquals(4, t3.numberOfLeaves());

        t3 = Tree.makeTreeInternals(3,2,r,10);
        assertEquals(2, t3.getBranchingFactor());


        assertEquals(3, t3.getHeight());
        assertEquals(7, t3.getNumberOfNodes());
        assertFalse(t3.isEmpty());
        assertFalse(t3.isLeaf());
        assertEquals(3, t3.numberOfInternalNodes());
        assertEquals(4, t3.numberOfLeaves());

        t3 = Tree.makeTreeLeaves(4,2,r,10);
        assertEquals(2, t3.getBranchingFactor());
        assertEquals(3, t3.getHeight());
        assertEquals(7, t3.getNumberOfNodes());
        assertFalse(t3.isEmpty());
        assertFalse(t3.isLeaf());
        assertEquals(3, t3.numberOfInternalNodes());
        assertEquals(4, t3.numberOfLeaves());
        Tree<Integer> t4;
        t4 = Tree.makeTreeNodes(1+5,5,r,10);
        assertEquals(5, t4.getBranchingFactor());
        assertEquals(3, t4.getHeight());
        assertEquals(6, t4.getNumberOfNodes());
        assertFalse(t4.isEmpty());
        assertFalse(t4.isLeaf());
        assertEquals(1, t4.numberOfInternalNodes());
        assertEquals(5, t4.numberOfLeaves());

        t4 = Tree.makeTreeInternals(1,5,r,10);
        assertEquals(5, t4.getBranchingFactor());


        assertEquals(3, t4.getHeight());
        assertEquals(6, t4.getNumberOfNodes());
        assertFalse(t4.isEmpty());
        assertFalse(t4.isLeaf());
        assertEquals(1, t4.numberOfInternalNodes());
        assertEquals(5, t4.numberOfLeaves());

        t4 = Tree.makeTreeLeaves(5,5,r,10);
        assertEquals(5, t4.getBranchingFactor());
        assertEquals(3, t4.getHeight());
        assertEquals(6, t4.getNumberOfNodes());
        assertFalse(t4.isEmpty());
        assertFalse(t4.isLeaf());
        assertEquals(1, t4.numberOfInternalNodes());
        assertEquals(5, t4.numberOfLeaves());
    }

}
