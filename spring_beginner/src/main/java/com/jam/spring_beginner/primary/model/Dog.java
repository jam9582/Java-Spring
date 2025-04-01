package com.jam.spring_beginner.primary.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 같은 타입(Animal)의 빈이 여러개일 때, 이 빈을 default로 사용해라
public class Dog implements Animal {

    @Override
    public void sound() {
        System.out.println("왈왈");
    }
}
