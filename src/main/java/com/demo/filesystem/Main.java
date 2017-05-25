package com.demo.filesystem;

import com.demo.filesystem.exceptions.InvalidPathException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("LLL - " + args.length);
            return;
        }
        String startPath = args[0];
        String destPath = args[1];

        FileSystem fileSystem = new BasicFileSystem(startPath);
        try {
            System.out.println("Source - " + startPath);
            System.out.println("Dest - " + destPath);
            String absPath = fileSystem.cd(destPath);
            System.out.println("Full Path - " + absPath);
        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }
    }
}
