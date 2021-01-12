import java.util.function.BinaryOperator;

import static org.junit.Assert.assertEquals;

//Here is my test
public class TreeFunctionsTest {

    @org.junit.Test
    public void countNodes() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        assertEquals(7, TreeFunctions.countNodes(t));
        BinTree<Integer,Integer> t7,t4109,tsecond,t2;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t);
        assertEquals(13, TreeFunctions.countNodes(t2));
        BinTree<Integer,Integer> t3;

        assertEquals(1, TreeFunctions.countNodes(new Leaf<>(1)));
    }

    @org.junit.Test
    public void countLeaves() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        assertEquals(4, TreeFunctions.countLeaves(t));
        BinTree<Integer,Integer> t7,t4109,tsecond,t2;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t);
        assertEquals(7, TreeFunctions.countLeaves(t2));
        BinTree<Integer,Integer> t3;

        assertEquals(1, TreeFunctions.countLeaves(new Leaf<>(1)));
    }

    @org.junit.Test
    public void countInternalNodes() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        assertEquals(3, TreeFunctions.countInternalNodes(t));
        BinTree<Integer,Integer> t7,t4109,tsecond,t2;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t);
        assertEquals(6, TreeFunctions.countInternalNodes(t2));
        BinTree<Integer,Integer> t3;

        assertEquals(0, TreeFunctions.countInternalNodes(new Leaf<>(1)));
    }

    @org.junit.Test
    public void height() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        assertEquals(2, TreeFunctions.height(t));
        BinTree<Integer,Integer> t7,t4109,tsecond,t2;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t);
        assertEquals(3, TreeFunctions.height(t2));
        BinTree<Integer,Integer> t3;

        assertEquals(0, TreeFunctions.height(new Leaf<>(1)));
    }

    @org.junit.Test
    public void isBalanced() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        assertEquals(true, TreeFunctions.isBalanced(t));
        BinTree<Integer,Integer> t7,t4109,tsecond,t2,t4;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t7);
        t4=new Node<>(12,t2,t7);
        assertEquals(false, TreeFunctions.isBalanced(t4));
        BinTree<Integer,Integer> t3;

        assertEquals(true, TreeFunctions.isBalanced(new Leaf<>(1)));
    }

    @org.junit.Test
    public void preorder() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
List<Integer> alp=        new NodeL<>(1,new NodeL<>(7,new NodeL<>(8,new NodeL<>(9,new NodeL<>(4,new NodeL<>(9,new NodeL<>(2,new EmptyL<>())))))));
        assertEquals(alp.toString(), TreeFunctions.preorder(t).toString());
        BinTree<Integer,Integer> t7,t4109,tsecond,t2,t4;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t7);
        t4=new Node<>(12,t2,t7);
        List<Integer> beta=        new NodeL<>(12,new NodeL<>(111,new NodeL<>(1,new NodeL<>(7,new NodeL<>(4,new NodeL<>(10,new NodeL<>(9,new NodeL<>(7,new NodeL<>(7,new EmptyL<>())))))))));
        assertEquals(beta.toString(), TreeFunctions.preorder(t4).toString());
        BinTree<Integer,Integer> t3;
        t3=new Leaf<>(1);
List<Integer> ceta= new NodeL<>(1,new EmptyL<>());
        assertEquals(ceta.toString(), TreeFunctions.preorder(t3).toString());
    }

    @org.junit.Test
    public void inorder() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        List<Integer> alp=        new NodeL<>(8,new NodeL<>(7,new NodeL<>(9,new NodeL<>(1,new NodeL<>(9,new NodeL<>(4,new NodeL<>(2,new EmptyL<>())))))));
        assertEquals(alp.toString(), TreeFunctions.inorder(t).toString());
        BinTree<Integer,Integer> t7,t4109,tsecond,t2,t4;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t7);
        t4=new Node<>(12,t2,t7);
        List<Integer> beta=        new NodeL<>(7,new NodeL<>(1,new NodeL<>(10,new NodeL<>(4,new NodeL<>(9,new NodeL<>(111,new NodeL<>(7,new NodeL<>(12,new NodeL<>(7,new EmptyL<>())))))))));
        assertEquals(beta.toString(), TreeFunctions.inorder(t4).toString());
        BinTree<Integer,Integer> t3;
        t3=new Leaf<>(1);
        List<Integer> ceta= new NodeL<>(1,new EmptyL<>());
        assertEquals(ceta.toString(), TreeFunctions.inorder(t3).toString());
    }

    @org.junit.Test
    public void postorder() {
        BinTree<Integer,Integer> t789, t492, t;

        t789 = new Node<>(7, new Leaf<>(8), new Leaf<>(9));
        t492 = new Node<>(4, new Leaf<>(9), new Leaf<>(2));
        t = new Node<>(1, t789, t492);
        List<Integer> alp=        new NodeL<>(8,new NodeL<>(9,new NodeL<>(7,new NodeL<>(9,new NodeL<>(2,new NodeL<>(4,new NodeL<>(1,new EmptyL<>())))))));
        assertEquals(alp.toString(), TreeFunctions.postorder(t).toString());
        BinTree<Integer,Integer> t7,t4109,tsecond,t2,t4;
        t7 = new Leaf<>(7);
        t4109 = new Node<>(4,new Leaf<>(10), new Leaf<>(9));
        tsecond = new Node<>(1, t7, t4109);
        t2=new Node<>(111,tsecond,t7);
        t4=new Node<>(12,t2,t7);
        List<Integer> beta=        new NodeL<>(7,new NodeL<>(10,new NodeL<>(9,new NodeL<>(4,new NodeL<>(1,new NodeL<>(7,new NodeL<>(111,new NodeL<>(7,new NodeL<>(12,new EmptyL<>())))))))));
        assertEquals(beta.toString(), TreeFunctions.postorder(t4).toString());
        BinTree<Integer,Integer> t3;
        t3=new Leaf<>(1);
        List<Integer> ceta= new NodeL<>(1,new EmptyL<>());
        assertEquals(ceta.toString(), TreeFunctions.postorder(t3).toString());
    }

    @org.junit.Test
    public void eval() {
         BinTree<BinaryOperator<Integer>,Integer> exp1, exp2, exp3,exp4;
        exp1 = new Node<>((a,b) -> a+b, new Leaf<>(7), new Leaf<>(9));
        assertEquals(16, TreeFunctions.eval(exp1));
        exp2 = new Node<>((a,b) -> a*b, exp1, new Leaf<>(9));
        assertEquals(144, TreeFunctions.eval(exp2));
        exp3 = new Node<>((a,b) -> a-b, exp1, exp2);
        assertEquals(-128, TreeFunctions.eval(exp3));
        exp4 = new Node<>((a,b) -> a%b, exp2, exp3);
        assertEquals(16, TreeFunctions.eval(exp4));
    }
}