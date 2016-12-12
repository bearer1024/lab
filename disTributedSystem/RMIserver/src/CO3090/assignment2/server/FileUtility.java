package CO3090.assignment2.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import CO3090.assignment2.FileItem;
import CO3090.assignment2.FileItemType;

public class FileUtility {
    final static String fileSystemPath="./filesystems/";

    public static void main(String[] args){

        Vector<String> fsIndex=readDistributedFilesystemList();

        for(String filesystem: fsIndex){
            for(FileItem f: readFS(fileSystemPath+filesystem)){
                System.out.println(fsIndex+" ->" +f);
            }
        }
    }


    /**
     * @return  return a list of files located in ./filesystems/.
     */
    public static Vector<String> readDistributedFilesystemList(){
        File[] files = new File(fileSystemPath).listFiles();

        Vector<String> list=new Vector<String>();
        for (File fsIndexFile : files) {
            if (fsIndexFile.isFile()) {
                list.add(fsIndexFile.getName());
            }
        }

        return list;
    }


    /**
     * @param txt file storing directory information
     * @return  return FileItem objects from the file provided.
     */

    public static Vector<FileItem> readFS(String filename){
        Vector<FileItem> map = new Vector<FileItem>();

        try {
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                if(!strLine.trim().startsWith("#") &&
                   !strLine.trim().equals(""))
                {
                    String[] array=strLine.split(",");

                    String fitype = array[0];
                    String name = array[1];
                    String parent = array[2];
                    FileItemType type = FileItemType.UNKNOWN;

                    if (fitype != null) {
                        if (fitype.equals("FILE")) {
                            type = FileItemType.FILE;
                        } else if (fitype.equals("DIR")) {
                            type = FileItemType.DIR;
                        } else {
                            type = FileItemType.UNKNOWN;
                        }
                    }

                    if (parent.equals("?")) {
                        parent = null;
                    }

                    FileItem f = new FileItem(name, type, parent);

                    map.add(f);
                }

            }

            in.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }
}
