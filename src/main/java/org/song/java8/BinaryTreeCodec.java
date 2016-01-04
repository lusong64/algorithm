package org.song.java8;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Purpose of this class is to
 */
public class BinaryTreeCodec {
    // Encodes a tree to a single string.
    private static final String nullString = "x";
    private static final String delimeter = ",";
    public String serialize(TreeNode root) {
        if (root == null){
            return "";
        }
        StringBuilder bldr = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        bldr.append(root.val).append(delimeter);
        while (!q.isEmpty()){
            TreeNode node = q.poll();
            if (node.left == null){
                bldr.append(nullString).append(delimeter);
            }
            else{
                bldr.append(node.left.val).append(delimeter);
                if (node.left.left != null || node.left.right !=null){
                    q.add(node.left);
                }
            }

            if (node.right == null){
                bldr.append(nullString).append(delimeter);
            }
            else{
                bldr.append(node.right.val).append(delimeter);
                if (node.right.left !=null || node.right.right !=null){
                    q.add(node.right);
                }

            }
        }

        return bldr.substring(0, bldr.length()-1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.trim().length()==0 ){
            return null;
        }
        else if (data.trim().length() == 1){
            return data.equals(nullString)?null:new TreeNode(Integer.valueOf(data).intValue());
        }

        String[] nodes = data.split(delimeter);
        if (nodes[0] == nullString){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]).intValue());
        int i=1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (i<nodes.length && !q.isEmpty()){
            TreeNode node = q.poll();
            TreeNode leftChild = null;
            TreeNode rightChild = null;
            if (!nodes[i].equals(nullString)){
                leftChild = new TreeNode(Integer.valueOf(nodes[i]).intValue());
                q.add(leftChild);
            }
            i++;
            if (i<nodes.length && !nodes[i].equals(nullString)){
                rightChild = new TreeNode (Integer.valueOf(nodes[i]).intValue());
                q.add(rightChild);
            }
            i++;
            node.left = leftChild;
            node.right = rightChild;

        }

        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString(){
            StringBuilder bldr = new StringBuilder("[");

            inOrder(this, bldr);
            return bldr.substring(0, bldr.length()-1)+"]";
        }

        private void inOrder(TreeNode node, StringBuilder bldr){

            if (node.left != null){
                inOrder(node.left, bldr);
            }
            bldr.append(node.val).append(",");
            if (node.right !=null){
                inOrder(node.right, bldr);
            }
        }
    }

    public static void main (String [] args){
        BinaryTreeCodec codec = new BinaryTreeCodec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println("Originral tree="+root);
        String se = codec.serialize(root);
        System.out.println("se="+se);
        TreeNode copy = codec.deserialize(se);
        System.out.println("Copied tree="+copy);

    }
}
