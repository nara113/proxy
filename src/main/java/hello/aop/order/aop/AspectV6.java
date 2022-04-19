package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV6 {

    @Around("hello.aop.order.aop.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;

        try {
            //@Before
            log.info("[transaction start] {}", joinPoint.getSignature());
            result = joinPoint.proceed();
            //@AfterReturning
            log.info("[transaction commit] {}", joinPoint.getSignature());
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[transation rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[transaction resource release] {}", joinPoint.getSignature());
        }

        return result;
    }

    @Before("hello.aop.order.aop.PointCuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @Before("hello.aop.order.aop.PointCuts.orderAndService()")
    public void doBefore2() {
        log.info("[hello]");
    }

    @AfterReturning(value = "hello.aop.order.aop.PointCuts.orderAndService()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //return 값을 조작할 수는 없다.
        log.info("[return] {} , result : {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.PointCuts.orderAndService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[throwing] {}, ex : {}", joinPoint.getSignature(), ex);
    }

    @After("hello.aop.order.aop.PointCuts.orderAndService()")
    public void after(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
