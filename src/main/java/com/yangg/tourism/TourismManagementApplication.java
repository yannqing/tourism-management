package com.yangg.tourism;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yangg.tourism.mapper")
public class TourismManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourismManagementApplication.class, args);
    }

}
