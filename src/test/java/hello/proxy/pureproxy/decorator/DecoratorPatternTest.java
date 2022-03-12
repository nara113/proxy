package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
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
        MessageDecorator component = new MessageDecorator(new RealComponent());
        DecoratorPatternClient client = new DecoratorPatternClient(component);
        client.execute();
    }

    @Test
    void decorate2() {
        MessageDecorator messageComponent = new MessageDecorator(new RealComponent());
        TimeDecorator timeDecorator = new TimeDecorator(messageComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
