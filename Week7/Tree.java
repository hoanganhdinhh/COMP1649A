package Labs.Week7;

public class Tree {
    private TreeNode root;

    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    public boolean search(int target) {
        return searchRec(root, target);
    }

    private boolean searchRec(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        if (node.value == target) {
            return true;
        }
        return target < node.value ? searchRec(node.left, target) : searchRec(node.right, target);
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);

        System.out.println(tree.search(4));
        System.out.println(tree.search(6));
    }
}
