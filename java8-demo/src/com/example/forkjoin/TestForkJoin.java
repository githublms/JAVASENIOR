package com.example.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * 测试fork join 框架
 *
 * Recursive --》》 迭代
 */
public class TestForkJoin {


    /** 使用普通的for循环来计算 100 0000 0000 L (0 到 100亿的范围)
     * 运行结果
     -5340232226128654848
     花费了34626毫秒
     */
    @Test
    public void test1(){
        Long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i = 0; i < 10000000000L; i++) {
            sum += i;
        }
        System.out.println(sum); // -5340232226128654848
        Long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "毫秒");// 花费了34004毫秒
    }

    /**使用原生的fork-join框架 实现 0到100亿的计算

     我们来看一下ForkJoin的实现：
     实现这个框架需要继承RecursiveTask (有返回值)或者 RecursiveAction(无返回值)，
     *
     *
     * 运行结果
     -5340232216128654848
     花费了2070毫秒
     */
    @Test
    public void test2(){
        Long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask task = new ForkJoinCalculate(0,10000000000L);

        Long sum = (Long) pool.invoke(task);
        System.out.println(sum);

        Long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "毫秒");// 花费了34004毫秒
    }

    /**
     *  java8中使用并行的stream来处理 ，才用了stream 和 forkjoin，使得速率很快
     *  运行结果
     -5340232216128654848
     花费了904毫秒

     */
    @Test
    public void test3(){

        Long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .sum();
        System.out.println(sum);
        Long end = System.currentTimeMillis();
        System.out.println("花费了" + (end - start) + "毫秒");// 花费了34004毫秒
    }



}
