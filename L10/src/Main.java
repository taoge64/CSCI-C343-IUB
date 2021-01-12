import java.util.ArrayList;

/**
 * TODO
 * You will be implementing nearestNeighbor using Binary Heaps, described by
 * your UIs in lab. You will submit to autograder for testing, as you may
 * not have implemented your own BinaryHeap yet. 
 */
public class Main {
    static Point NNHeap(KDTree t, Rect region, Point p) throws EmptyTreeE {
     BinaryHeap<KDTree> alpha = new BinaryHeap<>();
     Point result= new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
     int smallest_distance_so_far=Integer.MAX_VALUE;
     alpha.insert(new Item<>(t,"",0));
     while ((!alpha.isEmpty())&&(smallest_distance_so_far>alpha.findMin().getValue())) {
        Item<KDTree> min = alpha.extractMin();
        if(min.getData().getPoint().distanceSquaredTo(p)<smallest_distance_so_far){
         result=min.getData().getPoint();
         smallest_distance_so_far=min.getData().getPoint().distanceSquaredTo(p);
        }
         if (!min.getData().getChild1().isEmpty()){
            alpha.insert(new Item(min.getData().getChild1(),"",t.getRegion1(region).distanceSquaredTo(p)));}
         if(!min.getData().getChild2().isEmpty()){
            alpha.insert(new Item(min.getData().getChild2(),"",t.getRegion2(region).distanceSquaredTo(p)));}
        }


     return result;
    }
}
