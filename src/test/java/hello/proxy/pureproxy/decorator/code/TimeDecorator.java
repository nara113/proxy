package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {
    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operate() {
        log.info("TimeDecorator 실행");
        final long start = System.currentTimeMillis();

        final String data = component.operate();

        final long end = System.currentTimeMillis();
        final long resultTime = end - start;
        log.info("TimeDecorator 종료. result time : {}", resultTime);
        return data;
    }
}
