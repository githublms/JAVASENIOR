package com.example.java1;

import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用 -->> 主要用来读取和存储对象的流
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。
 *      它的强大之处就是可以把Java中的对象写入到数据源中（序列化），也能把对象从数据源中还原回来（反序列化）。
 *
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 * 4.序列化机制：
 * 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种
 * 二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
 * 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。
 *
 */
public class ObjectInputOutputStreamTest {

    /**
     *  实现序列化操作 将内存中的对象通过序列化保存到硬盘上
     */
    @Test
    public void test1() {

        ObjectOutputStream obs = null;
        try {
            FileOutputStream fis = new FileOutputStream("object.txt");
            obs = new ObjectOutputStream(fis);

            obs.writeObject(new String("我的祖国"));
            obs.flush();
            obs.writeObject(new Person("tom",12,1,new Account(100)));
            obs.flush();
            obs.writeObject(new Person("jerry",15));
            obs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (obs != null){
                try {
                    obs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  反序列化操作 ，将二进制数据转化成内存中的java 对象
     *
     */
    @Test
    public void test2() throws Exception {

        FileInputStream fis = new FileInputStream("object.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object object = ois.readObject();
        System.out.println(object);
        Person person= (Person) ois.readObject();
        System.out.println(person);
        Person person1 = (Person) ois.readObject();
        System.out.println(person1);
        ois.close();
    }



}
