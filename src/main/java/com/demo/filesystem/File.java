package com.demo.filesystem;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class File {
    private String name;
    private boolean folder; //sort of placeholder (not useful in this assignment).
    private Map<String, File> children;
    private File parent;

    /**
     * Constructor for a File that takes in the name of the file.
     * This is assumed to be a Folder with no parent.
     *
     * @param name Name of the file.
     */
    public File(String name) {
        this(name, true);
    }

    /**
     * Constructor for a File that takes in the name of the file, folder flag.
     * Parent is null.
     *
     * @param name   Name of the file.
     * @param folder true if it is a folder
     */
    public File(String name, boolean folder) {
        this(name, folder, null);
    }

    /**
     * Constructor for a File that takes in the name of the file and the Parent File.
     *
     * @param name   Name of the file.
     * @param parent the Parent File.
     */
    public File(String name, File parent) {
        this(name, true, parent);
    }

    /**
     * Constructor for a File that takes in the name of the file, folder flag and the Parent File.
     *
     * @param name   Name of the file
     * @param folder true if it is a folder
     * @param parent the Parent File.
     */
    public File(String name, boolean folder, File parent) {
        this.name = name;
        this.children = new HashMap<>();
        this.parent = parent;
        this.folder = folder;
    }

    /**
     * Returns the absolute pathname of this file from the root till the file.
     *
     * @return the absolute pathname of the file.
     */
    public String getAbsolutePath() {
        Deque<String> pathQueue = new ArrayDeque<>();
        File filePointer = this;
        while (filePointer != null) {
            pathQueue.addFirst(filePointer.name);
            if (filePointer.parent != null && !filePointer.parent.name.equals("/")) {
                pathQueue.addFirst("/");
            }
            filePointer = filePointer.parent;
        }
        String pathElement = pathQueue.pollFirst();
        StringBuilder absPath = new StringBuilder();
        while (pathElement != null) {
            absPath.append(pathElement);
            pathElement = pathQueue.pollFirst();
        }
        return absPath.toString();
    }

    /**
     * Adds a File to the list of Files as a child (sub-directory in the case of folders).
     *
     * @param child The File to be added as a child.
     */
    public void addChild(File child) {
        child.parent = this;
        this.children.put(child.getName(), child);
    }

    /**
     * Checks if the given file name is a child of this file.
     *
     * @param fileName name of the file whose presence is to be checked.
     * @return true if given file name is a child and false otherwise.
     */
    public boolean containsChildName(String fileName) {
        return this.children.containsKey(fileName);
    }

    /**
     * Checks if the given file name is a child of this file.
     *
     * Note that we only check using the name of the given file.
     *
     * @param file name of the file whose presence is to be checked.
     * @return true if given file name is a child and false otherwise.
     */
    public boolean containsChildFile(File file) {
        return this.containsChildName(file.getName());
    }

    /**
     * Retrieves child file of the given file.
     *
     * Note that this only uses the file name of the given file.
     *
     * @param file The given file.
     * @return the child file or null otherwise.
     */
    public File getChild(File file) {
        return this.getChildByName(file.getName());
    }

    /**
     * Retrieves child file of the given file.
     *
     * @param fileName file name of the given file.
     * @return the child file or null otherwise.
     */
    public File getChildByName(String fileName) {
        return this.children.get(fileName);
    }

    /**
     * Returns all the children of this file as a Collection.
     *
     * @return all the children of a file.
     */
    public Collection<File> getChildren() {
        return this.children.values();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (folder ? 1 : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        if (folder != file.folder) return false;
        if (!name.equals(file.name)) return false;
        if (children != null ? !children.equals(file.children) : file.children != null)
            return false;
        return parent != null ? parent.equals(file.parent) : file.parent == null;
    }

    /**
     * Gets parent.
     *
     * @return Value of parent.
     */
    public File getParent() { return this.parent; }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() { return this.name; }

    /**
     * Gets folder.
     *
     * @return Value of folder.
     */
    public boolean isFolder() { return this.folder; }
}