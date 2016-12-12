package CO3090.assignment2.client;
import CO3090.assignment2.*;

import java.rmi.*;
import java.util.HashMap;
import java.util.Vector;

public class RFSClient {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            String name = "rmi://localhost/FileSearch";
            RFSInterface comp = (RFSInterface) Naming.lookup(name);

            //Question (4.1) and (4.2)
            /*complete this method*/

            System.out.println("QueryFileSearch:");
            SearchCriteria criteria = new QueryFileSearch("hello.txt");
            Vector<String> result = comp.executeQuery(criteria);
            for (String line : result) {
                System.out.println(line);
            }

            System.out.println("QueryMaxDepth:");
            criteria = new QueryMaxDepth();
            result = comp.executeQuery(criteria);
            for (String line : result) {
                System.out.println(line);
            }

            System.out.println("QueryTree:");
            criteria = new QueryTree();
            result = comp.executeQuery(criteria);
            for (String line : result) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.err.println("exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
