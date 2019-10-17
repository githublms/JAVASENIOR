package com.example.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLambda1 {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 14, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //需求1 获取所有员工中年龄大于 35 的员工
    public List<Employee> getEmployeeAge(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getAge() > 35){
                list.add(emp);
            }
        }
        return list;
    }
    @Test
    public void test1(){
        List<Employee> employeeAge = getEmployeeAge(emps);
        for (Employee employee : employeeAge) {
            System.out.println(employee);
        }
    }

    //需求2 获取所有员工中工资大于 5000 的员工
    public List<Employee> getEmployeeSalary(List<Employee> emps){
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getSalary() > 5000){
                list.add(emp);
            }
        }
        return list;
    }
    @Test
    public void test2(){
        List<Employee> employeeAge = getEmployeeSalary(emps);
        for (Employee employee : employeeAge) {
            System.out.println(employee);
        }
    }

    //

    /**
     *  优化方式一: 策略模式
     *
     *  解决方案:
     *      针对上面两个方式，来一个需求就写一个方法，然后代码中就有许多个方法，每个方法解决一种需求
     *      这时候采用 策略模式 来什么样的需求，我就使用什么样的方式来解决这个需求，这样会减少方法的产生
     *
     *  创建一个接口，名字为 MyPredicate
     *  创建实现类一：来实现我们的接口，类中实现 MyPredicate 中的方法，解决对应的需求
     *  提供解决需求的通用方法
     */
    // 提供一个通用方法，传入一个 Employee 的集合，我们要对这个集合做什么样的处理 -->> 有点函数式编程的思想
    public List<Employee> commosMethod(List<Employee> emps , MyPredicate<Employee> mp){
        List<Employee> list = new ArrayList<>();

        for (Employee employee : emps) {
            if (mp.test(employee)){ // 如果这个对象做了处理后，满足条件 我们就将这个数据加入到集合中
                list.add(employee);
            }
        }
        return list;
    }

    // 获取所有员工中年龄大于 35 的员工
    // 获取所有员工中 工资大于 5000 的员工
    @Test
    public void test3(){
        List<Employee> employees = commosMethod(emps,new FilterEmployeeByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println("---------------------");
        List<Employee> list = commosMethod(emps,new FilterEmployeeBySalary());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }


    /**
     * 使用策略模式来解决这些个需求，发现一个需求就要创建一个类，而且每个类还只写一点内容，有点浪费资源
     * 这时候就采用 匿名内部类 的方式来解决这个需求
     */
    @Test
    public void test4(){

        // 获取所有员工中年龄大于 35 的员工
         List<Employee> employees = commosMethod(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 35;
            }
         });
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("--------------------------------------");
        List<Employee> list = commosMethod(emps, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee emp) {
                return emp.getSalary() > 5000;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     *  使用 匿名内部类 的方式 减少了需求类的创建，
     *  但是，使用匿名内部类的代码 时，发现了 真正起作用的是 实现方法方法体中的一两行代码，
     *  那么，有没有办法不要多余的代码，只要方法体体中的关键代码即可呢？
     *  这时候，lambda 表达式就出现了并解决了这个问题
     *
     *  先看代码，下一节介绍 lambda 表达式 -->> lambda 主要用来取代 匿名内部类
     *
     *   使用lambda 可以精简代码，也可以使用 Stream API 的方式来处理 --》》 stream 可以更加简洁
     *
     */

    //很明显的发现 代码减少了许多了，内容也更加精简
    @Test
    public void test5(){

        List<Employee> list = commosMethod(emps, (e) -> e.getAge() > 35 );
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("----------------------------");

        List<Employee> list2 = commosMethod(emps, (e) -> e.getSalary() > 5000 );
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }

    // 使用 Stream API 来处理集合数据
    @Test
    public void test6(){
        emps.stream()
                .filter( (e) -> e.getAge() > 35)
                .forEach(System.out::println);
        System.out.println("------------------------------");
        emps.stream()
                .filter((e) -> e.getSalary() > 5000 )
                .forEach(System.out::println);
    }
}
