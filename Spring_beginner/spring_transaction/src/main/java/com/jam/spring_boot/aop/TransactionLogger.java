package com.jam.spring_boot.aop;

/*
- 트랜잭션(Transaction): 데이터베이스에서 한 번에 처리돼야 하는 작업 단위, 실패하면 아예 처음부터 다시, 성공하면 전부 적용
여러 작업이 하나처럼 실행되게 하려할 때 사용, aop로 구현된 공통 기능 중 하나
ex) 송금: 1번 계좌에서 보내기 + 2번 계좌에서 돈 받기 → 둘 중 하나라도 실패하면 모두 취소

- @Transactional: 이 어노테이션을 붙인 메서드가 트랜잭션으로 실행됨
즉, 중간에 예외가 나면 자동으로 롤백, 성공하면 자동으로 커밋(저장)

- AOP (Aspect-Oriented Programming): 특정 기능을 공통된 방법으로 분리해서 처리하는 기법, @Aspect, @Around 등으로 사용
ex) 로그 출력, 권한 체크, 트랜잭션 관리 등을 비즈니스 로직에서 분리
*/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop 공동기능을 담당하는 클래스임을 선언, 공통 기능을 모아두는 클래스에 붙임
// ex) 트랜잭션 처리, 로깅(메소드 시작/끝 시간 출력), 권한 체크, 캐싱(같은 결과면 캐시에서 반환)
@Component // bean으로 등록
public class TransactionLogger {

    // @Around: 어떤 메소드 실행 전후를 감싸서 처리
    // @annotation(~): @Transactioal이 붙은 메서드를 대상으로 동작
    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    // 즉, @Transactional 이 붙은 메소드를 실행할 때, 그 실행 전후로 이 메서드를 감싸서 로그를 찍겠다는 뜻
    public Object logTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        // 반환 타입 Object 아무거나
        // ProceedingJoinPoint는 스프링이 제공하는 AOP용 클래스, 지금 어떤 메서드르 실행하려는지 담고 있는 컨트롤러
        // throws Throwable 쓰는 이유는 aop가 원래 메소드를 실행시킬때, 그 메소드가 예외 나면 같이 던질 수 있게 exception보다 상위 타입으로 던짐
        System.out.println("트랜잭션 시작" + joinPoint.getSignature()); // .getSignautre는 감싸고 있는 메소드의 이름, 클래스 등을 문자열로 꺼내는 역할
        //트랜잭션 시작void com.jam.spring_boot.service.OrderService.order(String) 이게 얘 때문에 나온거

    /* 전체 흐름
    1. 스프링이 어떤 메서드에 @Transactional 붙은 걸 감지하면
	2. 그 메서드를 실행하기 전에 AOP 메서드(logTransaction)를 호출
	3. joinPoint.getSignature()로 어떤 메서드인지 로그로 출력
	4. joinPoint.proceed()로 원래 메서드 실행, 이 proceed()가 호출되기 전까지는 @Transactional이 붙은 order() 메소드는 실행 x
	5. 성공하면 커밋 로그, 실패하면 롤백 로그 출력
     */

        try{
            Object result = joinPoint.proceed(); //실제 메서드 실행
            System.out.println("트랜잭션 커밋");
            return result;
        }
        catch (Throwable e){
            System.out.println("트랜잭션 롤백, 이유: " + e.getMessage());
            throw e;
        }
        // 만약 트랜잭션에서 롤백 없이 커밋만 잘 된다면, 이 커밋된 데이커는 다음 실행에도 그대로 남아있음
        // 하지만 지금은 실제 DB없이 list로 가짜 db 만들어서 쓰는거라 다음 실행 때 초기화 됨
    }
}
