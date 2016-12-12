package CO3090.assignment2.client;

import java.util.Stack;
import java.util.Vector;

import CO3090.assignment2.FileItemType;
import CO3090.assignment2.Filesystem;
import CO3090.assignment2.SearchCriteria;

//Question 3.2

/*
 *
 * QueryMaxDepth should return the maximum
 * directory depth on all file servers.
 *
 * For example, given the directory structure in
 * RemoteFilesystem1.txt and RemoteFilesystem2.txt.
 * RFSServer should return:
 *
 * {RemoteFilesystem2: maxDepth=5}
 * {RemoteFilesystem1: maxDepth=4}
 *
 *
 */

public class QueryMaxDepth implements SearchCriteria{

    private int maxDepth = 0;

    @Override
    public Vector<String> execute(Vector<Filesystem> filesystems) {
        Vector<String> result = new Vector<>();

        for (Filesystem filesystem : filesystems) {
            getMaxDepth(filesystem.getRoot(), 1);
            result.add("{" + filesystem.getName() + ": maxDepth=" + maxDepth + "}");
        }

        return result;
    }

    private void getMaxDepth(Filesystem.Node node, Integer currentDepth) {
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        FileItemType type = node.getFile().getFileType();
        if (type == FileItemType.DIR) {
            for (Filesystem.Node subNode : node.list()) {
                getMaxDepth(subNode, currentDepth + 1);
            }
        }
    }
}
