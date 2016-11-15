import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import util.DominanceTreeGenerator;
import util.cfg.Graph;
import util.cfg.Node;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
    *this class include method to produce control dependence graph
 */
public class ControlDependenceComputation{

    public class Edge {
        Node nodeA;
        Node nodeB;

        public Edge(Node a,Node b){
            nodeA= a;
            nodeB= b;
        }
    }

    Graph cfg;

    public ControlDependenceComputation(Graph cfg){
        this.cfg = cfg;
    }

    //produce augmented CFG
    protected void augmentedCfg(Graph graph){
        //add node to the graph
        Node startNode = new Node("Start");
        Node entryNode = graph.getEntry();
        Node exitNode = graph.getExit();

        graph.addNode(startNode);
        graph.addEdge(startNode,entryNode);
        graph.addEdge(startNode,exitNode);

    }

    //check a's ancestor is b or not
    public boolean leastCommonAncestorisB(Node a,Node b,Graph graph){
        for(Node node: graph.getPredecessors(a)){
            if(node == b)
                return true;
            else {
                Set<Node> predecessors = graph.getPredecessors(node);
                if(!predecessors.isEmpty())
                    return leastCommonAncestorisB(node,b,graph);
            }
        }
        return false;
    }

    public Graph ComputeControlDenpence(){
        augmentedCfg(cfg);
        try {
            DominanceTreeGenerator dominanceTreeGenerator = new DominanceTreeGenerator(cfg);
            Graph postDominatorTree = dominanceTreeGenerator.postDominatorTree();


            //1.3 compute control dependence from  postdominatorTree
            //select branches in graph
            Set<Node> nodes= cfg.getNodes();
            Set<Edge> edges = new HashSet<>();

            //select b is not postDominated by node a
            for(Node node: nodes){
                Set<Node> childNodes= cfg.getSuccessors(node);

                //if size>2 , it is branch node
                if(childNodes.size() >= 2){
                    //use Iterator.remove to avoid concurrentModificationException
                    Iterator<Node> iterator = childNodes.iterator();
                    while(iterator.hasNext()){
                        Edge edge = new Edge(node,iterator.next());
                        edges.add(edge);
                        iterator.remove();
                    }

                    for( Edge edge : edges){
                        if(leastCommonAncestorisB(edge.nodeA,edge.nodeB,cfg)) {
                            edges.remove(edge);
                        }
                    }

                }
            }

            if(!edges.isEmpty()){
                for(Iterator<Edge> iterator = edges.iterator(); iterator.hasNext();) {
                    Edge edge = iterator.next();
                        postDominatorTree.addEdge(edge.nodeA, edge.nodeB);
                }
            }

            System.out.println("-----------------Control dependence tree -------------------------");
            System.out.println(postDominatorTree);

            return postDominatorTree;

        }
        catch (AnalyzerException analyzerException){
            analyzerException.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }


}
