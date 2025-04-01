package com.jam.spring_beginner.primary;

import com.jam.spring_beginner.primary.model.Animal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrimaryMain {

    public static void main(String[] args) {
        SpringApplication.run(PrimaryMain.class, args);
    }

    @Bean // @Component 는 클래스에 붙이는 것, @Bean은 메소드에 붙이는 것
    public CommandLineRunner run (Animal animal){
        return args -> {
            animal.sound();
        };
    }
}
