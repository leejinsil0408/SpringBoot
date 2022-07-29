package com.example.test.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.domain.Board;

@Controller
public class BoardController {

    @RequestMapping ("/getBoard")
    public String getBoard(Board board, Model model) {
        model.addAttribute("board","num");
        return "getBoard";
    }

    @RequestMapping("/getBoardList")
    public String getBoardList(Model model) {
        //List 타입으로 Board 객체를 넣는 boardList 변수명 선언
        //= 대입 연산자로 heap 메모리에 Arraylist 타입으로 할당
        //List는 ArrayList의 부모클래스. 객체 Board 삽입
        List<Board> boardList = new ArrayList<Board>();
        //1부터 시작한 이유? 시퀀스
        for (int i = 1; i <=10; i++) {
            //Board Class로 board 인스턴스 생성
            Board board = new Board();
            //롬북으로 자동생성된 Setter 메서드로 데이터 입력
            board.setSeq(new Long(i));
            board.setTitle("게시판 프로그램 테스트");
            board.setWriter("도우너");
            board.setContent("게시판 프로그램 테스트입니다...");
            board.setCreateDate(new Date());
            board.setCnt(0L);
            //boardList배열에 board 객체 넣기 (for문 10번 도니까 board 객체 10개 넣기)
            boardList.add(board);
        }
        model.addAttribute("boardList", boardList);
        //ViewResolver
        //return getBoardList라는 문자열로 templates에 있는 같은 이름의 html 파일을 찾는다
        //model도 가지고 간다.
        return "getBoardList";
    }
}
