package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecoratorComponent;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import org.junit.jupiter.api.Test;

class DecoratorPatternTest {
    @Test
    void noDecorate() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void decorate() {
        MessageDecoratorComponent component = new MessageDecoratorComponent(new RealComponent());
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }
}
