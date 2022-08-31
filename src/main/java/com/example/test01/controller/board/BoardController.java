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
import com.example.test01.entity.customDto.CustomDtoSortPages;
//import com.example.test01.entity.data.FileUploadEntity;
import com.example.test01.entity.data.FileUploadEntity;
import com.example.test01.service.board.BoardService;
import com.example.test01.service.account.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping(path = "/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    protected BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/insertComments")
    public String insertComments(Board board, Model model) {
        System.out.println(board.getTitle());
        model.addAttribute("board", board);
        return "/board/insertComments";
    }

    @PostMapping("/insertComments")
    public String insertComments(@RequestParam("board_title") String boardTitle, Comments comments, Model model) {

        System.out.println("------inertComments---------");
        System.out.println(comments.getBoard_title());
        System.out.println(comments.getComments_content());
        boardService.insertComment(comments);
        return "redirect:/board/getBoardList";
    }

    //board Seq전달하면 전체 comments를 불러오는 controller method
    @GetMapping("/getCommentsList")
    public String getCommentsList(Comments comments, Model model) {
        System.out.println("-------getCommentsList-------");
        System.out.println(comments.getBoard_title());
        List<Comments> checkCommentsList = boardService.getAllComments(comments);

        model.addAttribute("commentsList", checkCommentsList);
        return "/board/getCommentsList";
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

    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        System.out.println("----------updateBoard---------");
        System.out.println(board.getContent());
        System.out.println(board.getSeq());
        boardService.updateBoard(board);
        return "redirect:/board/getBoard?seq=" + board.getSeq();
    }

    @GetMapping("/updateBoard")
    public String updateBoardView(Board board, Model model) {
        System.out.println("-------------------");
        model.addAttribute("board", boardService.getBoard(board));
        return "/board/insertBoard";
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        System.out.println("--------board delete-----------");
        System.out.println(board.getSeq());
        boardService.deleteBoard(board);
        return "redirect:/board/getBoardList";
    }

    @GetMapping("/selectBoard") //최종적으로 model도 리턴
    public String selectBoard(Member member, Model model) {
        System.out.println("--------board select-----------");
        //board.getId()는 클라이언트에서 가져옴
        //@Service에 board를 인자값으로 넣고 메서드 실행
        boardService.getBoardListByMemberId(member);
        model.addAttribute("boardList", boardService.getBoardListByMemberId(member));
        //회원이 작성한 게시글 리스트(List<Board>) 리턴 --> HTML에 뿌려주면 끝
        // 이유 : Controller에 가면 메서드가 실행돼서 다른 결과물을 리턴받기 때문
        // 어느 HTML로 가냐? 객체지향은 재활용성이 중요한 요인 중 하나
        //HTML 중에 재사용 할만한 것을 먼저 찾고, 그 후에 새로 만들기에 대해 고민 : getBoardList 재활용
        //최종적으로 무엇을 리턴? return 페이지 OR controller mapping
        return "/board/getBoardList";
    }

    @GetMapping("/viewUserWriteBoard")
    public String viewUserWriteBoard(Member member, Model model) {
        System.out.println("-------view-------");
        System.out.println(member.getId());
        model.addAttribute("boardList",
                boardService.getBoardListAllBoardListByMemberId(member));
        return "/board/getBoardList";
    }

    @GetMapping("/getAllUserBoardList")
    public String AllUsersBoard(Model model) {
        System.out.println(boardService.getBoardAndMemberUsersBoard().size());
        return "index";
    }

    //* client에서 server로 이미지파일 전송(데이터 전송)
    //html form태그에 upload 버튼으로 이미지 데이터 전송(MultipartFile) > Entity 기준으로 데이터 정보를 전달
    //-server는 이미지 파일을 특정 폴더에 저장
    //장점 : 서버에 원본 이미지 파일을 저장하므로 필요할 때 서버에서 바로 전달 받을 수 있음 = db에 부담감이 줄어듦
    //단점 : 다수의 서버에 이미지 파일을 저장할 경우, 이미지 데이터 처리에 대한 이슈 발생
    // = UUID(16bit로 동일한 이미지 파일명을 가지지 않게 함) 를 통해 이미지 이름을 구분
    //-server는 이미지 파일을 byte코드로 db에 저장
    //장점 : 이미지 데이터를 한 곳에 저장하고 관리
    //단점 : DB에 많은 부하가 걸림, 데이터 저장 포멧의 한계. (oracle 기준으로 Blob 단위로 저장할 때 4gb한계의 이슈)

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("uploadfile") MultipartFile[] uploadfile) throws IOException {
        //multipartfile을 클라이언트에서 서버로 RequestParam데이터 받아옴 name=uploadfile
        System.out.println("test");
        //@Slf4j Lombook라이브러리로 log 데이터 찍음
        //info / error / debug 단위가 있고 단위마다 필터링하여 정보를 수집하고 관리 가능
        log.info("img load session");
        //multipartfile 데이터를 수집하여 Entity FileUploadEntity에 데이터 저장
        List<FileUploadEntity> list = new ArrayList<>();
        for(MultipartFile file : uploadfile) {
            //MultipartFile file이 있을 때까지 for문 작업 진행
            if(!file.isEmpty()) {
                //MultipartFile의 정보를 dto에 저장
                FileUploadEntity dto = new FileUploadEntity(null, UUID.randomUUID().toString(),
                        file.getContentType(),
                        file.getName(),
                        file.getOriginalFilename()); //순서 맞춰 DTO로 바꿔줌
                list.add(dto);
                File newFileName = new File(dto.getUuid()+"_"+dto.getName()+".PNG");
                //file을 서버에 저장하는 스트림 행위는 서버가 성공할지 여부를 체크하므로 exception처리 필요
                //메서드에 throws IOException 처리 = try catch 처리 하여도 된다.
                file.transferTo(newFileName);
            }
        }

        return "/board/getBoardList";
    }

    //server에서 client로 이미지 전송
    //springboot에서 URL주소를 통해 이미지를 받음. InputStream을 통해 파일을 gttp 프로토콜에 전달하여 클라이언트에게 전송




//    @GetMapping ("/viewImage/{imgname}")
//    public ResponseEntity<byte[]> viewImage(@PathVariable("imgname")String input_imgname) {
//        ResponseEntity<byte[]> : http 프로토콜을 통해서 byte데이터를 전달하는 객체 (byte, 소문자=기본타입) []배열
//        @PathVariable : URL 주소의 값을 받아옴
//        InputStream : 데이터 입출력 용도 자바.io를 통해 클라이언트에게 전송.
//        데이터(이미지)를 전송하기 위한 객체로써 java에서는 항상 데이터를 스트링 타입으로 전달
//        1.String path ="이미지/파일/위치/입력/" + input_imgName (파일 이름을 알기 위해 붙여놓음 갯맵핑 옆 이름)
//        2.InputStream inputStream = new FileInputStream(path); //부모클래스 파일인풋스트림
//        byte 배열로 반환
//        3.byte[] = imgByteArr = toByteArray(inputStream);
//        4.inputStream.close();
//        ResponseEntity를 통해 http 프로토콜로 클라이언트에게 데이터 전송
//        5.return new ResponseEntity<byte[]>(imgByteArr, HttpStatus.OK);
    }

