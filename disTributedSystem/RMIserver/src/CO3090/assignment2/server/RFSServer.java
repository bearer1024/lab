package CO3090.assignment2.server;

import CO3090.assignment2.*;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//Question (2.3)
public class RFSServer extends UnicastRemoteObject implements RFSInterface {
    final static String fileSystemPath = "./filesystems/";

    Vector<Filesystem> filesystems = new Vector<>();

    public RFSServer() throws RemoteException {
       super();

       Vector<String> fsIndex = FileUtility.readDistributedFilesystemList();

       for (String name : fsIndex) {
           Filesystem fs = new Filesystem(name.split("\\.")[0], FileUtility.readFS(fileSystemPath + name));
//           fs.printFilesystem();
           filesystems.add(fs);
       }
    }

    @Override
    public Vector<String> executeQuery(SearchCriteria criteria) throws RemoteException {
        return criteria.execute(filesystems);
    }

    public static void main(String[] args) throws Exception {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        String name = "rmi://localhost/FileSearch";
        try {
            RFSInterface engine = new RFSServer();

            // complete this method

            Naming.rebind(name, engine);
            System.out.println("FileSearch Service bound");
        } catch (Exception e) {
            System.err.println("FileSearch exception: " + e.getMessage());
            e.printStackTrace();
        }

        /*
        RFSServer server = new RFSServer();
        HashMap<String, Vector<FileItem>> result = server.executeQuery("/");
        for (Map.Entry<String, Vector<FileItem>> filesystem : result.entrySet()) {
            String name = filesystem.getKey();
            Vector<FileItem> files = filesystem.getValue();
            for (FileItem file : files) {
                System.out.println(name + ": " + file);
            }
        }
        */
    }
}

