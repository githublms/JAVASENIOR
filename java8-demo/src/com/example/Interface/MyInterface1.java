package com.example.Interface;



public interface MyInterface1 {

    void show();

    default void defaultMethod(){
        System.out.println("这是一个接口中的默认方法");
    }

    public static void staticMethod(){
        System.out.println("这是一个接口中的静态方法");
    }



}
