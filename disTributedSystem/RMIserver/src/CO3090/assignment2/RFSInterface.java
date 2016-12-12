package CO3090.assignment2;

import java.util.Vector;

public interface RFSInterface extends java.rmi.Remote {
    Vector<String> executeQuery(SearchCriteria criteria) throws java.rmi.RemoteException;
}
