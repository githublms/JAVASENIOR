package com.example.exer;

import com.example.lambda.Employee;
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
public class InterfaceTest {

    public void happy(double money, Consumer con){
        con.accept(money);
    }
    @Test
    public void test1(){
        happy(50, (x) -> System.out.println("每次固定消费" + x + "元"));
    }
    public List<Double> getRandom(int count, Supplier supplier){
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double num = (double) supplier.get();
            list.add(num);
        }
        return list;
    } 
    @Test
    public void test2(){
        List<Double> doubleList= getRandom(5, ()-> Math.random());
        System.out.println(doubleList);
    }
    public Integer integerHandler(Integer num, Function function){
        Integer handler = (Integer) function.apply(num);
        return handler;
    }
    @Test
    public void test3(){
        Integer integer= integerHandler(5, (x) -> x.hashCode());
        System.out.println(integer);
    }
    public List<String> getStr(List<String> list, Predicate<String> predicate){
        List strList =  new ArrayList();
        for (String str : list) {
            boolean test = predicate.test(str);
            if (test){
                strList.add(str);
            }
        }
        return strList;
    }
    @Test
    public void test4(){
        List<String> strings = Arrays.asList("hello","good","byaba","weafs","test");
        List<String> str = getStr(strings, x -> x.length() == 4);
        System.out.println(str);
    }

    @Test
    public void test5(){
        Employee.method1();
        Employee employee = new Employee();
        employee.method2();

    }
}
