package com.example.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class TestLambda3 {

    //Consumer<T> : 消费型接口 -->> void accept(T t);
    @Test
    public void test1(){
        happy(55, x -> System.out.println("每次消费" + x + "元"));
    }

    public void happy(double num, Consumer consumer){
        consumer.accept(num);
    }


    // 产生指定个数的随机数，并将这些数放入集合中
    public List<Integer> getNumberList (Integer number, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < number ; i++) {
            Integer num =  supplier.get();
            list.add(num);
        }
        return list;
    }
    //  Supplier<T> : 供给型接口 T get();
    @Test
    public void test2(){
       List<Integer> list= getNumberList(5,() -> (int)(Math.random() * 100) ) ;
       System.out.println(list);
    }

    //需求：用于处理字符串
    public String strHandler(String string, Function<String,String> function) {
        return  function.apply(string);
    }
    // Function<T, R> : 函数型接口 -->> R apply(T t);
    @Test
    public void test3(){
         String str= strHandler("我是一只可爱的小乌龟", (x) -> x.substring(2,5));
        System.out.println(str);//一只可
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> getStr(List<String> strs, Predicate<String> predicate){
        List<String> list = new ArrayList<>();
        for (String str : strs) {
            if (predicate.test(str)){
                list.add(str);
            }
        }
        return list;
    }
    // Predicate<T> : 断言型接口 -->> boolean test(T t);
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "wwwx", "ok");
        List<String> stringList = getStr(list, x -> x.length() > 3);
        System.out.println(stringList);
        List<String> stringList2 = getStr(list, x -> x.length() > 3 && x.length() < 5);
        System.out.println(stringList2);
    }

}
