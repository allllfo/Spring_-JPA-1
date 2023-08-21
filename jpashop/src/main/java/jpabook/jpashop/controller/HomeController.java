package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j // logger를 쓸 수 있게 해주는 롬복의 기능
public class HomeController {

    @RequestMapping("/") // 첫화면과 매핑됨
    public String home(){
        log.info("home controller"); // home controller에 대한 로그가 찍히게 됨
        return "home"; //home.html로 찾아가서 타임리프 파일을 찾아가게 됨
    }
}
