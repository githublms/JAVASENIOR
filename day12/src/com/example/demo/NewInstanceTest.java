package com.example.demo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class NewInstanceTest {

    /**
     *  通过反射来创建运行时类的对象
     *
     *  通过调用 newInstance() 方法 来创建 运行时类 的对象
     *
     *  要想此方法 正常的创建一个运行类的对象，这个对象必须满足以下条件
     *
     *  1. 这个运行时类必须有空参构造器
     *  2. 空参构造器的访问权限必须得够，通常设置为 public
     *
     *  为什么要求一个javabean 必须有一个空参的构造器，原因如下:
     *      便于反射时，创建运行时的对象
     *      子类继承运行类的对象，使用super(),会默认调用父类的构造器，要保证这个运行时类有此构造器
     */
    @Test
    public void test1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Person.class;
        Constructor cons = clazz.getDeclaredConstructor();
        Object object = cons.newInstance(); // 这里打印了 Person()，说明newInstance()还是调用了 Person 的构造器
        System.out.println(object); // Person{name='null', age=0}
    }

    // 体会: 反射的多态性 ---》》 在编译的时候不知道要 new 哪一个具体的类。
    // 在运行的时候才知道要创建哪一个具体的对象
    @Test
    public void test2() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Random random = new Random();
        int num = random.nextInt(3);
        System.out.println(num);
        switch (num){
            case 0:
                System.out.println(getInstance("java.lang.Object"));
                break;
            case 1:
                System.out.println(getInstance("java.util.Date"));
                break;
            case 2:
                System.out.println(getInstance("com.example.demo.Person"));
                break;

        }
    }

    public Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(classPath);
       return  clazz.newInstance();
    }


}
