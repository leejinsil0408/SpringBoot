package com.example.test01.service;

import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//JPA가 기능에 맞춰 SQL을 바꿔주는 기능.
// @Service로 선언된 클래스를 갖고 JDBC에게 기능적인 구현을 위한 속성
@Service
public class BoardServicelmpl implements BoardService {

    @Autowired
    private BoardRepository boardRepo;
    //BoardRepository에 있는 DB와 연동하여 기능하는 것을 명시

    @Override

}
