package com.example.gateway6001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Gateway6001Application {

    public static void main(String[] args) {
        SpringApplication.run(Gateway6001Application.class, args);
    }

}
