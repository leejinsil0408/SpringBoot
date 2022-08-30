package com.example.test01.controller.Api;

import com.example.test01.entity.account.Member;
import com.example.test01.entity.customDto.CustomAPIDtoExample;
import com.example.test01.entity.customDto.CustomStudentData;
import com.example.test01.entity.customDto.Student;
import com.example.test01.service.account.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//api 데이터 전송 컨트롤로 메서드들은 @responsBody 속성 가짐
//@RestController
@Controller
public class TestController {
    private final MemberService memberService;

    @Autowired
    protected TestController(MemberService memberService) {
        this.memberService = memberService;
    }

    //CRUD restFullApi rest하게 API를 만들자 (암묵적 규칙)
    //Create, Read, Update, Delete
    //Data를 전달하는 controller URL 주소 만들기

    @ResponseBody
    @RequestMapping("/read/alldata")
    public CustomStudentData readAlldata() {
        Student jslee = new Student();

        jslee.setName("이진실");
        jslee.setGroup(1);
        jslee.setPosition("backend");
        jslee.setMemo("한국어~");

        Student jskim = new Student();

        jslee.setName("김진실");
        jslee.setGroup(1);
        jslee.setPosition("frontend");
        jslee.setMemo("영어~");

        CustomStudentData cst = new CustomStudentData();

        List<Student> studentList = new ArrayList<>();
        studentList.add(jslee);
        studentList.add(jskim);
        cst.setStudentList(studentList);

        return cst;
    }
    @ResponseBody
    @RequestMapping("/read/alltest")
    public List<Member> readAllTest() {

        return memberService.getMemberListEncodingByMemberList(
                memberService.getMemberList());
    }

    @ResponseBody
    @RequestMapping("/test1")
    public CustomAPIDtoExample test1(){

        CustomAPIDtoExample blog = new CustomAPIDtoExample();

        blog.setTitle("테스트1");
        blog.setContent("테스트1 내용");

        return blog;

    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(){
        JsonObject obj =new JsonObject();

        obj.addProperty("title", "테스트2");
        obj.addProperty("content", "테스트2 내용");

        JsonObject data = new JsonObject();

        data.addProperty("time", "12:00");

        obj.add("data", data);

        return obj.toString();
    }

}
