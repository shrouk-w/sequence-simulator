import java.util.ArrayList;

public class AVL {

    Node root;

    int totalnum;

    public AVL() {

        this.root = null;

        totalnum=0;

    }

    private int height(Node node) {

        return node == null ? 0 : node.height;

    }

    private void updateHeight(Node node) {

        node.height = Math.max(height(node.left), height(node.right)) + 1;

    }

    private Node rotateLeft(Node x) {

        Node y = x.right;

        Node T2 = y.left;

        y.left = x;

        x.right = T2;

        updateHeight(x);

        updateHeight(y);

        return y;

    }

    private Node rotateRight(Node y) {

        Node x = y.left;

        Node T2 = x.right;

        x.right = y;

        y.left = T2;

        updateHeight(y);

        updateHeight(x);

        return x;

    }

    private Node balance(Node node) {

        int balanceFactor = height(node.left) - height(node.right);

        if (balanceFactor > 1) {

            if (height(node.left.left) >= height(node.left.right)) {

                node = rotateRight(node);

            } else {

                node.left = rotateLeft(node.left);

                node = rotateRight(node);

            }

        } else if (balanceFactor < -1) {

            if (height(node.right.right) >= height(node.right.left)) {

                node = rotateLeft(node);

            } else {

                node.right = rotateRight(node.right);

                node = rotateLeft(node);

            }

        }

        return node;

    }

    public void insert(int index, int value) {

        totalnum++;

        root = InsRecursion(root, index, value);

    }

    private Node InsRecursion(Node node, int index, int value) {

        if (node == null) {

            return new Node(index, value);

        }

        if (index < node.index) {

            node.left = InsRecursion(node.left, index, value);

        } else {

            node.right = InsRecursion(node.right, index, value);

        }

        updateHeight(node);

        return balance(node);

    }

    public void delete(int index) {

        totalnum--;

        root = DelRecursion(root, index);

    }

    private Node DelRecursion(Node node, int index) {

        if (node == null) return node;

        if (index < node.index) {

            node.left = DelRecursion(node.left, index);

        } else if (index > node.index) {

            node.right = DelRecursion(node.right, index);

        } else {

            if (node.left == null || node.right == null) {

                node = (node.left != null) ? node.left : node.right;

            } else {

                Node temp = minValueNode(node.right);

                node.index = temp.index;

                node.value = temp.value;

                node.right = DelRecursion(node.right, temp.index);

            }

        }

        if (node == null) return node;

        updateHeight(node);

        return balance(node);

    }

    private Node minValueNode(Node node) {

        Node current = node;

        while (current.left != null) {

            current = current.left;

        }

        return current;

    }

     void updateIndexes(Node node, int startIndex, int delta) {

        if (node == null) return;

        if (node.index >= startIndex) {

            node.index += delta;

        }

        updateIndexes(node.left, startIndex, delta);

        updateIndexes(node.right, startIndex, delta);

    }

    public int getValue(int index) {

        Node node = getNode(root, index);

        return node != null ? node.value : -1;

    }

    private Node getNode(Node node, int index) {

        if (node == null) return null;

        if (index < node.index) return getNode(node.left, index);

        if (index > node.index) return getNode(node.right, index);

        return node;

    }

    void inorder_ini(Node node) {

        if (node != null) {

            // Wypisz wartość korzenia

            inorder_ini(node.left);

            preorder_toprint.add(node.value);

            inorder_ini(node.right);

        }

    }

    private ArrayList <Integer> preorder_toprint = new ArrayList<>();

    public ArrayList <Integer> preorderget()

    {

        inorder_ini(this.root);

        return preorder_toprint;

    }

}

