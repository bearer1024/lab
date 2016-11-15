import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;

import util.cfg.CFGExtractor;
import util.cfg.Graph;
import util.cfg.Node;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This should be the entry-point of your programming submission.
 *
 * You may add as many of your own classes and packages as you like to this project structure.
 *
 */
public class AssignmentSubmission implements Slicer {

	ClassNode targetClassNode;
	MethodNode targetMethod;
	Graph cfg;
	
	public AssignmentSubmission(String targetClass, String methodSignature){
		targetClassNode = findClassNode(targetClass);
		targetMethod = findMethodNode(targetClassNode, methodSignature);
		try{
			cfg = CFGExtractor.getCFG(targetClassNode.name, targetMethod);
		} catch (AnalyzerException e) {
			// Failed to extract CFG
			e.printStackTrace();
		}
	}
	
	public static ClassNode findClassNode(String targetClass){
		ClassNode target = new ClassNode(Opcodes.ASM4);
        InputStream in=CFGExtractor.class.getResourceAsStream(targetClass);
        ClassReader classReader;
		try {
			classReader = new ClassReader(in);
			classReader.accept(target, 0);
		} catch (IOException e1) {
			// Fail to read class file.
			e1.printStackTrace();
		} 
		return target;
	}
	
	/**
	 * Find the method with the given methodSignature, belonging to the targetClass.
	 * @param targetClass
	 * @param methodSignature
	 * @return
	 */
	public static MethodNode findMethodNode(ClassNode targetClass, String methodSignature){
		for(MethodNode mn : (List<MethodNode>)targetClass.methods){
	        	String signature = mn.name+mn.desc;
	        	if(!signature.equals(methodSignature))
	        		continue;
	        	else
	        		return mn;
		}
		return null; //Method signature not found.
	}

    /**
     * Returns true if there is a data dependence relation from a to b.
     *
     * In other words, a variable is assigned a value at a, and that value is
     * subsequently used at b, without any intervening definitions of that variable.
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean isDataDepence(AbstractInsnNode a, AbstractInsnNode b) {
		try{
			//generate data dependence graph
			DataDependenceComputation dataDependenceComputation = new DataDependenceComputation(targetClassNode,targetMethod);
			Graph ddg = dataDependenceComputation.buildGraph();
			Node na= new Node(a);
			Node nb= new Node(b);
			//get all nodes from data dependence graph
			Set<Node> nodes= ddg.getSuccessors(nb);
			//Judge if na and nb has an edge in data dependence graph
			if(nodes.contains(na)){
				Set<Node> nodeSet = ddg.getPredecessors(na);
				if(nodeSet.contains(nb)){
					return true;
				}
			}
		}
		catch (AnalyzerException e){
			e.printStackTrace();
		}
		return false;
    }

    /**
     * Returns true if a is dependent upon b and false otherwise.
     *
     * In other words, returns true if b represents a conditional instruction that
     * determines whether or not b will execute (following the definition of control
     * dependence discussed in lectures).
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean isControlDependentUpon(AbstractInsnNode a, AbstractInsnNode b)  {

		//use ControlDependenceComputation class to judge control dependence
		ControlDependenceComputation controlDependenceComputation= new ControlDependenceComputation(cfg);
		//generate control dependence graph
		Graph cdg = controlDependenceComputation.ComputeControlDenpence();
		Node na= new Node(a);
		Node nb = new Node(b);
		//get all successors of node nb
		Set<Node> nodeSet = cdg.getSuccessors(nb);
		//to judge whether it has an edge in graph or not
		if(nodeSet.contains(na))
			return true;
		else return false;
    }


    /**
     * Should return a backward slice on the criterion statement (for all variables).
     * @param criterion
     * @return
     */
    @Override
    public List<AbstractInsnNode> backwardSlice(AbstractInsnNode criterion) {

		//get control dependence graph
		ControlDependenceComputation controlDependenceComputation= new ControlDependenceComputation(cfg);
		Graph cdg = controlDependenceComputation.ComputeControlDenpence();
		try {
			DataDependenceComputation dataDependenceComputation = new DataDependenceComputation(targetClassNode,targetMethod);
			Graph ddg = dataDependenceComputation.buildGraph();

            //merge nodes form control dependence graph and data dependence graph
            Set<Node> nodesFromCdg = cdg.getNodes();
			Set<Node> nodesFromDdg = ddg.getNodes();
			Set<Node> nodesToBuildPDG = Collections.emptySet();
			nodesToBuildPDG.addAll(nodesFromCdg);
			nodesToBuildPDG.addAll(nodesFromDdg);
			Graph programGraph = new Graph();
			for(Iterator<Node> iterator = nodesToBuildPDG.iterator();iterator.hasNext();){
				programGraph.addNode(iterator.next());
			}
			System.out.println("-----------------------Program dependence graph------------------");
			System.out.println(programGraph.toString());


		}
		catch (AnalyzerException e){
			e.printStackTrace();
		}


        return null;
    }
}
