package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }
    
    @Test
    void save(){
        // given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //when
        ArrayList<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void updateItem(){
        // given
        Item itemA = new Item("itemA", 10000, 10);
        itemRepository.save(itemA);

        //when
        Item updateParam = new Item("itemB", 20000, 20);
        itemRepository.updateItem(itemA.getId(), updateParam);

        //then
        assertThat(itemA.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(itemA.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(itemA.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

}