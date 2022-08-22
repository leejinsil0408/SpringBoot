package com.example.test01.controller.board;

/**
 * @package : com.example.Test01.controller
 * @name : BoardController.java
 * @date : 2022-08-10 16:45
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 컨트롤러
 */

import com.example.test01.entity.account.Member;
import com.example.test01.entity.board.Board;
import com.example.test01.entity.board.Comments;
import com.example.test01.service.board.BoardService;
import com.example.test01.service.account.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    protected BoardController(BoardService boardService) { this.boardService = boardService; }

    @PostMapping("/insertComment")
        public String insertComment(Comments comments, Model model) {
        boardService.insertComment(comments);
        return "redirect:/board/getBoardList";
        }

    //BoardService의 getBoardList메서드 실행 > BoardRepository(CrudRepository).findAll()를 통해서 (JPA번역)
    //DB의 데이터 불러오기(테이블전체) (SQL)
    @GetMapping("/getBoardList")
    public String getBoardList(Model model, Board board) {
        System.out.println("-------------------");
        List<Board> boardList = boardService.getBoardList(board);
        model.addAttribute("boardList", boardList);
        return "/board/getBoardList";
    }

    @GetMapping("/insertBoard")
    public String insertBoard() {
        System.out.println("------insertBoard_get-------------");
        return "/board/insertBoard";
    }

    //클라이언트에서 board객체를 받아서 매개변수로 사용
    //[1]BoardService의 inserBoard메서드 실행 >
    //[2]BoardRepository(CrudRepository).save(board)를 통해서 (JPA번역)
    //DB의 저장 (SQL)
    //insertBoard라는 메서드에 board객체 인자값으로 넣기
    @PostMapping("/insertBoard")
    public String insertBoard(Board board) {
        System.out.println("--------insertBoard_post-----------");
        System.out.println(board.getCreateDate());
        System.out.println(board.getUpdateDate());
        board.setCreateDate(new Date());
        board.setUpdateDate(new Date());
        System.out.println(board.getCreateDate());
        System.out.println(board.getUpdateDate());
        boardService.insertBoard(board);
        return "redirect:board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        System.out.println("-------------------");
        System.out.println(board.getSeq());
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/getBoard";
    }

    @PostMapping ("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("----------updateBoard---------");
        System.out.println(board.getContent());
        System.out.println(board.getSeq());
        boardService.updateBoard(board);
        return "redirect:/board/getBoard?seq="+board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        System.out.println("-------------------");
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/insertBoard";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("--------board delete-----------");
        System.out.println(board.getSeq());
        boardService.deleteBoard(board);
        return "redirect:/board/getBoardList";
    }

    @GetMapping("/selectBoard") //최종적으로 model도 리턴
    public String selectBoard(Member member, Model model ) {
        System.out.println("--------board select-----------");
        //board.getId()는 클라이언트에서 가져옴
        //@Service에 board를 인자값으로 넣고 메서드 실행
        boardService.getBoardListByMemberId(member);
        model.addAttribute("boardList",boardService.getBoardListByMemberId(member));

        //회원이 작성한 게시글 리스트(List<Board>) 리턴 --> HTML에 뿌려주면 끝
        // 이유 : Controller에 가면 메서드가 실행돼서 다른 결과물을 리턴받기 때문
        // 어느 HTML로 가냐? 객체지향은 재활용성이 중요한 요인 중 하나
        //HTML 중에 재사용 할만한 것을 먼저 찾고, 그 후에 새로 만들기에 대해 고민 : getBoardList 재활용
        //최종적으로 무엇을 리턴? return 페이지 OR controller mapping
        return "/board/getBoardList";
    }
}
