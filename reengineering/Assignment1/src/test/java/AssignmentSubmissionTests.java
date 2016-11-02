

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class AssignmentSubmissionTests {

	AssignmentSubmission testSubmission;
	ClassNode targetClass;
	MethodNode targetMethod; 
	
	@Before
	public void setUp() throws Exception {
		testSubmission = new AssignmentSubmission("/java/lang/String.class","toCharArray()[C");
		targetClass = AssignmentSubmission.findClassNode("/java/lang/String.class");
		targetMethod = AssignmentSubmission.findMethodNode(targetClass, "toCharArray()[C");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testControlDependence() {
		System.out.println(targetMethod.instructions.size());
	}

}
