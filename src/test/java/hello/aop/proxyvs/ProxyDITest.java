package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
public class ProxyDITest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void test() {
        log.info("MemberService  : {}", memberService.getClass());
        log.info("MemberServiceImpl : {}", memberServiceImpl.getClass());

        memberServiceImpl.hello("hi");
    }
}
