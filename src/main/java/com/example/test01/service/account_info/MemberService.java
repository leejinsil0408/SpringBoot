package com.example.test01.service.account_info;

import java.util.List;
import com.example.test01.domain.account_info.Member;

public interface MemberService {
    //Email또는ID를 조회하여 튜플을 찾기
    List<Member> getMemberWhereIdOrEmail(String Email, String Id);

    Member getMemberWhereIdAndROWNUL1(String id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member member);
}
