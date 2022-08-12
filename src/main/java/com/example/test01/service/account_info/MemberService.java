package com.example.test01.service.account_info;

import java.util.List;
import com.example.test01.domain.account_info.Member;

public interface MemberService {

    List<Member> getMemberList(Member member);

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member member);
}
