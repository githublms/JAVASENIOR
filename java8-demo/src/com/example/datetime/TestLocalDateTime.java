package com.example.datetime;


import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTime {

    /**
     * LocalDate LocalTime LocalDateTime 基础介绍
     *  加减  ---> plus .. + minus.. -
     */
    @Test
    public void test1(){

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); // 2019-10-17T13:57:07.839
        LocalDateTime localDateTime = LocalDateTime.of(2018,05,12,12,15,16);
        System.out.println(localDateTime); // 2018-05-12T12:15:16

        LocalDateTime now1 = now.plusHours(5);
        System.out.println(now1);//2019-10-17T18:57:07.839
        LocalDateTime now2 = now.plusYears(2);
        System.out.println(now2); // 2021-10-17T13:58:01

        LocalDateTime now3 = now.minusMonths(5);
        System.out.println(now3); //2019-05-17T13:59:07.664
        LocalDateTime now4 = now.minusYears(3);
        System.out.println(now4); //2016-10-17T13:59:07.664

        System.out.println(now.getDayOfYear());//290
        System.out.println(now.getHour());//14
        System.out.println(now.getDayOfWeek());//THURSDAY
        System.out.println(now.getMinute());//0
    }

    /**
     * instant时间戳：
     *      从1970年1月1日0时0分0秒开始到现在的毫秒值
     */
    @Test
    public void test2(){
        Instant instant = Instant.now();
        System.out.println(instant);//2019-10-17T06:05:21.027Z -->> 使用的是UTC 时间，默认比现在少8个小时

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime); // 2019-10-17T14:08:16.357+08:00 -->> 加了8个小时后

        long second = instant.getEpochSecond();//得到秒值
        System.out.println(second);//1571292550

        long milli = instant.toEpochMilli();//得到毫秒值
        System.out.println(milli);//1571292699369

        Instant instant1 = Instant.ofEpochMilli(3); // epochMilli - the number of milliseconds from 1970-01-01T00:00:00Z
        System.out.println(instant1.toEpochMilli());
    }

    /**
     *  Duration : 计算两个 "时间"  之间的间隔
     *  Period : 计算两个 "日期" 之间的间隔
     */
    @Test
    public void test3(){
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime dateTime1 = dateTime.plusMinutes(5);

        Duration between = Duration.between(dateTime, dateTime1);
        System.out.println(between.getSeconds());

        LocalDate now = LocalDate.now();
        LocalDate now1 = now.minusDays(5);

        Period period = Period.between(now,now1);
        System.out.println(period.getDays());
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
    }

    /**
     *  时间矫正器 :  TemporalAdjuster -->> 将时间调整到下一个指定的时间
     *      TemporalAdjusters 提供了大量的静态方法来满足我们的需求
     */
    @Test
    public void test4(){
        LocalDateTime now =LocalDateTime.now();
        System.out.println(now); // 2019-10-17T14:39:38.617

        LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(with); //2019-10-21T14:39:38.617

    }

    /**
     *  日期时间的格式化和解析
     */
    @Test
    public void test5(){
        LocalDateTime now =  LocalDateTime.now(ZoneOffset.ofHours(8));
        System.out.println(now); // 2019-10-17T14:43:17.836

        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format); // 2019-10-17 14:43:17

        String string = "2019/10/17 14:43:17";
        LocalDateTime parse = LocalDateTime.parse(string, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println(parse);//2019-10-17T14:43:17
    }

    /**
     *  带时区的日期和时间
     *  ZonedDateTime ZonedTime ZonedDate
     */
    @Test
    public void test6(){
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime); // 2019-10-17T15:07:01.371

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zonedDateTime);//2019-10-17T00:08:08.307-07:00[US/Pacific]

        //查看所有的可用的时区
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        availableZoneIds.forEach(System.out::println);
    }

}
