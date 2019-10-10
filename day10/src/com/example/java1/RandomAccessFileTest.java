package com.example.java1;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 *  RandomAccessFile 随机存取流
 *      既可以座位输入流，又可以作为输出流
 *          与其他流不同的是，随机流多了一个指针，通过seek方法实现
 *          可以在指定的位置进行插入操作
 *
 * RandomAccessFile的使用
 * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
 *
 * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
 *
 * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 */
public class RandomAccessFileTest {

    /**
     *  RandomAccessFile 作为输入流和输出流
     */
    @Test
    public void test1() throws Exception {
        RandomAccessFile in = new RandomAccessFile("13.jpg","rw");
        RandomAccessFile out = new RandomAccessFile("25.jpg","rw");

        byte[] bytes = new byte[5];
        int len ;
        while ((len = in.read(bytes) )!= -1 ){
            out.write(bytes,0,len);
        }
        in.close();
        out.close();
    }

    /**
     *  使用RandomAccessFile 实现 数据插入功能
     */
    @Test
    public void  test2() throws Exception {
        RandomAccessFile out = new RandomAccessFile("random.txt","rw");
        out.seek(5);
        out.writeUTF("abc我爱我的祖国");
        out.close();
    }
}
