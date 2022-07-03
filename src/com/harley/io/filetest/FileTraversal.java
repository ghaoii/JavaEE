package com.harley.io.filetest;

import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class FileTraversal {
    // 深度优先遍历
    private static void traversalDepthFirst(File dir) throws IOException {
        // 1. 找到这个目录下得所有孩子
        File[] files = dir.listFiles();
        if(files == null) {
            return;
        }

        // 2. 针对每个孩子，判断目录还是文件
        for(File file : files) {
            if(file.isDirectory()) {
                // 如果是个目录，则继续地柜取遍历处理
                System.out.println("[D] " + file.getCanonicalPath());
                traversalDepthFirst(file);
            }else {
                System.out.println("[F] " + file.getCanonicalPath());
            }
        }
    }

    // 广度优先遍历
    private static void traversalBroadFirst(File dir) throws IOException {
        Deque<File> queue = new LinkedList<>();
        queue.offer(dir);
        while(!queue.isEmpty()) {
            File file = queue.poll();
            if(file.isDirectory()) {
                System.out.println("[D] " + file.getCanonicalPath());
                File[] files = file.listFiles();
                if(files == null) {
                    continue;
                }
                for(File file1 : files) {
                    queue.offer(file1);
                }
            }else {
                System.out.println("[F] " + file.getCanonicalPath());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        traversalDepthFirst(new File("/Users/harley/Desktop/code/FileTest"));
        System.out.println("========================我是分割线==============================");
        traversalBroadFirst(new File("/Users/harley/Desktop/code/FileTest"));
    }
}
