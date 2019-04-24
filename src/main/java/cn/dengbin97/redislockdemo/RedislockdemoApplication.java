package cn.dengbin97.redislockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedislockdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedislockdemoApplication.class, args);
    }
}
