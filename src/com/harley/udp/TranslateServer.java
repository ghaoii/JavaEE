package com.harley.udp;

import com.harley.util.Log;

import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class TranslateServer {
    // 公开的 IP 地址和端口(就看进程工作在哪个ip上)
    public static final int PORT = 8888;// 指定公开端口

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(PORT);
        // 作为服务器是被动的，循环地进行请求-响应周期的处理
        // 等待请求处理并发送响应，直到关闭服务器
        while(true) {
            initMap();
            // 1. 接收请求
            byte[] buf = new byte[1024];// 用于接收数据的数组
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            // receive方法会阻塞，直到收到客户端的请求
            Log.println("启动服务器，等待接收客户端请求");
            socket.receive(packet);
            // 2. 接收到请求之后，对请求拆分处理
            InetAddress address = packet.getAddress();// 获取发送方的IP地址
            int port = packet.getPort();// 获取发送方的port地址
            // 还有一种方法可以在获取对方的ip+port
            SocketAddress socketAddress = packet.getSocketAddress();
            byte[] data = packet.getData();// 获取发送方发送的内容
            int len = packet.getLength();// 获取数据data的长度
            // 3. 解析请求 - 应用层工作
            // 首先做字符集的编码，让byte数组转换成String类型，我们规定只支持utf-8字符集编码
            String request = new String(data, 0, len, "utf-8");
            // 拷贝一份用于做数据处理
            String english = request;
            // 4. 执行业务(翻译)
            String chinese = translate(english);
            // 5. 按照应用层协议封装响应
            String response = chinese;// 这个类似于前面的拷贝，可以不写
            // 进行字符集编码，将byte数组转换成String
            byte[] sendBuf = response.getBytes("utf-8");
            // 6. 发送响应
            // 封装报文包，这次作为发送方，需要传入对方的ip+port
            DatagramPacket sentPacket = new DatagramPacket(
                    sendBuf, 0, sendBuf.length, socketAddress);
            socket.send(sentPacket);
            // 7. 本次请求响应周期完成，继续等待下一次请求
        }
        //socket.close();// 由于上面是死循环永远走不到这里，所以可以不关
    }

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
