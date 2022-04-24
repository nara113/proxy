package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ParameterTest.ParameterAspect.class)
@SpringBootTest
public class ParameterTest {
    @Autowired
    MemberService memberService;

    @Test
    void test() {
        log.info("memberService proxy : {}", memberService.getClass());
        memberService.hello("hi");
    }

    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private void allMember() {}

        @Around("allMember()")
        Object parameter(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg = joinPoint.getArgs()[0];
            log.info("logArgs1 : {}, {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg, ..)")
        Object parameter2(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
            log.info("logArgs2 : {}, {}", joinPoint.getSignature(), arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg, ..)")
        public void parameter3(String arg) {
            log.info("logArg3 : {}", arg);
        }

        @Before("allMember() && target(object)")
        public void targetArgs(JoinPoint joinpoint, MemberService object) {
            log.info("target : {}, {}", joinpoint.getSignature(), object.getClass());
        }

        @Before("allMember() && this(object)")
        public void thisArgs(JoinPoint joinpoint, MemberService object) {
            log.info("this : {}", object.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void atTarget(JoinPoint joinpoint, ClassAop annotation) {
            log.info("atTarget : {}, {}", joinpoint.getSignature(), annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void atWithin(JoinPoint joinpoint, ClassAop annotation) {
            log.info("atWithin : {}, {}", joinpoint.getSignature(), annotation);
        }

        @Before("allMember() && @annotation(annotation)")
        public void atAnnotation(JoinPoint joinpoint, MethodAop annotation) {
            log.info("atAnnotation : {}, {}", joinpoint.getSignature(), annotation.value());
        }
    }
}
