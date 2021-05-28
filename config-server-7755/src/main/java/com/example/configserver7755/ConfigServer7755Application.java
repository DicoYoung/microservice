package com.example.configserver7755;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer7755Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer7755Application.class, args);
    }

}
