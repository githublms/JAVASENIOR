package com.example.java;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 练习3:获取文本上字符出现的次数,把数据写入文件
 *
 * 思路：
 * 1.遍历文本每一个字符
 * 2.字符出现的次数存在Map中
 *
 * Map<Character,Integer> map = new HashMap<Character,Integer>();
 * map.put('a',18);
 * map.put('你',2);
 *
 * 3.把map中的数据写入文件
 *
 */
public class WordCount {

    @Test
    public void test1() throws Exception {
        Map<Character,Integer> map = new HashMap();
        FileReader fileReader = new FileReader("2.txt");
        FileWriter fileWriter = new FileWriter("wordCount.txt");

        int len ;
        while ((len = fileReader.read()) != -1){
            char ch = (char)(len); //将读取道的int型数据转成字符
            //判断这个字符是否是第一次出现,第一次出现
            if(map.get(ch) == null ){
                map.put(ch,1);
            }else{
                map.put(ch,map.get(ch) + 1);
            }
        }
        for (Character character : map.keySet()) {
            int num = map.get(character);
            String string = character + "==" + num;
            fileWriter.write(string + "\n");
        }
        fileReader.close();
        fileWriter.close();
    }
}
