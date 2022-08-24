package com.example.test01.entity.board;

/**
 * @package : com.example.Test01.controller
 * @name : BoardController.java
 * @date : 2022-08-10 16:45
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 도메인
 */

//외장 라이브러리 (gradle로 다운로드한 롬북이 외장 라이브러리)
//보드라는 객체에서 롬북(외부 라이브러리)가져다 사용. 임포트 쓰기
import com.example.test01.entity.account.Member;
import com.example.test01.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;


//롬북에 있는 Getter라는 메서드를 통해 하단에 있는 클래스 Board는
//자동으로 getter, setter 메서드가 생성됨을 암시함 (@어노테이션)


//@Entity : 이 class가 JPA를 통해 데이터베이스 테이블로 쓰겠다고 명시 해주는 속성
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {
//테이블 구조를 바꾸지 않아도 됨.

    //@id : PK (primary key) SQL문의 기본키
    //@GeneratedValue 자동생성 속성
    @Id
    @GeneratedValue
    private Long seq;

    @Setter
    //@Column은 title 필드값을 컴럼화할 때 길이와 null 입력 가능여부 옵션
    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String writer;

    @OneToMany(mappedBy = "board")
    private List<Comments> commentsList= new ArrayList<>();


    @Setter
    //@ColumnDefault 생성할 때 기본 데이터
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

    //다양한 board는 1개의 member를 바라본다
    //member를 필드에 선언
    //참조키가 어디인지 선언 (member 기본키가 board 참조키로 기본적으로 할당)
    //board의 writer는 member의 id와 연관되어 있고, 참조키로 id로 연결되어 있다.
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Member member;


    //타입이 날짜
//    @Temporal(TemporalType.DATE)
//    private Date createDate;
//
//    @Temporal(TemporalType.DATE)
//    private Date updateDate;
    //w주석처리

    @Setter
    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private Long cnt;


//    //식별 필드
//
//    private int key;
//    private Long seq;
//    private String title;
//    private String writer;
//    private String content;
//    private Date createDate;
//    private Long cnt;

    //원래는 setter, getter라는 메서드가 있어야 private 필드값에 데이터를 넣을 수 있지만,
    //(gradle에서 설치)롬북 이라는 라이브러리로 자동 getter, setter 메서드 생성하여 사용

}
