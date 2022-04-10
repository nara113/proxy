package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Slf4j
    static class Hello {
        public String callA() {
            return "A";
        }

        public String callB() {
            return "B";
        }
    }

    @Test
    void reflectionV1() {
        Hello target = new Hello();

        // 공통로직 1
        log.info("start");
        final String return1 = target.callA();
        log.info("return : {}", return1);

        // 공통로직 2
        log.info("start");
        final String return2 = target.callB();
        log.info("return : {}", return2);
    }

    @Test
    void reflectionV2() throws Exception {
        final Class<?> hello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        final Method callA = hello.getMethod("callA");
        final Object return1 = callA.invoke(target);
        log.info("result1 : {}", return1);

        final Method callB = hello.getMethod("callB");
        final Object return2 = callB.invoke(target);
        log.info("result2 : {}", return2);
    }

    @Test
    void reflectionV3() throws Exception {
        final Class<?> hello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        final Method callA = hello.getMethod("callA");
        dynamicCall(callA, target);

        final Method callB = hello.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        final Object result = method.invoke(target);
        log.info("result : {}", result);
    }
}
