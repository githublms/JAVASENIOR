package com.example.exer;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *  获取当前运行时类的属性结构
 */
public class FiledTest {


    /** 运行结果
     public int com.atguigu.java1.Person.id
     public double com.atguigu.java1.Creature.weight
     ===================
     private java.lang.String com.atguigu.java1.Person.name
     int com.atguigu.java1.Person.age
     public int com.atguigu.java1.Person.id
     */

    @Test
    public void test1(){
        Class clazz = Person.class;

        // getFields 获取当前运行时类及其父类的所有public 修饰的属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("===================");

        //getDeclaredFields 获取当前运行时类的所有属性，包括私有和非私有，不包括父类的属性
        Field[] arr = clazz.getDeclaredFields();
        for (Field field : arr) {
            System.out.println(field);
        }
    }

    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){

        Class clazz = Person.class;
        Field[] arr = clazz.getDeclaredFields();
        for (Field field : arr) {
//            System.out.println(field);

            // 权限修饰符
            int number = field.getModifiers();
            System.out.print(Modifier.toString(number) + "\t");

            // 数据类型
            Class type = field.getType();
            System.out.print(type.getName() + "\t");

            // 变量名
            String name = field.getName();
            System.out.print(name);

            System.out.println();
        }



    }






}
