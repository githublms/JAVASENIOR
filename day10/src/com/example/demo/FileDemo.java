package com.example.demo;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * @author lms
 * @create 2019-10-09 11:22
 */
public class FileDemo {
    //创建一个同目录下面的另外一个文件,这个目录必须存在才行
    @Test
    public void test2() throws IOException {
        File file = new File("F:\\test\\hello.txt");
        File file1 = new File(file.getParent(),"ha.txt");
        if (!file1.exists()){
            file1.createNewFile();
        }
    }

//    课后练习2：判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
    @Test
    public void test1(){
        File file = new File("C:\\Users\\LMS\\Desktop\\img");
        String[] arr = file.list();
        for (String string : arr) {
            if (string.endsWith(".jpg")){
                System.out.println(string);
            }
        }
        System.out.println("*********************");
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.getName().endsWith(".jpg")){
                System.out.println(file1.getName());
            }

        }
        System.out.println("======================");
        /*
         * File类提供了两个文件过滤器方法 ,这个过滤器中的accept方法只保留指定格式的数据，其余的不用
         *      public String[] list(FilenameFilter filter)
         *      public File[] listFiles(FileFilter filter)
         */
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean boo = name.endsWith(".jpg");
                return boo;
            }
        });
        for (String str : list) {
            System.out.println(str);
        }
    }
}
