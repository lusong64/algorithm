package org.song.java8;

/**
 * Purpose of this class is to
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder("[");

        inOrder(this, bldr);
        return bldr.substring(0, bldr.length() - 1) + "]";
    }

    private void inOrder(TreeNode node, StringBuilder bldr) {

        if (node.left != null) {
            inOrder(node.left, bldr);
        }
        bldr.append(node.val).append(",");
        if (node.right != null) {
            inOrder(node.right, bldr);
        }
    }

    public String morrisTraversal() {
        TreeNode current = this;
        TreeNode pre = null;
        StringBuilder bldr = new StringBuilder("[");
        while (current != null) {
            if (current.left == null) {
                bldr.append(current.val).append(",");
                current = current.right;
            } else {
                pre = current.left;
                // find current's immediate predecessor
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    // make a temp link from right to current;
                    pre.right = current;
                    current = current.left;
                } else {
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
