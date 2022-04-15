package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteService {
    void call() {
        log.info("Concrete Service 호출");
    }
}
