package com.harley.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpClient {
    private Socket socket;
    private String ip;
    private int port;

    public HttpClient(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        socket = new Socket(ip, port);
    }

    public String get(String url) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // 构造首行
        stringBuilder.append("GET " + url + " HTTP/1.1\n");
        // 构造 header
        stringBuilder.append("Host: " + ip + ":" + port + "\n");
        // 构造空行
        stringBuilder.append("\n");
        // GET 请求不需要 body，到这里请求就构造完成了
        OutputStream outputStream = socket.getOutputStream();
        // outputStream 是一个字节流，以字节为单位进行写入，因此需要把 StringBuilder 转换成 byte[]
        outputStream.write(stringBuilder.toString().getBytes());
        // 读取响应
        InputStream inputStream = socket.getInputStream();
        // 设置一个 1MB 大小的缓冲区(大小可自定义)，用来存放响应数据
        byte[] buffer = new byte[1024 * 1024];
        // n 表示实际读到的字节数
        int n = inputStream.read(buffer);
        return new String(buffer, 0, n, "utf-8");
    }

    // post 的实现和 get 基本差不多
    public String post(String url, String body) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        // 构建首行
        stringBuilder.append("POST " + url + " HTTP/1.1");
        // 构建herder
        stringBuilder.append("Hps: " + ip + ":" + "\n");
        // 相比于 GET，还需要构造 ContentType 和 Content-Length
        stringBuilder.append("ContentType: text/plain\n");
        stringBuilder.append("Content-Length" + body.getBytes().length + '\n');
        // 构建空行
        stringBuilder.append("\n");
        // 构建 body
        stringBuilder.append(body);
        OutputStream os = socket.getOutputStream();
        os.write(stringBuilder.toString().getBytes());

        InputStream is = socket.getInputStream();
        byte[] buffer = new byte[1024 * 1024];
        int n = is.read(buffer);
        return new String(buffer, 0, n, "utf-8");
    }

    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient("42.192.83.143", 8089);
        // GET 请求
//        String ret = client.get("/AjaxMockServer/info");
//        System.out.println(ret);

        // POST 请求
        String ret= client.post("/AjaxMockServer/info", "这是body正文");
        System.out.println(ret);
    }
}
