package hello.proxy.jdkdynamic.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log.info("Time proxy 실행");
        final long startTime = System.currentTimeMillis();

        final Object result = method.invoke(target, objects);

        final long endTime = System.currentTimeMillis();
        log.info("Time proxy 종료 result time : {}", endTime - startTime);
        return result;
    }
}
