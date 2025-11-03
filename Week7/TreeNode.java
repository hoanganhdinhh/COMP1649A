package Week7;
public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    private TreeNode AddNode(TreeNode plocation, TreeNode p, int opt){
        if (p == null) {
            return null;
        }
        else if (plocation == null) {
            plocation.left = p;
            return p;
        }

    }

}
