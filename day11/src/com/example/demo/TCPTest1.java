package com.example.demo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  使用tcp 协议，完成客户端和服务端数据的传输
 */
public class TCPTest1 {


    /**
     *  tcp: 客户端
     *      需要知道tcp服务端的ip和端口号
     *      ip 和 端口号 组成通信套接字
     *  步骤:
     *      创建socket 对象 -->> 获取输入/输出流 --》》 读/写数据--》》资源的关闭
     */

    @Test
    public void test1() {

        Socket socket = null;
        OutputStream os = null;
        try {
            InetAddress inet = InetAddress.getByName("192.168.31.83");
            //1.创建Socket对象，指明服务器端的ip和端口号
            socket = new Socket(inet,8899);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("nihao,我是来自客户端的mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源的关闭
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     *  tcp 服务端
     *      只需要给客户端提供一个端口号即可
     *      使用的是serverSocket
     */
    @Test
    public void test2() {

        ServerSocket serverSocket = null;
        Socket accept = null;
        ByteArrayOutputStream baos = null;
        InputStream stream = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(8899);
            //2.调用accept()表示接收来自于客户端的socket
            accept = serverSocket.accept();
            //3.获取输入流
            stream = accept.getInputStream();
            System.out.println("接受来自客户端的信息如下:");


            //这样的写法可能会造成乱码 打印结果 ---》》 nihao,我���来自��户���的mm
            // 我们换用ByteArrayOutputStream 来替换即可
//        byte[] bytes = new byte[5];
//        int len ;
//        while ((len = stream.read(bytes)) != -1){
//            System.out.print(new String(bytes,0,len));
//        }

            //4.读取输入流中的数据
            //使用BateArrayOutputStream
            baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[5];
            int len ;
            while ((len = stream.read(bytes)) != -1){
                baos.write(bytes,0,len);
            }
//        System.out.println(baos.toString("utf-8"));
            System.out.println(baos.toString());
            System.out.println("我收到了来自 " + accept.getInetAddress().getHostAddress() + " 发过来的信息" );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //资源的关闭
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (accept != null){
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null ){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }








}
