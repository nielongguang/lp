package com.cn.nlg.lp.jdk8;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author <Authors name>
 * @version 1.0
 * @since <pre>2017/9/13 </pre>
 */
@Slf4j
public class FileCopy {


    public static void main(String[] args) throws IOException {
        //读取指定文件
        File file = new File("E://hello.txt");
        OutputStream out = null;
        byte date[] = new byte[100];
        //准备要用过来读取数据的数组
        @Cleanup InputStream in = null;
        try {
            //根据文件创建文件输入流

            out = new FileOutputStream(file);
            in = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(in, "UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
            //读取内容放字节组中
            System.out.println("in:" + in.read(date) + new String(date));
            byte c[] = new byte[10];
            for (int i = 0; i < 10; i++) {
                c[i] = (byte) (i + i << 1);
            }
            out.write(c);
            System.out.println("in:" + in.read(date) + new String(date));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.info("该文件不存在");
        } catch (IOException e) {
            log.info("io");
        }


    }


}
