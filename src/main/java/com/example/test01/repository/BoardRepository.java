package com.example.test01.repository;

/**
 * @package : com.example.Test01.persistence
 * @name : BoardRepository.java
 * @date : 2022-08-10 오후 17:00
 * @author : chueat
 * @version : 1.0.0
 * @modifyed :
 * @description : 게시판 레포지토리
 **/

import com.example.test01.entity.Board;
import org.springframework.data.repository.CrudRepository;

//CrudRepository를 상속받음 : entity를 매개체로 create, read, update, delete 기능을 하는 인터페이스
//CrudRepository<board, Long>의 매개변수 Board(Entity)와 Long(PK기본키의 타입)을 선언
//JPA가 어떤 객체로 어떤 타입으로 찾아야하는지 알아들음

public interface BoardRepository extends CrudRepository<Board, Long> {

}
