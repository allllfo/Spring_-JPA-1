package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // 전체 설정이 ReadOnly로 되어있기 때문에 얘는 저장하면 값이 바뀌어야 하므로 transactional 따로 지정해줌
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
