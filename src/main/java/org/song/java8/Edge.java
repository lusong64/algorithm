package org.song.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * Purpose of this class is to
 */
public class Edge {
    private static final String VERTEX_DELIMETER = "->";
    private int weight = 0;
    private final Vertex start;

    public Vertex getEnd() {
        return end;
    }

    private final Vertex end;

    public Vertex getStart() {
        return start;
    }


    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public boolean relax() {

        boolean changed = false;
        System.out.println(String.format("\nBefore relax: %s", this.toString()));
        if (end.getDistance() > start.getDistance() + weight){
            end.setDistance(start.getDistance() + weight);
            end.setParent(start);
            changed = true;
        }
        System.out.println(String.format("After relax: %s\n", this.toString()));

        return changed;

    }

    public boolean isNegativeCycleDetected(Vertex u){
        return end.getDistance() > u.getDistance() + weight;
    }

    @Override
    public String toString(){
        return String.format("weight=%d, (%s distance=%d ) %s (%s distance=%d )",
            weight, start.getId(), start.getDistance(), VERTEX_DELIMETER,
            end.getId(), end.getDistance());
    }

    /**
     * Building egdes with String like "s->e"
     * @param edgeString ex: "x->y" with x is the start and y end.
     * @param weight
     * @param existingVertice
     * @return
     */
    public static void buildAndAddEdge(String edgeString, int weight, Map<String, Vertex> existingVertice){
        if (existingVertice == null){
            throw new IllegalArgumentException("existingVertice is null");
        }
        if (edgeString == null || edgeString.trim().isEmpty()){
            throw new IllegalArgumentException(String.format("Illegal edgeString: %s", edgeString));
        }
        String[] vs = edgeString.split(VERTEX_DELIMETER);
        if(vs == null || vs.length !=2){
            throw new IllegalArgumentException(String.format("Illegal edgeString: %s", edgeString));
        }

        Vertex s = existingVertice.get(vs[0].trim());
        if (s == null){
            s = new Vertex(vs[0].trim());
            existingVertice.put(s.getId(), s);
        }

        Vertex e = existingVertice.get(vs[1].trim());
        if (e == null){
            e = new Vertex(vs[1].trim());
            existingVertice.put(e.getId(), e);
        }

        Edge edge = new Edge(s, e, weight);
        //System.out.println("Built an edge="+edge + " and adding it to Vertex: " + s);
        s.getEdges().add(edge);
    }

    /**
     * Building a list of Edges with string[], each of which is
     * like "x->y,10"
     * @param edges
     * @return
     */
    public static Map<String, Vertex> buildGraph(String[] edges){
        Map<String, Vertex> stringVertexHashMap= new HashMap<>();
        if (edges == null || edges.length==0){
            return stringVertexHashMap;
        }
        for (String s : edges){
            String[] vAndW = s.split(",");
            if (vAndW == null || vAndW.length !=2){
                throw new IllegalStateException(String.format("Illegal String founnd: %s", s));
            }
            buildAndAddEdge(vAndW[0], Integer.parseInt(vAndW[1].toString()), stringVertexHashMap);
        }

        return stringVertexHashMap;
    }

}
