package com.example.test01.repository.account;

import com.example.test01.entity.account.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//MemberRepository는 CrudRepository를 상속받아 기능을 온전히 씀
//CrudRepository : DB에 기본적인 SQL문을 통해 소통 (INSERT INTO , SELEST, UPDATE , DELETE)

                                                        //entity
public interface MemberRepository extends JpaRepository<Member, Long> {
     //Return 내용선언, Find~변수명에 맞춰서 메서드 생성, 필요한 매개변수
    @Query(value = "select m from Member m where m.email = :email_1 or m.id = :id_1")
    Member findMemberByEmailOrId(String email_1, String id_1);

    //(ID는 중복가능한 구조에서)Id값을 매개변수로 넣고, 아이디 생성날짜가 가장 최신인 것
    @Query(value = "select m from Member m where m.id = :id_1 order by m.createDate DESC")
    Member findFirstById(String id_1);

    @Query(value = "SELECT m FROM Member m JOIN fetch m.boardList WHERE m.id = :memberId")
    List<Member> findAllByMemberIdEqualsBoardWriter(String memberId);

    //JPA는 메서드 이름으로 DB에 조회하는 기능
    //JPQL : JPA를 통해 JPA에서 제공하는 쿼리문으로 조회 (단, 엔티티 기준으로만 조회가능)
    //NativeQuery : 일반 SQL문으로 DB 조회하며 보통 DTO단위로 리턴 (Entity단위로 리턴x)
    //jpql containing (SQL문의 like처럼 유사한 단어를 찾는 메서드명)
    List<Member> findByEmailContaining(String email);

    //jpql contains
    List<Member> findByIdContains(String id);

    //jpql iscontaing
    List<Member> findByPasswordIsContaining(String password);

}
