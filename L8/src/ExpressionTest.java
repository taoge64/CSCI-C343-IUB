import org.junit.Test;

public class ExpressionTest {
    @Test
    public void bookTest() {
        List<Data<String>> exp1 =
                new NodeL<>(new Base<>("a"), new NodeL<>(new Base<>("b"),
                        new NodeL<>(new Op<>("+", String::concat),
                                new NodeL<>(new Base<>("c"), new NodeL<>(new Base<>("d"),
                                        new NodeL<>(new Base<>("e"),
                                                new NodeL<>(new Op<>("+", String::concat),
                                                        new NodeL<>(new Op<>("*", String::concat),
                                                                new NodeL<>(new Op<>("*", String::concat),
                                                                        new EmptyL<>())))))))));
        ExpressionTree<String> expTree1 = new ExpressionTree<>(exp1);
        assert (expTree1.toString().equals("((a + b) * (c * (d + e)))"));
    }

    @Test
    public void evalTest () {
        List<Data<Integer>> exp1 =
                new NodeL<>(new Base<>(1), new NodeL<>(new Base<>(2),
                        new NodeL<>(new Op<>("+", (a,b) -> a+b),
                                new NodeL<>(new Base<>(3), new NodeL<>(new Base<>(4),
                                        new NodeL<>(new Base<>(5),
                                                new NodeL<>(new Op<>("+", (a,b) -> a+b),
                                                        new NodeL<>(new Op<>("*", (a,b) -> a*b),
                                                                new NodeL<>(new Op<>("*", (a,b) -> a*b),
                                                                        new EmptyL<>())))))))));
        ExpressionTree<Integer> expTree1 = new ExpressionTree<>(exp1);

                assert(expTree1.toString().equals("((1 + 2) * (3 * (4 + 5)))"));

        assert(ExpressionTree.evaluate(expTree1.root) == 81);
    }
}
