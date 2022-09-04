package com.example.test01.entity.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter //객체 정보 가져오기
@Entity //엔티티 선언
@AllArgsConstructor //생성자 객체
@NoArgsConstructor
public class FileUploadEntity {

    @Id @GeneratedValue
    private Long id;
    private String uuid;
    private String contentType;
    private String name;
    private String originalFilename;
}
