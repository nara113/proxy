package hello.aop.pointcut;

import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AtTargetArWithinTest.Config.class)
public class AtTargetArWithinTest {

    @Autowired
    Child child;

    @Test
    void test() {
        log.info("child proxy : {}", child.getClass());

        child.childMethod();
        child.parentMethod();
    }

    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public TargetAspect targetAspect() {
            return new TargetAspect();
        }
    }

    static class Parent {
        public void parentMethod() {}
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {}
    }

    @Slf4j
    @Aspect
    static class TargetAspect {
        @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("at target : {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }


        @Around("execution(* hello.aop..*(..)) && @within(hello.aop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("at within : {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
