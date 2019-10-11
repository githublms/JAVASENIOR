package com.example.exer;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author lms
 * @create 2019-10-10 17:49
 */
public class UdpTest {


    //客户端
    @Test
    public void test1() throws IOException {

        DatagramSocket socket = new DatagramSocket();
        String string = "wode数据是asdfasd";
        byte[] bytes = string.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length,inet,5820);
        socket.send(packet);
        socket.close();
    }
    //服务端
    @Test
    public void test2() throws IOException {

        DatagramSocket socket = new DatagramSocket(5820);
        byte[] bytes = new byte[4];
        DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        socket.close();


    }


}
