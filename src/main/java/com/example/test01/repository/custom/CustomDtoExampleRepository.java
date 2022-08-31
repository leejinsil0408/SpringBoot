package com.example.test01.repository.custom;

import com.example.test01.entity.customDto.CustomDtoExample;
import com.example.test01.entity.customDto.CustomDtoSortPages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CustomDtoExampleRepository extends JpaRepository<CustomDtoExample, String> {

    //nativeQuery :
    //1. Entity단위로 DB조회와 Client데이터 전송을 동시에 할 경우 table 구조가 드러나기 때문에 보안적인 위험 > DTO를 만들어서 데이터 전송에 쓰임
    //2. JPA Entity단위로 데이터를 조회할 경우 자유도가 현격히 떨어지므로 일반 DTO를 만들어서 JOIN 등등 데이터 리턴값을 자유롭게 받을 수 있음
    //단, 단점으로는 JPQL를 써서 데이터를 주고 받을 경우 객체 구조적인 -- 단단함. 유지보수에 용이.
    //SQL보다는 JPQL + DTO를 쓰는게 가장 좋다고 생각 : 구조적인 견고함과 DTO의 유연함을 동시에 취하고 상황에 따라 대처할 수 있는 유연을 가짐.

    @Query(value =
            "SELECT m.id as input_id, b.writer as input_writer, b.title as input_title " +
                    "from Member m " +
                    "inner join Board b on m.id = b.writer " +
                    "where m.id = :memberId",
            nativeQuery = true)
    CustomDtoExample findExample(String memberId);
}
