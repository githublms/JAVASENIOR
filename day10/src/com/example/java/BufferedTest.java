package com.example.java;

import org.junit.Test;

import java.io.*;
import java.util.function.BooleanSupplier;

/**
 * 处理流之一：缓冲流的使用
 *
 * 1.缓冲流：
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 2.作用：提供流的读取、写入的速度
 *   提高读写速度的原因：内部提供了一个缓冲区
 *
 * 3. 处理流，就是“套接”在已有的流的基础上。
 */
public class BufferedTest {

    //使用Buffered流来复制非文本文件
    @Test
    public void test1() {

        File srcFile = new File("3.mp4");
        File destFile = new File("4.mp4");

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            byte[] bytes = new byte[5];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //说明 :关闭了外层流，就可以不用关闭内层流==fis.close() fos.close()
    }

    //使用Buffered来实现文本文件的复制
    @Test
    public  void  test2() throws IOException {

        File srcFile = new File("hello.txt");
        File destFile = new File("hello4.txt");

        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(destFile);

        BufferedReader bfr = new BufferedReader(fr);
        BufferedWriter bfw = new BufferedWriter(fw);
        char[] chars = new char[5];
        int len ;
        //方式一
//        while ((len = bfr.read(chars)) != -1 ){
//            bfw.write(chars,0,len);
//        }

        //方式二
        String line ;
        while ((line = bfr.readLine())!= null) {
            bfw.write(line);
            bfw.newLine();
        }
        bfr.close();
        bfw.close();














    }





}
