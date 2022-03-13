package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.ProxyController;
import hello.proxy.config.v1_proxy.interface_proxy.ProxyRepository;
import hello.proxy.config.v1_proxy.interface_proxy.ProxyService;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        final OrderControllerV1Impl orderControllerV1 = new OrderControllerV1Impl(orderService(logTrace));
        return new ProxyController(orderControllerV1, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        final OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepository(logTrace));
        return new ProxyService(orderServiceV1, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        final OrderRepositoryV1Impl orderRepositoryV1 = new OrderRepositoryV1Impl();
        return new ProxyRepository(orderRepositoryV1, logTrace);
    }

}