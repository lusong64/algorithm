package org.song.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class TextJustification {
    public List<String> fullJustify(String[] ws, int l) {

        List<String> lines = new ArrayList<String>();
        if (ws == null || ws.length==0 || l==0){
            lines.add("");
            return lines;
        }

        int index = 0;
        StringBuilder bldr =  new StringBuilder();
        while(index < ws.length){
            int count = ws[index].length();
            int last = index+1;
            while(last < ws.length && count+ws[last].length()+1<=l){
                count+=ws[last].length()+1;
                last ++;
            }
            // got enough words
            int wordCount = last - index;
            int spaceSlots = wordCount-1;
            bldr.setLength(0);
            if (last == ws.length ||wordCount == 1){
                // last line or one word
                while (index < last){
                    bldr.append(ws[index]);
                    if (bldr.length()<l){
                        bldr.append(" ");
                    }
                    index++;
                }
                int remain = l-bldr.length();
                while(remain-->0){
                    bldr.append(" ");
                }
            }
            else{
                // mid-justification
                int spacesForEverySlot = (l-count)/spaceSlots + 1;
                int moreSpaceCount= (l-count)%spaceSlots;
                while (index < last-1){
                    bldr.append(ws[index]);
                    for (int i=0; i<spacesForEverySlot; i++){
                        bldr.append(" ");
                    }
                    if (moreSpaceCount-->0){
                        bldr.append(" ");
                    }
                    index++;
                }
                bldr.append(ws[index]);

            }
            lines.add(bldr.toString());
            index = last;
        }


        return lines;
    }


    private static void test(TextJustification tj, String[] ws, int cCount){
        System.out.println("Working on "+ Arrays.toString(ws));
        List<String> list = tj.fullJustify(ws, cCount);
        System.out.println("Here is the lines: ");
        for (String s : list){
            System.out.println("["+s+"]");
        }
        System.out.println("----------------------------------------------");
    }

    public static void main(String[] args){
        TextJustification tj = new TextJustification();
        test(tj, new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        test(tj, new String[]{""}, 2);
        test(tj, new String[]{"a"}, 1);
    }
}
