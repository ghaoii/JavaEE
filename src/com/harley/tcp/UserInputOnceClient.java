package com.harley.tcp;

import com.harley.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class UserInputOnceClient {
    public static void main(String[] args) throws Exception {
        // 直接创建 Socket，使用服务器的ip + 端口
        Socket socket = new Socket("localhost", TranslateServer.PORT);

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入单词: ");
        String english = sc.nextLine();
        String request = english;
        // 发送请求
        Log.println("发送请求: " + request);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osWriter = new OutputStreamWriter(os, "utf-8");
        PrintWriter pw = new PrintWriter(osWriter);
        pw.println(request);
        pw.flush();
        // 接收响应
        InputStream is = socket.getInputStream();
        Scanner scanner = new Scanner(is, "utf-8");
        String chinese = scanner.nextLine();
        System.out.println(chinese);
        Log.println("收到响应: " + chinese);
        // 关闭连接
        socket.close();
    }
}
