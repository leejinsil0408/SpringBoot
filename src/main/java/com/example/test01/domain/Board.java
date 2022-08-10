package com.example.test01.domain;

//외장 라이브러리 (gradle로 다운로드한 롬북이 외장 라이브러리)
//보드라는 객체에서 롬북(외부 라이브러리)가져다 사용. 임포트 쓰기
import lombok.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//롬북에 있는 Getter라는 메서드를 통해 하단에 있는 클래스 Board는
//자동으로 getter, setter 메서드가 생성됨을 암시함 (@어노테이션)


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity : 이 class가 JPA를 통해 데이터베이스 테이블로 쓰겠다고 명시 해주는 속성
@Entity

public class Board {

    //@id : PK {primary key) SQL문의 기본키
    //@GeneratedValue 자동생성 속성
    @Id @GeneratedValue
    private Long seq;

    //@Column은 title 필드값을 컴럼화할 때 길이와 null 입력 가능여부 옵션
    @Column(length = 40, nullable = false)
    private String title;


    //식별 필드

    private int key;
    private Long seq;
    private String title;
    private String writer;
    private String content;
    private Date createDate;
    private Long cnt;

    //원래는 setter, getter라는 메서드가 있어야 private 필드값에 데이터를 넣을 수 있지만,
    //(gradle에서 설치)롬북 이라는 라이브러리로 자동 getter, setter 메서드 생성하여 사용

}
