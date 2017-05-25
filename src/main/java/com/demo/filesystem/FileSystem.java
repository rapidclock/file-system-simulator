package com.demo.filesystem;


import com.demo.filesystem.commands.CD;
import com.demo.filesystem.extensions.FileSystemWalker;
import com.demo.filesystem.extensions.PathWalker;

/**
 * Represents a File System.
 */
public abstract class FileSystem implements CD {
    protected File root;
    protected File currentFilePointer;
    protected PathWalker fileSystemWalker;

    /**
     * Default Constructor for the File System.
     *
     * By Default, the root is represented by "/".
     */
    public FileSystem() {
        this.root = new File("/");
        this.currentFilePointer = this.root;
        this.fileSystemWalker = new FileSystemWalker();
    }

    /**
     * Returns the Root Folder of the File System.
     *
     * @return the root folder.
     */
    public File getRootFolder() {
        return this.root;
    }

    /**
     * Restore the given file pointer to the root folder of the file system.
     */
    public void restoreFilePointerToRoot() {
        this.currentFilePointer = this.root;
    }

    /**
     * Creates all the files in the given path.
     *
     * @param filePointer the given file.
     * @param path        the path to traverse.
     */
    public abstract void createFilesInPath(File filePointer, String path);

    /**
     * Walks the given path from the given file.
     *
     * @param filePointer the given point from which we walk the path.
     * @param path        given file pointer
     * @return final file pointer after walking the given path.
     */
    protected abstract File walkPath(File filePointer, String path);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileSystem)) return false;

        FileSystem that = (FileSystem) o;

        if (root != null ? !root.equals(that.root) : that.root != null) return false;
        return currentFilePointer != null ? currentFilePointer.equals(that.currentFilePointer) : that.currentFilePointer == null;
    }

    @Override
    public int hashCode() {
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + (currentFilePointer != null ? currentFilePointer.hashCode() : 0);
        return result;
    }
}