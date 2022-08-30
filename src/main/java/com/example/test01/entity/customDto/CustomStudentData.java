package com.example.test01.entity.customDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomStudentData {
    private int numOfRows;
    private int pageNo;
    private int totalCount;
    private List<Student> studentList;
}
