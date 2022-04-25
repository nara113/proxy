package hello.aop.exam;

import hello.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {

    private static int seq = 1;

    @Trace
    public String save(String itemId) {
        if (seq % 5 == 0) {
            throw new IllegalStateException("error");
        }

        seq++;
        return "ok";
    }
}
