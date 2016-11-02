import org.jgrapht.graph.DefaultEdge;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import util.DominanceTreeGenerator;
import util.cfg.Graph;
import util.cfg.Node;

import java.io.IOException;
import java.util.Set;

public class ControlDependenceComputation {
    Graph cfg;

    public ControlDependenceComputation(Graph cfg){
        this.cfg = cfg;
    }

    public Graph ComputeControlDenpence(){
        argumentedCfg(cfg);
        try {
            DominanceTreeGenerator dominanceTreeGenerator = new DominanceTreeGenerator(cfg);
            Graph postDominatorTreeGraph = dominanceTreeGenerator.postDominatorTree();


            //compute control dependence graph
            //if(postDominatorTreeGraph.
            Set<Node> nodeSet = cfg.getNodes() ;
            for(Node m: nodeSet){
                for(Node n: nodeSet){
                    //find (branches (a node that number of successor > 1)
                    if(cfg.getSuccessors(n).size()>1 && postDominate(cfg,m,n)){

                    }
                }
            }
            //branch---->go through every nodes in the graph, nodes' number of successors more than 1

        }
        catch (AnalyzerException analyzerException){
            analyzerException.printStackTrace();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public boolean postDominate(Graph graph, Node a,Node b){

        Set<Node> nodeSet = graph.getNodes();
        for(Node node : graph.getPredecessors(a)){
            if((node == b)){
                return true;
            }
            else {
                Set<Node>  
            }
        }
    }

    protected void argumentedCfg(Graph graph){
        //add node to the graph
        Node startNode = new Node("Start");
        Node entryNode = cfg.getEntry();
        Node exitNode = cfg.getExit();

        cfg.addNode(startNode);
        cfg.addEdge(startNode,entryNode);
        cfg.addEdge(startNode,exitNode);

    }



}
