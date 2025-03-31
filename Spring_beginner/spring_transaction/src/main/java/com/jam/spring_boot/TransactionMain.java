package com.jam.spring_boot;

import com.jam.spring_boot.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionMain {
    public static void main(String[] args) {
        SpringApplication.run(TransactionMain.class, args);
    }

    @Bean
    /* 아래 메소드는 spring boot에서 쓰이는 앱 실행 직후 테스트용 실행 코드

    - CommandLineRunner: Spring Boot 애플리케이션이 실행된 후에 자동으로 실행되는 인터페이스,
    SpringApplication.run(...) 이후에 한 번 실행됨, 주로 테스트용 코드나 초기 데이터 세팅에 사용

    - 이 경우처럼 위에 @Bean 까지 붙이면, 스프링이 public CommandLineRunner 한 결과를 빈으로 등록해줘서, 스프링이 자동으로 이 코드를 실행할 수 있게 됨
    - spring은 @bean 이나 @component로 등록 안된 코드는 자동으로 실행하거나 관리x, spring은 IOC 컨테이너 방식이어서 스프링이 직접 객체를 생성하고 관리해야 어노테이션 사용 가능,
    new로 만든 객체는 spring이 관리x!, 이걸로 만들면 그냥 평범한 자바 객체일 뿐,
    @Transactional 같은 건 프록시(proxy)란 기술을 통해 적용되는데 이런건 spring이 만들어준 객체에만 적용됨
     */
    public CommandLineRunner run(OrderService orderService) {
        return args -> {
            /*
            - CommandLineRunner는 run(String[] args)라는 메서드 하나만 갖고 있기 때문에,
             public void run(String[] args) 이거를 args -> { ... } 이런식으로 람다표현으로 간략화 가능
             */

            try {
                orderService.order("사과");
                orderService.order("error 상품"); //롤백 테스트용
            } catch (Exception e) {
                System.out.println("예외 발생 확인 완료");
            }
        };
    }
}
