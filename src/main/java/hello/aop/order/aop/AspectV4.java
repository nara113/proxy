package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV4 {
    @Around("hello.aop.order.aop.PointCuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("hello.aop.order.aop.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;

        try {
            log.info("[transaction start] {}", joinPoint.getSignature());
            result = joinPoint.proceed();
            log.info("[transaction commit] {}", joinPoint.getSignature());
        } catch (Exception e) {
            log.info("[transation rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[transaction resource release] {}", joinPoint.getSignature());
        }

        return result;
    }
}
