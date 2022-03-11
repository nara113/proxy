package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxySubject;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

class ProxyPatternTest {
    @Test
    void noProxyTest() {
        ProxyPatternClient client = new ProxyPatternClient(new RealSubject());

        // 3초
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void proxyTest() {
        CacheProxySubject cacheProxySubject = new CacheProxySubject(new RealSubject());
        ProxyPatternClient client = new ProxyPatternClient(cacheProxySubject);

        // 1초
        client.execute();
        client.execute();
        client.execute();
    }
}
