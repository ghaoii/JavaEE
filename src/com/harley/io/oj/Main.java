package com.harley.io.oj;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static Solution solution = new Solution();
    public static void main(String[] args) throws Exception {
        File inputFile = new File("/Users/harley/Desktop/code/FileTest/input.txt");
        File resultFile = new File("/Users/harley/Desktop/code/FileTest/output.txt");
        try (InputStream input = new FileInputStream(inputFile)) {
            try (InputStream result = new FileInputStream(resultFile)) {
                try (Scanner inputScanner = new Scanner(input)) {
                    try (Scanner resultScanner = new Scanner(result)) {
                        while(inputScanner.hasNextLine()) {
                            String inputLine = inputScanner.nextLine();
                            String resultLine = resultScanner.nextLine();
                            test(inputLine, resultLine);
                        }
                        System.out.println("编译通过");
                    }
                }
            }
        }
    }

    private static void test(String inputLine, String resultLine) {
        // 从 inputLine 中构造链表
        ListNode head = new ListNode(-1);
        ListNode last = head;
        String[] array = inputLine.split(" ");
        for(String s : array) {
            int val = Integer.parseInt(s);
            last.next = new ListNode(val);
            last = last.next;
        }
        // 自己的写法
//        ListNode head = new ListNode(inputLine.charAt(0) - '0');
//        ListNode last = head;
//        for (int i = 2; i < inputLine.length(); i += 2) {
//            last.next = new ListNode(inputLine.charAt(i) - '0');
//            last = last.next;
//        }
        try {
            ListNode reverseHead = solution.reverseList(head.next);
            // 对比reverseHead 构造的链表和 resultLine 是否一直
            StringBuilder sb = new StringBuilder();
            for (ListNode cur = reverseHead; cur != null; cur = cur.next) {
                sb.append(cur.val).append(" ");
            }
            // 删除最后一个空格
            if(sb.length() > 0) {
                sb.delete(sb.length() - 1, sb.length());
            }
            String actualLine = sb.toString();

            if (!actualLine.equals(resultLine)) {
                System.out.println("输入：" + inputLine);
                System.out.println("期望输出：" + resultLine);
                System.out.println("实际输出：" + actualLine);
                System.exit(1);// 当改组测试用例的实际输出不符合期望时，就退出程序
            }
        } catch (Exception e) {
            // 如果逆置链表写得有问题，就会在这里抛出异常
            System.out.println("输入：" + inputLine);
            System.out.println("期望输出：" + resultLine);
            System.out.println("实际输出：");
            throw new RuntimeException(e);
        }
    }
}
