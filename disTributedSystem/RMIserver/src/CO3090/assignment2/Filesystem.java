package CO3090.assignment2;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Vector;

public class Filesystem {
    public static class Node {
        Node parent;

        FileItem file;
        Vector<Node> subNodes = new Vector<>();

        public Vector<Node> list() {
            if (file.getFileType() == FileItemType.DIR) {
                return subNodes;
            }

            return null;
        }

        public FileItem getFile() {
            return file;
        }
    }

    String name;
    Node root = new Node();

    public Filesystem(String name, Vector<FileItem> fileItems) {
        this.name = name;

        for (FileItem f : fileItems){
            addFile(f);
        }
    }

    public String getName() {
        return name;
    }

    public Node getRoot() {
        return root;
    }

    private void addFile(FileItem file) {
        if (file.getParentDirectoryName() == null) {
            root.file = file;
        } else {
            Node parent = getDirectoryByName(file.getParentDirectoryName());
            if (parent != null) {
                Node node = new Node();
                node.parent = parent;
                node.file = file;
                parent.subNodes.add(node);
            }
        }
    }

    private Node getDirectoryByName(String name) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (queue.size() != 0) {
            Node node = queue.removeFirst();

            if (node.file.getName().equals(name)) {
                return node;
            }

            if (node.file.getFileType() == FileItemType.DIR) {
                for (Node subNode : node.subNodes) {
                    queue.addLast(subNode);
                }
            }
        }

        return null;
    }

    public void printFilesystem() {
        printFilesystemR(root, 0);
    }

    private void printFilesystemR(Node node, int level) {
        StringBuilder sb = new StringBuilder();
        int indentLength = 4;
        for (int i = 0; i < level * indentLength; ++i) {
            sb.append(' ');
        }
        sb.append(node.file.getName());
        System.out.println(sb.toString());

        if (node.file.getFileType() == FileItemType.DIR) {
            for (Node subNode : node.subNodes) {
                printFilesystemR(subNode, level + 1);
            }
        }
    }
}
