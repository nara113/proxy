package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Time proxy 실행");
        final long startTime = System.currentTimeMillis();

        final Object result = invocation.proceed();

        final long endTime = System.currentTimeMillis();
        log.info("Time proxy 종료 result time : {}", endTime - startTime);
        return result;
    }
}
