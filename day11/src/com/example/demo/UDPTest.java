package com.example.demo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.*;

/**
 *
 */
public class UDPTest{

    /**
     *  客户端
     */
    @Test
    public void test1() throws IOException {

        DatagramSocket datagramSocket = new DatagramSocket();

        String data = "我是通过UDP方式发送的数据";
        byte[] bytes = data.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length,inet,7894);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
        System.out.println("我是发送端，我已经发送了数据");

    }

    /**
     *  服务端
     */
    @Test
    public void test2() throws IOException {
        DatagramSocket socket = new DatagramSocket(7894);

        byte[] buffer = new byte[2000];
        DatagramPacket datagramPacket = new DatagramPacket(buffer,0,buffer.length);
        socket.receive(datagramPacket);
        //从接收端发送过来的数据，进行显示 ，由于接受的数据受限，并不能保证所有数据都能够接受
        System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getLength()));
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        baos.write(buffer,0,buffer.length);
//        System.out.println(baos.toString());
        socket.close();
    }

}
