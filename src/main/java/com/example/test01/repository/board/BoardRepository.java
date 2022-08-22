package com.example.test01.repository.board;

/**
 * @package : com.example.Test01.persistence
 * @name : BoardRepository.java
 * @date : 2022-08-10 오후 17:00
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 레포지토리
 **/

import com.example.test01.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

//CrudRepository를 상속받음 : entity를 매개체로 create, read, update, delete 기능을 하는 인터페이스
//CrudRepository<board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야하는지 알아들음

public interface BoardRepository extends JpaRepository<Board, Long> {
    //튜닝 : JOIN과 WHERE절의 순서를 정함으로써 SELECT속도 튜닝을 어떻게 할지 건략적 구성 1>2>3>4
    //Member의 튜플이 무척 많을 경우 where절을 통해 Id 검색 이후 Board와 Join하는 것이 DB 검색 속도에 유리
    //회원 id를 검색하면 (wirter와 id가 동일) 관련된 writer의 게시글 모두 출력 받아 리턴
    //inner JOIN : ANSI QUERY <> ORACLE QUERY (서로 다름)
    //board의 튜플을 가져와야 하기 때문에 FROM Board(Board 테이블이 기준)
    //SELECT b FROM Board b --> board 테이블의 튜플을 검색 (모든 컬럼)
    //INNER JOIN Member m --> member 테이블과 교집합 조인 (INNER JOIN)
    //ON b.writer = m. id --> board의 writer와 member의 id가 동일한 튜플을 검색
    //(b는 board의 별칭, m은 member의 별칭)
    //(3)
    //WHERE m.id = :memberId --> INNER 조인한 튜플들의 결과물중에 member.id가
    //매개변수 memberId와 동일한 조건을 걸음
    //(4)
    @Query(value = "SELECT b FROM Board b INNER JOIN Member m ON b.writer = m.id WHERE m.id = :memberId")
    List<Board> findAllByMemberIdEqualsBoardWriter(String memberId);

}
