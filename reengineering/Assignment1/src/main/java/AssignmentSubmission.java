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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
			ComputeDataDependence computeDataDependence = new ComputeDataDependence(targetClassNode,targetMethod);
			Graph ddg = computeDataDependence.ComputeDataDependence(a,b);
			Node nodeA = new Node(a);
			Node nodeB = new Node(b);
			Set<Node> nodeSet = ddg.getSuccessors(nodeB);
			if(nodeSet.contains(nodeA))	return true;
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


		ComputeControlDepence computeControlDepence = new ComputeControlDepence(cfg);
		Graph cdg = computeControlDepence.ComputeControlDenpence();
		Node nodeA = new Node(a);
		Node nodeB = new Node(b);
		Set<Node> nodeSet = cdg.getSuccessors(nodeB);
		if(nodeSet.contains(nodeA))	return true;
		else return false;
    }


    /**
     * Should return a backward slice on the criterion statement (for all variables).
     * @param criterion
     * @return
     */
    @Override
    public List<AbstractInsnNode> backwardSlice(AbstractInsnNode criterion) {

        Graph graph = new Graph();
		ComputeControlDepence computeControlDepence = new ComputeControlDepence(cfg);
		Graph cdg = computeControlDepence.ComputeControlDenpence();
		try {
			ComputeDataDependence computeDataDependence = new ComputeDataDependence(targetClassNode,targetMethod);
			Graph ddg = computeDataDependence.buildGraph(targetClassNode,targetMethod);

            //add nodes form control dependence graph and data dependence graph
            Set<Node> nodesInCdg = cdg.getNodes();
			Set<Node> nodesInDdg = ddg.getNodes();
            nodesInCdg.addAll(nodesInDdg);

			Graph programGraph = new Graph();
			for(Iterator<Node> iterator = nodesInCdg.iterator();iterator.hasNext();){
				programGraph.addNode(iterator.next());
			}

			System.out.println("program dependence graph is:"+programGraph.toString());

			//add edges

		}
		catch (AnalyzerException e){
			e.printStackTrace();
		}


        return null;
    }
}
