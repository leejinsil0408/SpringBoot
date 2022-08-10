package com.example.test01.service;

import com.example.test01.domain.Board;
import java.util.List;

public interface BoardService {

    List<Board> getBoardList(Board board);

    void insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void daleteBoard(Board board);
}
