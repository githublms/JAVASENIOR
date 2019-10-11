package com.example.demo;

import org.junit.Test;

import javax.naming.ldap.SortKey;
import javax.print.DocFlavor;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  实现TCP的网络编程
 * 例题2：客户端发送文件给服务端，服务端将文件保存在本地。
 */
public class TCPTest2 {

    /**
     *  客户端
     */
    @Test
    public void test1() throws IOException {

        FileInputStream fis = new FileInputStream("13.jpg");

        InetAddress inet = InetAddress.getByName("192.168.31.83");
        Socket socket = new Socket(inet,9630);
        OutputStream outputStream = socket.getOutputStream();

        byte[] bytes = new byte[12];
        int len;
        while ((len = fis.read(bytes)) != -1 ){
            outputStream.write(bytes,0,len);
        }

        fis.close();
        outputStream.close();
        socket.close();
    }


    /**
     *  服务端
     */
    @Test
    public void test2() throws IOException {

        ServerSocket serverSocket = new ServerSocket(9630);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileOutputStream fos = new FileOutputStream("15.jpg");

        int len;
        byte[] bytes = new byte[15];
        while ( (len = inputStream.read(bytes)) != -1 ){
            fos.write(bytes,0,len);
        }

        fos.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }

}
