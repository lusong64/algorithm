package org.song.java8;

import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class AddExpression {
    public List<String> addOperators(String num, int target) {

        List<String> res = new LinkedList<>();
        if (num == null || num.length()==0){
            return res;
        }

        dfs(res, num, 0, new StringBuilder(), "", target, 0, 0);

        return res;
    }



    private void dfs(List<String> res, String nums, int pos, StringBuilder bldr, String work, int target, long val, long preMultiVal){
        if (pos >= nums.length()){
            if (target == val){
                res.add(work);
            }
            return;
        }

        for (int i = pos; i<nums.length(); i++){
            if (i!=pos && nums.charAt(pos) == '0'){
                break;
            }
            long current = Long.parseLong(nums.substring(pos, i+1));

            int originalLen = bldr.length();

            if (pos ==0){
                dfs (res, nums, i+1, bldr, bldr.append(current).toString(), target, current, current);
                bldr.setLength(originalLen);
            }
            else{

                dfs (res, nums, i+1, bldr, bldr.append("+").append(current).toString(), target, val+current, current );
                bldr.setLength(originalLen);
                dfs (res, nums, i+1, bldr, bldr.append("-").append(current).toString(), target, val-current, -current );
                bldr.setLength(originalLen);
                dfs (res, nums, i+1, bldr, bldr.append("*").append(current).toString(), target, val-preMultiVal + preMultiVal*current, current*preMultiVal );
                bldr.setLength(originalLen);
            }
        }

    }
}
