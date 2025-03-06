package lucyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat; // 간결하게 변화(static import)
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {
    @Test
    void helloApi() {
        //RestTemplate
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "lucy");

        // 응답 검증 3가지: status code, header, body
        // status code 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);  // junits의 Assertions

        // header(content-type) text/plain? 어쩌구여도 앞부분만 비교
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);

        // body hello lucy
        assertThat(res.getBody()).isEqualTo("Hello lucy");
    }
}
