package com.example.datetime;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 运行结果
 ==========================
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 Wed Aug 23 15:16:39 CST 2017
 -----------------------------
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 2017-08-23T15:16:39
 */
public class TestDate {

    public static void main(String[] args) throws Exception {


//        String str2 = "2017-08-23 15:16:17";
//        //测试java7中日期时间的线程安全问题
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date date = sdf.parse(str2);
//        System.out.println(date); // 单线程下的时间转换 -->> Wed Aug 23 15:16:17 CST 2017
//
//        ExecutorService pool = Executors.newFixedThreadPool(10);//创建10个大小的线程池
//        //java7 之前的写法
//        Callable callable = new Callable<Date>() {
//            @Override
//            public Date call() throws Exception {
//                return sdf.parse(str2);
//            }
//        };
//
//        // java8 的写法
//        Callable callable1 = () -> sdf.parse(str2);
//
//        List<Future<Date>> list = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Future submit = pool.submit(callable1);
//            list.add(submit);
//        }
//
//        for (Future<Date> future : list) {
//            System.out.println(future.get()); //会出现一定的线程安全问题
//        }

//        //使用完线程池后，记得关闭线程池
//        pool.shutdownNow();

        System.out.println("==========================");

        //在转换日期时间格式时，出现了线程安全问题，我们如何去解决呢？
        // 利用ThreadLocal 来解决线程安全问题
        String str = "2017-08-23 15:16:39";
        ExecutorService pool2 = Executors.newFixedThreadPool(10);
        Callable<Date> callable2 = () -> DateFormatThreadLocal.convert(str);
        List<Future<Date>> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> task = pool2.submit(callable2);
            list2.add(task);
        }
        for (Future<Date> future : list2) {
            System.out.println(future.get());
        }
        pool2.shutdownNow();

        System.out.println("-----------------------------");

        //测试java8 中新的日期时间api是否会存在线程安全问题
        String dtstr = "2017-08-23 15:16:39";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Callable callable3 = () -> LocalDateTime.parse(dtstr, dtf);
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<LocalDateTime>> ldt = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<LocalDateTime> submit = service.submit(callable3);
            ldt.add(submit);
        }
        for (Future<LocalDateTime> future : ldt) {
            System.out.println(future.get());
        }
        service.shutdownNow();
    }

    @Test
    public void test3(){
        int i = 1;
        int i1 = ++i + ++i;
        System.out.println(i1);
    }

}
