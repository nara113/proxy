package hello.proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConcreteProxy extends ConcreteLogic {
    private final ConcreteLogic concreteLogic;

    @Override
    public String operate() {
        log.info("TimeDecorator 실행");
        final long start = System.currentTimeMillis();

        final String data = concreteLogic.operate();

        final long end = System.currentTimeMillis();
        final long resultTime = end - start;
        log.info("TimeDecorator 종료. result time : {}", resultTime);
        return data;
    }
}
