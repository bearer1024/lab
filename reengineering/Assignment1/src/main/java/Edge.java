import util.cfg.Node;

/**
 * used to store edge of graphs
 */
public class Edge {
    Node firstNode;
    Node secondNode;

    public Edge(Node a,Node b){
        firstNode = a;
        secondNode = b;
    }
}
