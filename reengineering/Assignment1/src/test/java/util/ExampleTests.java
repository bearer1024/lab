package util;

import br.usp.each.saeg.asm.defuse.Variable;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import util.cfg.CFGExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * These "tests" are simply to illustrate:
 *
 * (1) How JUnit tests can be used to execute aspects of your work.
 *
 * (2) To illustrate how the different CFG generation, dominance tree,
 * and data dependence functionalities are invoked.
 *
 * Created by neilwalkinshaw on 21/10/2016.
 */
public class ExampleTests {

    String subject = "/java/lang/String.class"; //Class we want to analyse

    @Test
    public void computeAndPrintCFGs() throws IOException {
        ClassNode cn = new ClassNode(Opcodes.ASM4);
        InputStream in=CFGExtractor.class.getResourceAsStream(subject);
        ClassReader classReader=new ClassReader(in);
        classReader.accept(cn, 0);
        for(MethodNode mn : cn.methods){
            try {
                System.out.println("================CFG FOR: "+cn.name+"."+mn.name+mn.desc+" =================");
                System.out.println(CFGExtractor.getCFG(cn.name, mn));
            } catch (AnalyzerException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void computeAndPrintDominanceTrees() throws IOException {
        ClassNode cn = new ClassNode(Opcodes.ASM4);
        InputStream in=CFGExtractor.class.getResourceAsStream(subject);
        ClassReader classReader=new ClassReader(in);
        classReader.accept(cn, 0);
        for(MethodNode mn : cn.methods){
            try {
                DominanceTreeGenerator dtg = new DominanceTreeGenerator(subject,mn);
                System.out.println("================Post-dominance tree FOR: "+cn.name+"."+mn.name+mn.desc+" =================");
                System.out.println(dtg.dominatorTree()); //can make a similar call to obtain post-dominator tree.
            } catch (AnalyzerException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void computeAndPrintNumberOfVariableUsesPerInstruction()throws IOException {
        ClassNode cn = new ClassNode(Opcodes.ASM4);
        InputStream in=CFGExtractor.class.getResourceAsStream(subject);
        ClassReader classReader=new ClassReader(in);
        classReader.accept(cn, 0);
        for(MethodNode mn : cn.methods){
            System.out.println(mn.name+":");
            for(int i = 0; i< mn.instructions.size(); i++) {
                try {
                    Collection<Variable> used = DataFlowAnalysis.usedBy(subject,mn,mn.instructions.get(i));
                    System.out.println(mn.instructions.get(i)+": "+used.size());
                } catch (AnalyzerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}