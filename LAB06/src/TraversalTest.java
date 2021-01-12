import org.junit.Test;

public class TraversalTest{
    @Test
    public void testTraversal(){
        // create the tree shown in the examples above
        TreeNode<Integer> intTree = new TreeNode<>(1,
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

        List<Integer> expectedInOrder = new Node<>(4, new Node<>(2, new Node<>(5,
                new Node<>(1, new Node<>(6, new Node<>(3, new Node<>(7, new Empty<>())))))));
        List<Integer> expectedPreOrder = new Node<>(1, new Node<>(2, new Node<>(4,
                new Node<>(5, new Node<>(3, new Node<>(6, new Node<>(7, new Empty<>())))))));
        List<Integer> expectedPostOrder = new Node<>(4, new Node<>(5, new Node<>(2,
                new Node<>(6, new Node<>(7, new Node<>(3, new Node<>(1, new Empty<>())))))));

        System.out.println(Traversal.inOrder(intTree));
        System.out.println(Traversal.postOrder(intTree));
        System.out.println(Traversal.preOrder(intTree));
        System.out.println(expectedInOrder);
        assert(Traversal.inOrder(intTree).equals(expectedInOrder));
        assert(Traversal.preOrder(intTree).equals(expectedPreOrder));
        assert(Traversal.postOrder(intTree).equals(expectedPostOrder));
    }
}
