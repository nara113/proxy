package hello.proxy.pureproxy.decorator.code;

import org.junit.jupiter.api.Test;

class DecoratorPatternTest {
    @Test
    void noDecorate() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }
}
