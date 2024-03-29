package com.example.optional;

import com.example.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/*
  暂时不写这个实例

 * 一、Optional 容器类：用于尽量避免空指针异常
 * 	Optional.of(T t) : 创建一个 Optional 实例
 * 	Optional.empty() : 创建一个空的 Optional 实例
 * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * 	isPresent() : 判断是否包含值
 * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {

    //创建optional 实例
    @Test
    public void tese1(){
        Optional optional = Optional.of(new Employee());
        System.out.println(optional.get());

        Optional<Employee> optional1 = Optional.ofNullable(new Employee());
        System.out.println(optional1.get());

        Optional optional2 = Optional.ofNullable(null);
//        System.out.println(optional2.get()); // 出现异常： java.util.NoSuchElementException: No value present
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get()); // 出现异常： java.util.NoSuchElementException: No value present




    }

}
