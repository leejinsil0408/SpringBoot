package com.example.test01.entity.board;

import com.example.test01.entity.account.Member;
import com.example.test01.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    private Long seq;

    private String comments;

//    @ManyToOne(fetch = FetchType.EAGER.LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private Member member;

//    @Transient
    private Long board_seq;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "board_seq",referencedColumnName = "seq")
//    private Board board;
@ManyToOne
@JoinColumn(referencedColumnName = "title")
private Board board;

}
