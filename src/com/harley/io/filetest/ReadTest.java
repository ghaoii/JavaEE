package com.harley.io.filetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class ReadTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 直接敲回车，意思是这次没有输入，但是以后还会以
        // 想要结束输入的一种方法(IDEA中:ctrl/cmd + D, 终端中:ctrl/cmd + Z)，即传入EOS
        while(scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(str);
        }
    }

    public static void main1(String[] args) throws Exception {
//        // 向上转型
//        InputStream is  = new FileInputStream("/Users/harley/Desktop/code/FileTest/hello.txt");
//
//        // 准备好一个水桶
//        byte[] buf = new byte[1024];// 1024代表最多存储1024滴(字节)的水，桶的容量
//
//        // 拿着桶取接水
//        // 这里的n代表这次真正接到了多少滴(字节)水
//        // n一定是小于等于1024 && n >= 0
//        int n = is.read(buf);
//        byte[] bytes = Arrays.copyOf(buf, n);
//        for(byte b : bytes) {
//            System.out.printf("%02x ", b);
//        }
//        is.close();

        InputStream is = new FileInputStream("/Users/harley/Desktop/code/FileTest/hello.txt");
        byte[] buf = new byte[5];// 一次最多存5个字节的数据
        while(true) {
            int n = is.read(buf);
            // n == 0 只是本次没数据，以后还有
            // n == -1 本次包括以后都没有数据了
            if(n == -1) {
                break;
            }
            for (int i = 0; i < n; i++) {
                byte b = buf[i];
                System.out.printf("%02x ", b);
            }
        }


//        while(true) {
//            int data = is.read();
//            if(data == -1) {
//                break;
//            }
//            byte b = (byte) data;
//            System.out.printf("%02x ", b);
//        }
        is.close();
    }

    //    public static void main(String[] args) throws Exception {
//        try (InputStream is = new FileInputStream("../FileTest/hello.txt")) {
//            try (Scanner scanner = new Scanner(is, "UTF-8")) {
//                while(scanner.hasNextLine()){
//                    String line = scanner.nextLine();
//                    System.out.println("|" + line + "|");
//                }
//            }
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        try(InputStream is = new FileInputStream("../FileTest/hello.txt")) {
//            byte[] buf = new byte[1024];
//            int n = is.read(buf);
//            //System.out.println(n);// 3
//            String str = new String(buf, 0, n, "UTF-8");
//            System.out.println(str);// 我
//            String str1 = new String(buf, 0, n, "GBK");
//            System.out.println(str1);// 鎴�
//            Scanner scanner = new Scanner(System.in);
//        }
//    }
}
