package com.example.demo;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给客户端。
 * 并关闭相应的连接。
 *
 *  这里需要注意的是 我们需要在图片传输完成之后，要及时关闭了流，并且准备接受流来接受服务端返回的数据
 */
public class TCPTest3 {
    /**
     *  客户端
     */
    @Test
    public void test1() throws IOException {

        FileInputStream fis = new FileInputStream("3.mp4");

        InetAddress inet = InetAddress.getByName("192.168.31.83");
        Socket socket = new Socket(inet,9630);
        OutputStream outputStream = socket.getOutputStream();

        byte[] bytes = new byte[12];
        int len;
        while ((len = fis.read(bytes)) != -1 ){
            outputStream.write(bytes,0,len);
        }
        //关闭数据的输出 ,
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int data ;
        byte[] bytes1 = new byte[48];
        while ((data =inputStream.read(bytes1)) != -1){
            baos.write(bytes1,0,data);
//            System.out.print(new String(bytes1,0,data));
        }
        System.out.println(baos.toString());

        inputStream.close();
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

        FileOutputStream fos = new FileOutputStream("4.mp4");

        int len;
        byte[] bytes = new byte[15];
        while ( (len = inputStream.read(bytes)) != -1 ){
            fos.write(bytes,0,len);
        }

        System.out.println("图片传输完成");

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("服务端说，我已经接收到你传过来的视频了".getBytes() );

        outputStream.close();
        fos.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
