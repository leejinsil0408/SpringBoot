package com.example.test01.repository.board;

import com.example.test01.entity.board.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//<entity, pk의 타입>
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    //Comments와 다대일관계의 보드를 조인, 코멘트의 보드 시퀀스와 이눗보드 시퀀스가 같을 경우.
    @Query(value = "SELECT c FROM Comments c JOIN fetch c.board WHERE c.board.seq = :input_board_Seq")
    List<Comments> findCommentByBoard_seq(Long input_board_seq);

}
