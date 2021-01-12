import java.util.LinkedList;
import java.util.Queue;

/**
 * Today's lab is meant to be another application of trees that
 * could potentially be a technical interview question. (I have
 * seen a similar problem to this in a technical interview
 * before)
 *
 * Your UIs will explain the problem to you and they will give
 * you some examples. You should try to think about various
 * examples that would be helpful for you to draw out.
 */

class Edges {
    /**
     *
     * Find the rightmost edge of a binary tree,
     * which is defined as the rightmost node on each level of the
     * tree.
     *
     * For example, in this tree:
     *             1
     *            / \
     *           2   3
     *          / \ / \
     *         4  5 6  7
     *  We return {1, 3, 7}. Easy peezy.
     *
     *  Not quite.
     *
     *  This is a situation where it is helpful to draw out a more
     *  complicated example and ask your interviewer lots of
     *  questions. We never said it was a FULL binary tree.
     *
     *  In the following tree:
     *             1
     *            / \
     *           2   3
     *          / \ /
     *         4  5 6
     *        /
     *       7
     *  It should return {1, 3, 6, 7}, which are the rightmost
     *  nodes in each level. If we only looked at the rightmost edge
     *  of the tree, we would get {1, 3}, which is wrong.
     *
     */

    static <E> List<E> findRight(BinTree<E> tree) {
        try {
            List<E> list = new Empty<>();
            LinkedList<NodeHeight> queue = new LinkedList<>();
            int prevHeight = 0;

            BinTree<E> prevNode = tree;
            queue.add(new NodeHeight<>(tree, prevHeight));

            while (!queue.isEmpty()) {
                // Remove the first item of the queue.
                NodeHeight temp = queue.removeFirst();
                BinTree<E> tempTree = temp.tree;

                // Check if its height is different from the previously removed Node. If it is, we're on a new row,
                // so add the previous Node to the list as it is the last item of its row.
                if (prevHeight != temp.height) {
                    list = list.append(new Node<>(prevNode.getData(), new Empty<>()));
                    // Update the prevHeight.
                    prevHeight = temp.height;
                }

                // Add the node's children. Its children's height is always just 1 + its height.
                if (!tempTree.getLeft().isEmpty()) {
                    queue.add(new NodeHeight<>(tempTree.getLeft(), temp.height + 1));
                }

                if (!tempTree.getRight().isEmpty()) {
                    queue.add(new NodeHeight<>(tempTree.getRight(), temp.height + 1));
                }

                // Update the previous node.
                prevNode = tempTree;
            }

            // The last child is always added since it's the last child of its row.
            list = list.append(new Node<>(prevNode.getData(), new Empty<>()));

            return list;
        } catch (EmptyTreeE e) {
            return new Empty<>();
        }
    }

    static class NodeHeight<E> {
        BinTree<E> tree;
        int height;

        NodeHeight(BinTree<E> tree, int height) {
            this.tree = tree;
            this.height = height;
        }
    }

    /**
     *
     * Find the leftmost edge of a binary tree,
     * which is defined as the leftmost node on each level of the
     * tree.
     *
     * For example, in this tree:
     *             1
     *            / \
     *           2   3
     *          / \ / \
     *         4  5 6  7
     *  We return {1, 2, 4}.
     *
     *  In the following tree:
     *             1
     *            / \
     *           2   3
     *            \ /
     *            5 6
     *               \
     *                7
     *
     *  It should return {1, 2, 5, 7}.
     *
     */
    static <E> List<E> findLeft(BinTree<E> tree) {
        try {
            List<E> list = new Empty<>();
            LinkedList<NodeHeight> queue = new LinkedList<>();
            int prevHeight = 0;

            BinTree<E> prevNode = tree;
            queue.add(new NodeHeight<>(tree, prevHeight));

            while (!queue.isEmpty()) {
                // Remove the first item of the queue.
                NodeHeight temp = queue.removeFirst();
                BinTree<E> tempTree = temp.tree;

                // Check if its height is different from the previously removed Node. If it is, we're on a new row,
                // so add the previous Node to the list as it is the last item of its row.
                if (prevHeight != temp.height) {
                    list = list.append(new Node<>(prevNode.getData(), new Empty<>()));
                    // Update the prevHeight.
                    prevHeight = temp.height;
                }

                // Add the node's children. Its children's height is always just 1 + its height.
                if (!tempTree.getRight().isEmpty()) {
                    queue.add(new NodeHeight<>(tempTree.getRight(), temp.height + 1));
                }

                if (!tempTree.getLeft().isEmpty()) {
                    queue.add(new NodeHeight<>(tempTree.getLeft(), temp.height + 1));
                }

                // Update the previous node.
                prevNode = tempTree;
            }

            // The last child is always added since it's the last child of its row.
            list = list.append(new Node<>(prevNode.getData(), new Empty<>()));

            return list;
        } catch (EmptyTreeE e) {
            return new Empty<>();
        }
    }
}
