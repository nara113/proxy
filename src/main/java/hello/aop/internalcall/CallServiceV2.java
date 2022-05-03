package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CallServiceV2 {

//    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> objectProvider;

    public void external() {
//        CallServiceV2 call = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 call = objectProvider.getObject();
        call.internal();
    }

    public void internal() {
        log.info("[internal call]");
    }
}
