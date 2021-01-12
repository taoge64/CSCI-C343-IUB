import com.sun.source.tree.Tree;

import java.util.Random;

public class Main {

    public static void main (String[] args) throws EmptyTreeE {
        //random creating trees and use insertB as well as deleteB to check its looks
        System.out.println("insertB");
            Random r;
            //Test the extreme situation, insert number which all bigger than the original root
            BinTree t1 = BinTree.makeLeaf(1);
            for (int i = 0; i < 10; i++) {
                r = new Random();
                t1 = t1.insertB(r.nextInt(25));
            }
            //Test the extreme situation, insert number which all smaller than the original root
            BinTree t2 = BinTree.makeLeaf(100);
            for (int i = 0; i < 10; i++) {
                r = new Random();
                t2 = t2.insertB(r.nextInt(25));
            }
            //Test the random case, random insert 10 numbers;
            BinTree t3 = new Empty();
            for (int i = 0; i < 10; i++) {
                r = new Random();
                t3 = t3.insertB(r.nextInt(1000));
            }
        TreePrinter.print(t1);
        TreePrinter.print(t2);
        TreePrinter.print(t3);
        System.out.println("deleteB");
        BinTree t11 = BinTree.makeLeaf(1);
        for (int i = 1; i <= 5; i++) {
            t11 = t11.insertB(i);
        }
        System.out.println("original tree");
        TreePrinter.print(t11);
        t11 = t11.deleteB(t11.getRightT().getData());
        System.out.println("tree after delete right node");
        TreePrinter.print(t11);
         BinTree t22 = BinTree.makeLeaf(100);
        for (int i = 1; i <= 10; i++) {
            t22 = t22.insertB(i);
        }
        System.out.println("original tree");
        TreePrinter.print(t22);
            r = new Random();
            t22 = t22.deleteB(t22.getLeftT().getData());
        System.out.println("tree after  delete left node");
        TreePrinter.print(t22);
        //Test the  case, delete the root number each time;
        BinTree t33 = new Empty();
        for (int i = 0; i < 10; i++) {
            r = new Random();
            t33 = t33.insertB(r.nextInt(1000));
        }
        System.out.println("original tree");
        TreePrinter.print(t33);
        t33 = t33.deleteB(t33.getData());
        System.out.println("tree after delete root");
        TreePrinter.print(t33);
    }
}
