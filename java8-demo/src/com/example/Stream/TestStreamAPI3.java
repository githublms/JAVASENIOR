package com.example.Stream;


import com.example.lambda.Employee;
import com.example.lambda.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 17, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 18, 7777.77, Status.FREE),
            new Employee(104, "赵六", 28, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    //3. 终止操作 -->	注意：流进行了终止操作后，不能再次使用
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
	@Test
    public void test1(){
        boolean boo = emps.stream()
                .allMatch(x -> x.getStatus().equals(Status.BUSY));
        System.out.println(boo);

        boolean boo2 = emps.stream()
                .anyMatch(x -> x.getStatus().equals(Status.BUSY));
        System.out.println(boo2);

        boolean boo3 = emps.stream()
                .noneMatch(x -> x.getStatus().equals(Status.BUSY));
        System.out.println(boo3);

        Optional<Employee> optional =  emps.stream()
                .findFirst();
        System.out.println(optional.get());

        Optional<Employee> optional2 =  emps.stream()
//                .filter( x -> x.getAge() > 35)
                .findAny(); //这里效果不明显，每次都是第一个
        System.out.println(optional2.get());

        long count = emps.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = emps.stream()
                .max((x, y) -> -(x.getAge() - y.getAge()));
        System.out.println(max.get());

        Optional<Employee> min = emps.stream()
                .min((x, y) -> -(x.getAge() - y.getAge()));
        System.out.println(min.get());
    }

    //3. 终止操作
	/*
		归约 -- BinaryOperator 二元运算 --identity 身份，一致
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
		T reduce(T identity, BinaryOperator<T> accumulator); -->> 身份 ，二元运算
	 */
	@Test
    public void test2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer reduce = list.stream().reduce(0, (x, y) -> x + y); //这里有起始值，所以返回类型为具体的类型
        System.out.println(reduce);//55

        Optional<Integer> reduce1 = list.stream().reduce((x, y) -> x + y);// 这里没有起始值，所以返回类型为optional
        System.out.println(reduce1.get());//55

        Optional<Integer> reduce2 = emps.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum);
        System.out.println(reduce2.get());
    }
    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test3(){
        long count = emps.stream()
                .map(Employee::getName)
                .filter(x -> x.equals("赵六"))
                .count();
        System.out.println(count);
    }


    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    //Collectors 提供大量的静态方法用来处理收集后的结果
    //collector 收集器，将流中的元素按照指定规则进行收集，得到一个新的流
    @Test
    public void test4(){
        List<String> list = emps.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.toList());
        list.stream().forEach(System.out::println);

        System.out.println("----------------------------------");

        Set<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        collect.forEach(System.out::print);

        System.out.println("----------------------------------");
        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hs);

    }

    //分组 -->> groupingBy
    @Test
    public void test5(){
        Map<Status, List<Employee>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }
    //分区 -->> partitioningBy -->> 分为true 和false q区域
    @Test
    public void  test6(){
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(x -> x.getAge() > 28));
        System.out.println(collect);
    }
    //合并 ,添加 --join
    @Test
    public void test7(){
        String collect = emps.stream()
                .map(Employee::getName)
//                .collect(Collectors.joining(","));//张三,李四,王五,赵六,赵六,赵六,田七
                .collect(Collectors.joining("," , "===","--"));//===张三,李四,王五,赵六,赵六,赵六,田七--
        System.out.println(collect);
    }

}



















