package com.qiuxk.datamagrate;

import com.qiuxk.service.ReadFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan(basePackages = "com.qiuxk")
public class DatamagrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatamagrateApplication.class, args);


    }



}
