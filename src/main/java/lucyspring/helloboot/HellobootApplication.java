package lucyspring.helloboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(HellobootApplication.class, args);
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			HelloController helloController = new HelloController();

			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						///  요청 객체 사용
						String name = req.getParameter("name");

						String ret = helloController.hello(name);

						/// 응답 객체
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}

					else if (req.getRequestURI().equals("/user")){
						//
					}

					else {
						resp.setStatus((HttpStatus.NOT_FOUND.value()));
					}
				}
			}).addMapping("/*"); // 모든 요청 처리(프론트 컨트롤러)
		});
		webServer.start();
	}

}
