package com.example.exer;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * @author lms
 * @create 2019-10-14 9:46
 */
public class OtherTest {

    /**
     * 获取构造器结构
     */
    @Test
    public void test1(){
        Class clazz = Person.class;
        //获取当前运行时类及其父类的构造器结构
        Constructor[] arr = clazz.getConstructors();
        for (Constructor constructor : arr) {
            System.out.println(constructor);
        }

        //获取当前运行时类的构造器接口，不包括父类
        Constructor[] arrs = clazz.getDeclaredConstructors();
        for (Constructor constructor : arrs) {
            System.out.println(constructor);

        }
    }


    /**
     *  获取运行时的父类
     *  获取运行时带泛型的父类
     */
    @Test
    public void test2(){
        Class clazz  = Person.class;
        //获取运行时的父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass); //class com.atguigu.java1.Creature

        //获取运行时带泛型的父类
        Type type = clazz.getGenericSuperclass();
        System.out.println(type); // com.atguigu.java1.Creature<java.lang.String>
    }
}
