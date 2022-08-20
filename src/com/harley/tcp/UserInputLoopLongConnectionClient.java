package com.harley.tcp;

import com.harley.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UserInputLoopLongConnectionClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // 直接创建 Socket，使用服务器的ip + 端口
        Log.println("建立连接");
        Socket socket = new Socket("localhost", TranslateServer.PORT);
        // 实例化只需要一次就好了
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osWriter = new OutputStreamWriter(os, "utf-8");
        PrintWriter pw = new PrintWriter(osWriter);
        InputStream is = socket.getInputStream();
        Scanner scanner = new Scanner(is, "utf-8");

        System.out.print("请输入单词: ");
        while(sc.hasNextLine()) {
            String english = sc.nextLine();
            String request = english;
            // 发送请求
            Log.println("发送请求: " + request);
            pw.println(request);
            pw.flush();
            // 接收响应
            String chinese = scanner.nextLine();
            System.out.println(chinese);
            Log.println("收到响应: " + chinese);

            System.out.print("请输入单词: ");
        }
        // 关闭连接
        Log.println("关闭连接");
        socket.close();
    }
}
