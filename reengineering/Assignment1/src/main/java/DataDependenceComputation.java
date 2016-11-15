import br.usp.each.saeg.asm.defuse.Variable;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import util.DataFlowAnalysis;
import util.cfg.CFGExtractor;
import util.cfg.Graph;
import util.cfg.Node;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 */
public class DataDependenceComputation {
    ClassNode targetClassNode;
    MethodNode targetMethod;
    Graph graph;

    public DataDependenceComputation(ClassNode classNode, MethodNode methodNode)throws AnalyzerException{
        targetClassNode = classNode;
        targetMethod = methodNode;
        graph = CFGExtractor.getCFG(classNode.name,methodNode);
    }

    public Graph buildGraph(){
        Set<Node> nodeSet = graph.getNodes();
        Set<Node> nodesToBuildDDF = Collections.emptySet();

        for(Node a : nodeSet){
            for(Node b: nodeSet){
                if(graph.isDecisionEdge(a,b)){
                    nodesToBuildDDF.add(b);
                    nodesToBuildDDF.add(a);
                    graph.addEdge(a,b);
                }
            }
        }
        System.out.println("---------------------Data Dependence Tree --------------------");
        System.out.println(graph.toString());
        return graph;
    }
}
