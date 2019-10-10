package com.example.demo;

import java.io.File;

//练习:遍历指定目录下面的所有文件和统计指定目录的大小，及删除目录大小
public class FileExer {

    public static void main(String[] args) {

        getAllFiles(new File("F:\\尚硅谷Java核心基础_2019年版\\课件笔记源码资料\\6_每日一考与复习"));
        long size =  getDirSize(new File("F:\\尚硅谷Java核心基础_2019年版\\课件笔记源码资料\\6_每日一考与复习"));
        System.out.println("指定目录大小是:" + size);

    }

    public static void getAllFiles(File file){
        if (file.isFile()){
            System.out.println(file);
        }else{
            File[] arr = file.listFiles();
            for (File file1 : arr) {
                getAllFiles(file1);
            }
        }
    }

    public static long getDirSize(File file){
        int size = 0 ;
        if (file.isFile()){
            size += file.length();
        }else{
            File[] arr = file.listFiles();
            for (File file1 : arr) {
                size += getDirSize(file1);
            }
        }
        return size;
    }


}
