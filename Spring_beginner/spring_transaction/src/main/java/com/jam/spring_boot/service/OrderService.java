package com.jam.spring_boot.service;

import com.jam.spring_boot.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 비즈니스 로직을 담는 클래스라고 스프링에게 알려줌
public class OrderService {
    private final OrderRepository orderRepository;
    // 초기화 후 통째로 변경되는거 방지용 final 사용, .add, .remove 같은걸로 참조된 객체 안의 값은 변경 가능

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    /* 트랜잭션으로 묶기, 트랜잭션을 쓰는 이유는 데이터를 안전하게 지키기 위해서, 작업 도중 문제가 생기면 전부 취소하는 것
    ex) 1번 계좌에서 출금해서 2번계좌에 송금하려는데, 1번계좌에서 돈 빠져나가기만 하고 2번에 송금 안되면 안됨
    */

    public void order(String item){
        orderRepository.save(item);

        if (item.contains("error")){
            throw new RuntimeException("알 수 없는 오류로 주문이 실패하였습니다.");
        }
    }
}
