package org.song.java8;

import java.util.*;

/**
 * Purpose of this class is to
 */
public class ReconstructItenary {
    public List<String> findItinerary(String[][] tickets) {

        List<String> result = new LinkedList<>();
        if (tickets == null || tickets.length == 0 || tickets[0].length==0){
            return result;
        }

        Map<String, Airport> airportMap = new HashMap<>();
        for (String[] pair : tickets){
            Airport srcAirport = airportMap.get(pair[0]);
            if (srcAirport == null){
                srcAirport = new Airport(pair[0]);
                airportMap.put(pair[0], srcAirport);
            }
            Airport destAirport = airportMap.get(pair[1]);
            if (destAirport == null){
                destAirport = new Airport(pair[1]);
                airportMap.put(pair[1], destAirport);
            }
            srcAirport.addDest(destAirport);
        }
        String srcId="JFK";
        Airport srcAirport = airportMap.get(srcId);
        dfs(result, srcAirport, 1, tickets.length);
        /*
        int visitCount = 1;
        List<Line> lines= new ArrayList(srcAirport.dests);
        for (Line line: lines){
            result.add(srcId);
            boolean find = dfs(result, line, visitCount, tickets.length);
            if (find){
                break;
            }
            visitCount++;
            result.clear();
        }
        */
        return result;
    }

    private boolean dfs(List<String> res, Airport start, int visitedCount, int tickesCount){
        if (start== null ){
            return false;
        }
        res.add(start.getId());
        if (res.size()-1 == tickesCount){
            return true;
        }

        int count = start.dests.size();
        List<String> resCopy = new LinkedList<>();
        resCopy.addAll(res);
        while (count-- > 0){
            Line line = start.getFirstUnvisited(visitedCount);
            if (line == null){
                return false;
            }
            line.visitCount=visitedCount;
            boolean find = dfs(res, line.dest, visitedCount, tickesCount);
            if (find){
                return true;
            }
            res.clear();
            res.addAll(resCopy);
            visitedCount++;
        }
        return false;
    }

    private static class Line{
        int visitCount=0;
        private final Airport dest;
        public Line(Airport airport){
            this.dest = airport;
        }
        @Override
        public String toString(){
            return dest.toString();
        }
    }
    private static class Airport{
        private final String id;
        private final PriorityQueue<Line> dests = new PriorityQueue<Line>(
            (l1, l2)->l1.dest.id.compareTo(l2.dest.id)
        );
        public Airport(String id){
            this.id = id;
        }

        public void addDest(Airport a){
            dests.add(new Line(a));
        }

        @Override
        public String toString(){
            return id;
        }

        public Line getFirstUnvisited(int visitedCount){
            Set<Line> lines = new HashSet<>();
            while (!dests.isEmpty() && dests.peek().visitCount>=visitedCount){
                lines.add(dests.poll());
            }
            Line toReturn = dests.isEmpty()?null:dests.poll();

            if (toReturn !=null){
                lines.add(toReturn);
            }
            dests.addAll(lines);
            return toReturn;
        }

        public String getId(){
            return id;
        }
    }

    public static void main(String[] args){
        ReconstructItenary ri = new ReconstructItenary();
        String[][] tickets1 = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        System.out.println("Tickets: " + Arrays.deepToString(tickets1));
        System.out.println("itenary: " + ri.findItinerary(tickets1));

        String[][] tickets2 = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        System.out.println("Tickets: " + Arrays.deepToString(tickets2) );
        System.out.println("itenary: " + ri.findItinerary(tickets2));

        String[][] tickets3 = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        System.out.println("Tickets: " + Arrays.deepToString(tickets3));
        System.out.println(" itneray: " + ri.findItinerary(tickets3));

        String[][] tickets4 = {{"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},
                {"ANU","JFK"},{"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},
                {"ADL","EZE"},{"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},
                {"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}};
        System.out.println("Tickets: " + Arrays.deepToString(tickets4));
        System.out.println(" itneray: " + ri.findItinerary(tickets4));
    }
}
