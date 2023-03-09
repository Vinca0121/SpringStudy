package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void BeforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //test
    @AfterEach
    void clearall(){
      memberRepository.clearStore();
    }

    @Test
    void 회원가입테스트() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        //when
        Long saveId = memberService.join(member1);

        //then
        Member result_member = memberService.findOne(saveId).get();
        assertThat(member1).isEqualTo(result_member);
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");



//        memberService.join(member2);

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}