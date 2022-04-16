package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {
    @Bean
    public OrderControllerV2 OrderControllerV2(LogTrace logTrace) {
        OrderControllerV2 OrderControllerV2 = new OrderControllerV2(OrderServiceV2(logTrace));

        final ProxyFactory proxyFactory = new ProxyFactory(OrderControllerV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        final OrderControllerV2 proxy = (hello.proxy.app.v2.OrderControllerV2) proxyFactory.getProxy();

        log.info("targetClass : {}, proxyClass : {}", OrderControllerV2.getClass(), proxy.getClass());

        return proxy;
    }

    @Bean
    public OrderServiceV2 OrderServiceV2(LogTrace logTrace) {
        OrderServiceV2 OrderServiceV2 = new OrderServiceV2(OrderRepositoryV2(logTrace));

        final ProxyFactory proxyFactory = new ProxyFactory(OrderServiceV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        final OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();

        log.info("targetClass : {}, proxyClass : {}", OrderServiceV2.getClass(), proxy.getClass());

        return proxy;
    }

    @Bean
    public OrderRepositoryV2 OrderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 OrderRepositoryV2 = new OrderRepositoryV2();

        final ProxyFactory proxyFactory = new ProxyFactory(OrderRepositoryV2);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        final OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();

        log.info("targetClass : {}, proxyClass : {}", OrderRepositoryV2.getClass(), proxy.getClass());

        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*" ,"save*");

        final LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
