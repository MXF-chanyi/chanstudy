package com.lening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSMPApplication {

    public static void main(String[] args) {

        System.setProperty("spring.devtools.restart.enabled", "false");

        //可以在启动springboot程序时断开读取外部临时配置的对应入口，也就是去掉读取外部参数的形参 args
        SpringApplication.run(SSMPApplication.class, args);
    }

}
