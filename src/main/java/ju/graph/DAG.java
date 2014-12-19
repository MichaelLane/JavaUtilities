package ju.graph;

import java.util.List;

/**
 *
 * @author Michael Lane <mlane@gatech.edu>
 */
public class DAG<N extends Node, E extends DirectedEdge<N>> 
extends Graph<N, E> {

    protected DAG(){
        super();
    }
    
    public DAG(List<? extends E> edges) {
        this(edges, true);
    }
    
    public DAG(List<? extends E> edges, boolean doCheck) {
        super();
        for (E edge : edges) {
            List<E> cycle = null;
            if (doCheck) {
                cycle = this.makesCycle(edge);
            }
            if (cycle == null) {
                this.addEdge(edge);
            } else {
                throw new GraphInstantiationException("Could not instantiate DAG;"
                    + " cycle detected: " + cycle.toString());
            }
        }
    }
    
    private List<E> makesCycle(E edge) {
        return this.bfs(edge.getToNode(), edge.getFromNode());
    }
}
