package hello.aop.order;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hello.aop.order.aop.AspectV4;
import hello.aop.order.aop.AspectV5;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import({AspectV5.LogAspect.class, AspectV5.TxAspect.class})
class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService : {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository : {}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("item");
    }

    @Test
    void fail() {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}