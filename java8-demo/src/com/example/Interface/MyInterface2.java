package com.example.Interface;

public interface MyInterface2 {

    void show();

    default void defaultMethod2(){
        System.out.println("这是一个接口中的默认方法");
    }

    public static void staticMethod2(){
        System.out.println("这是一个接口中的静态方法");
    }

}
