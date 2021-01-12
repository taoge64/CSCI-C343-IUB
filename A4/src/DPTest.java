import org.junit.Test;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

public class DPTest {

    // Timers

    static <A,B> long time1 (Function<A,B> f, A a) {
        long start = System.nanoTime();
        f.apply(a);
        long end = System.nanoTime();
        return (end-start)/1000000;
    }

    static <A,B,C> long time2 (BiFunction<A,B,C> f, A a, B b) {
        long start = System.nanoTime();
        f.apply(a,b);
        long end = System.nanoTime();
        return (end-start)/1000000;
    }

    // Testing fib correctness and timing

    @Test
    public void fib () {
        assertEquals(DP.fib(15), DP.mfib(15));

        long slow, fast;

        slow = time1(DP::fib, 30);
        fast = time1(DP::mfib, 30);
        assertTrue(slow > fast);

        slow = time1(DP::fib, 40);
        fast = time1(DP::mfib, 40);
        assertTrue(slow > fast);
    }

    @Test(timeout=5)
    public void fibT () {
        DP.fibMemo.clear();
        DP.mfib(100);

        //mytest
        DP.mfib(105);
        DP.mfib(109);
        DP.mfib(111);
    }

    @Test
    public void ffib () {
        DP.fibMemo.clear();
        long n1 = DP.mfib(50);
        long n2 = DP.ffib(50);
        assertEquals(n1, n2);

        //mytest
        DP.fibMemo.clear();
        long n11 = DP.mfib(39);
        long n22 = DP.ffib(39);

        DP.fibMemo.clear();
        long n111 = DP.mfib(55);
        long n222 = DP.ffib(55);
        assertEquals(n11, n22);

        DP.fibMemo.clear();
        long n1111 = DP.mfib(49);
        long n2222 = DP.ffib(49);
        assertEquals(n11, n22);

    }

    @Test(timeout=20)
    public void ffibT () {
        DP.fibMemo.clear();
        DP.mfib(1000);
        DP.ffib(1000);

        //My test

        DP.fibMemo.clear();
        DP.mfib(1100);
        DP.ffib(1100);
        DP.fibMemo.clear();
        DP.mfib(900);
        DP.ffib(900);
        DP.fibMemo.clear();
        DP.mfib(1300);
        DP.ffib(1300);
    }
    // My testing
    @Test
    public void  mytestinfib(){
        DP.fibMemo.clear();

        //accuracy test
        assertEquals(55,DP.fib(10));
        assertEquals(0,DP.fib(0));
        assertEquals(1,DP.fib(1));

        assertEquals(55,DP.mfib(10));
        assertEquals(0,DP.mfib(0));
        assertEquals(1,DP.mfib(1));

        assertEquals(55,DP.ffib(10));
        assertEquals(0,DP.ffib(0));
        assertEquals(1,DP.ffib(1));
        long slow, fast;

        slow = time1(DP::fib, 35);
        fast = time1(DP::mfib, 35);
        assertTrue(slow > fast);

        slow = time1(DP::fib, 45);
        fast = time1(DP::ffib, 45);
        assertTrue(slow > fast);

        slow = time1(DP::mfib, 37);
        fast = time1(DP::ffib, 37);
        assertTrue(slow == fast);

        //therefore, mfib has almost the same speed as ffib, when it comes large
    }

    // Testing coinChange correctness and timing

    @Test
    public void coinsC () {
        Coin quarter = new Coin(25);
        Coin dime = new Coin(10);
        Coin nickel = new Coin(5);
        Coin penny = new Coin(1);
        List<Coin> coins =
                new Node<>(quarter,
                new Node<>(dime, new Node<>(nickel, new Node<>(penny, new Empty<>()))));
        assertEquals(1, DP.coinChange(coins, 4));
        assertEquals(2, DP.coinChange(coins, 6));
        assertEquals(242, DP.coinChange(coins,100));
    }

