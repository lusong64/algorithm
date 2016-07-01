package org.song.java8;

/**
 * Purpose of this class is to
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        if ((version1 == null || version1.trim().length()==0) &&
                (version2 == null || version2.trim().length()==0)){
            return 0;
        }
        else if (version1==null || version1.trim().length()==0){
            return -1;
        }
        else if (version2==null || version2.trim().length()==0){
            return 1;
        }
        String [] v1 = version1.trim().split("\\.");
        String [] v2 = version2.trim().split("\\.");
        /*
        if (v1.length==0){
            int n1 = Integer.parseInt(version1);
            int n2 = v2.length==0?Integer.parseInt(version2):Integer.parseInt(v2[0]);
            if (n1>n2){
                return 1;
            }
            else if (n1<n2){
                return -1;
            }
            else{
                return v2.length==0 || v2.length>=2 && Integer.parseInt(v2[1])==0?0:-1;
            }
        }
        else if (v2.length==0){
            int n1 = v1.length==0?Integer.parseInt(version1):Integer.parseInt(v1[0]);
            int n2 = Integer.parseInt(version2);
            if (n1>n2){
                return 1;
            } else if (n1 < n2) {
                return -1;
            }
            else{
                return v1.length==0 || v1.length>=2 && Integer.parseInt(v1[1])==0?0:1;
            }
        }
        */

        int i=0;
        while (i<v1.length && i<v2.length){
            int n1 = Integer.parseInt(v1[i]);
            int n2 = Integer.parseInt(v2[i]);
            if (n1>n2){
                return 1;
            }
            else if (n1<n2){
                return -1;
            }

            i++;
        }

        if (i == v1.length && i== v2.length){
            return 0;
        }

        else return i<v1.length?1:-1;

    }

    public static void main(String[] args){
        CompareVersion cp = new CompareVersion();
        String v1_1 = "1.0";
        String v2_1 = "1.1";
        System.out.println("Comparing "+v1_1 + " and " + v2_1 + " : " + cp.compareVersion(v1_1, v2_1));

        String v1_2 = "1.0";
        String v2_2 = "1";
        System.out.println("Comparing "+v1_2 + " and " + v2_2 + " : " + cp.compareVersion(v1_2, v2_2));
    }
}
