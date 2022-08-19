package com.example.test01.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


//JPA Entity 클래스들이 BaseTimeEntity를 상속할 때 createDate,modifiedDate도 컬럼으로 인식
//AuditingEntityListener는 JPA 내부에서 Entity 객체가 생성/변경되는 것을 감지

    @Setter
    @Getter
    @MappedSuperclass
    public abstract class BaseTimeEntity {

        @Temporal(TemporalType.DATE)
        @CreatedDate
        private Date createDate;

        @Temporal(TemporalType.DATE)
        @LastModifiedDate
        private Date updateDate;
    }

//    @CreatedDate
//    private Date createDate;
//
//    @LastModifiedDate
//    private Date updateDate;

