package com.nextjob.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nextjob")
public class NextJobApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NextJobApiApplication.class, args);
    }

}
