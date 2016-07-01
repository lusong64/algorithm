package org.song.java8;

import java.util.HashMap;

/**
 * Purpose of this class is to
 */
public class BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        for (int i=0;i<inorder.length;++i){
            hm.put(inorder[i], i);
        }
        return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0,
                postorder.length-1,hm);
    }

    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe,
                                     HashMap<Integer,Integer> hm){
        TreeNode root = null;
        if (ps>pe || is>ie) {
            return root;
        }

        root = new TreeNode(postorder[pe]);

        int ri = hm.get(postorder[pe]);
        TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps + ri-1-is, hm);
        TreeNode rightchild = buildTreePostIn(inorder, ri+1, ie, postorder, ps + ri -is, pe-1, hm);
        root.left = leftchild;
        root.right = rightchild;
        return root;
    }

    private static void test(BuildTree bt, int[] in, int [] post){
        bt.buildTree(in, post);
    }

    public static void main(String[] args){
        BuildTree bt = new BuildTree();
        test(bt, new int[]{1,3,2}, new int[]{3, 2, 1,});

    }
}
