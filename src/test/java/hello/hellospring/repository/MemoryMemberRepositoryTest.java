package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    //MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();  // MemoryMemberRepository만 테스트하는것이므로 바꿔놓는다

    // 메서드가 끝날때마다 동작을 하는 것
    // save() > afterEach() > findByName() > afterEach() > findAll() > afterEach()
    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 메서드가 끝날때마다 저장소를 다 지운다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //optional에서 값을 꺼낼 때는 get() 메소드로 꺼낼 수 있다
        Member result = repository.findById(member.getId()).get();

        // 검증
        // member에 저장한거랑 db에 있는 데이터와 같으면 true
        System.out.println("result = " + (result == member));

        // 글자로 볼 수 없어서 Assertions라는 기능이 있다.
        // result와 member와 똑같은지 실행해볼수 있다.
        // Assertions.assertEquals(member, result);
        // Assertions.assertEquals(member, null);  // 의도적으로 실패가 뜨도록 하여 결과 확인

        // 요즘에는 이걸 많이 쓴다.
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
    }

    // findByName 테스트
    // spring1, spring2 라는 이름의 회원을 가입시킨다.
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
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
