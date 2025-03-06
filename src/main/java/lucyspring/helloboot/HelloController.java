package lucyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {
    private final HelloService helloService;
    // 생성자에서 주입받으니 final로 설정해도 됨
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
    }

    // DispatcherServlet과 직접 관련된 어느테이션
    @GetMapping("/hello")     // 두가지 정보: get, url == RequestMapping과 동일
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name)); // null이면 예외 던짐
    }
}
