package moon.mvc1Training.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.HandshakeResponse;
import java.util.Locale;

@Slf4j
@RestController
public class requestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletRequest response,
                          HttpMethod httpMethod,
                          Locale locale, //언어정보
                          @RequestHeader MultiValueMap<String,String> headerMap, //키와 값이 모두 String 인 헤더//같은 키에 여러 값이 있을때
                          @RequestHeader("host")String host, //키가 host인 헤더
                          @CookieValue(value = "myCookie", required = false) String cookie // 이름이 myCookie인 쿠키, 없어도 작동
                        )
    {
        log.info("request={}",request);
        log.info("response={}",response);
        log.info("httpMethod={}",httpMethod);
        log.info("locale={}",locale);
        log.info("headerMap={}",headerMap);
        log.info("header host={}",host);
        log.info("myCookie={}",cookie);
        return "ok";
    }
}
