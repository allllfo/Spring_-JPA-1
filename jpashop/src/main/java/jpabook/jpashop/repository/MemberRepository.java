package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // component스캔의 대상이 되어서 자동으로 스프링 빈에 등록된다.
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) { // 저장로직
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){ // 전부다 가지고 오는 것이므로 JPQL사용
        return em.createQuery("select m from Member m", Member.class) // JPQL + 반환타입
                .getResultList();

    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // :name 과 이 "name"이 바인딩됨
                .getResultList();
    }

}
