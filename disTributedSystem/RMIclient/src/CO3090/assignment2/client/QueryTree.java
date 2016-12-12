package CO3090.assignment2.client;

import java.util.Vector;

import CO3090.assignment2.FileItemType;
import CO3090.assignment2.Filesystem;
import CO3090.assignment2.SearchCriteria;

//Question 3.3

/*
 * For each file server, QueryTree should return the directory
 * structure as a string formatted according to the specified
 * format.
 *
 * For example, given the directory structure in
 * RemoteFilesystem1.txt and RemoteFilesystem2.txt.
 * RFSServer should return:
 *
 * RemoteFilesystem1:
 * A{B,C{books.xls,D,E{readme.txt,hello.txt},F{G}}}
 *
 * RemoteFilesystem2:
 * A{B{hello.txt,D{abc.txt,xyz.txt}},C{E{hello.txt,F{hello.txt}}}}
 *
 *
 */

public class QueryTree implements SearchCriteria {
    @Override
    public Vector<String> execute(Vector<Filesystem> filesystems) {
        /*
        A {
            B,
            C {
                books.xls,
                D,
                E {
                    readme.txt,
                    hello.txt
                },
                F {
                    G
                }
            }
        }
        */
        Vector<String> result = new Vector<>();

        for (Filesystem filesystem : filesystems) {
            result.add(filesystem.getName() + ":");

            StringBuilder structure = new StringBuilder();

            visitNode(structure, filesystem.getRoot());

            result.add(structure.toString());
        }

        return result;
    }

    private void visitNode(StringBuilder structure, Filesystem.Node node) {
        structure.append(node.getFile().getName());

        FileItemType type = node.getFile().getFileType();
        if (type == FileItemType.DIR) {
            Vector<Filesystem.Node> subNodes = node.list();
            if (subNodes.size() > 0) {
                structure.append('{');
                for (int i = 0; i < subNodes.size(); ++i) {
                    if (i > 0) {
                        structure.append(',');
                    }
                    visitNode(structure, subNodes.get(i));
                }
                structure.append('}');
            }
        }
    }
}
