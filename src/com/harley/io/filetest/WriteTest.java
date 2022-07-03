package com.harley.io.filetest;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil;

import java.io.*;
import java.util.Scanner;

public class WriteTest {
    public static void main(String[] args) throws IOException {
        try (OutputStream os = new FileOutputStream("../FileTest/test/word.txt")) {
            try (Writer writer = new OutputStreamWriter(os, "UTF-8")) {
                try (PrintWriter printWriter = new PrintWriter(writer)) {
                    printWriter.println(1 + 1);
                    printWriter.print(3);
                    printWriter.printf("%d + %s", 3, "我");
                    printWriter.flush();// 在最内存调用flush，能把所有外层也刷掉
                }
            }
        }
    }

//    public static void main(String[] args) throws IOException {
//        try (OutputStream os = new FileOutputStream("../FileTest/test/word.txt")) {
//            try (Writer writer = new OutputStreamWriter(os, "UTF-8")) {
//                writer.write("你好世界\n");
//                writer.write("你好中国");
//            }
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        try (OutputStream os = new FileOutputStream("../FileTest/test/word.txt")) {
//            byte[] buf = {0x65, 0x65, 0x20, 0x65, 0x0a, (byte)0xe6, (byte)0x88, (byte)0x91};
//            // os.write(buf);
//            os.write(buf, 2, 3);// 从下标为2的位置开始写，写3个
//            os.flush();
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        try (OutputStream os = new FileOutputStream("../FileTest/test/word.txt")) {
//            os.write(0x20);
//            os.write(0x0a);
//            os.write(0x65);
//            os.write(0x65);
//            os.write(0x65);
//            // 0xe6 0x88 0x91是'我'的UTF-8编码的字节序列
////            os.write(0xe6);
////            os.write(0x88);
////            os.write(0x91);
//
//            os.flush();// 关闭之前，手动刷盘
//        }
//    }
}
