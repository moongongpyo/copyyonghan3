package moon.mvc1Training.basic.request;
import lombok.extern.slf4j.Slf4j;
import moon.mvc1Training.basic.HelloData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class requestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response )throws IOException{
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={},age={}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 뷰 리졸버가 ok라는 뷰를 찾지 않고 ok를 바로 반환하게 만들어줌
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username")String memberName,
            @RequestParam("age")int mamberAge
    ){
        log.info("username={},age={}",memberName,mamberAge);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(               //변수명과 파라미터 명이 일치하면 괄호 생략 가능
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={},age={}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){ //단순 타입이면 @RequestParam도 생략 가능//직관성 떨어져서 권장하지 않음
        log.info("username={},age={}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age    //파라미터 값이 필수적으로 존재하지 않아도 됨//int는 null이 들어갈 수 없기에 객체형인 Integer 형으로 받아준다
    ){
        log.info("username={},age={}",username,age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
        log.info("username={},age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribyte-v1")
    public String modelAttributeV1(
    //        @RequestParam String username, @RequestParam int age
            @ModelAttribute HelloData helloData
    ){
      /*  HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);*/
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        log.info("hellopData={}",helloData);

        return "ok";
    }
    @ResponseBody
    @RequestMapping("/model-attribyte-v2")
    public String modelAttributeV2(HelloData helloData //@ModelAttribute  생략가능
                                    ){
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        log.info("hellopData={}",helloData);

        return "ok";
    }

}
