package com.example.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/*
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 * 						    箭头操作符将 Lambda 表达式拆分成两部分：
 *
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 * 语法格式一：无参数，无返回值 -- >> Runnable
 * 		() -> System.out.println("Hello Lambda!");
 *
 * 语法格式二：有一个参数，并且无返回值 -- >> Consumer
 * 		(x) -> System.out.println(x)
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 * 		x -> System.out.println(x)
 *
 * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("函数式接口");
 *			return Integer.compare(x, y);
 *		};
 *
 * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 上联：左右遇一括号省
 * 下联：左侧推断类型省
 * 横批：能省则省
 *
 * 二、Lambda 表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 			 可以检查是否是函数式接口
 */
public class TestLambda2 {

    // 无参无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        r1.run();
        System.out.println("--------------------");
        Runnable r2 = () -> System.out.println("hello baidu");
        r2.run();
    }

    // 一个参数，无返回值
    @Test
    public void test2(){
        Consumer consumer = (x) -> System.out.println(x);
        consumer.accept("hello");
    }

    // 两个参数，有返回值
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) ->{
            System.out.println("两个数之间相互比较");
            return Integer.compare(x,y);
        } ;
        List<Integer> list = Arrays.asList(12,15,16,17,8);
        list.stream().sorted(comparator).forEach(System.out::print);

        Comparator<Integer> conComparator1 = (x,y) -> Integer.compare(x,y) ;

    }
    //什么是函数式接口 --》》一个接口中只有一个抽象方法的接口叫函数式接口， -- >> 注意是抽象方法
    // 并且，可以用@Funcational 来检查是否是函数式接口
    //需求，传入一个值，得到该值得平方
    public Integer getDouble (Integer number,MyFun myFun){
        return myFun.getValue(number);
    }

    @Test
    public void test4(){
        Integer sum  = getDouble(5, new MyFun() {
            @Override
            public Integer getValue(Integer num) {
                return num * num ;
            }
        });
        System.out.println(sum);

        Integer sum1 = getDouble(10, x -> x +5 );
        System.out.println(sum1);
    }

}

