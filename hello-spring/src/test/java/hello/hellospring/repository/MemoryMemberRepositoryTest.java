package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository= new MemoryMemberRepository();

    //메소드가 끝날 때마다 호출되는 동작
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member testmember = new Member();
        testmember.setName("vinca");

        repository.save(testmember);
        Member member_got = repository.findById(testmember.getId()).get();
        assertThat(testmember).isEqualTo(member_got);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("rockwell");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("vinca");
        repository.save(member2);

        Member member_got = repository.findByName("rockwell").get();
        assertThat(member1).isEqualTo(member_got);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}
