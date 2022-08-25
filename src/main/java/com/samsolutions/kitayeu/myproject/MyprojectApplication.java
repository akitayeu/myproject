package com.samsolutions.kitayeu.myproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class MyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectApplication.class, args);
    }

    @Bean
    public MyBeanClass myBeanClass() {
        log.info("My first bean!");
        return new MyBeanClass("Hello");
    }
}


@AllArgsConstructor
@Getter
@Setter
class MyBeanClass {

    private String mytext;

}