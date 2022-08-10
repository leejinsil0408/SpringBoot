package com.example.test01.service;

/**
 * @package : com.example.Test01.service
 * @name : BoardService.java
 * @date : 2022-08-10 17:00
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 서비스
 **/


import com.example.test01.domain.Board;
import java.util.List;

public interface BoardService {

    List<Board> getBoardList();

    List<Board> getBoardList(Board board);

    void insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void daleteBoard(Board board);

    void deleteBoard(Board board);
}
