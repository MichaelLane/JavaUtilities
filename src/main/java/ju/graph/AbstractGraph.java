package ju.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static ju.snippets.MapSnippets.*;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public abstract class AbstractGraph<N extends Node, E extends Edge<N>> {
    
    protected final Map<NodePair<N>, E> edgeByNodePair;
    protected final Map<N, List<N>> neighborsByNode;
    protected final Map<N, List<E>> edgesByNode;
    
    protected AbstractGraph() {
        edgeByNodePair = new HashMap();
        neighborsByNode = new HashMap();
        edgesByNode = new HashMap();
    }
    
    public List<N> getNeighbors(N node) {
        List<N> neighbors = neighborsByNode.get(node);
        return (neighbors != null) ? neighbors : new ArrayList();
    }

    public List<N> getNodes() {
        return new ArrayList(neighborsByNode.keySet());
    }
    
    /**
     * Returns a DirectedEdge if fromNode connects to toNode but not vice-versa;
     * and returns a UndirectedEdge if both connect to each other.
     *
     * @param fromNode non-null
     * @param toNode   non-null
     * @return null if no Edge exists from fromNode to toNode
     */
    public E getEdge(N fromNode, N toNode) {

        if (fromNode == null || toNode == null) {
            return null;
        }

        NodePair<N> nodePair = new NodePair(fromNode, toNode);
        return edgeByNodePair.get(nodePair);
    }
    
    public List<N> getConnectedNodes(N node) {
        
        List<N> visited = new LinkedList();
        List<N> frontier = new LinkedList();
        
        frontier.addAll(this.getNeighbors(node));
        
        while (!frontier.isEmpty()) {
            N n = frontier.remove(0);
            if (!visited.contains(n)) {
                visited.add(n);
                List<N> neighbors = this.getNeighbors(n);
                for (N neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        frontier.add(neighbor);
                    }
                }                
            }      
        }
        return visited;
    }
    
    public List<N> getDisconnections() {
        List<N> nodes = this.getNodes();
        Iterator<N> nodesIter = nodes.iterator();
        int numNodes = nodes.size();
        List<N> totalConnected = new LinkedList();
        List<N> disconn = new LinkedList();
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
    
    public List<E> getEdges() {
        return new ArrayList(edgeByNodePair.values());
    }
    
    public List<E> getEdges(N fromNode) {
        List<E> theseEdges = edgesByNode.get(fromNode);
        return (theseEdges != null) ? theseEdges : new ArrayList();
    }
    
    public abstract void addEdge(E edge);
    
    public void addAllEdges(Collection<? extends E> edges) {
        for (E edge : edges) {
            this.addEdge(edge);
        }
    }
    
    public void union(AbstractGraph<N, E> graph) { 
        
        this.edgeByNodePair.putAll(graph.edgeByNodePair);
        unionCollMaps(this.neighborsByNode, graph.neighborsByNode);
        unionCollMaps(this.edgesByNode, graph.edgesByNode);
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
