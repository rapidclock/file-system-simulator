package com.demo.filesystem;

import com.demo.filesystem.exceptions.InvalidPathException;
import com.demo.filesystem.utils.FileSystemUtilities;

import java.util.regex.Pattern;

/**
 * Represents a basic file system with extensions to change directory.
 *
 * @author RT
 */
public class BasicFileSystem extends FileSystem {


    public BasicFileSystem() {
        super();
    }

    public BasicFileSystem(String path) {
        super();
        this.createFilesInPath(this.currentFilePointer, path);
    }

    public void createFilesInPath(File filePointer, String path) {
        path = this.cleanFilePath(path);
        if (!this.isPathValid(path)) {
            throw new InvalidPathException(path + " - is not a valid pathname");
        }
        String[] fileNames = path.split("/");
        this.createFileSystem(filePointer, fileNames);
    }

    private void createFileSystem(File filePointer, String[] files) {
        File parent = filePointer;
        for (String fileName : files) {
            if (fileName.length() == 0) {
                continue;
            }
            File file = new File(fileName);
//            file.parent = parent;
            parent.addChild(file);
            parent = file;
        }
        this.currentFilePointer = parent;
    }

    @Override
    public String cd(String destination) {
        if (!this.isPathValid(destination)) {
            throw new InvalidPathException(destination + " - This is not a valid path to a file or directory");
        }
        this.currentFilePointer = this.walkPath(this.currentFilePointer, destination);
        return this.currentFilePointer.getAbsolutePath();
    }

    @Override
    protected File walkPath(File filePointer, String path) {
        String cleanPath = this.cleanFilePath(path);
        if (path.startsWith("/")){
            this.restoreFilePointerToRoot();
        }
        String[] fileNamesInPath = cleanPath.split("/");
        for (String fileName : fileNamesInPath) {
            if (fileName.length() == 0){
                continue;
            }
            this.currentFilePointer = fileSystemWalker.walkPath(this.currentFilePointer, fileName);
        }
        return this.currentFilePointer;
    }

    private boolean isPathValid(String path) {
        String cleanPath = this.cleanFilePath(path);
        String[] fileNamesInPath = cleanPath.split("/");
        for (String fileName : fileNamesInPath) {
            if (fileName.equals("")) {
                continue;
            }
            if (!this.isValidFileName(fileName)) {
                return false;
            }
        }
        return true;
    }

    private String cleanFilePath(String path) {
        return FileSystemUtilities.removeConsecutive(path, '/');
    }

    protected boolean isValidFileName(String fileName) {
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        boolean hasSpecialChar = p.matcher(fileName).find();
        return fileName.equals(".") || fileName.equals("..") || !hasSpecialChar;
    }


}
