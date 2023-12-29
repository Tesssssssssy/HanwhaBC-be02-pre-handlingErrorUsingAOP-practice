package com.example.handlingerroraop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // 관점 지향 프로그래밍임을 위한 클래스임을 annotation으로 설정
@Component
public class SimpleAop {
    @Pointcut("execution(* com.example.handlingerroraop.member..*.*(..))")
    private void cut() {
        System.out.println("cut");
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        System.out.println(method.getName() + "메소드 실행 전");
    }

    @AfterReturning(value = "cut()", returning = "returnObject") // controller가 return까지 해주어야 끝나는 것으로 하자.
    public void afterReturning(JoinPoint joinPoint, Object returnObject) {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        if (returnObject != null) {
            System.out.println(returnObject.getClass().getName() + " 반환");
        }

        System.out.println(method.getName() + "메소드 실행 후");
    }

    /**
     * Advice
     * - Joinpoint에서 실행되어야 하는 프로그램 코드
     * - 독립된 클래스의 메소드로 작성함
     * - 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가기능을 담은 구현체
     * - 관심사를 구현한 소스코드
     * - BEFORE, AROUND, AFTER의 실행 위치 지정
     *
     * Joinpoint
     * - 메소드를 호출하는 '시점', 예외가 발생하는 '시점'과 같이 애플리케이션을 실행할 때 특정 작업이 실행되는 '시점'을 의미
     * - Advice를 적용할 수 있는 후보 지점 혹은 호출 이벤트
     * - Advice가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
     * - 관심사를 구현한 코드를 끼워 넣을 수 있는 프로그램의 이벤트를 말하며, 예로는 call events, execution events, initialization events 등이 있음
     *
     * Pointcut
     * - 여러 Joinpoint의 집합체로 언제 Advice를 실행할지를 정의
     * - Target 클래스와 Advice가 결합(Weaving)될 때 둘 사이의 결합규칙을 정의
     * - 예로 Advice가 실행된 Target의 특정 메소드등을 지정
     * - JoinPoint의 상세한 스펙을 정의한 것. 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음
     * - 관심사가 주프로그램의 어디에 횡단될 것인지를 지정하는 문장이며, 에로는 before call(public void update*(...))등이 있음
     *
     * Target
     * - 실질적인 비지니스 로직을 구현하고 있는 코드
     * - 핵심관점에 해당함 (업무로직)
     *
     * Aspect
     * - Advice + Pointcut
     * - 즉 일정한 패턴을 가지는 클래스에 Advice를 적용하도록 지원할 수 있는 것을 Aspect
     *
     * Weaving
     * - Aspect를 해당 지점에 주입하는 과정
     * - AOP에서 Joinpoint들을 Advice로 감싸는 과정을 Weaving
     * - Weaving 하는 작업을 도와주는 것이 AOP 툴이 하는 역할
     */
}
