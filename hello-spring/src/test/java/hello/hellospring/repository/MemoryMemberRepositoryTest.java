package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository= new MemoryMemberRepository();

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

}
