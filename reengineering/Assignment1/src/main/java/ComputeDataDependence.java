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
import java.util.Set;

/**
 */
public class ComputeDataDependence {
    ClassNode targetClassNode;
    MethodNode targetMethod;
    Graph graph;

    public ComputeDataDependence(ClassNode classNode, MethodNode methodNode)throws AnalyzerException{
        targetClassNode = classNode;
        targetMethod = methodNode;
        graph = CFGExtractor.getCFG(classNode.name,methodNode);
    }

    public Graph ComputeDataDependence(AbstractInsnNode a, AbstractInsnNode b) throws AnalyzerException{
        AssignmentSubmission assignmentSubmission = new AssignmentSubmission(targetClassNode.name,targetMethod.name);
        InsnList insnListSet = assignmentSubmission.targetMethod.instructions;

        Collection<Variable> usedVariables = DataFlowAnalysis.usedBy(targetClassNode.name,targetMethod,a);
        Collection<Variable> definedvariables = DataFlowAnalysis.definedBy(targetClassNode.name,targetMethod,b);
        graph.getNodes();
        for(Variable variable : definedvariables){
            Node nodeA = new Node(a);
            Node nodeB = new Node(b);
            if(usedVariables.contains(variable)){
                graph.addNode(nodeA);
                graph.addNode(nodeB);
                graph.addEdge(nodeB,nodeA);
            }
        }

       System.out.println(graph.toString());
        return graph;
    }

    public Graph buildGraph(ClassNode classNode, MethodNode methodNode){
        return graph;
    }
}
