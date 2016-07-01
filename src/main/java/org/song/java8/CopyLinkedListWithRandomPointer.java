package org.song.java8;

/**
 * Purpose of this class is to
 */
public class CopyLinkedListWithRandomPointer {
    static class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
    };

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null){
            return head;
        }
        RandomListNode src = head;
        RandomListNode copy = null;
        while (src != null){
            copy = new RandomListNode(src.label);
            copy.next = src.next;
            src.next = copy;
            src = src.next.next;
        }
        src = head;
        RandomListNode dummy = head.next;
        while (src != null){
            copy = src.next;
            copy.random = src.random!=null?src.random.next:null;
            src = src.next.next;
        }

        src = head;
        while (src !=null){
            copy = src.next;
            src.next = copy.next;
            src = src.next;
            copy.next = src!=null?src.next:null;
        }
        return dummy.next;
    }

    public static void main(String[] args){
        CopyLinkedListWithRandomPointer cl = new CopyLinkedListWithRandomPointer();
        RandomListNode head = new RandomListNode(-1);

        RandomListNode copyHead = cl.copyRandomList(head);
        System.out.println(head == copyHead);
    }

}
