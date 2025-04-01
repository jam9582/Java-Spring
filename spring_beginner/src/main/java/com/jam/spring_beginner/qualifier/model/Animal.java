package com.jam.spring_beginner.qualifier.model;

public interface Animal {
    void sound();
}

/*
- 인터페이스로 만든 이유: 각 동물들의 구현이 다양할 수 있게 두려고
- 클래스로 만들었으면: 다중상속 불가능
 */