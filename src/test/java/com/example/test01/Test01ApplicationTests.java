package com.example.test01;

import com.example.test01.entity.account.Member;
import com.example.test01.repository.account.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test01ApplicationTests {

    //생성자 주입방식은 에러가 뜨므로 필드 주입방식 사용
    @Autowired
    ApiTest apiTest_1;

    @Test
    void setApiTest_2() {
        apiTest_1.readAPI();
    }
    //오토와이어를 통해 ApiTest를 주입받는다.
    // 그 중 특정 메소드만 실행하여 정상적으로 로직이 동작하는지 확인하는 작업이 유닛테스트

    @Test
    @DisplayName("저장, 데이터가 잘 들어갔는지 확인")
    void contextSave() {
        //Setter로 엔티티를 생성하고 repository가 정상 작동하는지 확인
        Member member = new Member();
        member.setId("humanClass4");
        member.setPassword("12341234@");
        member.setEmail("class4@123.com");
        MemberRepository.save(member);
    }
    @Test
    void contextLoads() {
    }

}
