package com.harley.udp;

import com.harley.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UserInputLoopClient {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        // 1. 创建 UDP socket
        DatagramSocket socket = new DatagramSocket();
        // 2. 发送请求
        System.out.print("请输入需要翻译的英文单词: ");
        while(scanner.hasNextLine()) {
            String english = scanner.nextLine();
            byte[] sendBuf = english.getBytes("utf-8");
            // 手动构造服务器地址，目前服务器地址在本机上
            DatagramPacket sendPacket = new DatagramPacket(
                    sendBuf, 0, sendBuf.length,
                    InetAddress.getLoopbackAddress(), TranslateServer.PORT);
            Log.println("发送客户器请求: " + english);
            socket.send(sendPacket);
            // 3. 接收响应
            byte[] buf = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            socket.receive(receivePacket);
            byte[] data = receivePacket.getData();
            int len = receivePacket.getLength();
            String response = new String(data, 0, len, "utf-8");
            String chinese = response;
            System.out.println(chinese);
            Log.println("收到服务器响应: " + chinese);

            System.out.print("请输入需要翻译的英文单词: ");
        }
        // 4. 关闭socket
        socket.close();
    }
}
