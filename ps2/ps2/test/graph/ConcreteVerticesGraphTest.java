///* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
// * Redistribution of original or derived work requires permission of course staff.
// */
//package graph;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
///**
// * Tests for ConcreteVerticesGraph.
// * 
// * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
// * well as tests for that particular implementation.
// * 
// * Tests against the Graph spec should be in GraphInstanceTest.
// */
//public class ConcreteVerticesGraphTest extends GraphInstanceTest {
//    
//    /*
//     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
//     */
//    @Override public Graph<String> emptyInstance() {
//        return new ConcreteVerticesGraph();
//    }
//    
//    /*
//     * Testing ConcreteVerticesGraph...
//     */
//    
//    // Testing strategy for ConcreteVerticesGraph.toString()
//    //   TODO
//    
//    // TODO tests for ConcreteVerticesGraph.toString()
//    
//    /*
//     * Testing Vertex...
//     */
//    
//    // Testing strategy for Vertex
//    //   TODO
//    
//    // TODO tests for operations of Vertex
//    
//}
package graph;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    private ConcreteVerticesGraph graph;

    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }

    @Before
    public void setUp() {
        graph = new ConcreteVerticesGraph();
    }

    // Test the toString method
//    @Test
//    public void testToString() {
//        graph.add("A");
//        graph.add("B");
//        graph.set("A", "B", 5);
//        String expectedOutput = "Graph: A -> B (5), ";
//        assertEquals("Expected specific string representation", expectedOutput, graph.toString());
//    }

    // Test removing a vertex with connected edges
    @Test
    public void testRemoveVertexWithEdges() {
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        graph.set("B", "A", 4);

        assertTrue("Expected removal of vertex A", graph.remove("A"));
        assertFalse("Expected vertex A to be absent", graph.vertices().contains("A"));
        assertTrue("Expected removal of edges associated with A", graph.targets("B").isEmpty());
    }
}
