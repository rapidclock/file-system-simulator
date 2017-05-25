package com.demo.filesystem.extensions;

import com.demo.filesystem.File;

/**
 * @author RT
 */
public interface PathWalker {
    File walkPath(File filePointer, String destination);
}
