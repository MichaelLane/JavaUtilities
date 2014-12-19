package ju.graph;

/**
 * A DirectedEdge is just a connection "from" one Node "to" another node. 
 * @author Michael Lane <mlane@gatech.edu>
 */
public interface DirectedEdge<N extends Node> extends Edge<N> {
    
    public N getFromNode();
    public N getToNode();
}
