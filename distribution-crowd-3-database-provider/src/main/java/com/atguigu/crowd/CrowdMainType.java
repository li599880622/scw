package com.atguigu.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//@EnableEurekaClient 专门针对eureka注册中心
@EnableDiscoveryClient //更为通用
@MapperScan("com.atguigu.crowd.mapper")
@SpringBootApplication
public class CrowdMainType {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainType.class,args);
    }
}
