package com.demo.item.repository;


import com.demo.item.domain.entities.Item;
import com.demo.item.domain.entities.VasItem;
import com.demo.item.domain.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ItemRepositoryTest extends BaseRepositoryTest{

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void givenItemObject_whenSave_thenReturnSavedItem() {

        List<VasItem> vastItem = new ArrayList<>();
        // given - precondition or setup
        Item item = new Item(1001L,vastItem,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);

        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        // then - verify the output
        assertThat(savedItem.getItemId()).isGreaterThan(0L);
        assertThat(savedItem).isNotNull();

    }

    @Test
    public void givenItemObject_when_thenReturnAllItemByItemId() {

        List<Item> dataList = new ArrayList<>();

        List<VasItem> vastItem = new ArrayList<>();
        // given - precondition or setup
        Item item = new Item(1001L,vastItem,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        dataList.add(item);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        itemRepository.findAllByItemId();

        // then - verify the output
        assertThat(dataList.size()).isEqualTo(1);
        assertThat(savedItem).isNotNull();

    }

    @Test
    public void givenItemObject_when_thenReturnItemByItemId() {

        List<Item> dataList = new ArrayList<>();

        List<VasItem> vastItem = new ArrayList<>();
        // given - precondition or setup
        Item item = new Item(1001L,vastItem,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        dataList.add(item);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        itemRepository.findByItemId(1001L);

        // then - verify the output
        assertThat(item.getItemId()).isEqualTo(savedItem.getItemId());
        assertThat(savedItem).isNotNull();

    }

    @Test
    public void givenItemObject_when_thenReturnAllItemByCategoryId() {


        List<VasItem> vastItem = new ArrayList<>();
        // given - precondition or setup
        Item item = new Item(1001L,vastItem,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        List<Long> result =  itemRepository.findAllByItemId();

        // then - verify the output
        assertThat(result.get(0)).isEqualTo(item.getCategoryId());
        assertThat(savedItem).isNotNull();

    }

    @Test
    public void givenItemObject_when_thenReturnItemByCartId() {


        List<VasItem> vastItem = new ArrayList<>();
        // given - precondition or setup
        Item item = new Item(1001L,vastItem,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        List<Item> result =  itemRepository.findByCartId("e58ed763-928c-4155-bee9-fdbaaadc15f3");

        // then - verify the output
        assertThat(result.get(0).getCartId()).isEqualTo(item.getCartId());
        assertThat(savedItem).isNotNull();

    }

    @Test
    public void givenItemObject_when_thenDeleteItemById() {


        List<VasItem> vastItem = new ArrayList<>();
        // given - precondition or setup
        Item item = new Item(1001L,vastItem,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);

        boolean result = itemRepository.deleteByItemId(Long.valueOf(1001L) );

        // then - verify the output
        assertThat(result).isEqualTo(true);
        assertThat(savedItem).isNotNull();

    }




}
