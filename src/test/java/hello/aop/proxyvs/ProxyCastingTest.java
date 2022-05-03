package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        MemberService proxy = (MemberService) proxyFactory.getProxy(); //JDK 동적 프록

        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl impl = (MemberServiceImpl) proxy;
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); //CGLIB

        MemberService proxy = (MemberService) proxyFactory.getProxy();

        MemberServiceImpl impl = (MemberServiceImpl) proxy;
    }
}
