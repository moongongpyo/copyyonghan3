package moon.mvc1Training.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import moon.mvc1Training.basic.HelloData;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.HandshakeResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController{

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream(); // 요청 본문을 읽어오는 메서드
       /* String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //입력 스트림을 문자열로 바꾸는 메서드
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody,HelloData.class);*/ //예제는 이렇게 하는데 왜 궅이 문자열로 파싱하는지 이해안됨
        HelloData helloData = objectMapper.readValue(inputStream,HelloData.class);
        log.info("helloData={}",helloData);

        response.getWriter().write("ok");
    }
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody,HelloData.class); //문자열을 자바 객체로 역직렬화
        log.info("helloData={}",helloData);

        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData data //@RequestBody는 생략 불가//생략하면 @ModelAttribute가 적용됨
    ){
        log.info("helloData={}",data);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
        HelloData data = httpEntity.getBody();
        log.info("helloData={}",data);
        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data){
        log.info("helloData={}",data);
        return data;
    }
}
