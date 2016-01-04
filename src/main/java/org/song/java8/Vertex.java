package org.song.java8;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class Vertex {
    private int distance = Integer.MAX_VALUE;
    private final String id;
    private Vertex parent = null;

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Vertex)){
            return false;
        }

        if (this == o){
            return true;
        }
        Vertex other = (Vertex) o;
        return other.hashCode() == this.hashCode();
    }

    public void addToEdges(Vertex e, int weight){
        if (e == null){
            return;
        }
        edges.add(new Edge (this, e, weight));
    }

    private final List<Edge> edges = new LinkedList<>();

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getDistance() {
        return distance;
    }

    public String getId() {
        return id;
    }

    public Vertex getParent() {
        return parent;
    }


    public Vertex(String id) {
        this.id = id;
    }

    public static void printGraph(Collection<Vertex> g){
        for (Vertex v : g){
            System.out.println(String.format("Vertex: %s", v));
        }

    }
    public static void initGraph(Collection<Vertex> g, Vertex source){
        if (g == null){
            return;
        }

        for (Vertex v : g){
            if (v == source){
                v.setDistance(0);
            }
            else{
                v.setDistance(Integer.MAX_VALUE);
            }
            v.setParent(null);
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder ("[");
        stringBuilder.append("id=").append(id)
            .append(", distance=").append(distance)
            .append(", parent=").append(parent!=null?parent.getId():"null")
            .append(", edges={");
            for (Edge e : edges){
                stringBuilder.append(e.getEnd().getId()).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            stringBuilder.append("}]");

        return stringBuilder.toString();
    }

}
