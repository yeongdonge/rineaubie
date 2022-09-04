package com.rineaubie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RineaubieApplication {

    public static void main(String[] args) {
        SpringApplication.run(RineaubieApplication.class, args);
    }
}
