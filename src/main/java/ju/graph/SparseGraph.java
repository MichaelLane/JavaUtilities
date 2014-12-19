package ju.graph;

import java.util.List;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class SparseGraph<N extends Node, E extends DirectedEdge<N>> 
extends Graph<N, E> {

    public SparseGraph(List<? extends E> edges) {
        super(edges);
    }
    
    public Tree<N, E> getMST() {
        // use binary heap and adjacency list implementation
        return null;
    }
}
