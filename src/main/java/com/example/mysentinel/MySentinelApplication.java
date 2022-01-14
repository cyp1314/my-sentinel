package com.example.mysentinel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mysentinel.mapper")
@SpringBootApplication
public class MySentinelApplication {

    public static void main (String[] args) {
        SpringApplication.run(MySentinelApplication.class, args);
    }

}
