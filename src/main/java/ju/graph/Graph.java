package ju.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import static ju.snippets.CollectionSnippets.*;
import static ju.snippets.MapSnippets.*;
import ju.tbd.ItemIndexPair;

/**
 * @author Michael Lane <mlane@gatech.edu>
 */
public class Graph<N extends Node, E extends DirectedEdge<N>> 
extends AbstractGraph<N, E> {
    
    
    protected Graph() {
        super();
    }
    
//    private List<Node> expanded;

    /**
     * Two nodes should not be connected with more than one edge. However, if a 
     * DirectedEdge connects node A to B, and another DirectedEdge connects node
     * B to A, this is not considered a violation of this rule. The rule of thumb
     * is: if you are allowed to "walk" along a DirectedEdge in only the direction
     * that it points, and along an UndirectedEdge in either direction, then there 
     * can't be more than one way to "walk" from one node to another along single
     * edges. #irrart
     * 
     * 
     * @param directedEdges null specifies no DirectedEdges
     * @param undirectedEdges null specifies no UndirectedEdges
     */
    public Graph(
    List<? extends E> edges) {
        
        super();
//        prettyPrint("undirectedEdges", undirectedEdges, 1);
        
        if (edges != null) {
            for (E edge : edges) {
                if (edge != null) {
                    asymmetricalAdd(edge.getFromNode(), edge.getToNode(), edge);   
                }
            }
        }
    }
    
//    public Graph(List<? extends DirectedEdge<N>> directedEdges, List<? extends UndirectedEdge> undirectedEdges, 
//    List<Node> isolatedNodes) {
//        
//        this(directedEdges, undirectedEdges);
//        if (isolatedNodes != null) {
//            List<Edge> empty = new ArrayList();
//            for (Node node : isolatedNodes) {
//                if (node != null) {
//                    edgesByNode.put(node, empty);              
//                }
//            }
//        }
//
//    }

    private void asymmetricalAdd(N fromNode, N toNode, E edge) {
        
//        System.out.println("Attempting to add " + nodePair.toString() + " in asymmetricalAdd");
        NodePair<N> nodePair = new NodePair(fromNode, toNode);
        
        // this line is why you shouldn't have two nodes that are connected by both
        // a DirectedEdge and an UndirectedEdge.
        if (edgeByNodePair.get(nodePair) == null) {
            edgeByNodePair.put(nodePair, edge);
        } else {
            throw new IllegalArgumentException("There is more than one way to\"walk\" "
                + "from node " + fromNode.toString() + " to " + toNode.toString()
                + ".");
        }
        
        List<N> neighbors = neighborsByNode.get(fromNode);
        List<E> theseEdges = edgesByNode.get(fromNode);
        if (neighbors != null && !neighbors.contains(toNode)) {
//            System.out.println(toNode.toString() + " added as neighbor of " + fromNode.toString());
            neighbors.add(toNode);
            theseEdges.add(edge);

        } else if (neighbors == null) {
//            System.out.println(toNode.toString() + " added as neighbor of " + fromNode.toString());
            List<N> newNeighbors = new ArrayList();
            newNeighbors.add(toNode);
            neighborsByNode.put(fromNode, newNeighbors);
            List<E> newEdges = new ArrayList();
            newEdges.add(edge);
            edgesByNode.put(fromNode, newEdges);
        }
    }

    

    

    /**
     * This is a graph-search based implementation; i.e. it maintains a "visited"
     * list. #irrart
     * 
     * @param start non-null
     * @param end   non-null
     * @return A path from start to end. The first node in the list is the first
     * node to visit from start; the last node is end. null if start and end are
     * disconnected in the graph.
     */
    public List<E> bfs(N start, N end) {
        
        if (start == null || end == null) {
            return null;
        }

        // edge case
        if (start.equals(end)) {
            return new ArrayList();
        }
        Set<N> visited = new HashSet();
        visited.add(start);
//        System.out.println("Neighbors of start: ");
//        for (Node neighbor : this.getNeighbors(start)) {
//            System.out.println("\t" + ((StarSystem) neighbor).getName());
//        }
        Map<N, List<E>> pathByNode = new HashMap();
        // init pathByNode
        List<N> neighbors = this.getNeighbors(start);
        for (N neighbor : neighbors) {
            E edge = this.getEdge(start, neighbor);
            List<E> path = new ArrayList();
            path.add(edge);
            pathByNode.put(neighbor, path);
        }

        List<N> frontier = new LinkedList(neighbors);
        while (!frontier.isEmpty()) {
//            System.out.println("Frontier:");
            // int i = 0;
//            for (Node node : frontier) {
//                System.out.println("\tIndex " + i++ + ": " + ((StarSystem) node).getName());
//            }
            N node = frontier.remove(0);
//            System.out.println("Popped StarSystem: " + ((StarSystem) node).getName());
            if (!visited.contains(node)) {
//                System.out.println("Neighbors of popped StarSystem: ");
                // goal test
                if (node.equals(end)) {
//                    System.out.println("RETURNED\n\n");
                    return pathByNode.get(node);
                }
                visited.add(node);
                neighbors = this.getNeighbors(node);
//                for (Node aNode : neighbors) {
//                    System.out.println("\t" + ((StarSystem) aNode).getName());
//                }
                // update frontier
                frontier.addAll(neighbors);

                // update neighbor paths
                List<E> path = pathByNode.get(node);
                for (N neighbor : neighbors) {
                    if (pathByNode.get(neighbor) == null) {
                        List<E> newPath = new ArrayList(path);
                        E edge = this.getEdge(node, neighbor);
                        newPath.add(edge);
                        pathByNode.put(neighbor, newPath);
                    }
                }
            }
        }

        // iff start and end are disconnected
//        System.out.println("RETURNED\n\n");
        return null;
    }

    /**
     * This graph search algorithm should be used on graphs that are a priori known
     * to be trees. Assuming that the search space is finite, this is optimal no 
     * matter the behavior of the heuristic. However, if the heuristic is particularly
     * stupid, this may run longer than comparable graph search algorithms, like BFS.
     * 
     * If the graph is a tree, then it runs in less time than aStarGraphSearch because
     * it doesn't need to keep a list of visited nodes.
     * 
     * @param start non-null
     * @param end   non-null
     * @param heur  non-null
     * @return A path from start to end. The first node in the list is the first
     * node to visit from start; the last node is end. null if start and end are
     * disconnected in the graph.
     */
    public List<E> aStarTreeSearch(N start, N end, Heuristic<N> heur) {

        if (start == null || end == null || heur == null) return null;
        
        if (start.equals(end)) {
            return new LinkedList();
        }
        
        // linked list because we're only removing the first element and adding
        // via insertion sort. with an arraylist implementation, the object
        // would have to be copied over and over again
        List<Action<N, E>> frontier = new LinkedList();
        Map<Action<N, E>, List<E>> pathByAction = new HashMap();
        Map<N, Double> hByNode = new HashMap(); // not actually used
        Map<Action<N, E>, Double> costByAction = new HashMap();

        initAStarData(start, end, heur, frontier, hByNode, pathByAction, costByAction);

        // Something to keep in mind: in this implementation, all nodes are only peeked at a 
        // single time. This means that pathByNode and costByNode will be 
        // put to, but their values will never be updated.

        while (!frontier.isEmpty()) {
            Action<N, E> action = frontier.remove(0);
            N node = action.state;
            // goal test
            if (node.equals(end)) {
                return pathByAction.get(action);
            }
            List<E> path = pathByAction.get(action);
            // add neighbors to frontier
            List<N> neighbors = this.getNeighbors(node);
            double cost = costByAction.get(action);
            for (N neighbor : neighbors) {
                Action<N, E> next = new Action(neighbor, this.getEdge(node, neighbor));
            	// the only thing we need to check for optimality is that
            	// neighbor hasn't already been added to the frontier
            	if (pathByAction.get(next) == null) {
	                // create path
	                List<E> newPath = new LinkedList(path);
	                newPath.add(next.edge);
	                double w = this.getEdge(node, neighbor).getWeight();
	                double h = heur.calculate(neighbor, end);
	                costByAction.put(next, cost + w);
	                pathByAction.put(next, newPath);
	                // insert neighbor into frontier
	                insertIntoFrontier(next, frontier, costByAction, hByNode, heur, end);        		
            	}
            }
        }
        return null;
    }

    /**
     * This graph search algorithm should be used on graphs that are a priori not known
     * to be trees. Assuming that the search space is finite, this is optimal no 
     * matter the behavior of the heuristic. However, if the heuristic is particularly
     * stupid, this may run longer than comparable graph search algorithms, like BFS.
     * 
     * @param start non-null
     * @param end non-null
     * @param heur non-null
     * @return A path from start to end. The first node in the list is the first
     * node to visit from start; the last node is end. null if start and end are
     * disconnected in the graph.
     */
    public List<E> aStarGraphSearch(N start, N end, Heuristic<N> heur) {
        
//        for (Node node : this.getNodes()) {
//            prettyPrint("Neighbors of " + node.toString(), this.getNeighbors(node), 1);
//        }
        
        if (start == null || end == null || heur == null) return null;
        
        if (start.equals(end)) {
            return new LinkedList();
        }
        
        List<Action<N, E>> frontier = new LinkedList();
//        System.out.println("Expanded " + ((StarSystem) start).getName());
        // the path with least total cost from start to node
        Map<Action<N, E>, List<E>> pathByAction = new HashMap();
        Map<N, Double> hByNode = new HashMap();
        // the cost along the path will least total cost from start to node 
        Map<Action<N, E>, Double> costByAction = new HashMap();
        Set<N> visited = new HashSet();
        visited.add(start);
        
        initAStarData(start, end, heur, frontier, hByNode, pathByAction, costByAction);
        
//        prettyPrintMapSubset("Frontier after initialization/sorting:", frontier, sumMaps(hByNode, costByNode));
        
        while(!frontier.isEmpty()) {
            
//            prettyPrint("Visited:", visited, 1);
//            prettyPrint("Frontier:", frontier, 1);
            
//            prettyPrintMapSubset("Frontier:", frontier, sumMaps(hByNode, costByNode));
            Action<N, E> action = frontier.remove(0);
            if (!visited.contains(action.state)) {
                
                N node = action.state;
                
//                System.out.println(node.toString() + " => " + (heur.calculate(node, end) + costByAction.get(action)));
                // goal test
                if (node.equals(end)) {
                    return new ArrayList(pathByAction.get(action));
                }
                visited.add(node);
                double cost = costByAction.get(action);
                
//                prettyPrint("neighbors", this.getNeighbors(node), 1);

                // peek at neighbors
//                System.out.println("neighbors: " + this.getNeighbors(node));
                for (N neighbor : this.getNeighbors(node)) {
                	if (!visited.contains(neighbor)) {
                		Double newH = null;
                        boolean toRecalc = heur.toRecalc();
                		if (toRecalc || (newH = hByNode.get(neighbor)) == null) {
                            newH = heur.calculate(neighbor, end);
                            if (!toRecalc)  {
                                hByNode.put(neighbor, newH);
                            }
                        }
                        
                        E edge = this.getEdge(node, neighbor);
                        Action<N, E> next = new Action(neighbor, edge);
            			double newCost = cost + edge.getWeight();
            			double newF = newCost + newH;
		                List<E> newPath = new ArrayList(pathByAction.get(action));
		                newPath.add(edge);
        				pathByAction.put(next, newPath);
        				costByAction.put(next, newCost);
//                        prettyPrint("hByNode", hByNode, 1);
//                        prettyPrint("costByNode", costByNode, 1);
        				insertIntoFrontier(next, frontier, costByAction, hByNode, heur, end);
                	}
                }
            }
                
        }
        return null;
        
    }	

    private void insertIntoFrontier(Action<N, E> next, List<Action<N, E>> frontier,
    Map<Action<N, E>, Double> costByAction, Map<N, Double> hByNode, Heuristic<N> heur, N end) {
        
//        System.out.println("Adding neighbor " + neighbor.toString() + 
//            " to frontier.");
//        prettyPrintMapSubset("Frontier:", frontier, sumMaps(hByNode, costByNode));
//        prettyPrint("hByNode: ", hByNode);
//        prettyPrint("costByNode:", costByNode);
        
        ListIterator<Action<N, E>> iter = frontier.listIterator();
        N neighbor = next.state;
        
        double h;
        if (heur.toRecalc()) {
            h = heur.calculate(neighbor, end);
        } else h = hByNode.get(neighbor);
        double f = costByAction.get(next) + h;
        boolean proceed = true;
//        for (int i = 0; i < frontier.size(); i++) {
//            Node n1 = frontier.get(i);
//            if (n1 == null) System.out.println("null");
//            else System.out.println(n1);
//        }
        while (iter.hasNext() && proceed) {
            Action<N, E> action = iter.next();
//            System.out.println("iterated");
            if (action.state != null) {
                if (heur.toRecalc()) {
                    h = heur.calculate(action.state, end);
                } else h = hByNode.get(action.state);
//                System.out.println("attempting to access costByNode.get(" + n.toString() + ")");
//                System.out.println("null: " + costByNode.get(n) == null);
//                System.out.println("node " + n.toString() + " has f-value " +
//                    (costByNode.get(n) + h));
                if (f < costByAction.get(action) + h) {
                    iter.previous(); // move cursor back
                    iter.add(next);
                    proceed = false;
                }
            } else {
//                System.out.println("but found a null?");
            }
        }
        // if we reached the end of the list without adding
        if (proceed) {
            // cursor will be at the end
            
            iter.add(next);
        }
        
//        System.out.println("added " + neighbor.toString() + " to frontier at position "
//            + (iter.nextIndex() - 1));
    }
    
    
    
    
    
//    public Edge<N> getMinimalEdge(N node) {
//        List<Edge<N>> someEdges = this.getEdges(node);
//        Edge<N> minEdge = someEdges.get(0);
//        double minWeight = minEdge.getWeight();
//        for (Edge<N> edge : someEdges) {
//            double weight = edge.getWeight();
//            if (weight < minWeight) {
//                minEdge = edge;
//                minWeight = weight;
//            }
//        }
//        return minEdge;
//    }

    
    
    private void initAStarData(
        N start, N end, Heuristic<N> heur, List<Action<N, E>> frontier,
        Map<N, Double> hByNode, 
        Map<Action<N, E>, List<E>> pathByAction, Map<Action<N, E>, Double> costByAction) {

//        System.out.println("end position: " + ((Positionable) end).getPosition());
        
        for (N neighbor : this.getNeighbors(start)) {
//            System.out.println(neighbor.toString() + " position: " + 
//                ((Positionable) neighbor).getPosition().toString());
            
            E edge = this.getEdge(start, neighbor);
            Action<N, E> action = new Action(neighbor, edge);
            frontier.add(action);
            double w = edge.getWeight();
            double h = heur.calculate(neighbor, end);
            costByAction.put(action, w);            
            hByNode.put(neighbor, h);
            List<E> path = new LinkedList();
            path.add(edge);
            pathByAction.put(action, path);
//            System.out.println(neighbor.toString() + " h = " + h);
//            System.out.println(neighbor.toString() + " w = " + w);
//            System.out.println(neighbor.toString() + " f = " + (w + h));
        }

        // sort the initialized frontier
        Collections.sort(frontier, (a1, a2) -> {
            double f1 = hByNode.get(a1.state) + costByAction.get(a1);
            double f2 = hByNode.get(a2.state) + costByAction.get(a2) ;
            if (f1 > f2) return 1;
            if (f1 < f2) return -1;
            return 0;
        });
    }
    
    public Map<N, List<E>> dijkstra(N start) {
        
        Map<N, Double> costByNode = new HashMap();
        List<N> neighbors = this.getNeighbors(start);
        List<N> frontier = new LinkedList(neighbors);
        Map<N, List<E>> pathByNode = new HashMap();
        pathByNode.put(start, new LinkedList());
        // init costByNode
        for (N neighbor : neighbors) {
            E edge = this.getEdge(start, neighbor);
            double weight = edge.getWeight();
            costByNode.put(neighbor, weight);
            List<E> path = new LinkedList();
            path.add(edge);
            pathByNode.put(neighbor, path);
        }
        
        costByNode.put(start, 0.0);
        double min = 0;
        while (!frontier.isEmpty()) {
            ItemIndexPair<N> minPair = getMinUsingValuesInMap(frontier, costByNode);
            frontier.remove(minPair.index);
            N node = minPair.item;
            
            // if node has not yet been visited
            if (costByNode.get(node) >= min) {
                min = costByNode.get(node);
                neighbors = this.getNeighbors(node);
                for (N neighbor : neighbors) {
                    dijkstraUpdate(node, neighbor, costByNode, pathByNode);
                }
            }
        }
        
        return pathByNode;
    }
    
    private void dijkstraUpdate(
    N node, N neighbor, Map<N, Double> costByNode, Map<N, List<E>> pathByNode) {
        
        E edge = this.getEdge(node, neighbor);
        double weight = edge.getWeight();
        Double nodeCost = costByNode.get(node);
        Double neighborCost = costByNode.get(neighbor);
        double newCost = nodeCost + weight;
        if (neighborCost == null || newCost < neighborCost){
            List<E> path = new LinkedList(pathByNode.get(node));
            path.add(edge);
            costByNode.put(neighbor, newCost);
            pathByNode.put(neighbor, path);
        }
    }
    
    public List<E> dfs(N start, N end) {

        if (start.equals(end)) {
            return new ArrayList();
        }
        
        Deque<Integer> branchingFactors = new LinkedList();
        Set<N> visited = new HashSet();
        visited.add(start);
        List<N> neighbors = this.getNeighbors(start);
        Deque<E> path = new LinkedList(); // path is returned, so it must implement List!
        Deque<N> frontier = new LinkedList(neighbors);
        N prevNode = start;
        
        // an element of branchingFactors is the number
        // of branchingFactors of the respective edge in
        // path, the toNode
        
        while (!frontier.isEmpty()) {
            
            N node = frontier.pop();
            if (!visited.contains(node)) {
                if (node.equals(end)) {
                    return (List<E>) path;
                }
                visited.add(node);
//                E edge = this.getEdge(prevNode, node);
                boolean proceed = true;
                while (!path.isEmpty() && proceed) {
                    int bf = branchingFactors.peek();
                    if (bf == 0) {
                        prevNode = path.pop().getFromNode();
                        branchingFactors.pop();
                    } else {
                        proceed = false;
                    }
                }
                
                E edge = this.getEdge(prevNode, node);                
                int numUnvisited = 0;
                neighbors = this.getNeighbors(node);
                for (N neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        numUnvisited++;
                        frontier.push(neighbor);
                    }
                }
                path.push(edge);
                branchingFactors.push(numUnvisited);
                
                prevNode = node;
            }
        }
        
        return new LinkedList();
    }
    
    public Tree<N, E> getMST() {
        return null;
        // use fibonacci heap and adjacency list implementation
    }
    
