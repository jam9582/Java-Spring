package com.jam.spring_beginner.qualifier.model;

import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal {

    @Override // 안써도 실행은 되지만 오타났을 때 컴파일 오류 못 잡아줌
    public void sound() {
        System.out.println("야옹");
    }


}
