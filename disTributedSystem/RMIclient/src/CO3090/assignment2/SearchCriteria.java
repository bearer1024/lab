package CO3090.assignment2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public interface SearchCriteria extends Serializable {
     Vector<String> execute(Vector<Filesystem> filesystems);
}
