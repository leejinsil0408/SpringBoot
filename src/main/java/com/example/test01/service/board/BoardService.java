package com.example.test01.service.board;

/**
 * @package : com.example.Test01.service
 * @name : BoardService.java
 * @date : 2022-08-10 17:00
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 서비스
 **/

import com.example.test01.entity.account.Member;
import com.example.test01.entity.board.Board;
import com.example.test01.entity.board.Comments;
import com.example.test01.entity.customDto.CustomDtoSortPages;
import com.example.test01.entity.data.FileUploadEntity;

import java.util.List;

public interface BoardService {

    List<Board> getBoardList(Board board);

    Long insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(Board board);

    void insertComment(Comments comments);

    //board의 작성자와 회원이 같은지 확인 [2,5조...]
    boolean booleanMemberIdEqualsBoardWriterByMember(Member member);

    //작성자의 모든 게시글 출력
    List<Board> getBoardListByMemberId(Member member);

    //키워드분석 [4조]
    List<String> doNounsAnalysis(List<Board> boardList);

    //관련된 키워드 게시글 출력
    List<Board> getAutoKeywordBoardList(List<String> keyword);

    //오름차순으로 변경 (arrayList)
    List<Board> getBoardListSortColumnByBoardList(List<Board> boardlist);

    //작성자의 모든 게시글 출력
    List<Board> getBoardListAllBoardListByMemberId(Member member);

    List<List<Object>> getBoardAndMemberUsersBoard();

    List<Comments> getAllComments(Comments comments);

    CustomDtoSortPages getPagesSortIndex(Board board);

    Long insertFileUploadEntity(FileUploadEntity fileUploadEntity);
    //이제 보드서비스Impl 구현하기

//    FileUploadEntity getFileUploadEntity(String board_seq);


    FileUploadEntity getFileUploadEntity2(Long board_seq);
}

