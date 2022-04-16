package hello.proxy.cglib.code;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("Time proxy 실행");
        final long startTime = System.currentTimeMillis();

        final Object result = methodProxy.invoke(target, args);

        final long endTime = System.currentTimeMillis();
        log.info("Time proxy 종료 result time : {}", endTime - startTime);
        return result;
    }
}
