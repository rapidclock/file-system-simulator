package com.demo.filesystem.extensions;

import com.demo.filesystem.File;
import com.demo.filesystem.FileSystem;

/**
 * @author RT
 */
public class FileSystemWalker implements PathWalker {

    public FileSystemWalker() {
        super();
    }

    @Override
    public File walkPath(File filePointer, String destination) {
        switch (destination){
            case ".":
                filePointer = stay(filePointer);
                break;
            case "..":
                filePointer = up(filePointer);
                break;
            default:
                filePointer = traverse(filePointer, destination);
        }
        return filePointer;
    }

    private File stay(File current){
        return current;
    }

    private File up(File current){
        return current.getParent() != null ? current.getParent() : current;
    }

    /**
     * Traverses to the position of the given next file. Creates the next file if it doesn't exist.
     *
     * @param current The current File/Directory.
     * @param nextFile The destination Directory to traverse to.
     * @return File Pointer to the Destination.
     */
    private File traverse(File current, String nextFile){
        if (nextFile.length() == 0){
            return current;
        }
        if(current.containsChildName(nextFile)){
            return current.getChildByName(nextFile);
        } else {
            File newFile = new File(nextFile);
            current.addChild(newFile);
            return current.getChildByName(nextFile);
        }
    }

}
