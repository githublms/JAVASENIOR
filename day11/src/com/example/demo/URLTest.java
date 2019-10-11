package com.example.demo;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL网络编程
 * 1.URL:统一资源定位符，对应着互联网的某一资源地址
 * 2.格式：
 *  http://localhost:8080/examples/beauty.jpg?username=Tom
 *  协议   主机名    端口号  资源地址           参数列表
 *
 */
public class URLTest {

    /**
     *  测试示例
     * @throws MalformedURLException
     */
    @Test
    public void test1() throws MalformedURLException {
        URL url = new URL("http://localhost:8080/examples/beauty.jpg?username=Tom");

        //获取 url 的协议名称
        System.out.println(url.getProtocol()); //http
        //获取 url 的主机名
        System.out.println(url.getHost()); //localhost
        //获取 url 的端口
        System.out.println(url.getPort());//8080
        //获取 url 的文件名 --》》端口之后的数据
        System.out.println(url.getFile());// examples/beauty.jpg?username=Tom
        //获取 url 的查询名 -->> ？ 之后的数据
        System.out.println(url.getQuery());// username=Tom
    }

    /**
     *  测试rtsp 格式是否正确 rtsp -->> http
     https
     192.168.31.225
     554
     /Streaming/Channels/102
     null
     */
    @Test
    public void test2() throws MalformedURLException {
        URL url = new URL("https://admin:abcd1234@192.168.31.225:554/Streaming/Channels/102");
        //获取 url 的协议名称
        System.out.println(url.getProtocol());
        //获取 url 的主机名
        System.out.println(url.getHost());
        //获取 url 的端口
        System.out.println(url.getPort());
        //获取 url 的文件名 --》》端口之后的数据
        System.out.println(url.getFile());
        //获取 url 的查询名 -->> ？ 之后的数据
        System.out.println(url.getQuery());
    }


}
