package com.example.consumerfeign9003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
public class ConsumerFeign9003Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeign9003Application.class, args);
    }

}
