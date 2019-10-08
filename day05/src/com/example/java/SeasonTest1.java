package com.example.java;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于java.lang.Enum类
 */

/**
 Season2{name='秋天', desc='秋高气爽'}
 *********
 class java.lang.Enum
 Season2{name='春天', desc='春暖花开'}
 Season2{name='夏天', desc='夏日炎炎'}
 Season2{name='秋天', desc='秋高气爽'}
 Season2{name='冬天', desc='冰天雪地'}

 Season2{name='夏天', desc='夏日炎炎'}
 */

public class SeasonTest1 {
    public static void main(String[] args) {
        Season2 season2 = Season2.AUTUMN;
        System.out.println(season2.toString());
        System.out.println("*********");

        System.out.println(Season2.class.getSuperclass());//class java.lang.Enum 表明 使用enum关键字修饰的类是继承与java.lang.Enum类
        Season2[] arr  = Season2.values();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        Season2 summer = Season2.valueOf("SUMMER");
        System.out.println(summer);
//        Season2 chun = Season2.valueOf("chun");
//        System.out.println(chun);//出现异常"main" java.lang.IllegalArgumentException: No enum constant com.example.java.Season2.chun
        System.out.println("=====================");

        Season2.AUTUMN.show();//22222222
    }
}

//接口里面有抽象方法和全局长量static final
interface AA {
    void show();
}

//5.0 后 使用enum 来实现枚举类

enum Season2 implements AA{

    //这个枚举类整体来实现抽象方法
//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }


    //1 提供当前枚举类的对象，多个对象之间用","隔开，末尾对象";"结束
    SUMMER("夏天","夏日炎炎") {
        @Override  //这是每一个枚举类对象都来重写抽象方法
        public void show() {
            System.out.println("11111111");
        }
    },
    AUTUMN("秋天","秋高气爽") {
        @Override
        public void show() {
            System.out.println("22222222");
        }
    },
    SPRING("春天","春暖花开") {
        @Override
        public void show() {
            System.out.println("33333333");
        }
    },
    WINTER("冬天","冰天雪地") {
        @Override
        public void show() {
            System.out.println("444444444");
        }
    };
    //2 提供枚举类的属性
    private final String name;
    private final String desc;

    //私有化构造器
    private Season2(String name,String desc){
       this.name = name;
       this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season2{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


}