//    public List<Node> getExpanded() {
//        return expanded;
//    }
    
    
    
//    /**
//     * There was no other good place to put this method, so here it is.
//     * 
//     * @param edge non-null
//     * @param node non-null
//     * @return The node in given Edge that isn't the given Node; null if the Node
//     *         isn't along the Edge.
//     */
//    public static Node getOtherNode(Edge edge, Node node) {
//        
//        if (edge == null || node == null) return null;
//        
//        if (edge instanceof DirectedEdge) {
//            DirectedEdge dirEdge = (DirectedEdge) edge;
//            if (dirEdge.getFromNode().equals(node)) {
//                return dirEdge.getToNode();
//            } else if (dirEdge.getToNode().equals(node)) {
//                return dirEdge.getFromNode();
//            } else return null;
//            
//        } else if (edge instanceof UndirectedEdge) {
//            UndirectedEdge undirEdge = (UndirectedEdge) edge;
//            if (undirEdge.getNode1().equals(node)) {
//                return undirEdge.getNode2();
//            } else if (undirEdge.getNode2().equals(node)) {
//                return undirEdge.getNode1();
//            } else return null;
//        }
//        
//        return null;
//    }

    @Override
    public void addEdge(E edge) {
        this.edgeByNodePair.put(new NodePair(edge.getFromNode(), edge.getToNode()), edge);
        addToCollMap(this.edgesByNode, edge.getFromNode(), edge);
        addToCollMap(this.neighborsByNode, edge.getFromNode(), edge.getToNode());
    }
}

