package com.example.test01.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public abstract class BaseTimeEntity {

//JPA Entity 클래스들이 BaseTimeEntity를 상속할 때 createDate,modifiedDate도 컬럼으로 인식
//AuditingEntityListener는 JPA 내부에서 Entity 객체가 생성/변경되는 것을 감지

    @Setter
    @Getter

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date updateDate;
}

