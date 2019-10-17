package com.example.Stream;

import com.example.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/*
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作(终端操作)
 */
public class TestStreamAPI1 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //创建 Stream 的几种方式
    @Test
    public void  test1(){
        //通过 集合对象 (Collection) 的两个方法 stream() 和parallelStream()
        List<String> list = new ArrayList<>();
        Stream stream = list.stream();
        Stream stream1 = list.parallelStream();

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] arr = new Integer[5];
        Stream<Integer> stream2 = Arrays.stream(arr);

        //3. 通过 Stream 类中静态方法 of()
        Stream stream3 = Stream.of("1",",2,","36");

        //4. 创建无限流 iterate(种子，一元运算符) --》》 迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 5).limit(5);
        iterate.forEach(System.out::println);

        //生成无限流 -- 》》 生成
        Stream<Double> limit = Stream.generate(() -> Math.random()).limit(5);
        limit.forEach(System.out::println);

        Stream<Double> generate = Stream.generate(Math::random).limit(2);
        generate.forEach(System.out::println);
    }

    // 关于内部迭代和外部迭代---》》》（使用迭代器进行遍历）
   @Test
   public void test(){
       //内部迭代
       Stream<Employee> stream = emps.stream()
               .filter(x -> {
                   System.out.println("中间操作-----");
                   return x.getAge() > 35;
               });
       //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
       stream.forEach(System.out::println);

       System.out.println("---------------------------------");
       // 外部迭代
       Iterator<Employee> iterator = emps.iterator();
       while(iterator.hasNext()){
           System.out.println(iterator.next());
       }
   }

    // stream 中间操作
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */

    /**
     * 运行结果：
     Employee [id=102, name=李四, age=59, salary=6666.66]
     Employee [id=105, name=田七, age=38, salary=5555.55]
     -----------------------------
     Employee [id=102, name=李四, age=59, salary=6666.66]
     Employee [id=101, name=张三, age=18, salary=9999.99]
     Employee [id=104, name=赵六, age=8, salary=7777.77]
     -----------------------------
     Employee [id=104, name=赵六, age=8, salary=7777.77]
     Employee [id=104, name=赵六, age=8, salary=7777.77]
     Employee [id=105, name=田七, age=38, salary=5555.55]
     -----------------------------
     Employee [id=102, name=李四, age=59, salary=6666.66]
     Employee [id=101, name=张三, age=18, salary=9999.99]
     Employee [id=104, name=赵六, age=8, salary=7777.77]
     Employee [id=105, name=田七, age=38, salary=5555.55]
     */
    @Test
    public  void test2(){
        emps.stream()
                .filter(x -> x.getAge() > 35)
                .forEach(System.out::println);
        System.out.println("-----------------------------");
        emps.stream()
                .filter(x -> x.getSalary() > 5000.0).limit(3)
                .forEach(System.out::println);
        System.out.println("-----------------------------");
        emps.stream()
                .filter(x -> x.getSalary() > 5000.0).skip(3)
                .forEach(System.out::println);
        System.out.println("-----------------------------");
        emps.stream()
                .filter(x -> x.getSalary() > 5000.0).distinct()
                .forEach(System.out::println);
    }




}
