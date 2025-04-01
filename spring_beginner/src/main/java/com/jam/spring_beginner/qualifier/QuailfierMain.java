package com.jam.spring_beginner.qualifier;

import com.jam.spring_beginner.qualifier.model.Animal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuailfierMain {

    public static void main(String[] args) {
        SpringApplication.run(QuailfierMain.class, args);
    }

    @Bean
    public CommandLineRunner run (@Qualifier("cat") Animal animal){
        /*
        - "cat" 은 bean의 이름, 일반적으로 클래스 이름의 첫글자를 소문자로 바꿔서 자동으로 bean이름으로 바꿔줌, Cat 쓰면 에러
        - @Component("doggy") 식으로 하면 bean 이름을 doggy로 설정 가능

        - @Primary있어도 @Qualifier가 우선되고, 2번 primary 과제 처럼 Dog 클래스 위에 @Qualifier 이런 식으로는 못 씀
        1. @Primary는 기본값으로 주입할 bean 정의 쪽에 씀
        2. @Qualifier는 특정 빈을 명시적으로 선택하는 하는 거라 파라미터나 필드 등의 주입하는 쪽에 씀

         */
        return args -> {
            animal.sound();
        };
    }
}
