import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class Tests {

    @org.junit.Test
    public void isEmpty() {
        int num = 14;
        ArrayList<Item<String>> items = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(1);
            items.add(new Item<>("B"+k, "B" + k, k));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        assertFalse(bhp.isEmpty());
        ArrayList<Item<String>> ite = new ArrayList<>();
        BinaryHeap<String> bp = new BinaryHeap<>(ite);
        assertTrue(bp.isEmpty());
  ite.add(new Item<>("C",""+11,10));
         bp = new BinaryHeap<>(ite);
        assertFalse(bp.isEmpty());
        //DHeap Testing
        DHeap<String> dhp=new DHeap<String>(3,items);
        assertFalse(dhp.isEmpty());
        ite = new ArrayList<>();
        BinaryHeap<String> dp = new BinaryHeap<>(ite);
        assertTrue(dp.isEmpty());
        ite.add(new Item<>("C",""+11,10));
        dp = new BinaryHeap<>(ite);
        assertFalse(dp.isEmpty());

    }

    @org.junit.Test
    public void getSize() {
        int num = 10;
        ArrayList<Item<String>> items = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(1);
            items.add(new Item<>("B"+k, "B" + k, k));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        assertEquals(num,bhp.getSize());
        ArrayList<Item<String>> ite = new ArrayList<>();
        BinaryHeap<String> bp = new BinaryHeap<>(ite);
        assertEquals(0,bp.getSize());
        ite.add(new Item<>("C",""+11,10));
        bp = new BinaryHeap<>(ite);
        assertEquals(1,bp.getSize());

        //DHeap Testing
        DHeap<String> dhp=new DHeap<String>(3,items);
        assertEquals(10,dhp.getSize());
        ite = new ArrayList<>();
        DHeap<String> dp = new DHeap<String>(3,ite);
        assertEquals(0,dp.getSize());
        ite.add(new Item<>("C",""+11,10));
        dp = new DHeap<String>(3,ite);
        assertEquals(1,dp.getSize());
    }

    @org.junit.Test
    public void findMin() {
        int num = 10;
        ArrayList<Item<String>> items = new ArrayList<>();
        Random r = new Random(2);
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B"+k, "B" + k, k*3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        assertEquals(-9,bhp.findMin().getValue());
        ArrayList<Item<String>> ite = new ArrayList<>();
        BinaryHeap<String> bp = new BinaryHeap<>(ite);
        assertEquals(null,bp.findMin());
        ite.add(new Item<>("C",""+11,10));
        bp = new BinaryHeap<>(ite);
        assertEquals(10,bp.findMin().getValue());
        //DHeap Testing
        DHeap<String> dhp=new DHeap<String>(3,items);
        assertEquals(-9,dhp.findMin().getValue());
        ite = new ArrayList<>();
        ite.add(new Item<>("C",""+11,5));
        DHeap<String> dp = new DHeap<String>(3,ite);
        assertEquals(5,dp.findMin().getValue());
        ite.add(new Item<>("C",""+11,10));
        dp = new DHeap<>(3,ite);
        assertEquals(5,dp.findMin().getValue());

    }

    @org.junit.Test
    public void getElems() {
        int num = 15;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B"+k, "B" + k, k*3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        assertEquals(items,bhp.getElems());
        ArrayList<Item<String>> ite = new ArrayList<>();
        BinaryHeap<String> bp = new BinaryHeap<>(ite);
        assertEquals(ite,bp.getElems());
        ite.add(new Item<>("C",""+11,10));
        bp = new BinaryHeap<>(ite);
        assertEquals(ite,bp.getElems());
        //DHeap Testing
        DHeap<String> dhp=new DHeap<String>(3,items);
        assertEquals(items,dhp.getElems());
        ite = new ArrayList<>();
        ite.add(new Item<>("C",""+11,5));
        DHeap<String> dp = new DHeap<>(5,ite);
        assertEquals(ite,dp.getElems());
        ite.add(new Item<>("C",""+11,10));
        dp = new DHeap<>(10,ite);
        assertEquals(ite,dp.getElems());
    }

    @org.junit.Test
    public void getParentIndex() {
        int num = 15;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        try {
            assertEquals(5,bhp.getParentIndex(10));
            assertEquals(2,bhp.getParentIndex(4));
            assertEquals(1,bhp.getParentIndex(2));
        }catch(NoParentE e){
            throw new Error();
        }
        DHeap<String> dhp=new DHeap<String>(3,items);
        try {
            assertEquals(3,dhp.getParentIndex(10));
            dhp=new DHeap<String>(4,items);
            assertEquals(1,dhp.getParentIndex(4));
            dhp=new DHeap<String>(5,items);
            assertEquals(3,dhp.getParentIndex(13));
        }catch(NoParentE e){
            throw new Error();
        }
    }
    @org.junit.Test
    public void getLeftChildIndex() {
        int num = 55;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        try {
            assertEquals(20,bhp.getLeftChildIndex(10));
            assertEquals(8,bhp.getLeftChildIndex(4));
            assertEquals(4,bhp.getLeftChildIndex(2));
        }catch(NoLeftChildE e){
            throw new Error();
        }
        DHeap<String> dhp=new DHeap<String>(3,items);
        try {
            assertEquals(30,dhp.getChildIndex(10,2));
            dhp=new DHeap<String>(4,items);
            assertEquals(49,dhp.getChildIndex(12,4));
            dhp=new DHeap<String>(5,items);
            assertEquals(14,dhp.getChildIndex(3,3));
        }catch(NoChildE e){
            throw new Error();
        }

    }

    @org.junit.Test
    public void getRightChildIndex() {
        int num = 55;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        try {
            assertEquals(21,bhp.getRightChildIndex(10));
            assertEquals(9,bhp.getRightChildIndex(4));
            assertEquals(5,bhp.getRightChildIndex(2));
        }catch(NoRightChildE e){
            throw new Error();
        }
        DHeap<String> dhp=new DHeap<String>(3,items);
        try {
            assertEquals(31,dhp.getChildIndex(10,3));
            dhp=new DHeap<String>(4,items);
            assertEquals(46,dhp.getChildIndex(12,1));
            dhp=new DHeap<String>(5,items);
            assertEquals(13,dhp.getChildIndex(3,2));
        }catch(NoChildE e){
            throw new Error();
        }
    }

    @org.junit.Test
    public void swap() {
        int num = 55;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        int alpha=bhp.getKey(1);
        int beta=bhp.getKey(3);
        bhp.swap(1,3);
        assertEquals(alpha,bhp.getKey(3));
        assertEquals(beta,bhp.getKey(1));
         alpha=bhp.getKey(5);
         beta=bhp.getKey(2);
        bhp.swap(5,2);
        assertEquals(alpha,bhp.getKey(2));
        assertEquals(beta,bhp.getKey(5));
         alpha=bhp.getKey(10);
         beta=bhp.getKey(9);
        bhp.swap(10,9);
        assertEquals(alpha,bhp.getKey(9));
        assertEquals(beta,bhp.getKey(10));
        DHeap<String> dhp=new DHeap<String>(3,items);
         alpha=dhp.getKey(1);
         beta=dhp.getKey(3);
        dhp.swap(1,3);
        assertEquals(alpha,dhp.getKey(3));
        assertEquals(beta,dhp.getKey(1));
        alpha=dhp.getKey(5);
        beta=dhp.getKey(2);
        dhp.swap(5,2);
        assertEquals(alpha,dhp.getKey(2));
        assertEquals(beta,dhp.getKey(5));
        alpha=dhp.getKey(10);
        beta=dhp.getKey(9);
        dhp.swap(10,9);
        assertEquals(alpha,dhp.getKey(9));
        assertEquals(beta,dhp.getKey(10));

    }

    @org.junit.Test
    public void getKey() {
        int num = 55;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        assertEquals(-9,bhp.getKey(1));
        assertEquals(18,bhp.getKey(10));
        assertEquals(54,bhp.getKey(22));
        DHeap<String> dhp=new DHeap<String>(3,items);
        assertEquals(-9,dhp.getKey(1));
        assertEquals(18,dhp.getKey(10));
        assertEquals(54,dhp.getKey(22));

    }

    @org.junit.Test
    public void updateKey() {
        int num = 55;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
    bhp.updateKey(1,100);
        assertEquals(100,bhp.getKey(1));
    bhp.updateKey(10,1000);
        assertEquals(1000,bhp.getKey(10));
    bhp.updateKey(34,-999);
        assertEquals(39,bhp.getKey(34));
        DHeap<String> dhp=new DHeap<String>(3,items);
        dhp.updateKey(1,100);
        assertEquals(100,dhp.getKey(1));
        dhp.updateKey(10,1000);
        assertEquals(1000,dhp.getKey(10));
        dhp.updateKey(34,-999);
        assertEquals(21,dhp.getKey(34));
    }
    @org.junit.Test
    public void insert() {
        int num = 10;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        bhp.insert(new Item<>("D","1",10));
        bhp.insert(new Item<>("D","1",100));
        bhp.insert(new Item<>("D","1",1000));
        assertEquals(1000,bhp.getKey(17));
        assertEquals(100,bhp.getKey(16));
        assertEquals(10,bhp.getKey(15));
        DHeap<String> dhp=new DHeap<String>(3,items);
        dhp.insert(new Item<>("D","1",10));
        dhp.insert(new Item<>("D","1",100));
        dhp.insert(new Item<>("D","1",1000));
        assertEquals(1000,dhp.getKey(17));
        assertEquals(100,dhp.getKey(16));
        assertEquals(10,dhp.getKey(15));
    }


    @org.junit.Test
    public void minChildIndex() {
        int num = 30;
        ArrayList<Item<String>> items = new ArrayList<>();
        for (int i = -3; i <= num; i++) {
            int k = i;
            items.add(new Item<>("B" + k, "B" + k, k * 3));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        try {
            assertEquals(6, bhp.minChildIndex(3));
            assertEquals(12, bhp.minChildIndex(6));
            assertEquals(8, bhp.minChildIndex(4));
        }catch (NoLeftChildE e){
            throw new Error();
        }
        DHeap<String> dhp=new DHeap<String>(3,items);
        try {
            assertEquals(8, dhp.minChildIndex(3));
            assertEquals(17, dhp.minChildIndex(6));
            assertEquals(11, dhp.minChildIndex(4));
        }catch (NoChildE e){
            throw new Error();
        }
    }
}