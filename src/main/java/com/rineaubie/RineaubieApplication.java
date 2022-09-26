package com.rineaubie;

import com.rineaubie.api.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
        CorsProperties.class,

})
public class RineaubieApplication {

    public static void main(String[] args) {
        SpringApplication.run(RineaubieApplication.class, args);
    }
}
