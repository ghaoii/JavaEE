package com.harley.io.filetest;

import java.io.File;
import java.io.IOException;

public class DeleteTest {
    public static void main(String[] args) throws IOException {
        File file1 = new File("/Users/harley/Desktop/code/FileTest/d.txt");
        // 在d目录下创建一个文本文件 d.txt，然后再删除
        System.out.println(file1.createNewFile());
        System.out.println(file1.delete());

        File file2 = new File("/Users/harley/Desktop/code/FileTest/c/");
        // 删除c目录下的1目录
        traversal(file2);
        file2.delete();
    }

    private static void traversal(File dir) throws IOException {
        File[] files = dir.listFiles();// 查看这个目录下得所有孩子
        for(File file : files) {
            if(file.isDirectory()) {
                // 如果这个孩子也是个目录，就继续深度优先遍历
                System.out.println(file.getCanonicalPath() + "/");
                traversal(file);
                // 当深度优先遍历完成时，该目录为空，可删除
                file.delete();
            }else {
                System.out.println(file.getCanonicalPath());// 得到这个文件的一个标准路径(去除一切无意义的.和..)
                file.delete();
            }
        }
    }
}
