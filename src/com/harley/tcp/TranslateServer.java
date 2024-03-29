package com.harley.tcp;

import com.harley.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TranslateServer {
    public static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        initMap();
        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true) {
            Log.println("等待对方连接");
            Socket socket = serverSocket.accept();
            Log.println("连接已建立");
            InetAddress ip = socket.getInetAddress();// 获得对方ip
            int port = socket.getPort();// 获取对方端口
            // 获取远端ip + port
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            // 通过输入流获取发送端的数据
            InputStream is = socket.getInputStream();
            Scanner scanner = new Scanner(is, "utf-8");
            String request = scanner.nextLine();// 这里会把我们的换行符去掉，所以我们不需要额外处理
            String english = request;
            // 处理 english 的翻译
            String chinese = translate(english);
            // 通过输出流把处理的结果返回给发送端
            // 这里连接已经建立，想发送什么，直接发就好，不需要像UDP那样再次封装请求
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osWriter = new OutputStreamWriter(os, "utf-8");
            PrintWriter pw = new PrintWriter(osWriter);
            pw.println(chinese);// 同时写入了换行作为数据包区分的标识
            pw.flush();// 刷新缓冲区
            // 单次服务，关闭连接
            socket.close();
        }
    }
    // 下面这段代码都是和 UDP 一样的
    private static final Map<String, String> map = new HashMap<>();
    private static void initMap() {
        map.put("apple", "苹果");
        map.put("mango", "芒果");
        map.put("cherry", "樱桃");
        map.put("strawberry", "草莓");
    }
    private static String translate(String english) {
        return map.getOrDefault(english, "查无此单词");
    }
}