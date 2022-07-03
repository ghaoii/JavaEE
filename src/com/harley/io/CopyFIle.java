package com.harley.io;

import java.io.*;

public class CopyFIle {
    public static void main(String[] args) throws IOException {
        File srcFile = new File("/Users/harley/Desktop/图片.png");
        File destFile = new File("/Users/harley/Desktop/code/FileTest/test/dest.png");
        // 准备好搬数据的桶
        byte[] buf = new byte[1024];

        // 打开两个文件
        int count = 0;
        try (InputStream is = new FileInputStream(srcFile)) {
            try(OutputStream os = new FileOutputStream(destFile)) {
                // 不断地用桶从is接收，放到os中
                while(true) {
                    int n = is.read(buf);
                    count += n;
                    System.out.println("已经复制了" + count + "字节的数据");
                    if(n == -1) {
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
