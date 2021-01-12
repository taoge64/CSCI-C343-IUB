import java.util.*;

abstract class Graph<E> {
    protected Hashtable<Item<E>, ArrayList<Item<E>>> neighbors;
    protected Hashtable<Pair<Item<E>, Item<E>>, Integer> weights;
    protected NodeCollection<E> ncoll;

    Graph(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors,
          Hashtable<Pair<Item<E>, Item<E>>, Integer> weights,
          NodeCollection<E> ncoll) {
        this.neighbors = neighbors;
        this.weights = weights;
        this.ncoll = ncoll;
    }

    void setSource(ArrayList<Item<E>> nodes, Item<E> s) {
        ncoll.setSource(nodes,s);
    }

    abstract void relax(Item<E> u, Item<E> v);

    void visitPrint(Item<E> u) {
        if (!u.isVisited()) {
            u.setVisited(true);
            System.out.print(u);
            neighbors.get(u).forEach(v -> relax(u, v));
        }
    }

    void traverse() {
        while (!ncoll.isEmpty()) visitPrint(ncoll.extract());
    }

    abstract void findCycles();

    abstract boolean isEmpty();
}

class DFS<E> extends Graph<E> {
    DFS(Hashtable<Item<E>, ArrayList<Item<E>>> neighbors) {
        super(neighbors, null, NodeCollection.make(KIND.STACK));
    }

    boolean isEmpty() {
        return ncoll.isEmpty();
    }

    void relax(Item<E> u, Item<E> v) {
        ncoll.insert(v);
    }

    void findCycles() {
        ArrayList<Pair<Item<E>, Item<E>>> pairMemory = new ArrayList<>();

        while(!ncoll.isEmpty()) {
            Item<E> current = ncoll.extract();
            System.out.print(current.getName() + "--->");

            ArrayList<Item<E>> memory = neighbors.get(current);

            for(int i = 0; i < memory.size(); i++) {
                Item<E> n = memory.get(i);
                ncoll.insert(n);
                Pair<Item<E>, Item<E>> val = new Pair<>(current, n);

                if(pairMemory.contains(val)) {
                    System.out.print("cycle");
                    return;
                } else {
                    pairMemory.add(val);
                }
            }
        }
    }
}
