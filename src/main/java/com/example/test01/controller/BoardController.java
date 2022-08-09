package com.example.test01.controller;

//외장 라이브러리 호출(import), gradle로 설치한 라이브러리
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.test01.domain.Board;

//내장 라이브러리 호출(import) 굳이 설치 안해도 자바에 있는 것 씀
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BoardController {


    //step1. 일반 문자열 변수 사용
    static String title_String_static = "";
    static String writer_String_static = "";
    static String content_String_static = "";

    //step2. 배열 객체 사용
    static ArrayList<String> title_array = new ArrayList<String>();
    static ArrayList<String> writer_array = new ArrayList<String>();
    static ArrayList<String> content_array = new ArrayList<String>();

    //step3. 사용자 생성 객체 사용
    //개터세터가 있는 보드를 사용하겠다고 선언
    static ArrayList<Board> board_array = new ArrayList<Board>();
    static int count = 0;

    //@getMapping 또는 @PostMapping은 @RequestMapping의 자식 클래스이다.
    //@RequestMapping의 기능을 모두 쓸 수 있다.
    //자식클래스 어노테이션이 아닌 부모클래스 어노테이션을 쓰는 이유는 기능의 한정을 통해서
    //서버의 리소스 감소 및 보안을 위해서이다.


    @GetMapping("insertBoard")
    public String insertBoard() {
        return "insertBoard";
    }

    //[클라이언트]html form태그의 mothod속성의 값인 post를 인식하여
    //아래의 postMapping에 연결

    @PostMapping("insertBoard")
    public String insertBoard(
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content) {

        title_String_static = title;
        writer_String_static = writer;
        content_String_static = content;

        title_array.add(title);
        writer_array.add(writer);
        content_array.add(content);

        count++;  //인덱스 값. 인썰트값에서 필요한 경우만 추가, 향후 삭제를 할 수 있기에 카운트++
        Board board = new Board();
        //Board라는 타입으로 board 변수 선언. 대입연산자(=) 인스턴스 Board를 만들고 메모리 직접 할당

        board.setSeq((long) count );
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content); //매개변수로 받은 인자값 보드의 객체 필드값은 데이터가 하나하나씩 저장
        board.setCreateDate(new Date());
        board.setCnt(0l);
        board_array.add(board); //보드 어레이에 순차적으로 집어 넣음

        //redirect : 페이지 전환 이동
        return "redirect:getBoardList";

    }


    //@어노테이션은 메서드 혹은 클래스에 속성, 정의를 해서 스프링이나 자바에서 찾기 쉽도록 해주는 선언부
    //예) @Override은 부모 메서드를 재정의하여 사용한다고 자바나 스프링에게 속성 명시
    //@RequestParam : [클라이언트]에서 String문자열을 [서버]에 전달하는 매개변수 선언
    //@RequestParam("title")String title
    //("title)은 [클라이언트]의 name이라는 속성으로써
    //key값을 매개변수로 전달

    @RequestMapping("getBoard")
    public String getBoard(
            @RequestParam("seq")String seq,
            @RequestParam("title") String title,
            @RequestParam("writer") String writer,
            @RequestParam("content") String content,
            @RequestParam("createDate") String createDate,
            @RequestParam("cnt") String cnt,
            Model model) {
        model.addAttribute("seq", seq);
        model.addAttribute("title", title);
        model.addAttribute("writer", writer);
        model.addAttribute("content", content);
        model.addAttribute("createDate", createDate);
        model.addAttribute("cnt", cnt);
        return "getBoard";
    }

    //@RequsetMapping은 [서버[에서 디스페쳐서블릿을 통해 [클라이언트] html의 action태그의 주소와 동일한
    //문자열을 찾는 매핑기능(연결)이 실행되고 하단에 메서드가 실행
    //return String인 이유는 뷰리졸버가 html파일을 찾기 위한 문자열을 리턴

    @RequestMapping("/getBoardList")
    public String getBoardList(Model model) {
        //List 타입으로 Board 객체를 넣는 boardList 변수명 선언
        //= 대입 연산자로 heap 메모리에 ArrayList 타입으로 할당
        //List는 ArrayList의 부모클래스. 객체 Board 삽입
        List<Board> boardList = new ArrayList<Board>();


        //title_array.size()로 게시판 글이 1개 이상일 경우에만 model에 데이터 입력하여 [클라이언트]에게 전달
        if (board_array.size() > 0) {
            for (int i = 0; i < board_array.size(); i++) {
                //Board 클래스로 board인스턴스 정의
                Board board = new Board();
                //롬북으로 자동생성된 seter 메서드로 데이터 입력
                board.setSeq(board_array.get(i).getSeq());
                //매개변수 title_array.get(i)은 BoardController의 필드인
                //title_array, writer_array, content_array의 값을 순회하여 출력 (get(i));
                //board.setter를 통해서 board객체에 데이터 입력
                board.setTitle(board_array.get(i).getTitle());
                board.setWriter(board_array.get(i).getWriter());
                board.setContent(board_array.get(i).getContent());
                //내장클래스인 java.util.Date 객체로 시간 데이터 출력
                board.setCreateDate(board_array.get(i).getCreateDate());
                board.setCnt(board_array.get(i).getCnt());
                //boardList배열에 board객체 넣기
                boardList.add(board);

            }


//        //1부터 시작한 이유? 시퀀스
//        for (int i = 1; i<=10; i++) {
//            //Board Class로 board 인스턴스 생성
//            Board board = new Board();
//            //롬북으로 자동생성된 Setter 메서드로 데이터 입력
//            board.setSeq(new Long(i));
//            board.setTitle("게시판 프로그램 테스트");
//            board.setWriter("도우너");
//            board.setContent("게시판 프로그램 테스트입니다...");
//            //내장 클래스인 java.util.Date객체로 시간 데이터 출력
//            board.setCreateDate(new Date());
//            board.setCnt(0L);
//            //boardList 배열에 객체 board 넣기 (for문 10번 도니까 board 객체 10개 넣기)
//            boardList.add(board);
//        }

        }


        //model 객체에 boardList(arrayList)를 boardList key값으로 넣음
        //attributeName = key
        //attributeValue = value
        //model에는 참조타입만 넣을 수 있다 (addAttribute 메서드 안에 매개변수 타입으로 확인 가능)
        model.addAttribute("boardList", boardList);
        //디스페처서블릿이 뷰 리졸버를 찾아서 연걸해준다...
        //ViewResolver
        //return getBoardList라는 문자열로 templates에 있는 같은 이름의 html 파일을 찾는다.
        //model도 가지고 간다.
        return "getBoardList";
    }

    /**
     *  Board domain CONTROLLER
     * @Param String HTML에서 받아온 데이터
     * @reurn String HTML파일과 연결 (ViewResolber)
     * @author 김준석
     * @version 20220808.0.0.1
     */
    @PostMapping("/updateBoard")
    public String updateBoard(
            //HTML에서 name 속성을 가진 값을 매개변수 String seq에 할당 = @RequestParam("seq})
            @RequestParam("seq") String seq,
            @RequestParam("content") String content,
            @RequestParam("title") String title
            ) {
        System.out.println("update board access");
        //board array배열을 순회하여 board 객체의 seq 필드값을 매개변수 seq와 비교하여 true값 찾기
        for(int i=0; i< board_array.size(); i++) {
            if (Long.toString(board_array.get(i).getSeq()).equals(seq)) {
                //set title과 같은 setter로 데이터 변경
                board_array.get(i).setTitle(title);
                board_array.get(i).setContent(content);
            }
        }
        return "forward:getBoardList";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("seq") String seq) {
        //seq 매개변수 (getboard.html에서 받아옴)로 board_array 배열에서
        //getseq로 같은 index를 찾아 board_array에 있는 board 객체 삭제
        for(int i =0; i<board_array.size(); i++) {
            //board_array.get(i).getSeq() :
            //board_array의 i번째 객체를 찾아서 getSeq()메서드를 통해
            //seq 필드값 가져오기
            //참조 타입이므로 equals()메서드를 통해서 매개변수 seq 값과 비교
            //seq 타입은 Long(소수점있는 숫자)이므로 매개변수 seq(String)과 같은 타입이 아니므로 비교 불가
            //board_array.get(i).getSeq()의 값 Long을 String으로 바꿔준다. => Long.toString()
            if(Long.toString(board_array.get(i).getSeq()).equals(seq)) {
                //board_array의 i번째 객체 삭제
                board_array.remove(i);
            }
        }
        return "redirect:getBoardList";
    }
}