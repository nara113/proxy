package hello.aop;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "hello.aop")
public class AopApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(AopApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
	}

}