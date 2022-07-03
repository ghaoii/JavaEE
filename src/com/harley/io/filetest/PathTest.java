package com.harley.io.filetest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PathTest {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/harley/Desktop/code/FileTest/a");
//        System.out.println(file.exists());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalPath());

//        System.out.println(file.getName());
//        System.out.println(file.getParent());
//        System.out.println(file.getParentFile().getCanonicalPath());

//        System.out.println(file.isAbsolute());

        File[] files = file.listFiles();
        System.out.println(Arrays.toString(files));// [/Users/harley/Desktop/code/FileTest/a/.DS_Store, /Users/harley/Desktop/code/FileTest/a/a.txt]

        File file1 = new File("/Users/harley/Desktop/code/FileTest/test");
        File[] files1 = file1.listFiles();
        System.out.println(Arrays.toString(files1));// []

        File file2 = new File("/Users/harley/Desktop/code/FileTest/test.txt");
        File[] files2 = file2.listFiles();
        System.out.println(Arrays.toString(files2));// null
    }
}
