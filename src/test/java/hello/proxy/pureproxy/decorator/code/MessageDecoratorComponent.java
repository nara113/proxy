package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecoratorComponent implements Component {
    private Component component;

    public MessageDecoratorComponent(Component component) {
        this.component = component;
    }

    @Override
    public String operate() {
        log.info("MessageDecorator 실행");
        final String data = component.operate();
        String decoratedData = "**" + data;
        log.info("decorate 전 : {}, 후 : {}", data, decoratedData);
        return decoratedData;
    }
}
