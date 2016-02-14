package org.song.java8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

        public String morrisTraversal(){
            TreeNode current = this;
            TreeNode pre=null;
            StringBuilder bldr = new StringBuilder("[");
            while (current !=null){
                if (current.left == null){
                    bldr.append(current.val).append(",");
                    current = current.right;
                }
                else{
                    pre = current.left;
                    // find current's immediate predecessor
                    while (pre.right !=null && pre.right !=current){
                        pre = pre.right;
                    }

                    if (pre.right == null){
                        // make a temp link from right to current;
                        pre.right = current;
                        current = current.left;
                    }
                    else{
                        // pre.right is pointed to current; meaning that we finished the traversal of all current's left.
                        // we need to visit current now.
                        // restore pre.right to null;
                        pre.right = null;
                        bldr.append(current.val).append(",");
                        current = current.right;
                    }

                }
            }

            return bldr.deleteCharAt(bldr.lastIndexOf(",")).append("]").toString();
        }

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length==0){
            return null;
        }

        return buildATree(nums, 0, nums.length-1);


    }

    private TreeNode buildATree(int[] nums, int start, int end){
        if (start>end){
            return null;
        }
        else if (start == end && start>=0 && start<nums.length){
            return new TreeNode(nums[start]);
        }

        int mid = start + (end-start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildATree(nums, start, mid-1);
        root.right = buildATree(nums, mid+1, end);

        return root;
    };

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        return pathExists(root, 0, sum);
    }

    private boolean pathExists(TreeNode root, int sumSofar, int sum){
        if (root==null){
            return sumSofar == sum;
        }
        if (root.left == null && root.right==null){
            return sumSofar+root.val==sum;
        }

        return pathExists(root.left, sumSofar+root.val, sum) || pathExists(root.right, sumSofar+root.val, sum);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }

        return findPathSum(root, 0, sum);
    }

    private List<List<Integer>> findPathSum(TreeNode root, int sumSofar, int sum){

        if (root.left == null && root.right == null){
            if (sumSofar + root.val == sum){
                List<Integer> list= new LinkedList<>();
                list.add(root.val);
                List<List<Integer>> result = new LinkedList<>();
                result.add(list);
                return result;
            }
            else{
                return new ArrayList<List<Integer>>();
            }

        }

        List<List<Integer>> fromLeft = null;
        if (root.left !=null){
            fromLeft=findPathSum(root.left, sumSofar+root.val, sum);
            if (fromLeft!=null && !fromLeft.isEmpty()){
                for (List<Integer> list : fromLeft){
                    list.add(0, root.val);
                }
            }
        }

        List<List<Integer>> fromRight = null;
        if (root.right !=null){
           fromRight = findPathSum(root.right, sumSofar+root.val, sum);
           if (fromRight!=null && !fromRight.isEmpty()){
                for (List<Integer> list : fromRight){
                    list.add(0, root.val);
                }
           }
        }

        List<List<Integer>> result = new LinkedList<>();
        if (fromLeft!=null && !fromLeft.isEmpty()){
            result.addAll(fromLeft);
        }
        if (fromRight!=null && !fromRight.isEmpty()){
            result.addAll(fromRight);
        }
        return result;
    }


    public void flatten(TreeNode root) {
        if (root==null){
            return;
        }
        flattenAndGetTail(root);
    }

    private TreeNode flattenAndGetTail(TreeNode root){
        if (root.left == null && root.right == null){
            return root;
        }

        TreeNode tail = null;
        TreeNode theLeft = root.left;
        TreeNode theRight = root.right;

        if (theLeft != null){
            tail = flattenAndGetTail(theLeft);
            root.right = theLeft;
            root.left = null;
        }
        else{
            tail = root;
        }

        if (theRight == null){
            return tail;
        }
        else{
            tail.right = theRight;
            tail = flattenAndGetTail(theRight);
        }

        return tail;
    }

    public int largestBSTSubtree(TreeNode root) {
        int[] result = count(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return result[1];
    }

    private int[] count (TreeNode root, long lower, long higher){
        int [] result = new int[2];
        if (root==null){
            return result;
        }

        if (root.val <=lower || root.val>=higher){
            result[0] = -1;
        }


        int[] countLeft = count(root.left, Long.MIN_VALUE, root.val);
        int[] countRight = count(root.right, root.val, Long.MAX_VALUE);

        if (countLeft[0] != -1 && countRight[0] != -1){
            result[1] = countLeft[1] + countRight[1]+1;
        }
        else if (countLeft[0] == -1 || countRight[0] == -1){
            result[0] = -1;
            result[1] = Math.max(countLeft[1], countRight[1]);
        }
        /*
        else{
            result[1] = 1 + Math.max(countLeft[1], countRight[1]);
        }
        */
        return result;

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

        String s = "1,2,3,4,5,6,7,8,9,10,11,12,x,x";
        TreeNode tree = codec.deserialize(s);
        System.out.println("Morris Traversial: " + tree.morrisTraversal());
        System.out.println("In order Traversial: " + tree);

        String s1 = "3,1,x,2,x,x,4";
        TreeNode rootS1 = codec.deserialize(s1);

        System.out.println(codec.largestBSTSubtree(rootS1));

        String s2 = "3,2,4,x,x,1";
        TreeNode rootS2 = codec.deserialize(s2);
        System.out.println(codec.largestBSTSubtree(rootS2));


    }


}
