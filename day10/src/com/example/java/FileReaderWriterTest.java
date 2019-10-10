package com.example.java;

import org.junit.Test;

import java.io.*;

/**
 * 一、流的分类：
 * 1.操作数据单位：字节流、字符流
 * 2.数据的流向：输入流、输出流
 * 3.流的角色：节点流、处理流
 *
 * 二、流的体系结构
 * 抽象基类         节点流（或文件流）                               缓冲流（处理流的一种）
 * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
 * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
 * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
 * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
 */
public class FileReaderWriterTest {

    public static void main(String[] args) {

        File file = new File("test.txt"); // 相对路径在当前项目下面
        System.out.println(file.getAbsolutePath());// F:\ideaworkspace-senior\day10\test.txt

        File file1 = new File("day10\\test.txt");
        System.out.println(file1.getAbsolutePath());//F:\ideaworkspace-senior\day10\day10\test.txt
    }

    /*
  将day10下的hello.txt文件内容读入程序中，并输出到控制台

  说明点：
  1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
  2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
  3. 读入的文件一定要存在，否则就会报FileNotFoundException。
   */
    @Test
    public void test1() {

        //1创建文件对象
        File file = new File("hello.txt");
//        System.out.println(file.getAbsolutePath());//F:\ideaworkspace-senior\day10\hello.txt
        //2.提供具体的流操作，读取文件中的内容
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            //3.数据的读入
            //read():返回读入的一个字符。如果达到文件末尾，返回-1
            //方式一：
//            int data = fr.read();
//            while (data != -1 ){
//                System.out.print((char) data);
//                data = fr.read();
//            }
            //方式二 对方式一的优化
            int data ;
            while ((data = fr.read()) != -1){
                System.out.print((char)data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //方式一
//            try {
//                if (fr != null) {
//                    fr.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //方式二
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //对read()方法的使用升级,read()的重载方法
    @Test
    public void test2() {

        //1创建文件对象
        File file = new File("hello.txt");
//        System.out.println(file.getAbsolutePath());//F:\ideaworkspace-senior\day10\hello.txt
        //2.提供具体的流操作，读取文件中的内容
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            //3.数据的读入
            //read():返回读入的一个字符。如果达到文件末尾，返回-1
            //方式一：
//            int data = fr.read();
//            while (data != -1 ){
//                System.out.print((char) data);
//                data = fr.read();
//            }
            //方式二 对方式一的优化
            char[] cbuf = new char[4];
            int data ;
            while ((data = fr.read(cbuf)) != -1){
//                System.out.print((char)data);
                //方式一：错误的写法
//                for (int i = 0; i < cbuf.length; i++) {
//                    System.out.print(cbuf[i]);//得到的结果是: helloworldor
//                }
                //方式一:正确的写法
//                for (int i = 0; i < data; i++) {
//                    System.out.print(cbuf[i]);//helloworld
//                }
                //方式二 :错误的写法
//                String str = new String(cbuf);
//                System.out.print(str);//helloworldor
                //方式二:正确的写法
                String string = new String(cbuf,0,data);
                System.out.print(string);//helloworld


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //方式一
//            try {
//                if (fr != null) {
//                    fr.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //方式二
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

     /*
    从内存中写出数据到硬盘的文件里。

    说明：
    1. 输出操作，对应的File可以不存在的。并不会报异常
    2.
         File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
         File对应的硬盘中的文件如果存在：
                如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
                如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容

     */
     @Test
     public void test3() {
         File file = new File("hello1.txt");
         FileWriter fw = null;
         try {
//             fw = new FileWriter(file,false);//false == 只输出 你好啊
             fw = new FileWriter(file,true);//true == 原有文件内容的追加
             fw.write("你好啊good");
             fw.flush();
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             if (fw != null) {
                 try {
                     fw.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
     }

     @Test
     public void test4()  {
        File file1 = new File("hello.txt");
        File file2 = new File("hello2.txt");

//         File file1 = new File("13.jpg");//字符流不能处理字节数据
//         File file2 = new File("14.jpg");

         FileReader fileReader = null;
         FileWriter fileWriter = null;
         try {
             fileReader = new FileReader(file1);
             fileWriter = new FileWriter(file2);
             char[] cbuf = new char[3];
             int len ;
             while ((len = fileReader.read(cbuf)) != -1){
                 for (int i = 0; i < len; i++) {
                     System.out.print(cbuf[i]);
//                     fileWriter.write(cbuf[i]);
                 }
                 fileWriter.write(cbuf,0,len);
             }
         } catch (IOException e) {
             e.printStackTrace();
         } finally {
             if (fileReader != null){
                 try {
                     fileReader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if (fileWriter != null){
                 try {
                     fileWriter.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
}













