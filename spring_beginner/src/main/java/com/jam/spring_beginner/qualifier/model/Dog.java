package com.jam.spring_beginner.qualifier.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {

    @Override
    public void sound() {
        System.out.println("왈왈");
    }
}
