package ju.graph;

/**
 * A heuristic, as in the A* algorithm and its variants.
 * 
 * @author Michael Lane <mlane@gatech.edu>
 * @param <N> 
 */
public abstract class Heuristic<N extends Node> {
    
    /**
     * If this Heuristic should be recalculated or not. During an A* search, for 
     * example, the estimated distance from a node to the goal will have to be
     * referenced several times as the search progresses. If this estimation
     * does not change over the course of the search, and if the cost of calculating
     * it is high, it would be better to calculate it a single time, then store 
     * it for later reference. However, if the estimation changes and could generally
     * improve, or if the cost of calculation is low, then the search algorithm 
     * could save on memory usage by not storing estimations. In short, set toRecalc
     * to true if this heuristic should be recalculated (i.e. not stored for later
     * reference), or to false if it should not be (i.e. stored for later reference.)
     */
    protected boolean toRecalc;
    
    /**
     * Setting this to true can save on both time and memory during A* searches.
     */
    protected boolean isConsistent;
    
    /**
     * Generally this should never be false; A* is basically pointless as an algorithm
     * if its heuristic is not admissible.
     */
    protected boolean isAdmissible;
        
    /**
     * Calculate the estimated cost between two nodes.
     * @param n1 
     * @param n2
     * @return 
     */
    public abstract double calculate(N n1,N n2);
    
    public boolean toRecalc() {
        return toRecalc;
    }
    
    public boolean isConsistent() {
        return isConsistent;
    }
    
    public boolean isAdmissible() {
        return isAdmissible;
    }
    
    public void setConsistent(boolean isConsistent) {
        this.isConsistent = isConsistent;
    }
    
    public void setAdmissible(boolean isAdmissible) {
        this.isAdmissible = isAdmissible;
    }
    
    public void setRecalc(boolean toRecalc) {
        this.toRecalc = toRecalc;
    }
}
