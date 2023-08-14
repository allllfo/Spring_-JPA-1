package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) { // item은 jpa에서 저장하기 전까지 id 값이 없대 => 즉, 완전히 새로 생성한 객체이다.
        if (item.getId() == null){
            em.persist(item); // id 값이 없다는 것은 완전 새로 생성한 객체이므로, persist해서 새로 등록함
        } else { // 이미 등록된 경우에
            em.merge(item); // update 해라
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class) // 뒤에가 조회 대상인듯
                .getResultList();
    }

}
