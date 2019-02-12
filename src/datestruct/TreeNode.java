package datestruct;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(String x){
        val = Integer.getInteger(x);
    }
}
