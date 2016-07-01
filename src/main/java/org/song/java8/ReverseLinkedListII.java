package org.song.java8;

/**
 * Purpose of this class is to
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head==null || head.next == null || m>=n){
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        int count = n-m+1;
        while (cur !=null && --m>0){
            prev=cur;
            cur=cur.next;
        }

        ListNode revertRes = reverse(cur, count);
        if (prev!=null){
            prev.next = revertRes;
            return head;
        }
        else{
            // revert from the start;
            return revertRes;
        }


    }

    private ListNode reverse(ListNode head, int count){
        ListNode prev =null;
        ListNode front=null;
        ListNode tail = head;

        while(count-->0 && head!=null){
            front = head.next;
            head.next = prev;
            prev = head;
            head = front;
        }
        if (tail !=null){
            tail.next=front;
        }

        return prev;

    }

    public static void main(String[] args){

        ReverseLinkedListII reII = new ReverseLinkedListII();
        ListNode n3 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        n2.next = n3;
        ListNode n1 = new ListNode(1);
        n1.next = n2;

        System.out.println("Before reverse: "+n1);
        System.out.println("After reverse:"+ reII.reverseBetween(n1, 1, 2));

    }
}
