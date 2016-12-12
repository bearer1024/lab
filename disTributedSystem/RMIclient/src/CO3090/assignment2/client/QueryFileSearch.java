package CO3090.assignment2.client;

import java.util.*;

import CO3090.assignment2.FileItemType;
import CO3090.assignment2.Filesystem;
import CO3090.assignment2.SearchCriteria;

//Question 3.1

/*
 *
 *  Given a file, QueryFileSerach should return the
 *  full paths (absolute path to your root directory)
 *  of the given file, if it exists on one of the file
 *  servers. If more than one file with the given name
 *  is found then the result should include all paths.
 *
 *  For example:
 *
 *  Given the directory structure in
 *  RemoteFilesystem1.txt and RemoteFilesystem2.txt.
 *  (inside filesystems folder on the RMI server)
 *  When searching for “hello.txt”, RFSServer should
 *  return:
 *
 *  RemoteFilesystem1://A/C/E/hello.txt
 *  RemoteFilesystem2://A/B/hello.txt
 *  RemoteFilesystem2://A/C/E/hello.txt
 *  RemoteFilesystem2://A/C/E/F/hello.txt
 *
 *
 *
 */

public class QueryFileSearch implements SearchCriteria {
    String keyword;

    QueryFileSearch(String keyword){
        this.keyword=keyword;
    }

    @Override
    public Vector<String> execute(Vector<Filesystem> filesystems) {
        Vector<String> result = new Vector<>();

        Stack<String> paths = new Stack<>();

        for (Filesystem filesystem : filesystems) {
            paths.push(filesystem.getName() + "://");

            visitNode(result, paths, filesystem.getRoot());

            paths.pop();
        }

        return result;
    }

    private void visitNode(Vector<String> result, Stack<String> paths, Filesystem.Node node) {
        String filename = node.getFile().getName();
        FileItemType type = node.getFile().getFileType();
        if (type == FileItemType.DIR) {
            paths.push(filename + "/");
            for (Filesystem.Node subNode : node.list()) {
                visitNode(result, paths, subNode);
            }
            paths.pop();
        } else if (type == FileItemType.FILE) {
            if (filename.equals(keyword)) {
                paths.push(filename);
                result.add(foldPaths(paths));
                paths.pop();
            }
        }
    }

    private String foldPaths(Stack<String> paths) {
        StringBuilder sb = new StringBuilder();
        for (String path : paths) {
            sb.append(path);
        }
        return sb.toString();
    }
}
