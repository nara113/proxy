package hello.aop.exam;

import hello.aop.exam.aop.TraceAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TraceAspect.class)
@SpringBootTest
class ExamTest {
    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 10; i++) {
            examService.request("hi " + i);
        }
    }

}