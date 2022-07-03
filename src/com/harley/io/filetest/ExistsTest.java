package com.harley.io.filetest;

import java.io.File;
import java.io.IOException;

public class ExistsTest {
    public static void main(String[] args) throws IOException {
        File file1 = new File("/Users/harley/Desktop/code/FileTest/hello.txt");
        System.out.println(file1.exists());// false
        file1.createNewFile();
        System.out.println(file1.isDirectory());// false
        System.out.println(file1.isFile());// true
    }
}
