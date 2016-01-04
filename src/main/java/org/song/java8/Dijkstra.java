package org.song.java8;

import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Purpose of this class is to
 */
public class Dijkstra {

    private static void dijkStraAddingSFirst(Collection<Vertex> g, Vertex s){
        Vertex.initGraph(g, s);

        PriorityQueue<Vertex> q = new PriorityQueue<>(g.size(),
            (v1, v2) -> v1.getDistance() - v2.getDistance());
        q.add(s);
        dijkstraCore(q);
    }

    private static void dijkStraAddinAll(Collection<Vertex> g, Vertex s){
        Vertex.initGraph(g, s);

        PriorityQueue<Vertex> q = new PriorityQueue<>(g.size(),
            (v1, v2) -> v1.getDistance() - v2.getDistance());
        q.addAll(g);
        dijkstraCore(q);
    }
    private static void dijkstraCore(PriorityQueue<Vertex> q){

        while (!q.isEmpty()){
            Vertex u = q.poll();
            System.out.println("\n-----------Popping "+u.getId());
            for (Edge e : u.getEdges()){
                Vertex v = e.getEnd();
                if (e.relax()){
                    if (q.remove(v) ){
                        System.out.println("Modifying " + v.getId());
                    }
                    else{
                        System.out.println("Enqueueing " + v.getId());
                    }
                    q.add(v);

                }
                else{
                    System.out.println("No change for " + v.getId());
                }
            }
        }
    }

    public static void main (String[] args){

        String[] edgeStrings = {
            "s->t,10", "s->y,5",
            "t->x,1", "t->y,2",
            "y->x,9", "y->t,3", "y->z,2",
            "x->z,4",
            "z->s,7", "z->x,6",
        };

        Map<String, Vertex> vertice = Edge.buildGraph(edgeStrings);

        System.out.println("Before Dijkstra\n");
        Vertex.printGraph(vertice.values());
        dijkStraAddinAll(vertice.values(), vertice.get("s"));
        System.out.println("\nAfter Dijkstra");
        Vertex.printGraph(vertice.values());
    }
}
