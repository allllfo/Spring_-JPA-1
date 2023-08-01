package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 디비 입장에서 저장할때 구분할 수 있도록 넣는 값
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
