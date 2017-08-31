package com.cn.nlg.lp.generate;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GenerateMyabatis {


    public void Generate() {


        List<String> warnings = new ArrayList<>();
//        File configFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"generatorConfig.xml");
        File configFile = new File("generatorConfig.xml");


        try {
            @Cleanup InputStream in = null;

            System.out.println("以字节为单位读取文件内容，一次读多个字节：" + configFile.exists());
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(configFile);
            this.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }

        } catch (IOException e) {
            log.debug("文件读取失败");
        }


        ConfigurationParser cp = new ConfigurationParser(warnings);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        try {

            FileReader fr = new FileReader(configFile);
            Configuration config = cp.parseConfiguration(fr);


            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(null);
        } catch (IOException e) {
            log.debug("读取文件失败");
        } catch (XMLParserException e) {
            log.debug("xml文件格式错误");
        } catch (InvalidConfigurationException e) {
            log.debug(("xml文件配置错误"));
            e.printStackTrace();
        } catch (SQLException e) {
            log.debug("sql运行异常");
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.debug("异常中断");
            e.printStackTrace();
        }

        for (String warning : warnings) {
            System.out.println(warning);
        }


    }


    private void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}