package lucyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        //  Spring Container
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // getBean 내부 타입 클래스를 찾아옴
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                //dispatcherServlet.setApplicationContext(this); // 스프링 컨테이너 받아옴

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                            .addMapping("/*"); // 모든 요청 처리(프론트 컨트롤러)
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);  // 빈 등록(DI)
        applicationContext.refresh(); // 구성 정보를 이용해 빈 오브젝트 초기화(스프링 컨테이너 초기화)
    }
}
