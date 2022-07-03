package com.harley.io.filetest;

import java.io.File;

public class Test {
    public static void main(String[] args) {
//        File file = new File("/Users/harley/Desktop/code/FileTest/c/d/e");
//        // c目录不存在
//        System.out.println(file.mkdir());// false
//        System.out.println(file.mkdirs());// true

        File file = new File("/Users/harley/Desktop/code/FileTest/test.txt");
        File dest = new File("/Users/harley/Desktop/code/FileTest/test/dest.txt");
        System.out.println(file.renameTo(dest));
    }

}
