package com.demo.filesystem;

import com.demo.filesystem.exceptions.InvalidPathException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author RT
 */
public class BasicFileSystemTest {
    private String startPath = "/abc/def";
    private String startPath2 = "/abc//def";
    private String invalidStartPath = "/..abc/def";

    private String destPath1 = "../gh///../klm/.";
    private String destPath2 = "/abc";
    private String destPath3 = "/abc/klm";
    private String destPath4 = "../../..";
    private String destPath5 = "//////";
    private String destPath6 = "......";
    private String destPath7 = "";

    private FileSystem fileSystem;
    private FileSystem fileSystem2;
    private FileSystem fileSystem3;


    @Before
    public void setUp() throws Exception {
        this.fileSystem = new BasicFileSystem(startPath);
        this.fileSystem2 = new BasicFileSystem(startPath2);
    }

    @Test
    public void testCd() {
        Assert.assertEquals(this.fileSystem.cd(destPath1), "/abc/klm");
        Assert.assertEquals(this.fileSystem.cd(destPath2), "/abc");
        Assert.assertEquals(this.fileSystem.cd(destPath3), "/abc/klm");
        Assert.assertEquals(this.fileSystem.cd(destPath4), "/");
        Assert.assertEquals(this.fileSystem.cd(destPath5), "/");
        Assert.assertEquals(this.fileSystem2.cd(destPath7), "/abc/def");
    }

    @Test(expected = InvalidPathException.class)
    public void testExceptions() {
        this.fileSystem.cd(destPath6);
        this.fileSystem3 = new BasicFileSystem(invalidStartPath);
    }

}