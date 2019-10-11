package com.example.exer;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *  实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 */
public class TcpTest {


    /**
     *  客户端
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {

        InetAddress inet = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(inet,8520);

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("你好，我是中国人abc".getBytes());
        outputStream.close();
    }

    /**
     *  服务端
     */
    @Test
    public void test2() throws IOException {

        ServerSocket serverSocket = new ServerSocket(8520);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[5];
        int len;
        while ((len = inputStream.read(bytes)) != -1){
            baos.write(bytes,0,len);
        }
        System.out.println("收到了来自客户端:" + socket.getInetAddress().getHostAddress() + "的信息,信息如下:");
        System.out.println(baos.toString());


    }





}
