package org.song.java8;

import java.util.PriorityQueue;

/**
 * Purpose of this class is to
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }

        PriorityQueue<ListNode> q = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        for (ListNode node : lists){
            if (node != null){
                q.add(node);
            }
        }
        if (q.isEmpty()){
            return null;
        }

        ListNode node = q.poll();
        ListNode root = node;
        if (node.next !=null){
            q.add(node.next);
        }

        while (!q.isEmpty()){
            ListNode head = q.poll();
            node.next = head;
            if (head.next !=null){
                q.add(head.next);
            }
            node = node.next;
        }

        return root;
    }
}
