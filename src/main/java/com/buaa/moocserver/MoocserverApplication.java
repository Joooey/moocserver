package com.buaa.moocserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.buaa.moocserver.mapper")
public class MoocserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoocserverApplication.class, args);
    }

}
