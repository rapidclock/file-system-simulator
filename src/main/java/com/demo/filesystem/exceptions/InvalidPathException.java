package com.demo.filesystem.exceptions;

/**
 * @author RT
 */
public class InvalidPathException extends RuntimeException {
    public InvalidPathException() {

    }

    public InvalidPathException(String message) {
        super(message);
    }
}
