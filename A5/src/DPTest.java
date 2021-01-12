import org.junit.Test;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DPTest {
//My personal test is in the back of the page
    // Timers

    static <A, B> long time1(Function<A, B> f, A a) {
        long start = System.nanoTime();
        f.apply(a);
        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    static <A, B, C> long time2(BiFunction<A, B, C> f, A a, B b) {
        long start = System.nanoTime();
        f.apply(a, b);
        long end = System.nanoTime();
        return (end - start) / 1000000;
    }

    // Testing coinChange correctness and timing

    @Test
    public void coinsC() throws EmptyListE {
        Coin quarter = new Coin(25);
        Coin dime = new Coin(10);
        Coin nickel = new Coin(5);
        Coin penny = new Coin(1);
        List<Coin> coins =
                new Node<>(quarter,
                        new Node<>(dime, new Node<>(nickel, new Node<>(penny, new Empty<>()))));
        assertEquals(1, DP.coinChange(coins, 3));
        assertEquals(2, DP.coinChange(coins, 6));
        assertEquals(242, DP.coinChange(coins, 100));
        assertEquals(1, DP.bucoinChange(coins, 3));
        assertEquals(2, DP.bucoinChange(coins, 6));
        assertEquals(242, DP.bucoinChange(coins, 100));
    }

    @Test(timeout = 500)
    public void coinsT1() {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.coinChangeMemo.clear();
        DP.mcoinChange(coins, 1000);
    }

    // Testing partition correctness and timing

    @Test
    public void partitionCorrectness() {
        List<Integer> ns = new Node<>(5, new Node<>(3,
                new Node<>(7, new Node<>(1, new Empty<>()))));
        //assertFalse(DP.partition(ns, 2));
        //assertTrue(DP.partition(ns, 8));
        //assertTrue(DP.partition(ns, 6));
        //assertFalse(DP.bupartition(ns, 2));
        //System.out.println(DP.bupartition(ns,5));
        assertTrue(DP.bupartition(ns, 5));
        assertTrue(DP.bupartition(ns, 6));
        assertTrue(DP.bupartition(ns, 0));
        assertTrue(DP.bupartition(new Node(10, new Node(10, new Empty())), 0));
    }

    @Test
    public void partitionTiming() {
        Random r = new Random(1);
        List<Integer> s = List.MakeIntList(r, 100, 1000);
        DP.partitionMemo.clear();
        //long t = time2(DP::mpartition, s, 50000);
        long t = time2(DP::bupartition, s, 50000);
        //System.out.println(t);
        assertTrue(t < 799);
    }

    // minDistance

    @Test
    public void minDistance() {
        List<DP.BASE> dna1 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.C, new Empty<>()));
        List<DP.BASE> dna2 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Empty<>()));
        assertEquals(1, DP.minDistance(dna1, dna2));
        assertEquals(1, DP.buminDistance(dna1, dna2));
        dna1 = new Node<>(DP.BASE.A, new Empty<>());
    }

    @Test
    public void minDistance2() {
        Random r = new Random(1);
        Function<Void, DP.BASE> g = v -> DP.BASE.class.getEnumConstants()[r.nextInt(4)];
        List<DP.BASE> dna1 = List.MakeList(g, 521);
        List<DP.BASE> dna2 = List.MakeList(g, 450);
        assertEquals(337, DP.mminDistance(dna1, dna2));
    }

    // longest common subsequence

    @Test
    public void lcs() {
        List<Character> cs1 =
                new Node<>('A', new Node<>('B', new Node<>('C',
                        new Node<>('B', new Node<>('D', new Node<>('A',
                                new Node<>('B', new Empty<>())))))));
        List<Character> cs2 =
                new Node<>('B', new Node<>('D', new Node<>('C',
                        new Node<>('A', new Node<>('B',
                                new Node<>('A', new Empty<>()))))));
        List<Character> c = new Node<>('B', new Node<>('D',
                new Node<>('A', new Node<>('B', new Empty<>()))));
        assertEquals(c, DP.bulcs(cs1, cs2));
    }

    @Test
    public void lcs2() {
        Random r = new Random(1);
        Function<Void, Character> g = v -> Character.forDigit(r.nextInt(256), 10);
        List<Character> cs1 = List.MakeList(g, 310);
        List<Character> cs2 = List.MakeList(g, 250);
        assertEquals(240, DP.mlcs(cs1, cs2).length());
        assertEquals(240, DP.bulcs(cs1, cs2).length());
    }



    //MY personal test
    @Test(timeout = 500)
    public void coinsT2()throws EmptyListE {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.bucoinChange(coins, 1200);
    }

    @Test(timeout = 500)
    public void coinsT3()throws EmptyListE   {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.bucoinChange(coins, 1300);
    }

    @Test(timeout = 500)
    public void coinsT4()throws EmptyListE {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.bucoinChange(coins, 1400);
    }

    @Test
    public void mytestincoin() throws EmptyListE{
        //correctness
        Coin quarter = new Coin(25);
        Coin dime = new Coin(10);
        Coin nickel = new Coin(5);
        Coin penny = new Coin(1);
        List<Coin> coins =
                new Node<>(quarter,
                        new Node<>(dime, new Node<>(nickel, new Node<>(penny, new Empty<>()))));

        assertEquals(2, DP.coinChange(coins, 9));
        assertEquals(4, DP.coinChange(coins, 13));
        assertEquals(307, DP.coinChange(coins, 111));

        assertEquals(2, DP.bucoinChange(coins, 9));
        assertEquals(4, DP.bucoinChange(coins, 13));
        assertEquals(193637, DP.bucoinChange(coins, 1111));
    }

    @Test(timeout = 1000)
    public void mytestinpartion() {
        //correctness
        List<Integer> ss = new Node<>(10, new Node<>(2,
                new Node<>(5, new Node<>(1,new Node<>(6, new Empty<>())))));
        assertTrue(DP.partition(ss, 5));
        assertTrue(DP.partition(ss, 11));
        assertTrue(DP.partition(ss, 1));

        assertTrue(DP.bupartition(ss, 5));
        assertTrue(DP.bupartition(ss, 11));
        assertTrue(DP.bupartition(ss, 1));

        List<Integer> nss = new Node<>(1, new Node<>(-4,
                new Node<>(6, new Node<>(7, new Empty<>()))));
        assertTrue(DP.partition(nss, 13));
        assertFalse(DP.partition(nss,19));
        assertTrue(DP.partition(nss,0));

        //assertTrue(DP.bupartition(nss, -3));
        assertFalse(DP.bupartition(nss,19));
        assertTrue(DP.bupartition(nss,0));

        List<Integer> n = new Empty<>();
        assertTrue(DP.partition(n,0));

        assertTrue(DP.bupartition(n, 0));
        // speed
        Random r = new Random(1);
        List<Integer> s = List.MakeIntList(r, 100, 1000);
        DP.partitionMemo.clear();
        long t1 = time2(DP::bupartition,s,49999);
        assertTrue (t1 < 500);

        long t2 = time2(DP::bupartition,s,50001);
        assertTrue(t2<500);

        long t3 = time2(DP::bupartition,s,0);
        assertTrue(t3<500);



    }
    @Test
    public void speedcomparepartition(){
        long slow2, fast2;
        Random r = new Random(1);
        List<Integer> s = List.MakeIntList(r, 100, 1000);
        DP.partitionMemo.clear();
        slow2 = time2(DP::partition, s,49999);
        fast2 = time2(DP::bupartition, s,49999);
        assertTrue(slow2 > fast2);

    }
    @Test
    public void mytestinmindistance(){
        //correctness
        List<DP.BASE> dna11 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.C, new Node<>(DP.BASE.G,new Empty<>())));
        List<DP.BASE> dna22 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Empty<>()));
        assertEquals(2,DP.minDistance(dna11,dna22));
        assertEquals(2,DP.buminDistance(dna11,dna22));

        List<DP.BASE> dna111 =
                new Empty<>();
        List<DP.BASE> dna222 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Empty<>()));
        assertEquals(4,DP.minDistance(dna111,dna222));
        assertEquals(4,DP.buminDistance(dna111,dna222));

        List<DP.BASE> dna1111 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.C, new Node<>(DP.BASE.A,new Empty<>())));
        List<DP.BASE> dna2222 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Node<>(DP.BASE.C,new Node<>(DP.BASE.T, new Empty<>()))));
        assertEquals(3,DP.minDistance(dna1111,dna2222));
        assertEquals(3,DP.buminDistance(dna1111,dna2222));
        //speed
        Random r = new Random(1);
        Function<Void,DP.BASE> g = v -> DP.BASE.class.getEnumConstants()[r.nextInt(4)];
        List<DP.BASE> dna1 = List.MakeList(g, 551);
        List<DP.BASE> dna2 = List.MakeList(g, 470);
        assertEquals(373, DP.buminDistance(dna1,dna2));


        List<DP.BASE> dna311 = List.MakeList(g, 529);
        List<DP.BASE> dna322 = List.MakeList(g, 452);
        assertEquals(353, DP.buminDistance(dna311,dna322));

        List<DP.BASE> dna3111 = List.MakeList(g, 511);
        List<DP.BASE> dna3222 = List.MakeList(g, 460);
        assertEquals(337, DP.buminDistance(dna3111,dna3222));

    }
    @Test
    public void speedcomparemindistence (){
        long slow2, fast2;
        DP.minDistanceMemo.clear();
        Random r = new Random(1);
        Function<Void,DP.BASE> g = v -> DP.BASE.class.getEnumConstants()[r.nextInt(4)];
        List<DP.BASE> dna4111 = List.MakeList(g, 5);
        List<DP.BASE> dna4222 = List.MakeList(g, 7);
        slow2 = time2(DP::minDistance, dna4111,dna4222);
        fast2 = time2(DP::buminDistance, dna4111,dna4222);
        assertTrue(slow2 > fast2);


    }
    @Test
    public void mytestinlcs(){
        List<Character> cs11 =new Node<>('A', new Node<>('B',new Empty<>()));
        List<Character> cs22 = new Node<>('B',new Empty<>());
        assertEquals(cs22,DP.lcs(cs11,cs22));
        assertEquals(cs22,DP.bulcs(cs11,cs22));

        List<Character> cs111 =new Node<>('A', new Node<>('B',new Empty<>()));
        List<Character> cs222 =new Empty<>();
        assertEquals(new Empty<>(),DP.lcs(cs111,cs222));
        assertEquals(new Empty<>(),DP.bulcs(cs111,cs222));

        List<Character> cs1111 =
                new Node<>('A', new Node<>('B', new Node<>('C',
                        new Node<>('B', new Node<>('D', new Node<>('A',
                                new Node<>('B',new Node<>('A', new Node<>('E',new Node<>('F',new Empty<>()))))))))));
        List<Character> cs2222 =
                new Node<>('B', new Node<>('D', new Node<>('C',
                        new Node<>('A', new Node<>('B',
                                new Node<>('A',new Node<>('E',new Empty<>())))))));
        List<Character> c = new Node<>('B', new Node<>('D',
                new Node<>('A', new Node<>('B', new Node<>('A',new Node<>('E',new Empty<>()))))));
        assertEquals(c,DP.lcs(cs1111,cs2222));
        assertEquals(c,DP.bulcs(cs1111,cs2222));

        Random r = new Random(1);
        Function<Void,Character> g = v -> Character.forDigit(r.nextInt(256),10);
        List<Character> cs1 = List.MakeList(g, 330);
        List<Character> cs2 = List.MakeList(g, 230);
        assertEquals(222, DP.bulcs(cs1,cs2).length());

        List<Character> cs01 = List.MakeList(g, 70);
        List<Character> cs02 = List.MakeList(g, 90);
        assertEquals(70, DP.bulcs(cs01,cs02).length());

        List<Character> cs001 = List.MakeList(g, 170);
        List<Character> cs002 = List.MakeList(g, 190);
        assertEquals(167, DP.bulcs(cs001,cs002).length());
    }
    @Test
    public void speedcomparelcs (){
        long slow2, fast2;
        DP.lcsMemo.clear();
        Random r = new Random(1);
        Function<Void,Character> g = v -> Character.forDigit(r.nextInt(256),10);
        List<Character> cs1 = List.MakeList(g, 30);
        List<Character> cs2 = List.MakeList(g, 40);
        slow2 = time2(DP::lcs,cs1,cs2);
        fast2 = time2(DP::bulcs,cs1,cs2);
        assertTrue(slow2 > fast2);


    }
}