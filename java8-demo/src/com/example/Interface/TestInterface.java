package com.example.Interface;


import org.junit.Test;

public class  TestInterface{

    // 如果实现的接口中有重名的方法，必须指定实现某一个具体接口的方法
    @Test
    public void test1(){
        MyClass myClass = new MyClass();
        myClass.show();
    }


    //测试接口中的默认方法 -->> 对象调用方法
    @Test
    public void  test2(){
        MyClass myClass = new MyClass();
        myClass.defaultMethod();//这是一个接口中的默认方法
    }
    //类优先原则，接口中有一个默认方法，父类中有一个普通方法，两个方法同名，当子类调用这个方法时，如何选择？
    //MyClass extends MySuperClass implements MyInterface1
    @Test
    public void test4(){
        MyClass myClass = new MyClass();
        myClass.defaultMethod(); //MySuperClass中的普通方法
    }

    //测试接口中的静态方法 --》》 接口名直接调用静态方法
    @Test
    public void test3(){
        MyInterface1.staticMethod();//这是一个接口中的静态方法
    }
}
