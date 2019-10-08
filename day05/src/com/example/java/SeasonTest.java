package com.example.java;


/**
 *  枚举类的使用
 *      理解：类的对象只有有限个时，确定的，我们就称此类为枚举类
 *      当我们需要定义一组常量时，强烈建议使用枚举类
 *      如果枚举类中只有一个对象的时候，我们可以采用单例模式的实现方式
 *
 *  如何定义枚举类
 *      jdk5.0前 自定义枚举类
 *      jdk5.0后 使用enum关键字来定义枚举类
 *
 *  Enum类中的常用方法
 *      values() : 返回枚举类型的对象数组。该方法可以很方便的遍历所有的枚举值
 *      valuesof(String str)：可以将一个字符串转为对应的枚举类对象，要求字符串必须是枚举类对象的名字，
 *          如果不是就是产生异常 IllegalArgumentException
 *      toString() 返回当前枚举类对象常量的名称
 *
 *  使用enum关键字定义的枚举类来实现接口的情况
 *      情况一:实现接口，在enum 类中实现抽象方法
 *      情况二:让枚举类的对象分别实现接口中的抽象方法
 */
public class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.AUTUMN;
        System.out.println(season);//Season{seasonName='秋天', seasonDesc='秋高气爽'}
        System.out.println(season.getSeasonName());//秋天
        System.out.println(season.getSeasonDesc());//秋高气爽
    }
}

//5.0前 自定义枚举类
class Season{
    //声明season对象的属性 private final 来修饰
    private final String seasonName;
    private final String seasonDesc;

    //私有化构造器
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    //提供当前枚举类的多个对象 public static final 修饰
    public static final Season SPRING = new Season("春天","春暖花开");
    public static final Season SUMMER = new Season("夏天","夏日炎炎");
    public static final Season AUTUMN = new Season("秋天","秋高气爽");
    public static final Season WINTER = new Season("冬天","冰天雪地");

    // 其他诉求 获取某个枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}












