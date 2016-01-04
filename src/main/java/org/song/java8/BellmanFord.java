package org.song.java8;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Purpose of this class is to
 */
public class BellmanFord {


    private static boolean bellManFord(Collection<Vertex> g, Vertex s){

        Vertex.initGraph(g, s);

        List<Edge> allEdges = new LinkedList<>();
        for (Vertex v : g){
            allEdges.addAll(v.getEdges());
        }

        for (Vertex v : g){
            System.out.println(String.format("Relaxing Vertex id=%s", v.getId()));
            for (Edge e : allEdges){
                e.relax();
        }
            System.out.println("--------------------------------------------------");
            System.out.println("");
        }

        for (Vertex v: g){
            for (Edge e: v.getEdges()){
                if (e.isNegativeCycleDetected(v)){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main (String[] args){

        String[] edgeStrings = {
            "s->t,6", "s->y,7",
            "t->x,5", "t->z,-4", "t->y,8",
            "y->x,-3", "y->z,9",
            "x->t,-2",
            "z->s,2", "z->x,7",
        };

        Map<String, Vertex> vertice = Edge.buildGraph(edgeStrings);

        System.out.println("Before BellmanFord: ");
        Vertex.printGraph(vertice.values());

        boolean hasShortex = bellManFord(vertice.values(), vertice.get("s"));

        System.out.println("After BellmanFord: shortest_exists = " + hasShortex);
        Vertex.printGraph(vertice.values());

    }

}
