import java.util.Comparator;
import java.util.Random;

class EmptyTreeE extends Exception {}

/**
 * This hierarcy implements m-ary trees.
 * An m-ary tree is either empty, or
 * it is a node with a list of m-subtrees
 * 
 * The parameter m is often called the branchingFactor
 * 
 * It is convenient to recognize trees with no children which are 
 * called 'leaves'. Technically an m-leaf is an m-ary tree with 
 * one node and a list of length m of empty subtrees.
 * 
 * In the three methods makeTree* below, you may assume the incoming
 * parameter is tuned to create a perfect tree in which every level
 * has the same number of nodes. So for example for a branching 
 * factor of 3, you can assume the argument n to makeTreeNodes will be 
 * one of:
 * 0, 1, 1+3, 1+3+9, 1+3+9+27, 1+3+9+27+81, ...
 * and nothing else. This simplifies your implementation considerably.
 * We will never test your code with other numbers.
 * 
 * The Random number generator and bound are used to create the data
 * at each node: use r.nextInt(bound). We will not assume you are 
 * creating the nodes in any particular order.
 */
abstract class Tree<E> {
    static int m;

    Tree(int m){
        this.m=m;
    }
    abstract E getData() throws EmptyTreeE;
    abstract List<Tree<E>> getChildren() throws EmptyTreeE;
    
    abstract boolean isEmpty();
    abstract boolean isLeaf();

    int getBranchingFactor(){return m;};
    abstract int getNumberOfNodes(); 
    abstract int numberOfInternalNodes();
    abstract int numberOfLeaves();
    abstract int getHeight();
    static <E> Tree<E> makeLeaf(E elem, int m) {
        List<Tree<E>> result = new EmptyL<>();
        for(int i=0;i<m;i++){
            result.append(new EmptyL<>());
        }
        return new NodeT<>(elem,m,result);
    }

    /*
     * Assume that 'n' is perfect
     */
    static Tree<Integer> makeTreeNodes(int n, int m, Random r, int bound) {
        if (n==0){return new EmptyT<>(m);
        }
        else {

            return new NodeT<>(r.nextInt(bound), m, List.MakeList(list->
                    (makeTreeNodes((n - 1)/m, m, r, bound)),m));
        }
    }

    static Tree<Integer> makeTreeInternals(int i, int m, Random r, int bound) {
//        if (i==0){return new EmptyT<>(m);
//        }
//        else {

//            return new NodeT<>(r.nextInt(bound), m, List.MakeList(list->
                    return (makeTreeNodes(m*i+1, m, r, bound));
//        }
    }

    static Tree<Integer> makeTreeLeaves(int ell, int m, Random r, int bound) {
        return (makeTreeNodes((m*ell-1)/(m-1), m, r, bound));
    }

}

class EmptyT<E> extends Tree<E> {
    E getData() throws EmptyTreeE {
        throw new EmptyTreeE();
    }
    int m;
    EmptyT(int m){
        super(m);
        this.m=m;

    }
    List<Tree<E>> getChildren() {
        return new EmptyL<>();
    }
    boolean isEmpty() {
        return true;
    }
    boolean isLeaf(){return false;}
    // int getBranchingFactor(){return m;}
     int getNumberOfNodes(){return 0;}
     int numberOfInternalNodes(){return 0;}
     int numberOfLeaves(){return 0;}
     int getHeight(){return 0;}
}

class NodeT<E> extends Tree<E> {
    private E data;
    private int m;
    private List<Tree<E>> rest;

    NodeT(E data, int m, List<Tree<E>> rest) {
        super(m);
        this.m = m;
        this.data = data;
        this.rest = rest;
    }

    E getData() {
        return data;
    }

    boolean isEmpty() {
        return false;
    }

    boolean isLeaf() {
        if(getChildren().isEmpty()){
         return true;
        }
        else{
            return false;
        }
    }

    List<Tree<E>> getChildren() {
        return this.rest;
    }

    //int getBranchingFactor(){ }
    int getNumberOfNodes() {
        int node = 1;
        try {
            List<Tree<E>> temp = this.getChildren();
            for (int i = 0; i < getChildren().length(); i++) {
                node += temp.get(i).getNumberOfNodes();
            }
            return node;
        } catch (EmptyListE e) {
            return node;
        }
    }

    int numberOfInternalNodes() {
        return (getNumberOfNodes() - 1) / m;
    }

    int numberOfLeaves() {
        return ((m - 1) * getNumberOfNodes() + 1) / m;
    }

    int getHeight() {
        int n = getNumberOfNodes();
        int count = 1;
        int result = 0;
        while (n > 0) {
            n -= count;
            result = result +1;
            count=count*3;

        }
        return result;
    }
}


