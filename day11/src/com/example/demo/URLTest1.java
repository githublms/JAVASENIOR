package com.example.demo;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 *  通过url 获取(下载)资源
 */
public class URLTest1 {

    /**
     *  从网络上下载一个图片到本地 : 图片地址：http://b-ssl.duitang.com/uploads/item/201612/16/20161216235942_Lmc4W.jpeg
     */
    @Test
    public void test1() throws IOException {

        URL url = new URL("http://b-ssl.duitang.com/uploads/item/201612/16/20161216235942_Lmc4W.jpeg");

        //域名对应的ip
        InetAddress inetAddress = InetAddress.getByName(url.getHost());
        System.out.println(inetAddress); //  b-ssl.duitang.com/223.86.152.238

        // url 基础信息
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getFile());
        System.out.println(url.getQuery());

        //下载图片
        //打开链接 -->> 这里需要转换下，保证这个连接是可以手动关闭的， 所以使用 HttpURLConnection
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();

        //读取数据--》》 input
        InputStream inputStream = urlConnection.getInputStream();
        // 保存数据 --》》 output
        FileOutputStream fos = new FileOutputStream("download.jpeg");

        int len;
        byte[] bytes = new byte[10];
        while((len = inputStream.read(bytes)) != -1 ){
            fos.write(bytes,0,len);
        }

        System.out.println("下载完成");
        fos.close();
        inputStream.close();
        urlConnection.disconnect();
    }
}
