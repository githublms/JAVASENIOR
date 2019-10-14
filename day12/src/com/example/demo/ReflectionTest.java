package com.example.demo;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *  使用反射 前 后对比
 */
public class ReflectionTest {

    //使用反射前。通过new 的方式来创建对象和访问属性和方法
//    Person()
//    你好，我是一个人
    @Test
    public void test2(){
        Person person = new Person();
        person.age = 10; // age 属性为public
        person.show();
    }

    /** 运行结果
     Person{name='jack', age=15}
     Person{name='jack', age=16}
     你好，我是一个人
     **********不同之处如下******************
     Person{name='king', age=0}
     Person{name='query', age=0}
     我的国籍是：中国

     * @throws Exception
     */

    //使用反射后 ，通过反射的方式来创建对象和访问属性，方法
    @Test
    public void test3() throws Exception {
        //获取Clazz 的实例对象
        Class clazz = Person.class;
        //获取Clazz 的构造器，用来创建对象
        Constructor cons1 = clazz.getConstructor(String.class,int.class);
        //构造器被私有化了，要想调用构造器，必须 cons1.setAccessible(true);
        Object object = cons1.newInstance("jack",15);
        Person person = (Person) object;
        System.out.println(person); // Person{name='jack', age=15}

        //通过反射调用对象指定的属性，方法
        Field age = clazz.getDeclaredField("age");
        age.set(person,16); // Person{name='jack', age=16}
        System.out.println(person);

        Method method =  clazz.getDeclaredMethod("show");
        method.invoke(person);//你好，我是一个人

        // 通过上诉的例子，我们实现了与 new 方式一样的结果，那么反射方式有什么不同呢？
        System.out.println("**********不同之处如下******************");

        //通过反射，可以调用私有结构，比如，私有的构造器，属性等
        //调用私有的构造器
        Constructor cons2 = clazz.getDeclaredConstructor(String.class);
        cons2.setAccessible(true); //设置为true，解除校验，可以调用私有结构
        Object obj = cons2.newInstance("king");
        Person person1 = (Person) obj;
        System.out.println(person1); //Person{name='king', age=0}

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person1,"query");
        System.out.println(person1); // Person{name='query', age=0}

        //调用私有的方法
        Method  nation= clazz.getDeclaredMethod("showNation", String.class); //方法名 参数类型
        nation.setAccessible(true);
        nation.invoke(person1,"中国"); //我的国籍是：中国
    }

    /**
     *  疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
     *      建议使用 new 的方式来创建对象
     *
     *  什么时候会使用：反射的方式 ? --》》 反射的特征：动态性
     *
     *      1 在编译的时候不能确定要具体 new 哪一个类的对象，这时候就可以使用 反射的方式
     *      2 反射，可以用来调用私有的结构，
     *
     *  疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
            首先两则并不矛盾。
            反射机制解决的是私有属性能不能调用的问题
            封装性 解决的是 建议你怎么调用的问题--》》有的方法公共方法比你私有方法封装的更好
     */

    /**
     *  对java.lang.Class 类的理解 -- >> Class 是反射的源头
     *
     *  首先说类的加载过程：
     *      程序编写完之后，使用 javac.exe 命令（编译）后，我们会生成对应的字节码文件(.class )
     *      接着，我们使用 java.exe 命令对某个字节码文件进行解释运行，
     *      相当于将某个字节码文件加载到内存中，这个过程就叫类的加载，
     *      加载到内存中的类，我们叫运行时类，这个运行时类就作为一个Class的实例
     *
     *  换句话说：一个运行时类就对应着一个Class的实例
     *
     *  加载到内存中的类，会缓存一段时间(为什么会缓存一段时间呢？如果一直存在，就不能垃圾回收)，
     *  我们可以在这段时间内使用几种不同的方式来访问这个运行时类
     */
    //获取Class实例的4中方式，前三种掌握，后一种了解
    @Test
    public void test1() throws ClassNotFoundException {

        //通过类的属性来获取 Class 的实例 -->> 类的class属性
        Class clazz1 = Person.class;

        //通过一个类的实例(对象) 来获取 Class 的实例 -->> 对象的getClass 方法
        Person person = new Person("tom",12);
        Class clazz2 = person.getClass();

        //通过Class 的静态方法（forName()）来获取Class 的实例
        Class clazz3 = Class.forName("com.example.demo.Person");

        //返回的结果为 true，说明这几个实例都是同一个对象
        System.out.println(clazz1 == clazz2);// true
        System.out.println(clazz1 == clazz3); // true

        //通过类加载器 的loadClass()  获取 Class 的实例
        ClassLoader loader = ReflectionTest.class.getClassLoader();
        Class clazz4 = loader.loadClass("com.example.demo.Person");

        System.out.println(clazz1 == clazz4); // true

    }

    /**
     *  java中哪些结构可以作为Class 的实例呢？
     *  答案是所有结构都可以作为Class的实例，具体如下:
     */
    @Test
    public void test4(){
        Class c1 = Object.class; //类
        Class c2 = Comparable.class;//接口
        Class c3 = String[].class;//数组
        Class c4 = int[][].class;//二维数据
        Class c5 = ElementType.class;//枚举
        Class c6 = Override.class;//注解
        Class c7 = int.class;//基本数据类型
        Class c8 = void.class;//空
        Class c9 = Class.class;//Class实例

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }

}
