import org.objectweb.asm.tree.analysis.AnalyzerException;
import util.DominanceTreeGenerator;
import util.cfg.Graph;
import util.cfg.Node;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * used to compute control dependence
 * it will return a control dependence graph
 */
public class ComputeControlDepence {
    Graph cfg;

    public ComputeControlDepence(Graph cfg){
        this.cfg = cfg;
    }

    public Graph ComputeControlDenpence(){
        argumentCfg(cfg);
        try {
            DominanceTreeGenerator dominanceTreeGenerator = new DominanceTreeGenerator(cfg);
            Graph postDominatorTreeGraph = dominanceTreeGenerator.postDominatorTree();
            //select branches in graph
            Set<Node> nodeSet = cfg.getNodes();
            Set<Edge> edges = new HashSet<>();

            //select b is not postDominated by node a
            for(Node node: nodeSet){
                Set<Node> successors = cfg.getSuccessors(node);


                //successor node
                if(successors.size() >= 2){
                    for(Iterator<Node> iterator = successors.iterator();iterator.hasNext();){
                        Edge edge = new Edge(node,iterator.next());
                        edges.add(edge);
                        iterator.remove();
                    }

                    for( Edge edge : edges){
                        if(hasAncestorFromSameNodes(edge.firstNode,edge.secondNode,cfg)) {
                            edges.remove(edge);
                        }
                    }

                }
            }

        if(!edges.isEmpty()){
            for(Iterator<Edge> iterator = edges.iterator(); iterator.hasNext();){
                Edge edge = iterator.next();
//                Node node = postDominatorTreeGraph.getLeastCommonAncestor(edge.secondNode,edge.firstNode);
//
//                if( node != edge.firstNode){
//                    iterator.remove();
//                }
                postDominatorTreeGraph.addEdge(edge.firstNode,edge.secondNode);
            }
        }

           System.out.println("Data dependence tree is:"+postDominatorTreeGraph);

           return postDominatorTreeGraph;

        }
        catch (AnalyzerException analyzerException){
            analyzerException.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }

    //check a's ancestor is b or not
    public boolean hasAncestorFromSameNodes(Node a,Node b,Graph graph){
        for(Node node: graph.getPredecessors(a)){
            if(node == b)
                return true;
            else {
                Set<Node> predecessors = graph.getPredecessors(node);
                if(!predecessors.isEmpty())
                return hasAncestorFromSameNodes(node,b,graph);
            }
        }
        return false;
    }

    protected void argumentCfg(Graph graph){
        //add node to the graph
        Node startNode = new Node("Start");
        Node entryNode = graph.getEntry();
        Node exitNode = graph.getExit();

        graph.addNode(startNode);
        graph.addEdge(startNode,entryNode);
        graph.addEdge(startNode,exitNode);

    }

}
