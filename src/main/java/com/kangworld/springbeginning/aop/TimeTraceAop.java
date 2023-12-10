package com.kangworld.springbeginning.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Aspect : AOP로 사용하도록 명시하는 어노테이션
 */
@Aspect
/**
 * TimeTraceAop을 Bean으로 등록할 때 @Component 어노테이션을 써도 되지만,
 * SpringConfig에 @Bean 어노에티션으로 등록해서 쓰는게 AOP임을 보다 명확하게 명시하는 방법임
 */
public class TimeTraceAop {
    /**
     * @Around : AOP를 어디에다 적용할지 타겟팅하는 어노테이션
     * hellospring 패키지 산하에 있는 모든 클래스에 적용
     * <p>
     * 그렇다면 AOP가 동작하는 방식은? AOP가 있으면 Spring은 가짜 controller, service, repository인 proxy를 생성한다.
     * 가령, service의 어떤 메서드가 호출된다면 proxy service의 execute를 실행하고 joinPoint.proceed()를 호출한다.
     * joinPoint.proceed()는 내부적으로 실제 service의 메서드를 실행한다.
     */
    @Around("execution(* com.kangworld.springbeginning..*(..)) && !target(com.kangworld.springbeginning.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
