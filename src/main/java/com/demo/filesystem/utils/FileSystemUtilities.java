package com.demo.filesystem.utils;

/**
 * Utility methods for the FileSystem package.
 * @author RT
 */
public class FileSystemUtilities {
    /**
     * Collapses continuous occurrences of the given identifier into a single one.
     *
     * @param inputString given input string
     * @param identifier the given identifier to observe.
     * @return string with the continuous occurences collapsed into each other.
     */
    public static String removeConsecutive(String inputString, char identifier){
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++){
            if (i == inputString.length() - 1){
                output.append(inputString.charAt(i));
            } else if (inputString.charAt(i) == identifier){
                output.append(inputString.charAt(i));
                while (i < inputString.length() - 1 && inputString.charAt(i+1) == identifier){
                    i++;
                }
            } else {
                output.append(inputString.charAt(i));
            }
        }
        return output.toString();
    }
}
