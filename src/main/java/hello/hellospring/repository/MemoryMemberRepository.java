package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 구현체 만들기
public class MemoryMemberRepository implements MemberRepository {

    // 저장
    // <id, 값>
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키값을 생성해주는 변수


    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 아이디 세팅
        store.put(member.getId(), member); // store에 멤버 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();  // 람다 사용. 루프로 돌림. 파라미터로 넘어온 name이랑 같으면 반환을 한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
