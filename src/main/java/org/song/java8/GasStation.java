package org.song.java8;

/**
 * Purpose of this class is to
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length==0 || cost==null || cost.length==0){
            return 0;
        }
        else if (gas.length==1){
            return gas[0]>=cost[0]?0:-1;
        }
        Status status = new Status(0, 0);
        int carry = 0;
        for (int i=1; i<=gas.length; i++){
            carry +=gas[i-1] - cost[i-1];
            if (carry < status.gas ){
                status.index = i;
                status.gas = carry;
            }

        }
        System.out.println(status);

        return carry >=0?status.index:-1;
    }

    private static class Status{
        int gas;
        int index;
        public Status(int gas, int index){
            this.gas = gas;
            this.index = index;
        }

        @Override
        public String toString(){
            return String.format("gas: %d and city index: %d", gas, index);
        }

    }

    public static void main(String[] args){
        GasStation gs = new GasStation();
        System.out.println(gs.canCompleteCircuit(new int[]{4}, new int[]{5}));
        System.out.println(gs.canCompleteCircuit(new int[]{2, 4}, new int[]{3, 4}));
        System.out.println(gs.canCompleteCircuit(new int[]{1, 2}, new int[]{2, 1}));
    }
}
