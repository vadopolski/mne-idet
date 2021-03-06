package com.mneidet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableJpaRepositories("com.mneidet")
@EntityScan
public class MneIdetApplication {

    public static void main(String[] args) {

        SpringApplication.run(MneIdetApplication.class, args);
    }
}
