package com.example.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 方法引用是lambda的另外一种表现形式，并且方法引用中
    方法的调用涉及到的 参数列表和 返回类型要和函数式接口中抽象方法的参数列表和返回类型相同


 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 *  方法的调用形式： 类名.静态方法名，对象.实例方法名
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 *
 */
public class TestMethodRef {

    //对象的引用 :: 实例方法名
    @Test
    public void test1(){
        PrintStream ps = System.out;
        ps.println("hello");

        Consumer consumer = (x) -> ps.println("输出" + x);
        consumer.accept("125");

        System.out.println("-----------------");

        Consumer consumer1 = ps::println;
        consumer1.accept("123");
        consumer1 = System.out::println;
        consumer1.accept("789");
    }
    //对象的引用 :: 实例方法名
    @Test
    public void test2(){
        Employee employee = new Employee(1,"张三",15,5000.0);
        Supplier supplier = () -> employee.getName();
        System.out.println(supplier.get());
        System.out.println("-------------------------------");

        Supplier supplier1 = employee::getAge;
        System.out.println(supplier1.get());
    }

    // 类名 :: 静态方法名
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(5,7);
        System.out.println(comparator.compare(5, 12));

        System.out.println("------------------------");

        Comparator<Integer> con = Integer::compare;
        System.out.println(con.compare(12, 16));
    }
    //类名 :: 实例方法名 -->> 有点特殊
    @Test
    public void test4() {
        BiPredicate bp = (x, y) -> x.equals(y);
        boolean bool = bp.test("12", "15");
        System.out.println(bool);

        System.out.println("------------------------");

        BiPredicate<String, String> bp2 = String::equals;
        boolean boo = bp2.test("78", "79");
        System.out.println(boo);
    }
    //构造器引用 类名 :: new
    @Test
    public void test5(){
        Supplier<Employee> employeeSupplier = () -> new Employee();
        Employee employee = employeeSupplier.get();
        System.out.println(employee);

        System.out.println("-------------------------");

        Supplier<Employee> employeeSupplier1 = Employee :: new;
        System.out.println(employee.getName());
    }

    //数组引用 -- >> 数组类型[][] :: new
    @Test
    public void test6(){
        Function<Integer,String[]> function = (x) -> new String[x];
        String[] arr = function.apply(10);
        System.out.println(arr.length);

        System.out.println("---------------------");

        Function<Integer,Employee[]> function1 = (x) -> new Employee[x];
        Employee[] arr1 = function1.apply(45);
        System.out.println(arr1.length);


    }


}
