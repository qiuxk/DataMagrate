package com.qiuxk.datamagrate;

import com.qiuxk.entity.SystemConfigure;
import com.qiuxk.properties.SystemProperties;
import com.qiuxk.service.ReadFile;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatamagrateApplication.class)
//@EnableConfigurationProperties(SystemProperties.class)
public class Test {

    @Resource
    private ReadFile readFile;

    @Resource
    private SystemConfigure systemConfigure;

    @Resource
    private SystemProperties systemProperties;


    @org.junit.Test
    public  void testData(){
        System.out.println(systemProperties.getInPath());
        String inPath  =systemConfigure.getInPath();
        readFile.readFile(inPath +"t_phone_order.sql","order");
        //readFile.readFile(inPath +"t_phone_channel.sql","channel");
        //readFile.readFile(inPath +"t_phone_operator.sql","operator");
        //readFile.readFile(inPath +"t_phone_package_info.sql","package");
    }
}
