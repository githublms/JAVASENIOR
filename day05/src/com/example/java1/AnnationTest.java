package com.example.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 *  注解的使用
 *  理解Annation ===== 5.0 新增内容
 *
 *      Annation 其实是代码中的特殊标记，这些标记可以在 编译 类加载，运行时被读取，并做相应的处理。
 *      通过使用Annation 可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息
 *
 *      javaSE中注解过于简单，主要是用来 标记过时，忽略警告等
 *      javaEE中注解较为重要，替代了xml配置和冗余配置
 *
 *  Annation 注解使用示例
 *      示例一:生成文档相关的注解
 *
 *      示例二:在编译时，进行格式检查（jdk内置的三个基本注解）
 *          @Override : 限定重写父类方法，只能用在方法上
 *          @Deprecated : 标记当前结构已经过时，有更好的替代。 主要用于方法 或者 类
 *          @SuppressWarnings : 抑制编译器警告
 *      示例三:替代冗余的配置文件
 *
 *  如何自定义注解呢？参照@supperressWarnings 定义
 *      1: 注解声明为 @interface
 *      2: 内部定义成员，通常使用 value 表示
 *      3: 可以给成员指定默认值 ，default
 *      4: 如果自定义的注解没有成员 ，表明是一个标识作用
 *
如果注解有成员，在使用注解时，需要指明成员的值。 比如: @springbootApplication(bask...)

自定义注解必须配上注解的信息处理流程(使用反射)才有意义。
自定义注解通过都会指明两个元注解：Retention、Target

 *
 *
    @SuppressWarnings 的 源码如下:

    @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SuppressWarnings {
        String[] value();
    }

 *  看到上诉的注解源码:主要关注四种元注解(对现有的注解进行解释说明的注解)
 *      Retention : 指明注解的声明周期
            SOURCE\CLASS（默认行为）\RUNTIME （源码\类加载\运行时）
            只有声明为RUNTIME生命周期的注解，才能通过反射获取。
 *      Target: 指明注解能够修饰的元素
 *
 *      Documented : 所修饰的注解在被javadoc时，会被保留下来
 *      Inherited : 这个注解具有继承性
 *
 * 5.通过反射获取注解信息 ---到反射内容时系统讲解
 *
 *  java8 中注解新特性
 *      可重复注解 和 类型注解
 *      可重复注解: 可以重复使用的注解
            ① 在MyAnnotation上声明 @Repeatable，成员值为MyAnnotations.class
            ② MyAnnotation的Target和Retention等元注解与MyAnnotations相同。

 *      类型注解: 使用在 @Target枚举对象中 java8 以前注解只能在定义的地方，java8之后，可以在任何地方
            ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
            ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
 */

/** 实例一:
 * @author lms
 * @version 1.0.2
 */
public class AnnationTest {
    public static void main(String[] args) {
        Person person = new Student();
        person.walk();//Student 重写父类的方法 =--- 使用@override方法

        @SuppressWarnings("unused") //抑制编译器警告 == 得到的结果是: num下面没有下划线了
        int num = 10;

        @SuppressWarnings({ "unused", "rawtypes" })
        ArrayList list = new ArrayList();

        Date date = new Date(2012,10,20);//进入源码。可以看到被@Deprecated修饰
    }

    //通过反射来获取注解信息
    //运行结果:@com.example.java1.MyAnnation(values=hello)
    @Test
    public void testGetAnnotation(){
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for(int i = 0;i < annotations.length;i++){
            System.out.println(annotations[i]);
        }
    }
}

//class Generic<@MyAnnotation T>{
//
//    public void show() throws @MyAnnotation RuntimeException{
//
//        ArrayList<@MyAnnotation String> list = new ArrayList<>();
//
//        int num = (@MyAnnotation int) 10L;
//    }
//
//}


//jdk 8之前的写法：
//@MyAnnations({@MyAnnation(value="hi"),@MyAnnation(value="hi")})
@MyAnnation(value="hi")
@MyAnnation(value="abc")
class Person {

    private String name;
    private int age;

    @Deprecated
    public void walk(){
        System.out.println("人走路");
    }
    public void eat(){
        System.out.println("人吃饭");
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

interface Info{
    void show();
}

class Student extends Person implements Info {

    @Override
    public void show() {
        System.out.println("重写方法");
    }

    @Override
    public void  walk(){
        System.out.println("Student 重写父类的方法");
    }

}