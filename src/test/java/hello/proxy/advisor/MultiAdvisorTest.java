package hello.proxy.advisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class MultiAdvisorTest {

    @DisplayName("여러 프록시")
    @Test
    void test1() {
        ServiceInterface service = new ServiceImpl();

        ProxyFactory proxyFactory1 = new ProxyFactory(service);
        final DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvisor1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        final DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvisor2());
        proxyFactory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.find();
    }


    @DisplayName("하나의 프록시")
    @Test
    void test2() {
        ServiceInterface service = new ServiceImpl();

        final DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvisor1());
        final DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new MyAdvisor2());

        ProxyFactory proxyFactory1 = new ProxyFactory(service);

        proxyFactory1.addAdvisor(advisor1);
        proxyFactory1.addAdvisor(advisor2);
        ServiceInterface proxy = (ServiceInterface) proxyFactory1.getProxy();

        proxy.find();
    }

    static class MyAdvisor1 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advisor 1");
            return invocation.proceed();
        }
    }

    static class MyAdvisor2 implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advisor 2");
            return invocation.proceed();
        }
    }

}
