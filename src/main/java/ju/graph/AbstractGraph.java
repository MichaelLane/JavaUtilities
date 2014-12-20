package ju.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import static ju.snippets.MapSnippets.*;

/**
 * All Graph types must extend this class. 
 * Currently, edges are limited to connect
 * only two nodes; this class must be modified to allow edges that connect more 
 * than two edges.
 * @author Michael Lane <mlane@gatech.edu>
 */
public abstract class AbstractGraph<N extends Node, E extends Edge<N>> {
    
    /** Map for getting the edge between a couple nodes. */
    protected final Map<NodePair<N>, E> edgeByNodePair;
    /** The nodes adjacent to a given node. */
    protected final Map<N, Set<N>> neighborsByNode;
    /** The edges attached to a given node. */
    protected final Map<N, Set<E>> edgesByNode;
    
    protected AbstractGraph() {
        edgeByNodePair = new HashMap();
        neighborsByNode = new HashMap();
        edgesByNode = new HashMap();
    }
    
    public Set<N> getNeighbors(N node) {
        Set<N> neighbors = neighborsByNode.get(node);
        return (neighbors != null) ? neighbors : new HashSet();
    }
    
    public Set<N> getNodes() {
        return neighborsByNode.keySet();
    }
    
    /**
     * #todo
     * @param node1
     * @param node2
     * @return null if no edge exists between node1 and node2
     */
    public E getEdge(N node1, N node2) {
        return edgeByNodePair.get(new NodePair(node1, node2));
    }
    
    /**
     * Get all the nodes connected to the given node via some path; i.e. the transitive
     * closure of the "neighbor" relation.
     * @param node
     * @return 
     */
    public Set<N> getConnectedNodes(N node) {
        
        Set<N> visited = new HashSet();
        List<N> frontier = new LinkedList();
        
        frontier.addAll(this.getNeighbors(node));
        
        while (!frontier.isEmpty()) {
            N n = frontier.remove(0);
            if (!visited.contains(n)) {
                visited.add(n);
                Set<N> neighbors = this.getNeighbors(n);
                for (N neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        frontier.add(neighbor);
                    }
                }                
            }      
        }
        return visited;
    }
    
    /**
     * Get a List of nodes that are all disconnected from each other, such that
     * every node in this graph is connected to exactly one node in the returned
     * List.
     * @return 
     */
    public Set<N> getDisconnections() {
        Set<N> nodes = this.getNodes();
        Iterator<N> nodesIter = nodes.iterator();
        int numNodes = nodes.size();
        Set<N> totalConnected = new HashSet();
        Set<N> disconn = new HashSet();
        while (totalConnected.size() < numNodes) {
            N n = nodesIter.next();
            while (totalConnected.contains(n)) {
                n = nodesIter.next();
            }
            totalConnected.addAll(this.getConnectedNodes(n));
            disconn.add(n);
        }
        
        return disconn;
    }
    
    public Set<E> getEdges() {
        return new HashSet(edgeByNodePair.values());
    }
    
    public Set<E> getEdges(N node) {
        Set<E> theseEdges = edgesByNode.get(node);
        return (theseEdges != null) ? theseEdges : new HashSet();
    }
    
    public abstract void addEdge(E edge) throws EdgeRuleException;
    
    public void addAllEdges(Collection<? extends E> edges) 
    throws EdgeRuleException {
        for (E edge : edges) {
            this.addEdge(edge);
        }
    }
    
    /**
     * Add all the edges from the parameter graph to this graph that do not already
     * occur in this graph. Since the edge rule for a superclass also applies to
     * all its subclasses, if the parameter's type is not a subclass of this type,
     * then a priori it is impossible to know if this union will obey the edge rule
     * of this type. Even if the parameter's type is a subclass of this type, the
     * edge rule may still be violated.
     * 
     * @param graph 
     * @throws ju.graph.EdgeRuleException 
     */
    public void union(AbstractGraph<N, E> graph) throws EdgeRuleException { 
        
        if (this.getClass().isAssignableFrom(graph.getClass())) {
            Set<E> thoseEdges = graph.getEdges();
            Set<E> theseEdges = this.getEdges();
            for (E edge : thoseEdges) {
                if (!theseEdges.contains(edge)) {
                    this.addEdge(edge);
                }
            }
        } else {
            throw new EdgeRuleException("The type of the argument is not a subclass "
                + "of this class. The type of the argument is " + graph.getClass().toString()
                + ".");
        }
    }
    
    public int getNumNodes() {
        return edgesByNode.size();
    }
    
    public int getNumEdges() {
        return edgeByNodePair.size();
    }
    
    public boolean hasNeighbors(N node) {
        return !this.neighborsByNode.get(node).isEmpty();
    }
    
    /**
     * Simply represents a pair of nodes; this is how Edge data is encoded in this
     * graph.
     * @param <N> 
     */
    protected static class NodePair<N extends Node> {

        public final N node1;
        public final N node2;

        public NodePair(N node1, N node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + Objects.hashCode(this.node1);
            hash = 89 * hash + Objects.hashCode(this.node2);
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            NodePair<N> nodePair = (NodePair) o;
            return node1.equals(nodePair.node1)
                && node2.equals(nodePair.node2);
        }
        
        @Override
        public String toString()  {
            return "(" + node1.toString() + ", " + node2.toString() + ")";
        }
    }
    
    /**
     * Represents a Node (called the "state") and an Edge. Used for graph-search
     * algos like A*.
     * @param <N>
     * @param <E> 
     */
    protected static class Action<N extends Node, E extends Edge<N>>  {
        
        final N state;
        final E edge;
        
        Action(N state, E edge) {
            this.state = state;
            this.edge = edge;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this.getClass() != o.getClass()) return false;
            Action a = (Action) o;
            return a.state.equals(this.state) && a.edge.equals(this.edge);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.state);
            hash = 97 * hash + Objects.hashCode(this.edge);
            return hash;
        }
    }
}
