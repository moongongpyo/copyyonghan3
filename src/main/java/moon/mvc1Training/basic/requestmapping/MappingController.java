package moon.mvc1Training.basic.requestmapping;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping("/hello-basic") //배열로 여러 개 넣을 수 도 있음
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data
                              //PathVariable 의 이름과 변수명이 같으면 생략 가능
                              //예시 @PathVariable String userId
                              //다중 매핑도 가능
    ) {

        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode", //모드라는 헤더가 있어야함
     * headers="!mode"//모드라는 헤더가 있으면 안됨
     * headers="mode=debug" // 조건이 맞아야 함
     * headers="mode!=debug" (! = )// 조건이 맞으면 안됨
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }
    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    //헤더의 컨텐트 타입이 서버가 소비하고자 하는 타입과 같아야 함// 아니면 404
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }
    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    //http 요청의 accept 헤더와 맞아야함//아니면 406//헤거가 요청으로 받기 원하는 다입과 서버가 제공하는 타입이 같아야 함
    @PostMapping(value = "mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

}
