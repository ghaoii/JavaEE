package com.harley.io;

import java.io.*;

public class CopyDirectory {
    private static File srcFile = new File("/Users/harley/Desktop/code/FileTest/b");
    private static File destFile = new File("/Users/harley/Desktop/code/FileTest/copyb");

    public static void main(String[] args) throws IOException {
        traversal(srcFile);
    }

    private static void traversal(File dirFile) throws IOException {
        File[] files = dirFile.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {

            // 截取后的相对路径，然后加到destFile路径中
            String srcFilePath = srcFile.getCanonicalPath();
            String filePath = file.getCanonicalPath();
            String destPath = destFile.getCanonicalPath();
            // 得到相对路径 - 截取当前文件路径中长度超过srcFile路径长度的部分
            String relativePath = filePath.substring(srcFilePath.length());
            // 得到新目录的路径
            String destFilePath = destPath + relativePath;
            File newDestFile = new File(destFilePath);
            if (file.isDirectory()) {
                // 是目录的时候，直接创建目录
                newDestFile.mkdir();// 创建目录的时候，我们能够保证副目录一定是存在的
                traversal(file);
            } else {
                copyFile(file, newDestFile);
            }
        }

    }

    private static void copyFile(File srcFile, File destFile) throws IOException {
        // 准备好搬数据的桶
        byte[] buf = new byte[1024];

        // 打开两个文件
        int count = 0;
        try (InputStream is = new FileInputStream(srcFile)) {
            try (OutputStream os = new FileOutputStream(destFile)) {
                // 不断地用桶从is接收，放到os中
                while (true) {
                    int n = is.read(buf);
                    count += n;
                    System.out.println("已经复制了" + count + "字节的数据");
                    if (n == -1) {
                        // 全都复制完了，可以中止循环了
                        break;
                    }
                    // 将读取的数据，原封不动地写入os中
                    os.write(buf, 0, n);
                }
                // 冲刷缓冲区
                os.flush();
            }
        }
    }
}
