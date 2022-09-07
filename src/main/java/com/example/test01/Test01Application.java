package com.example.test01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class Test01Application {
    //@EnableScheduling : 스크래핑이나 api등 특정 메서드를 반복적 혹은 주기적으로 돌리는 어노테이션
    //메인 메서드 위에 @EnableScheduling
    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class, args);
    }

}
