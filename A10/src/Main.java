import java.util.*;

class NOT_DONE_E extends Error {}

public class Main {

    public static void main(String[] args) {
        int num = 4;

        ArrayList<Item<String>> items = new ArrayList<>();
        Random r = new Random(1);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            items.add(new Item<>("a" + k, "a" + k, k));
        }
        BinaryHeap<String> bhp = new BinaryHeap<>(items);
        System.out.printf("Elems = %s%n", bhp.getElems());
        System.out.printf("From smallest to largest:%n");
        for (int i = 0; i < num; i++) {
            System.out.printf("%s%n", bhp.extractMin());
        }

        bhp = new BinaryHeap<>();
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            bhp.insert(new Item<>("a" + k, "a" + k, k));
        }
        System.out.printf("Elems = %s%n", bhp.getElems());
        System.out.printf("From smallest to largest:%n");
        for (int i = 0; i < num; i++) {
            System.out.printf("%s%n", bhp.extractMin());
        }

        DHeap<String> dhp = new DHeap<>(2);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            dhp.insert(new Item<>("a" + k, "a" + k, k));
        }
        System.out.printf("Elems = %s%n", dhp.getElems());
        System.out.printf("From smallest to largest:%n");
        for (int i = 0; i < num; i++) {
            System.out.printf("%s%n", dhp.extractMin());
        }

        dhp = new DHeap<>(3);
        for (int i = 0; i < num; i++) {
            int k = r.nextInt(100);
            dhp.insert(new Item<>("a" + k, "a" + k, k));
        }
        System.out.printf("Elems = %s%n", dhp.getElems());
        System.out.printf("From smallest to largest:%n");
        for (int i = 0; i < num; i++) {
            System.out.printf("%s%n", dhp.extractMin());
        }
    }
}

/* 
Expected output:

Elems = [(N=a13,V=13,P=1,V?=false), (N=a85,V=85,P=2,V?=false), (N=a47,V=47,P=3,V?=false), (N=a88,V=88,P=4,V?=false)]
From smallest to largest:
(N=a13,V=13,P=1,V?=false)
(N=a47,V=47,P=1,V?=false)
(N=a85,V=85,P=1,V?=false)
(N=a88,V=88,P=1,V?=false)
Elems = [(N=a4,V=4,P=1,V?=false), (N=a6,V=6,P=2,V?=false), (N=a34,V=34,P=3,V?=false), (N=a54,V=54,P=4,V?=false)]
From smallest to largest:
(N=a4,V=4,P=1,V?=false)
(N=a6,V=6,P=1,V?=false)
(N=a34,V=34,P=1,V?=false)
(N=a54,V=54,P=1,V?=false)
Elems = [(N=a48,V=48,P=1,V?=false), (N=a73,V=73,P=2,V?=false), (N=a69,V=69,P=3,V?=false), (N=a78,V=78,P=4,V?=false)]
From smallest to largest:
(N=a48,V=48,P=1,V?=false)
(N=a69,V=69,P=1,V?=false)
(N=a73,V=73,P=1,V?=false)
(N=a78,V=78,P=1,V?=false)
Elems = [(N=a17,V=17,P=1,V?=false), (N=a63,V=63,P=2,V?=false), (N=a62,V=62,P=3,V?=false), (N=a34,V=34,P=4,V?=false)]
From smallest to largest:
(N=a17,V=17,P=1,V?=false)
(N=a34,V=34,P=1,V?=false)
(N=a62,V=62,P=1,V?=false)
(N=a63,V=63,P=1,V?=false)

*/
