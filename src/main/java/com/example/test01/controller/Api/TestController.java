package com.example.test01.controller.Api;

import com.example.test01.entity.customDto.CustomStudentData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //CRUD restFullApi rest하게 API를 만들자 (암묵적 규칙)
    //Create, Read, Update, Delete
    //Data를 전달하는 controller URL 주소 만들기

    @ResponseBody
    @RequestMapping("/read/alldata")
    public CustomStudentData readAlldata() {
        CustomStudentData jslee = new CustomStudentData();

        jslee.setName("이진실");
        jslee.setGroup(1);
        jslee.setPosition("backend");

        return jslee;
    }


}
