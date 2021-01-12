import java.util.*;

public class SpatialGraph {
    private Rect grid;
    private ArrayList<Point> points;

    private ArrayList<Item<Point>> nodes;
    private Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors;
    private Hashtable<Edge, Integer> weights;

    private XTree kdtree; // initialized by preprocess
    private Hashtable<Edge, HashSet<Region>> regionalEdges; // initialized by preprocess

    SpatialGraph(
            Rect grid, ArrayList<Point> points,
            ArrayList<Item<Point>> nodes,
            Hashtable<Item<Point>, ArrayList<Item<Point>>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.grid = grid;
        this.points = points;
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    SpatialGraph(Random r, int numberNodes, int maxNeighbors, int maxWeight, int gridSize) {
        this.grid = new Rect(0, 0, gridSize, gridSize);
        this.points = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.neighbors = new Hashtable<>();
        this.weights = new Hashtable<>();

        for (int i = 0; i < numberNodes; i++) {
            Point p = new Point(r.nextInt(gridSize), r.nextInt(gridSize));
            Item<Point> n = new Item<>(p, p.toString(), Integer.MAX_VALUE);
            while (nodes.contains(n)) {
                p = new Point(r.nextInt(gridSize), r.nextInt(gridSize));
                n = new Item<>(p, p.toString(), Integer.MAX_VALUE);
            }
            points.add(p);
            nodes.add(n);
        }

        for (int i = 0; i < numberNodes; i++) {
            ArrayList<Item<Point>> neighbors = new ArrayList<>();
            int numberNeighbors = r.nextInt(maxNeighbors);

            for (int j = 0; j < numberNeighbors; j++) {
                int neighborIndex = r.nextInt(numberNodes);
                while (i == neighborIndex) {
                    neighborIndex = r.nextInt(numberNodes);
                }
                Item<Point> neighbor = nodes.get(neighborIndex);
                neighbors.add(neighbor);
                weights.put(new Edge(nodes.get(i).getData(), neighbor.getData()), r.nextInt(maxWeight));
            }

            this.neighbors.put(nodes.get(i), neighbors);
        }
    }

    // -----

    Rect getGrid() {
        return grid;
    }

    ArrayList<Point> getPoints() {
        return points;
    }

    ArrayList<Item<Point>> getNodes() {
        return nodes;
    }

    Hashtable<Item<Point>, ArrayList<Item<Point>>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    XTree getKdtree() {
        return kdtree;
    }

    Hashtable<Edge, HashSet<Region>> getRegionalEdges() {
        return regionalEdges;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item<Point> source) {
        nodes.forEach(v->v.reset());
        for (Item<Point>i : nodes){
            i.reset();
            i.setValue(Integer.MAX_VALUE);
        }
        source.setValue(0);
        BinaryHeap<Point> heap= new BinaryHeap<>(nodes);
        while(!heap.isEmpty()){
            Item<Point> u=heap.extractMin();
            if (!u.isVisited()) {
                if(u.getValue() == Integer.MAX_VALUE)
                    break;
                u.setVisited(true);
                neighbors.get(u).forEach(v -> {
                    int value = u.getValue() + weights.get(new Edge(u.getData(), v.getData()));
                    if (value < v.getValue()) {
                        v.setValue(value);
                        heap.updateKey(v.getPosition(), value);
                        v.setPrevious(u);
                    }
                });
            }
        }
    }


    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination

    ArrayList<Edge> shortestPath(Item<Point> source, Item<Point> dest) {
        nodes.forEach(v->v.reset());
        for (Item<Point>i : nodes){

            i.setValue(Integer.MAX_VALUE);
        }
        source.setPosition(0);
        source.setValue(0);

        BinaryHeap<Point> heap= new BinaryHeap<>(nodes);

        while(!heap.isEmpty()) {
            Item<Point> u = heap.extractMin();
            if (u.equals(dest)) {
                break;
            }
            if (!u.isVisited()) {

                    if (u.getValue() == Integer.MAX_VALUE){
                        break;}
                    u.setVisited(true);
                    neighbors.get(u).forEach(v -> {
                        int value = u.getValue() + weights.get(new Edge(u.getData(), v.getData()));

                        if ((value < v.getValue())) {
                            v.setValue(value);
                            v.setPrevious(u);
                            heap.updateKey(v.getPosition(), value);
                        }
                    });
                }
            }

        return  Item.pathToSource(dest);
    }

    // -----

    void buildKDTree(int bound) {
        //Collections.sort(points,Comparator.comparing(Point::getX));
        List<Point> points1Copy = (List<Point>)points.clone();
        List<Point> points2Copy = (List<Point>)points.clone();
        Collections.sort(points2Copy,Comparator.comparing(Point::getY));
        Collections.sort(points1Copy,Comparator.comparing(Point::getX));
        this.kdtree= XTree.makeXTree(points1Copy,points2Copy,bound);
    }


    void preprocess(int bound) {
        buildKDTree(bound);
        regionalEdges= new Hashtable<>();
        weights.keySet().forEach(v->regionalEdges.put(v,new HashSet<>()));
        for (Item<Point> s :nodes) {
            allShortestPaths(s);
            for (Item<Point> d : nodes) {
                ArrayList<Edge> path = Item.pathToSource(d);

                if ((path.size() > 0)&&(d.getData()!=s.getData())) {
                    Edge lastedge = path.get(path.size()-1);
                    lastedge=lastedge.flip();
                    Region currentregion = kdtree.findRegion(d.getData());
                    regionalEdges.get(lastedge).add(currentregion);
                }
            }
        }
    }

    // -----

    ArrayList<Edge> regionalShortestPath(Item<Point> source, Item<Point> dest) {
        nodes.forEach(v->v.reset());
        for (Item<Point>i : nodes){

            i.setValue(Integer.MAX_VALUE);
        }
        source.setPosition(0);
        source.setValue(0);
        HashSet<Region> targetRegion= regionalEdges.get(new Edge(source.getData(),dest.getData()));
        BinaryHeap<Point> heap= new BinaryHeap<>(nodes);
        while(!heap.isEmpty()) {
            Item<Point> u = heap.extractMin();
                if (u.equals(dest)) {
                    break;
                }
                if (!u.isVisited()) {

                    if (u.getValue() == Integer.MAX_VALUE) {
                        break;
                    }

                    u.setVisited(true);

                    neighbors.get(u).forEach(v -> {
                        if (regionalEdges.get(new Edge(u.getData(), v.getData())).contains(kdtree.findRegion(dest.getData()))) {
                            int value = u.getValue() + weights.get(new Edge(u.getData(), v.getData()));

                            if ((value < v.getValue())) {
                                v.setValue(value);
                                v.setPrevious(u);
                                heap.updateKey(v.getPosition(), value);
                            }
                        }
                    });
                }

        }

        return  Item.pathToSource(dest);
    }

    // -----

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Nodes:%n%s%n", nodes));
        res.append(String.format("Neighbors:%n%s%n", neighbors));
        res.append(String.format("Weights:%n%s%n", weights));
        return new String(res);
    }
}
