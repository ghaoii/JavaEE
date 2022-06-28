package com.harley.io.filetest;

import java.io.File;
import java.io.IOException;

public class CreateTest {
    public static void main(String[] args) throws IOException {
        // 1. 绝对路径的方式创建
        // 1.1 直接传入字符串的路径即可
        File file1 = new File("/Users/harley/Desktop/code/FileTest/a/a.txt");
        // 1.2 传入父路径 + 子路径
        File file2 = new File("/Users/harley/Desktop/code/FileTest/b", "b.txt");
        // 1.3 以file传入 parent
        File parent = new File("/Users/harley/Desktop/code/FileTest");
        File file3 = new File(parent, "c.txt");

        File file4 = new File("/Users/harley/Desktop/code/FileTest/d/d.txt");
        System.out.println(file4.delete());

        System.out.println(file4.createNewFile());
        System.out.println(file4.createNewFile());

    }
}
