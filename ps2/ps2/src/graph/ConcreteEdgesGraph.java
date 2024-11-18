///* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
// * Redistribution of original or derived work requires permission of course staff.
// */
//package graph;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * An implementation of Graph.
// * 
// * <p>PS2 instructions: you MUST use the provided rep.
// */
//public class ConcreteEdgesGraph implements Graph<String> {
//    
//    private final Set<String> vertices = new HashSet<>();
//    private final List<Edge> edges = new ArrayList<>();
//    
//    // Abstraction function:
//    //   TODO
//    // Representation invariant:
//    //   TODO
//    // Safety from rep exposure:
//    //   TODO
//    
//    // TODO constructor
//    
//    // TODO checkRep
//    
//    @Override public boolean add(String vertex) {
//        throw new RuntimeException("not implemented");
//    }
//    
//    @Override public int set(String source, String target, int weight) {
//        throw new RuntimeException("not implemented");
//    }
//    
//    @Override public boolean remove(String vertex) {
//        throw new RuntimeException("not implemented");
//    }
//    
//    @Override public Set<String> vertices() {
//        throw new RuntimeException("not implemented");
//    }
//    
//    @Override public Map<String, Integer> sources(String target) {
//        throw new RuntimeException("not implemented");
//    }
//    
//    @Override public Map<String, Integer> targets(String source) {
//        throw new RuntimeException("not implemented");
//    }
//    
//    // TODO toString()
//    
//}
//
///**
// * TODO specification
// * Immutable.
// * This class is internal to the rep of ConcreteEdgesGraph.
// * 
// * <p>PS2 instructions: the specification and implementation of this class is
// * up to you.
// */
//class Edge {
//    
//    // TODO fields
//    
//    // Abstraction function:
//    //   TODO
//    // Representation invariant:
//    //   TODO
//    // Safety from rep exposure:
//    //   TODO
//    
//    // TODO constructor
//    
//    // TODO checkRep
//    
//    // TODO methods
//    
//    // TODO toString()
//    
//}
package graph;

import java.util.*;

/**
 * An implementation of Graph.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   Represents a directed graph using a set of vertices and a list of edges.
    
    // Representation invariant:
    //   No two edges in the edges list have the same source and target.
    
    // Safety from rep exposure:
    //   All fields are private, and defensive copying is used when necessary.
    
    public ConcreteEdgesGraph() {
        checkRep();
    }

    private void checkRep() {
        Set<String> edgePairs = new HashSet<>();
        for (Edge e : edges) {
            assert e.getWeight() > 0; // Weights must be positive
            String edgePair = e.getSource() + "->" + e.getTarget();
            assert !edgePairs.contains(edgePair);
            edgePairs.add(edgePair);
        }
    }
    
    @Override
    public boolean add(String vertex) {
        return vertices.add(vertex);
    }
    
    @Override
    public int set(String source, String target, int weight) {
        if (weight == 0) {
            for (Iterator<Edge> iterator = edges.iterator(); iterator.hasNext(); ) {
                Edge e = iterator.next();
                if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                    iterator.remove();
                    checkRep();
                    return e.getWeight();
                }
            }
            return 0;
        }
        for (Edge e : edges) {
            if (e.getSource().equals(source) && e.getTarget().equals(target)) {
                int oldWeight = e.getWeight();
                edges.remove(e);
                edges.add(new Edge(source, target, weight));
                checkRep();
                return oldWeight;
            }
        }
        edges.add(new Edge(source, target, weight));
        vertices.add(source);
        vertices.add(target);
        checkRep();
        return 0;
    }
    
    @Override
    public boolean remove(String vertex) {
        if (!vertices.remove(vertex)) {
            return false;
        }
        edges.removeIf(e -> e.getSource().equals(vertex) || e.getTarget().equals(vertex));
        checkRep();
        return true;
    }
    
    @Override
    public Set<String> vertices() {
        return Collections.unmodifiableSet(vertices);
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Edge e : edges) {
            if (e.getTarget().equals(target)) {
                sources.put(e.getSource(), e.getWeight());
            }
        }
        return Collections.unmodifiableMap(sources);
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Edge e : edges) {
            if (e.getSource().equals(source)) {
                targets.put(e.getTarget(), e.getWeight());
            }
        }
        return Collections.unmodifiableMap(targets);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : edges) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}

/**
 * Represents an immutable edge in the graph.
 */
class Edge {
    private final String source;
    private final String target;
    private final int weight;

    // Abstraction function:
    //   Represents a directed edge with a source, target, and weight.

    // Representation invariant:
    //   Weight is positive and both source and target are non-null.

    // Safety from rep exposure:
    //   Fields are private and final.

    public Edge(String source, String target, int weight) {
        if (source == null || target == null || weight <= 0) {
            throw new IllegalArgumentException("Invalid edge parameters");
        }
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }

    private void checkRep() {
        assert weight > 0;
        assert source != null && target != null;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " -> " + target + " (" + weight + ")";
    }
}