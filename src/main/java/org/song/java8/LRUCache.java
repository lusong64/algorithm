package org.song.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose of this class is to
 */
public class LRUCache {
    private int size = 0;
    private final int capacity;
    private final DNode head = new DNode(Integer.valueOf(-1), -1);
    private final DNode tail= new DNode(Integer.valueOf(-1), -1);
    private final Map<Integer, DNode> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity=capacity;
        head.next=tail;
        head.pre=null;
        tail.pre=head;
        tail.next=null;
    }

    public int get(int key) {
        DNode node = map.get(Integer.valueOf(key));
        int result = -1;
        if (node !=null){
            result = node.value;
            DNode.refreshNode(tail, node);
        }
        return result;
    }

    public void set(int key, int value) {
        Integer theKey = Integer.valueOf(key);
        if (map.containsKey(theKey)){
            DNode node = map.get(theKey);
            node.value=value;
            DNode.refreshNode(tail, node);
        }
        else{
            DNode node = new DNode(theKey, value);
            if (size < capacity){
                size++;
                map.put(theKey, node);
            }
            else{
                DNode.removeFirst(head, tail, map);
            }
            DNode.addToLast(tail, node);
            map.put(theKey, node);
        }

    }

    private static class DNode{
        private final Integer key;
        private int value;
        private DNode pre;
        private DNode next;

        public DNode(Integer key, int value){
            this.key = key;
            this.value = value;

        }

        public static void refreshNode( DNode tail, DNode node){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            addToLast(tail, node);
        }

        public static void removeFirst(DNode head, DNode tail, Map<Integer, DNode> map){
            DNode toBeRemoved = head.next;
            if (toBeRemoved == tail){
                return;
            }
            head.next =toBeRemoved.next;
            toBeRemoved.next.pre = head;
            map.remove(toBeRemoved.key);
        }

        public static void addToLast(DNode tail, DNode node){

            tail.pre.next=node;
            node.next=tail;
            node.pre=tail.pre;
            tail.pre=node;

        }

    }

    public boolean isMatch(String s, String p) {
        int sL=s.length(), pL=p.length();

        boolean[][] dp = new boolean[sL+1][pL+1];
        dp[0][0] = true; // If s and p are "", isMathch() returns true;

        for(int i=0; i<=sL; i++) {

            // j starts from 1, since dp[i][0] is false when i!=0;
            for(int j=1; j<=pL; j++) {
                char c = p.charAt(j-1);

                if(c != '*') {
                    // The last character of s and p should match;
                    // And, dp[i-1][j-1] is true;
                    dp[i][j] = i>0 && dp[i-1][j-1] && (c=='.' || c==s.charAt(i-1));
                }
                else {
                    // Two situations:
                    // (1) dp[i][j-2] is true, and there is 0 preceding element of '*';
                    // (2) The last character of s should match the preceding element of '*';
                    //     And, dp[i-1][j] should be true;
                    dp[i][j] = (j>1 && dp[i][j-2]) ||
                        (i>0 && dp[i-1][j] && (p.charAt(j-2)=='.' || p.charAt(j-2)==s.charAt(i-1)));
                }
            }
        }

        return dp[sL][pL];
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache(1);
        cache.set(2, 1);
        System.out.println(cache.get(2));
        cache.set(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}

