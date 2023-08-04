package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", // 중간테이블
        joinColumns = @JoinColumn(name = "category_id"), // 중간테이블에 있는 category_id 매핑
            inverseJoinColumns = @JoinColumn(name = "item_id"))// 중간테이블에 item 쪽으로 들어가는 놈 매핑  ) // 다대다이므로 중간 테이블 필 -> 중간테이블을 매핑해줌
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY) // 자기 자신이랑 연관관계도 이렇게 똑같이 해주면 됨
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드 ==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
