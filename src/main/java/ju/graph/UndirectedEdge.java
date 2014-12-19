package ju.graph;

/**
 * An UndirectedEdge is just a connection between two Nodes, where both Nodes are
 * the same as far as this Edge is concerned.
 * 
 * @author Michael Lane <mlane@gatech.edu>
 * @param <N>
 */
public interface UndirectedEdge<N extends Node> extends Edge<N> {
    
    public N getNode1();
    public N getNode2();
}
