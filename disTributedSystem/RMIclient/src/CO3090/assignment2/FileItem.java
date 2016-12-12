package CO3090.assignment2;

/**
 * @author Yi Hong
 *
 */

public class FileItem {
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public FileItemType getFileType() {
        return fileType;
    }
    public void setFileType(FileItemType fileType) {
        this.fileType = fileType;
    }
    public String getParentDirectoryName() {
        return parentDirectoryName;
    }
    public void setParentDirectoryName(String parentDirectoryName) {
        this.parentDirectoryName = parentDirectoryName;
    }

    public FileItem(String name, FileItemType fileType,
            String parentDirectoryName) {
        super();
        this.name = name;
        this.fileType = fileType;
        this.parentDirectoryName = parentDirectoryName;
    }

    @Override
    public String toString() {
        return "FileItem [name=" + name + ", fileType=" + fileType
                + ", parentDirectoryName=" + parentDirectoryName + "]";
    }

    String name;
    FileItemType fileType;
    String parentDirectoryName;

}
