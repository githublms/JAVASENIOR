package com.example.Stream;


import com.example.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStreamAPI2 {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 18, 7777.77),
            new Employee(104, "赵六", 38, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    /*
    映射
    map —— 接收 Lambda ，对每一个元素都进行相应的处理后得到一个新的stream。 --》》 相当于集合中的add 方法
    flatMap —— 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流 --》》 相当于集合中的 addAll 方法
	 */
    @Test
    public void test2(){
        List<String> stringList = Arrays.asList("AAbba","VV","AAWWW","VVXXX","AADDD","VVBBB");

        List list = new ArrayList<>();
        list.add("789");
        list.add(stringList); // 这里类比于 add 方法
        System.out.println(list);// [789, [AAbba, VV, AAWWW, VVXXX, AADDD, VVBBB]]

        List list1 = new ArrayList();
        list1.add("789");
        list1.addAll(stringList); // 这里类比于 addAll 方法
        System.out.println(list1); // [789, AAbba, VV, AAWWW, VVXXX, AADDD, VVBBB]

        stringList.stream()
                .map((x) -> x.toLowerCase()) //转换成小写
                .forEach(System.out::println);
        System.out.println("--------------");
        stringList.stream()
                .map(String::toLowerCase) //转换成小写
                .forEach(System.out::println);
    }


    /*
    sorted()——自然排序
    sorted(Comparator com)——定制排序
	 */
    @Test
    public void test1(){
        //自然排序
        emps.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("--------------------");
        //定制排序
        emps.stream()
                .sorted( (x,y) -> {
                    if (x.getName().equals(y.getName())){
                        return -Integer.compare(x.getAge(),y.getAge());
                    }else{
                        return -1;
                    }
                })
                .forEach(System.out::println);

    }

}
