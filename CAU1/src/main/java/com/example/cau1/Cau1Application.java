package com.example.cau1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Cau1Application {

    public static void main(String[] args) {
        SpringApplication.run(Cau1Application.class, args);
    }

}
