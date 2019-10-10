package com.example.demo;


import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;

/**
 *  File 类的使用
 *      File类的一个对象，代表一个文件或者一个文件目录(文件夹)
 *      File类声明在io包下面
 *      File类涉及到文件或者文件目录的创建，删除，重命名，修改时间，文件大小等方法
 *          并没有涉及文件内容的操作，读取或者写入文件内容，必须通过IO流来进行操作
 *      File对象常作为参数传递到流的构造器中，作为读取/写入的终点
 */
public class FileTest {

    /*
    1.如何创建File类的实例
        File(String filePath)
        File(String parentPath,String childPath)
        File(File parentFile,String childPath)

    2.
    相对路径：相较于某个路径下，指明的路径。
    绝对路径：包含盘符在内的文件或文件目录的路径

    3.路径分隔符
     windows:\\
     unix:/
     */
    @Test
    public void test1(){
        File file1 = new File("hello.txt");
        File file2 = new File("F:\\ideaworkspace-senior\\day10\\hi.txt");

        System.out.println(file1);//hello.txt
        System.out.println(file2);//F:\ideaworkspace-senior\day10\hi.txt

        File file3 = new File("F:\\ideaworkspace-senior","TEST");
        System.out.println(file3);//F:\ideaworkspace-senior\TEST

        File file4 = new File(file3,"test2");
        System.out.println(file4);//F:\ideaworkspace-senior\TEST\test2

        String path = File.separator;
        File file = new File("C:" + path ,"test3");
        System.out.println(file);//C:\test3
    }

        /* 文件或者目录存在，则返回对应信息，如果不存在，则返回各个属性的默认值
public String getAbsolutePath()：获取绝对路径
public String getPath() ：获取路径
public String getName() ：获取名称
public String getParent()：获取上层文件目录路径。若无，返回null
public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
public long lastModified() ：获取最后一次的修改时间，毫秒值

如下的两个方法适用于文件目录：
public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
     */
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(file1.lastModified());
        System.out.println(new Date(file1.lastModified()));
        System.out.println("********************");
        File file2 = new File("F:\\hello.txt");
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
        System.out.println(new Date(file2.lastModified()));
    }
    @Test
    public void test3(){
        File file = new File("F:\\ideaworkspace-senior\\");

        String[] arr = file.list();
        for (String string : arr) {
            System.out.println(string);
        }
        System.out.println("*********");
        File[] arrs = file.listFiles();
        for (File arr1 : arrs) {
            System.out.println(arr1);
        }
    }

    /*
        public boolean renameTo(File dest):把文件重命名为指定的文件路径
        比如：file1.renameTo(file2)为例：
        要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
     */
    @Test
    public void test4(){
        File file1 = new File("hello1.txt");
//        File file2 = new File("hello0.txt"); // true
        File file2 = new File("C:\\hello3.txt");//false
        boolean boo = file1.renameTo(file2);
        System.out.println(boo);
    }
        /*
public boolean isDirectory()：判断是否是文件目录
public boolean isFile() ：判断是否是文件
public boolean exists() ：判断是否存在
public boolean canRead() ：判断是否可读
public boolean canWrite() ：判断是否可写
public boolean isHidden() ：判断是否隐藏
     */
    @Test
    public void test5(){
        File file1 = new File("hello1.txt");
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
        System.out.println("********************");
        File file2 = new File("d:\\io");
        file2 = new File("d:\\io1");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());
    }

       /*
    创建硬盘中对应的文件或文件目录
public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建

    删除磁盘中的文件或文件目录
public boolean delete()：删除文件或者文件夹 ，要求对应的路径下面没有任何文件
    删除注意事项：Java中的删除不走回收站。
     */
    @Test
    public void test6(){
           File file1 = new File("hi.txt");
           if (!file1.exists()){
               try {
                  boolean boo = file1.createNewFile();
                   System.out.println(boo);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
    @Test
    public void test7(){
        File file = new File("F:\\io");
        if (!file.exists()){
            file.mkdir();
        }
        File file2 = new File("F:\\io2\\12\\");
        if (!file2.exists()){
            file2.mkdirs();
        }
        System.out.println(file2.delete());
        file.deleteOnExit();
    }
}
