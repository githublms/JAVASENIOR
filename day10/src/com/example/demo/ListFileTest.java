package com.example.demo;

import java.io.File;

/**
 * 3. 遍历指定目录所有文件名称，包括子文件目录中的文件。
 * 拓展1：并计算指定目录占用空间的大小
 * 拓展2：删除指定文件目录及其下的所有文件
 */
public class ListFileTest {

    public static void main(String[] args) {
        //递归方式获取所有的文件
        File file = new File("F:\\尚硅谷Java核心基础_2019年版\\课件笔记源码资料\\3_软件");
        printFile(file);
        System.out.println("****************");
        listAllFile(file);

        //获取指定目录的大小
        long size = getDirsSize(file);
        System.out.println("指定目录大小是:" + size);

        deleteDirs(new File("C:\\Users\\LMS\\Desktop\\新建文件夹\\"));
    }

    //递归方法（自己调用自己）得到指定目录下所有的文件名称，包含子目录下面的文件
    public static void printFile(File dir){
        File[] arr = dir.listFiles();
        for (File file : arr) {
            if (file.isDirectory()){
                printFile(file);
            }else{
                System.out.println(file);
            }
        }
    }

    //通过递归方式来获取指定目录下面所有的文件
    public static void listAllFile(File file){
        if (file.isFile()){
            System.out.println(file);
        }else{
           File[] arr = file.listFiles();
            for (File file1 : arr) {
                listAllFile(file1);
            }
        }
    }

    //拓展1：并计算指定目录占用空间的大小
    public static long getDirsSize(File file){
        int size = 0 ;
        if (file.isFile()){
            size += file.length();
        }else{
            File[] list = file.listFiles();
            for (File file1 : list) {
                size += getDirsSize(file1);
            }
        }
        return size;
    }

    //拓展2：删除指定文件目录及其下的所有文件

    public static void deleteDirs(File file){
        if (file.isFile()){
            file.deleteOnExit();
        }else{
            File[] list = file.listFiles();
            for (File file1 : list) {
                deleteDirs(file1);
            }
            file.delete();
        }
    }





}
