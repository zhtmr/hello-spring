package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// DB 가 정해지지 않은 상황을 가정한 구현체
//@Repository  --> @Bean 으로 등록가능
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // db 같은개념
    private static long seq = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++seq); //  id 값 세팅
        store.put(member.getId(), member); // 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store 에 있는 값들 중 name 이 전달된 name 과 같은 것 반환
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
