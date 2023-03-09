package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // db 가 없으므몰 사용하는 임시 저장소
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        // 임시저장소에다가
        store.put(member.getId(), member);
        return member;
    }

    // null 처리 때문에 Optional로 표기 함.
    // 꺼낼 때 get
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));

    }

    // 저장된 사용자의 name을 통해서 Member 객체를 반환.
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
