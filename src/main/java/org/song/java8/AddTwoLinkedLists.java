package org.song.java8;

/**
 * Purpose of this class is to
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString(){
        StringBuilder bldr = new StringBuilder("[");
        ListNode c = this;
        while (c != null){
            bldr.append(c.val).append(" ");
            c=c.next;
        }
        return bldr.append("]").toString();
    }
}
public class AddTwoLinkedLists {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null){
            return l1!=null?l1:l2;
        }
        ListNode result = l1;

        int carry = 0;
        ListNode l1Prev = null;
        // using l1 as the result;
        while (l2 != null && l1 !=null){
            int prevL1Value = l1.val;
            l1.val = (l1.val + carry + l2.val)%10;
            carry = (prevL1Value + carry + l2.val)/10;
            l1Prev=l1;
            l1=l1.next;
            l2=l2.next;
        }

        if (l2 != null){
            l1Prev.next = l2;
        }

        if (carry != 0){
            addCarry(carry, l1Prev);
        }


        return result;

    }

    private static void addCarry(int carry, ListNode l){

        while (l.next != null && carry !=0){
            l.next.val = (l.next.val + carry )%10;
            carry = (l.next.val + carry )/10;
            l=l.next;
        }
        if (carry!=0){
            l.next=new ListNode(carry);
        }

    }



    public static void main (String[] args){
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(5);

        addTwoNumbers(n1, n2);
        System.out.println(n1);
    }

}
