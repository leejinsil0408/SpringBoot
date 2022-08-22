package com.example.test01.service.account;

import java.util.List;
import com.example.test01.entity.account.Member;

public interface MemberService {

    //Email또는ID를 조회하여 튜플을 찾기
    Member getMemberWhereIdOrEmail(String Email, String Id);

    Member getMemberWhereIdAndROWNUL1(String id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member member);


    //일부분만 검색하여 사용 유저 찾기 (3조)
    //결과값 : 입력받은 정보(email, id, pw)가 유사 사실 유무 확인 후
    //비밀번호 변경(updateMember의 password)

    boolean booleanSearchUserByEmail(Member member);
    boolean booleanSearchUserById(Member member);
    boolean booleanSearchUserByPassword(Member member);

//    //별표처리 MemberList (replace..) (6조)
List<Member> getMemberListEmailSecurityStarByMemberList(List<Member> memberlist);
//
//    //민감데이터 (SHA256..)
List<Member> getMemberListEncodingByMemberList(List<Member> memberlist);
//
//    //작성자의 모든 게시글 출력 (2,5조)
//    List<Member> getBoardWhereMemberId(Member member);
//
//    //board의 작성자와 회원이 같은지 확인
//    boolean booleanMemberIdEqualsBoardWriterByMember(Member member);
//
//    //키워드분석
//    //doNouns
//
//    //getAutokeyWordBoardList
//
//    //email @앞의 문자열과 id가 동일할 경우 (1조)
//    boolean booleanEmailEqualsIdByMemberEmail(List<>)
//
//    //id와 pw가 동일할 경우
//    boolean --(Member member);
//
//    //30일 지난 회원에게 변경 페이지 안내
boolean booleanAfter30DaysChangePasswordByMemberUpdateDate(Member member);
//
//    //비밀번호 변경 테이블 생성 후 변경한 기록을 남긴 뒤, 변경 내용 최신 3회 내용과 비교
boolean booleanChangedPassword3CheckByMemberPassword(Member member);
//
//
//껍데기 만들고 Impl에서 오버라이드 구현하면 끝
}