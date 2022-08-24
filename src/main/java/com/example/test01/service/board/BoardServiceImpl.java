package com.example.test01.service.board;

/**
 * @package : com.example.Test01.service
 * @name : BoardService.java
 * @date : 2022-08-10 17:00
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 서비스 구현체
 **/

import com.example.test01.entity.account.Member;
import com.example.test01.entity.board.Board;
import com.example.test01.entity.board.Comments;
import com.example.test01.repository.board.BoardRepository;
import com.example.test01.repository.board.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//JPA가 기능에 맞춰 SQL을 바꿔주는 기능.
// @Service로 선언된 클래스를 갖고 JDBC에게 기능적인 구현을 위한 속성
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepo;
    private final CommentsRepository commentsRepository;

    @Autowired
    protected BoardServiceImpl(BoardRepository boardRepo, CommentsRepository commentsRepository, CommentsRepository commentsRepository1) {
        this.commentsRepository = commentsRepository;
        this.boardRepo = boardRepo;
    }

//BoardRepository에 있는 DB와 연동하여 기능하는 것을 명시

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의
    //findAll 메서드를 통해서 전체 조회

    @Override
    public List<Board> getBoardList(Board board) {
        return (List<Board>) boardRepo.findAll();
    }

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository
    //save 메서드를 통해서 DB에 저장 (저장하는 SQL문 만들어서 실행)

    @Override
    public void insertBoard(Board board) {
        boardRepo.save(board);
    }

    @Override
    public Board getBoard(Board board) {
        return boardRepo.findById(board.getSeq()).get();
    }

    @Override
    public void updateBoard(Board board) {
        Board findBoard = boardRepo.findById(board.getSeq()).get();

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepo.save(findBoard);
    }

    @Override
    public void deleteBoard(Board board) {
        boardRepo.deleteById(board.getSeq());
    }

    @Override
    public void insertComment(Comments comments) {
        //boolean title 체크
        //insert comment 실행
        //트랜젝션 처리
    }

    @Override
    public boolean booleanMemberIdEqualsBoardWriterByMember(Member member) {
        return false;
    }

    @Override
    public List<Board> getBoardListByMemberId(Member member) {
        //Repository
        System.out.println("----getBoardListByMemberId----");
        System.out.println(member.getId());
        return boardRepo.findAllByMemberIdEqualsBoardWriter(member.getId());
    }

    @Override
    public List<String> doNounsAnalysis(List<Board> boardlist) {
        return null;
    }

    @Override
    public List<Board> getAutoKeywordBoardList(List<String> keyword) {
        return null;
    }

    @Override
    public List<Board> getBoardListSortColumnByBoardList(List<Board> boardlist) {
        return null;
    }

    @Override
    public List<Comments> getAllComments(Comments comments) {
        return commentsRepository.findCommentByBoard_seq(comments.getBoard_seq());
    }
}