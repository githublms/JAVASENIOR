package com.example.annatation;


import org.junit.Test;

import java.lang.reflect.Method;

public class TestAnnatation {

    @MyAnnatation(value = "king-01")
    @MyAnnatation(value = "king-02")
    public void show(){

    }

//    king-01
//    king-02
    //通过反射来查看注解的内容 -->> 可重复性注解
    @Test
    public void test1() throws Exception {
        Class<TestAnnatation> clazz = TestAnnatation.class; //拿到反射对象
        Method method= clazz.getMethod("show");//找到对应的方法
        MyAnnatation[] mys = method.getDeclaredAnnotationsByType(MyAnnatation.class);//获取方法上对应的注解
        for (int i = 0; i < mys.length; i++) {
            System.out.println(mys[i].value());
        }
    }

    //类型注解
    public void show(@MyAnnatation(value = "parm") String name){

    }
    //类型注解
    @Test
    public void test2(){

    }


}
