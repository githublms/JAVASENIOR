package com.example.java1;


import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 *  使用开源工具快速创建和书写代码
 *  apache-commons
 *  IDEA 中快速导入外部依赖的jar包 请看 这个连接的操作，进行实践
 *      https://jingyan.baidu.com/article/0f5fb0993e9e1f6d8334ead2.html
 *      使用工具能够减少许多代码，更加方便和快捷
 */
public class FileUtil {
    @Test
    public void test1() throws IOException {
        File src = new File("2.txt");
        File desc = new File("C:\\Users\\LMS\\Desktop\\1.txt");
        FileUtils.copyFile(src,desc);
    }

}
