package com.example.test01.service.account_info;

import com.example.test01.domain.account_info.Member;
import com.example.test01.persistence.account_info.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServicelmpl implements MemberService {

    private final MemberRepository memberRepo;

    @Autowired
    protected MemberServicelmpl(MemberRepository memberRepo) {this.memberRepo = memberRepo; }
    //모든 회원의 정보를 가져다 오는 것
    //return List<Member> : 모든 회원의 정보를 List 배열에 담아서 return
    //List<Member> : 이 메서드가 실행되면 return되는 타입
    //(List<Member>) : 뒤에 있는 결과값을 형 변환
    //memberRepo : @Autowried MemberRepository를 통해 기능 실행
    //findAll() : memberRepo에 있는 보든 정보 가져오기 메서드 실행
    @Override
    public List<Member> getMemberList() {
        return (List<Member>) memberRepo.findAll(); //형변환
    }

    //회원 1명의 정보를 entity에 맞춰서 DB에 저장
    @Override
    public void insertMember(Member member) {
        memberRepo.save(member);
    }

    @Override
    public Member getMember(Member member) {
        return memberRepo.findById(member.getSeq()).get();}

    @Override
    public void updateMember(Member member) {
        //1. seq를 통해서 튜플 정보를 모두 가져오기
        //2. 가져온 튜플 정보 중 수정할 내용 적용
        //3. DB에 저장 (덮어쓰기)
        //findById().get() : 고유키 기준으로 튜플 전체 데이터 가져오기
        Member findmember = memberRepo.findById(member.getSeq()).get();
        //튜플 전체 내용 중에 ID/email주소 수정하는 메서드 (setter)
        findmember.setId(member.getId());
        findmember.setEmail(member.getEmail());
        //CrudRepo의 save 메서드를 통해 데이터 저장
        memberRepo.save(findmember);

        //고유키
        //1. 튜플을 식별할 수 있는 값 (데이터 한 줄) : DB 관점
        //2. 다른 테이블의 튜플과 연동하기 위한 값 (JOIN, 외래키) : DB 관점
        //3. 객체지향 방법으로 DB를 저장
        //3-1. 영속성 : DB에 영구 저장
        //3-2. 고립성 : 다른 트랜잭션 작업에 연관되지 않도록 해주는 것
        //3-3. 관리자1이 seq 10의 회원정보 변경 -> 접속중인 관리자2가 seq10 회원 정보 조회하고 수정
        //seq10의 회원정보 변경, 회원 정보 조회 및 수정 작업 모두 트랜잭션.
        //관리자1의 트랜잭션 작업이 완료될 떄까지
        //관리자2의 seq10 회원정보는 옛날 정보를 조회하고
        //관리자1의 트랜젝션 작업이 완료되는 순간까지 관리자2는 seq10의 회원 정보를 수정할 수 없다.
        //다른 필드값은 수정이 가능해도, seq는 튜플의 식별자로써 수정이 불가해야
        //관리자 1,2의 트랜젝션 작업을 스프링부트에서 구분할 수 있다.
        //==> entity의 고유값이 있어야지 스프링부트가 작업할 수 있다.
    }

    @Override
    public void deleteMember(Member member) {

        memberRepo.deleteById(member.getSeq());
    }
}
