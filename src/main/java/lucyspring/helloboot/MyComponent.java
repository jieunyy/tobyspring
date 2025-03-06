package lucyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 애노테이션 유지 기간
@Target(ElementType.TYPE)           // 애노테이션 적용할 대상
@Component                          // 메타 애노테이션화
public @interface MyComponent {
}
