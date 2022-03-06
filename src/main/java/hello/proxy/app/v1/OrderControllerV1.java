package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller 또는 @RequestMapping이 있으면 스프링 컨트롤러로 인식
//@Controller는 @Component가 있어서 스캔 대상이 되므로 RequestMapping사
@RequestMapping
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
