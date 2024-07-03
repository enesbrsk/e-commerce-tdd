package com.demo.item.repository;


import com.demo.item.domain.entities.Item;
import com.demo.item.domain.entities.VasItem;
import com.demo.item.domain.repository.ItemRepository;
import com.demo.item.domain.repository.VasItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class VasItemRepositoryTest extends BaseRepositoryTest{

    @Autowired
    private VasItemRepository vasItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void givenItemObject_whenSave_thenReturnVasItemByVasItemId() {

        Item item = new Item(1001L,null,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);
        // given - precondition or setup
        VasItem vasItem = new VasItem(null,1234L,item,1001,5003,500,2);

        // when -  action or the behaviour that we are going test
        VasItem savedVasItem = vasItemRepository.save(vasItem);

        // then - verify the output
        assertThat(savedVasItem.getVasItemId()).isGreaterThan(0L);
        assertThat(savedVasItem).isNotNull();

    }


    @Test
    public void givenItemObject_when_thenReturnVasItemByItemId() {

        Item item = new Item(1001L,null,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);
        // given - precondition or setup
        VasItem vasItem = new VasItem(null,1234L,item,1001,5003,500,2);

        // when -  action or the behaviour that we are going test
        VasItem savedVasItem = vasItemRepository.save(vasItem);

        List<VasItem> vasItemList = vasItemRepository.findByItemItemId(1001L);

        // then - verify the output
        assertThat(vasItemList.get(0).getItem().getItemId()).isEqualTo(item.getItemId());
        assertThat(savedItem).isNotNull();

    }

    @Test
    public void givenItemObject_when_thenReturnItemByCartId() {

        Item item = new Item(1001L,null,"e58ed763-928c-4155-bee9-fdbaaadc15f3",3004,5003,150,2);
        // when -  action or the behaviour that we are going test
        Item savedItem = itemRepository.save(item);
        // given - precondition or setup
        VasItem vasItem = new VasItem(null,1234L,item,1001,5003,500,2);

        // when -  action or the behaviour that we are going test
        VasItem savedVasItem = vasItemRepository.save(vasItem);

        List<VasItem> vasItemList = vasItemRepository.findByItemItemId(1001L);

        // then - verify the output
        assertThat(vasItemList.get(0).getItem().getItemId()).isEqualTo(item.getItemId());
        assertThat(savedItem).isNotNull();

    }

}
