package com.example.dogproject.controller;
//핸들링

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//분기점. @comtroller 선언을 해주면 Spring boot는 HelloController 클래스가 컨트롤 담당이라고 인식
@Controller
public class HelloController {

    private final static String main ="index";
    private final static String err = "error";
    private final static String account = "account";
    private final static String slimeview = "slimState";

    //@getMapping 선언을 해주면 특정(html에서 지정한) url로 인식해서 받아오게 된다.
    //일방통행 구조 (페이지전환.변경)
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        //아래 return hello는 템플릿에 hello.html로 이동
        //Controller는 return을 통해 '알아서' hello라는 이름의 heml 파일을 찾는다.
        return "hello";
    }

    //호출하는 클라이언트의 정보를 가져다가 서버(controller)에 전달해주는 매핑
    //클라이언트가 요청한 정보를 가져오는 어노테이션

    //중괄호 {}안에 데이터를 컨트롤러에 전달 = url 이라는 변수에 데이터를 전달
    //@RequsetMapping 어노테이션이 url 정보를 갖고 있기 때문에
    //컨트롤러에서 데이터 매개변수를 받을 수 있다.
    @RequestMapping("/{url}")
    public String index(@PathVariable int url, Model model) {
        int urlFix = url;
        model.addAttribute("msg", "이 주소는 없는 경로입니다. " + urlFix);
        return main;
    }

    @RequestMapping(value = "account", method = RequestMethod.POST)
    public String account(@RequestParam("id1") String id, Model model) {
        //클라이언트에서 받아온 id1 변수이름의 데이터를
        //RequestParam의 데이터로 연산작업
        String abc = id + "안녕하세요";
        //작업한 데이터를 model에 넣어서 클라이언트에 전송
        //model.addAttribute(key, val) // value에는 배열, 객체도 아무거나 넣을 수 있음
        model.addAttribute("msg", "Hi" + id + "!!");
        model.addAttribute("msg", abc);
        //model.addAttribute("id2", id);
        //static으로 선언한 문자열 변수를 return하여 String 메모리 절약
        return account;
        //ViewResolver를 통해 html 확장자를 가진 제목의 문서를 비교해서 찾아간다.
        //return 문자열과 Model 데이터를 viewResolver에 전달

    }
}

