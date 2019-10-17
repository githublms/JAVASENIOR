package com.example.exer;


import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkjoinTest {

//    932355974711512064
//    耗费的时间是:26460
    @Test
    public void test1(){
        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0 ;i < 100000000000L; i++){
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("耗费的时间是:" + (end - start));
    }

//    93495224680790894
//    耗费的时间是:18386
    @Test
    public void test2(){
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkjoinCalculate task = new ForkjoinCalculate(0,100000000000L);
        Long invoke = pool.invoke(task);
        System.out.println(invoke);

        long end = System.currentTimeMillis();
        System.out.println("耗费的时间是:" + (end - start));
    }

//    932356074711512064
//    耗费的时间是:8374
    @Test
    public void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 100000000000L)
                .parallel().sum();
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("耗费的时间是:" + (end - start));
    }



}
