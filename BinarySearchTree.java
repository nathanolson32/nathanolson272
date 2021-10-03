public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BinarySearchTree() {
    }
    public BinarySearchTree(E val) {
    }


    // returns true if BST has val else false.
    public boolean contains(Node root, E val) {
        if (root == null)
            return false;
        else if (root.info == val)
            return true;
        else
            return contains(root.left, val) || contains(root.right, val);
    }
    // inserts val at the right place satisfying search tree properties, should handle if the tree is empty
    // if value is already there then donâ€™t insert it again
    public void insert(E val) {
        Node newNode = new Node(val);
        if (root == null) {
            root = newNode; // to add node val if the tree is empty
        } else {
            Node current = root;
            boolean keepTraversingTree = true;
            while (keepTraversingTree) {
                if (val.compareTo((E) current.info) != 0) {
                    if (val.compareTo((E) current.info) <0) {
                        if (current.left == null) {
                            current.left = newNode;
                            current.parent = newNode;
                            keepTraversingTree = false;
                        } else {
                            current = current.left;
                        }
                    } else { // go right
                        if (current.right == null) {
                            current.right = newNode;
                            current.parent = newNode;
                            keepTraversingTree = false;
                        } else {
                            current = current.right;
                        }
                    }
                } else { // in case of duplicate
                    keepTraversingTree = false;
                }
            }
        }
    }
    // returns the minimum value stored in the tree
    public E findMin() {
        Node<E> current = root;
        while (current.left != null)
            current = current.left;

        return (current.info);
    }
    // returns the maximum value stored in the tree
    public E findMax() {
        Node<E> current = root;
        while (current.right != null)
            current = current.right;

        return (current.info);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();
        bt.insert(5);
        bt.insert(10);
        bt.insert(3);
        bt.insert(20);
        bt.insert(8);
        bt.insert(8);
    }
}