    @Test(timeout=500)
    public void coinsT1 () {
        List<Coin> coins =
                new Node<>(new Coin(3),
                new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.coinChangeMemo.clear();
        DP.mcoinChange(coins,1000);


    }
    // My test
    @Test(timeout=500)
    public void coinsT2 () {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.coinChangeMemo.clear();
        DP.mcoinChange(coins,1200);
    }

    @Test(timeout=500)
    public void coinsT3 () {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.coinChangeMemo.clear();
        DP.mcoinChange(coins,1300);
    }
    @Test(timeout=500)
    public void coinsT4 () {
        List<Coin> coins =
                new Node<>(new Coin(3),
                        new Node<>(new Coin(2), new Node<>(new Coin(1), new Empty<>())));
        DP.coinChangeMemo.clear();
        DP.mcoinChange(coins,1400);
    }
    @Test
    public void  mytestincoin(){
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

        assertEquals(2, DP.mcoinChange(coins, 9));
        assertEquals(4, DP.mcoinChange(coins, 13));
        assertEquals(193637, DP.mcoinChange(coins, 1111));
        // speed
        long slow, fast;

        slow = time2(DP::coinChange, coins,33);
        fast = time2(DP::mcoinChange, coins,33);
        assertTrue(slow > fast);

        long slow1, fast1;
        DP.coinChangeMemo.clear();
        slow1 = time2(DP::coinChange, coins,39);
        fast1 = time2(DP::mcoinChange, coins,39);
        assertTrue(slow1 > fast1);

        long slow2, fast2;
        DP.coinChangeMemo.clear();
        slow2 = time2(DP::coinChange, coins,100);
        fast2 = time2(DP::mcoinChange, coins,100);
        assertTrue(slow2 > fast2);
    }
    // Testing partition correctness and timing

    @Test
    public void partitionCorrectness () {
        List<Integer> ns = new Node<>(5, new Node<>(3,
                        new Node<>(7, new Node<>(1, new Empty<>()))));
        assertFalse(DP.partition(ns, 2));
        assertTrue(DP.partition(ns, 8));
        assertTrue(DP.partition(ns, 6));

    }
    @Test
    public void partitionTiming () {
        Random r = new Random(1);
        List<Integer> s = List.MakeIntList(r, 100, 1000);
        DP.partitionMemo.clear();
        long t = time2(DP::mpartition,s,50000);
        assertTrue (t < 500);
    }

    @Test(timeout = 1000)
    public void mytestinpartion() {
        //correctness
        List<Integer> ss = new Node<>(10, new Node<>(2,
                new Node<>(-5, new Node<>(1,new Node<>(6, new Empty<>())))));
        assertTrue(DP.partition(ss, 5));
        assertTrue(DP.partition(ss, 11));
        assertTrue(DP.partition(ss, 1));

        assertTrue(DP.mpartition(ss, 5));
        assertTrue(DP.mpartition(ss, 11));
        assertTrue(DP.mpartition(ss, 1));

        List<Integer> nss = new Node<>(1, new Node<>(-4,
                new Node<>(6, new Node<>(-7, new Empty<>()))));
        assertTrue(DP.partition(nss, -3));
        assertFalse(DP.partition(nss,19));
        assertTrue(DP.partition(nss,0));

        assertTrue(DP.mpartition(nss, -3));
        assertFalse(DP.mpartition(nss,19));
        assertTrue(DP.mpartition(nss,0));

        List<Integer> n = new Empty<>();
        assertTrue(DP.partition(n,0));

        assertTrue(DP.mpartition(n, 0));
        // speed
        Random r = new Random(1);
        List<Integer> s = List.MakeIntList(r, 100, 1000);
        DP.partitionMemo.clear();
        long t1 = time2(DP::mpartition,s,49999);
        assertTrue (t1 < 500);

        long t2 = time2(DP::mpartition,s,50001);
        assertTrue(t2<500);

        long t3 = time2(DP::mpartition,s,0);
        assertTrue(t3<500);



    }
    @Test
    public void speedcomparepartition(){
        long slow2, fast2;
        Random r = new Random(1);
        List<Integer> s = List.MakeIntList(r, 100, 1000);
        DP.partitionMemo.clear();
        slow2 = time2(DP::partition, s,49999);
        fast2 = time2(DP::mpartition, s,49999);
        assertTrue(slow2 > fast2);

    }

        // minDistance

    @Test
    public void minDistance () {
        List<DP.BASE> dna1 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.C, new Empty<>()));
        List<DP.BASE> dna2 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Empty<>()));
        assertEquals(1, DP.minDistance(dna1,dna2));

    }

    @Test
    public void minDistance2 () {
        Random r = new Random(1);
        Function<Void,DP.BASE> g = v -> DP.BASE.class.getEnumConstants()[r.nextInt(4)];
        List<DP.BASE> dna1 = List.MakeList(g, 521);
        List<DP.BASE> dna2 = List.MakeList(g, 450);
        assertEquals(337, DP.mminDistance(dna1,dna2));
    }
    @Test
    public void mytestinmindistance(){
        //correctness
        List<DP.BASE> dna11 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.C, new Node<>(DP.BASE.G,new Empty<>())));
        List<DP.BASE> dna22 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Empty<>()));
        assertEquals(2,DP.minDistance(dna11,dna22));
        assertEquals(2,DP.mminDistance(dna11,dna22));

        List<DP.BASE> dna111 =
                new Empty<>();
        List<DP.BASE> dna222 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Empty<>()));
        assertEquals(4,DP.minDistance(dna111,dna222));
        assertEquals(4,DP.mminDistance(dna111,dna222));

        List<DP.BASE> dna1111 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.C, new Node<>(DP.BASE.A,new Empty<>())));
        List<DP.BASE> dna2222 =
                new Node<>(DP.BASE.A, new Node<>(DP.BASE.G, new Node<>(DP.BASE.C,new Node<>(DP.BASE.T, new Empty<>()))));
        assertEquals(3,DP.minDistance(dna1111,dna2222));
        assertEquals(3,DP.mminDistance(dna1111,dna2222));
        //speed
        Random r = new Random(1);
        Function<Void,DP.BASE> g = v -> DP.BASE.class.getEnumConstants()[r.nextInt(4)];
        List<DP.BASE> dna1 = List.MakeList(g, 551);
        List<DP.BASE> dna2 = List.MakeList(g, 470);
        assertEquals(373, DP.mminDistance(dna1,dna2));


        List<DP.BASE> dna311 = List.MakeList(g, 529);
        List<DP.BASE> dna322 = List.MakeList(g, 452);
        assertEquals(353, DP.mminDistance(dna311,dna322));

        List<DP.BASE> dna3111 = List.MakeList(g, 511);
        List<DP.BASE> dna3222 = List.MakeList(g, 460);
        assertEquals(337, DP.mminDistance(dna3111,dna3222));

    }
    @Test
    public void speedcomparemindistence (){
        long slow2, fast2;
        DP.minDistanceMemo.clear();
        Random r = new Random(1);
        Function<Void,DP.BASE> g = v -> DP.BASE.class.getEnumConstants()[r.nextInt(4)];
        List<DP.BASE> dna4111 = List.MakeList(g, 10);
        List<DP.BASE> dna4222 = List.MakeList(g, 12);
        slow2 = time2(DP::minDistance, dna4111,dna4222);
        fast2 = time2(DP::mminDistance, dna4111,dna4222);
        assertTrue(slow2 > fast2);


    }

    // longest common subsequence

    @Test
    public void lcs () {
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
        assertEquals(c, DP.lcs(cs1,cs2));
    }



    @Test
    public void lcs2 () {
        Random r = new Random(1);
        Function<Void,Character> g = v -> Character.forDigit(r.nextInt(256),10);
        List<Character> cs1 = List.MakeList(g, 310);
        List<Character> cs2 = List.MakeList(g, 250);
        assertEquals(240, DP.mlcs(cs1,cs2).length());
    }

    @Test
    public void mytestinlcs(){
        List<Character> cs11 =new Node<>('A', new Node<>('B',new Empty<>()));
        List<Character> cs22 = new Node<>('B',new Empty<>());
        assertEquals(cs22,DP.lcs(cs11,cs22));
        assertEquals(cs22,DP.mlcs(cs11,cs22));

        List<Character> cs111 =new Node<>('A', new Node<>('B',new Empty<>()));
        List<Character> cs222 =new Empty<>();
        assertEquals(new Empty<>(),DP.lcs(cs111,cs222));
        assertEquals(new Empty<>(),DP.mlcs(cs111,cs222));

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
        assertEquals(c,DP.mlcs(cs1111,cs2222));

        Random r = new Random(1);
        Function<Void,Character> g = v -> Character.forDigit(r.nextInt(256),10);
        List<Character> cs1 = List.MakeList(g, 330);
        List<Character> cs2 = List.MakeList(g, 230);
        assertEquals(222, DP.mlcs(cs1,cs2).length());

        List<Character> cs01 = List.MakeList(g, 70);
        List<Character> cs02 = List.MakeList(g, 90);
        assertEquals(70, DP.mlcs(cs01,cs02).length());

        List<Character> cs001 = List.MakeList(g, 170);
        List<Character> cs002 = List.MakeList(g, 190);
        assertEquals(167, DP.mlcs(cs001,cs002).length());
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
        fast2 = time2(DP::mlcs,cs1,cs2);
        assertTrue(slow2 > fast2);


    }
}
