package com.example.test01.repository.board;

import com.example.test01.entity.data.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

//FileUploadEntity를 저장하는 인터페이스 (JPA CrudRepository 활용 )
// 파일을 저장할 수 있는 CRUD 레파지토리 구축 . 이제 서비스로직 만들기 보드서비스로 가기


//특정 튜플값을 가지고 라이브러리를 가지고 오려먼 JPA레파지토리 만들기
public interface FileUploadInfoRepository extends JpaRepository<FileUploadEntity, Long> {

    //findBy : 튜플을 찾는다
    //BoardSeq : BoardSeq 컬럼에 데이터를 찾는다

    FileUploadEntity findAllByBoardSeq(Long boardSeq);
}
