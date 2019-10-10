package com.example.java;


import org.junit.Test;

import java.io.*;

public class PictureTest{

    /**
     *  异或去在异或回来，还是其本身
     */
    @Test
    public void test3(){
        int a = 5 ;
        int k = a ^ 5 ;
        System.out.println(k);//0
        int m = k ^ 5 ;
        System.out.println(m);//5
    }

    /**
     *  图片的加密操作
     */
    @Test
    public void test1() throws Exception {
        File src = new File("meitu.png");
//        File desc = new File("meitu-secret.png");
        File desc = new File("meitu-secret2.png");

        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(desc);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] bytes = new byte[1024];
        int len ;
        while ((len = bis.read(bytes)) != -1){
//            for (byte aByte : bytes) { // 这种方式加密无效，因为每次赋予的变量都是abyte，并不是数组中的每个元素进行异或
//                aByte = (byte) (aByte ^ 5);
//            }
            for (int i = 0; i < len; i++) {
                bytes[i] = (byte) (bytes[i] ^ 5); // 这样才完成了图片加密的操作
            }
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.close();
    }

    /**
     *  图片的解密操作 ，加密和解密 的过程相反 加密是 ^5 ,解密在 ^5 就回到了原始图片
     */
    @Test
    public void test2() throws IOException {

        File src = new File("meitu-secret2.png");
        File desc = new File("meitu-decode.png");

        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(desc);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] bytes = new byte[1024];
        int len ;
        while ((len = bis.read(bytes)) != -1){
//            for (byte aByte : bytes) { // 这种方式加密无效，因为每次赋予的变量都是abyte，并不是数组中的每个元素进行异或
//                aByte = (byte) (aByte ^ 5);
//            }
            for (int i = 0; i < len; i++) {
                bytes[i] = (byte) (bytes[i] ^ 5); // 这样才完成了图片解密的操作
            }
            bos.write(bytes,0,len);
        }
        bis.close();
        bos.close();
    }
}
