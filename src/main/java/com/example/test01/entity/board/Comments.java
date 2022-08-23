package com.example.test01.entity.board;

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

    private String Comments;

    @ManyToOne
    @JoinColumn(referencedColumnName = "title")
    private Board board;
}
