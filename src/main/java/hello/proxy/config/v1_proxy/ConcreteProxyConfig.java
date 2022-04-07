package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerV2Proxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryV2Proxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceV2Proxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        final OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderService(logTrace));
        return new OrderControllerV2Proxy(orderControllerV2, logTrace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        final OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepository(logTrace));
        return new OrderServiceV2Proxy(orderServiceV2, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        final OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
        return new OrderRepositoryV2Proxy(orderRepositoryV2, logTrace);
    }
}
