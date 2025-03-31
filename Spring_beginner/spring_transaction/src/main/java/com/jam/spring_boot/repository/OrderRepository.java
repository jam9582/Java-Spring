package com.jam.spring_boot.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository //bean으로 등록인데 데이터 저장소 역할로 등록
public class OrderRepository {
    private final List<String> orders = new ArrayList<>();

    public void save(String item){
        orders.add(item);
        System.out.println(item + " 저장되었습니다.");
    }

    public List<String> findAll(){
        return orders;
    }

}
