package org.song.java8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class RestoreIpAddress {
    /*
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        if (s==null || s.length()==0){
            return res;
        }
        Deque<Integer> dq= new LinkedList<>();
        restore(s.toCharArray(), 0, dq, res);
        return res;
    }

    private void restore(char [] cs, int index, Deque<Integer> dq, List<String> result ){
        if (dq.size() == 3 && cs.length-index>3){
            return;
        }
        else if (dq.size() == 4 && index<cs.length-1){
            return;
        }
        else if (dq.size()==4 && index>cs.length-1 ){
            StringBuilder bldr = new StringBuilder();
            Iterator<Integer> itor = dq.iterator();
            while (itor.hasNext()){
                bldr.append(itor.next()).append(".");
            }
            result.add(bldr.substring(0, bldr.length()-1));
            return;
        }

        for (int len =0; len <3 && index+len<cs.length; len++){
            int num = getNum(cs, index, index+len);
            if (num<0 || num>255){
                continue;
            }
            dq.addLast(num);
            restore(cs, index+len+1, dq, result);
            dq.removeLast();
        }

    }
    */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        if (s==null || s.length()==0){
            return new ArrayList<String>();
        }
        int[] dq = new int[4];
        restore(s.toCharArray(), 0, dq, 0, res);
        return res;
    }

    private void restore(char [] cs, int index, int[] dq, int qIndex, List<String> result ){
        if (qIndex == 3 && cs.length-index>3){
            return;
        }
        else if (qIndex==4 ){
            if (index > cs.length-1){
                StringBuilder bldr = new StringBuilder();
                for (int i=0; i<4; i++){
                    bldr.append(dq[i]).append(".");
                }
                result.add(bldr.substring(0, bldr.length()-1));

            }
            return;
        }

        for (int len =0; len <3 && index+len<cs.length; len++){
            int num = getNum(cs, index, index+len);
            if (num<0 || num>255){
                continue;
            }
            dq[qIndex]=num;
            restore(cs, index+len+1, dq, qIndex+1, result);

        }

    }

    private int getNum(char[] cs, int s, int e){
        int result = 0;
        if (cs[s] == '0' && e-s>0){
            return -1;
        }
        for (int i=s; i<=e; i++){
            result=result*10+(cs[i]-'0');
        }
        return result;
    }

    public static void main(String[] args){
        RestoreIpAddress ri= new RestoreIpAddress();
        String s1 = "0000";
        System.out.println("Restoring "+s1);
        System.out.println("result: "+ ri.restoreIpAddresses(s1));

        String s2 = "010010";
        System.out.println("Restoring "+s2);
        System.out.println("result: "+ ri.restoreIpAddresses(s2));
    }
}
