import org.junit.Test;

public class EdgeTest{
    @Test
    public void testRightEdge(){
        // create the full binary tree shown in the example
        BinTree<Integer> fullTree = new TreeNode<>(1,
                new TreeNode<>(2,
                        new TreeNode<>(4,
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new TreeNode<>(5,
                                new EmptyTree<>(),
                                new EmptyTree<>())),
                new TreeNode<>(3,
                        new TreeNode<>(6,
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new TreeNode<>(7,
                                new EmptyTree<>(),
                                new EmptyTree<>())));

        List<Integer> expectedFullTree = new Node<>(1, new Node<>(3, new Node<>(7, new Empty<>())));

        assert(Edges.findRight(fullTree).equals(expectedFullTree));

        // create the more interesting tree shown in the example
        BinTree<Integer> interestingTree = new TreeNode<>(1,
                new TreeNode<>(2,
                        new TreeNode<>(4,
                                new TreeNode<>(7,
                                        new EmptyTree<>(),
                                        new EmptyTree<>()),
                                new EmptyTree<>()),
                        new TreeNode<>(5,
                                new EmptyTree<>(),
                                new EmptyTree<>())),
                new TreeNode<>(3,
                        new TreeNode<>(6,
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new EmptyTree<>()));

        List<Integer> expectedInterestingTree = new Node<>(1, new Node<>(3, new Node<>(6,
                new Node<>(7, new Empty<>()))));
        assert(Edges.findRight(interestingTree).equals(expectedInterestingTree));

        // remember these don't have to be integers! It should still work
        // create the more interesting tree shown in the example
        BinTree<String> stringTree = new TreeNode<>("a",
                new TreeNode<>("b",
                        new TreeNode<>("c",
                                new TreeNode<>("d",
                                        new EmptyTree<>(),
                                        new EmptyTree<>()),
                                new EmptyTree<>()),
                        new TreeNode<>("e",
                                new EmptyTree<>(),
                                new EmptyTree<>())),
                new TreeNode<>("f",
                        new TreeNode<>("g",
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new EmptyTree<>()));

        List<String> expectedStringTree = new Node<>("a", new Node<>("f", new Node<>("g",
                new Node<>("d", new Empty<>()))));
        assert(Edges.findRight(stringTree).equals(expectedStringTree));

    }

    @Test
    public void testLeftEdge(){
        // create the full binary tree shown in the example
        BinTree<Integer> fullTree = new TreeNode<>(1,
                new TreeNode<>(2,
                        new TreeNode<>(4,
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new TreeNode<>(5,
                                new EmptyTree<>(),
                                new EmptyTree<>())),
                new TreeNode<>(3,
                        new TreeNode<>(6,
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new TreeNode<>(7,
                                new EmptyTree<>(),
                                new EmptyTree<>())));

        List<Integer> expectedFullTree = new Node<>(1, new Node<>(2, new Node<>(4, new Empty<>())));

        assert (Edges.findLeft(fullTree).equals(expectedFullTree));

        // create the more interesting tree shown in the example
        BinTree<Integer> interestingTree = new TreeNode<>(1,
                new TreeNode<>(2,
                        new TreeNode<>(5,
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new EmptyTree<>()),
                new TreeNode<>(3,
                        new TreeNode<>(6,
                                new EmptyTree<>(),
                                new TreeNode<>(7,
                                        new EmptyTree<>(),
                                        new EmptyTree<>())),
                        new EmptyTree<>()));

        List<Integer> expectedInterestingTree = new Node<>(1, new Node<>(2, new Node<>(5,
                new Node<>(7, new Empty<>()))));
        assert(Edges.findLeft(interestingTree).equals(expectedInterestingTree));

        // remember these don't have to be integers! It should still work
        // create the more interesting tree shown in the example
        BinTree<String> stringTree = new TreeNode<>("a",
                new TreeNode<>("b",
                        new TreeNode<>("c",
                                new EmptyTree<>(),
                                new EmptyTree<>()),
                        new EmptyTree<>()),
                new TreeNode<>("d",
                        new TreeNode<>("e",
                                new EmptyTree<>(),
                                new TreeNode<>("f",
                                        new EmptyTree<>(),
                                        new EmptyTree<>())),
                        new EmptyTree<>()));

        List<String> expectedStringTree = new Node<>("a", new Node<>("b", new Node<>("c",
                new Node<>("f", new Empty<>()))));
        assert(Edges.findLeft(stringTree).equals(expectedStringTree));
    }
}
