package com.example.test01.persistence.account_info;

import com.example.test01.domain.account_info.Member;
import org.springframework.data.repository.CrudRepository;


//MemberRepository는 CrudRepository를 상속받아 기능을 온전히 씀
//CrudRepository : DB에 기본적인 SQL문을 통해 소통 (INSERT INTO , SELEST, UPDATE , DELETE)

                                                        //entity
public interface MemberRepository extends CrudRepository<Member, Long> {


}
