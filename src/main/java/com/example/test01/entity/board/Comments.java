package com.example.test01.entity.board;

import com.example.test01.entity.account.Member;
import com.example.test01.entity.base.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String comments_content;

//    @ManyToOne(fetch = FetchType.EAGER.LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private Member member;

    @Transient
    private Long board_title;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "board_seq",referencedColumnName = "seq")
//    private Board board;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "board_title", referencedColumnName = "title")
private Board board;

    public Comments(String comments_content, Board board) {
        this.comments_content = comments_content;
        this.board = board;
    }

}
