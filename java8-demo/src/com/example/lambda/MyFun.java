package com.example.lambda;

@FunctionalInterface
public interface MyFun {

    Integer getValue(Integer num);
    default void getValue2(Integer num){

    }
    static void getValue3(Integer num){

    }
}
