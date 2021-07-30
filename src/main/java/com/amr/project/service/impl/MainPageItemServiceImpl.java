package com.amr.project.service.impl;

import com.amr.project.converter.ItemsMapper;
import com.amr.project.dao.abstracts.ItemRepository;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.MainPageItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MainPageItemServiceImpl implements MainPageItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public MainPageItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> findPopularItems() {
        List<Item> allItem = itemRepository.findAll();
        Stream<Item> popularItems = allItem.stream().sorted(Comparator.comparing(Item::getRating,
                Comparator.reverseOrder())).limit(10);
        List<Item> itemList = popularItems.collect(Collectors.toList());
        List<ItemDto> itemDto = new ArrayList<>();
        for (Item item : itemList) {
            itemDto.add(ItemsMapper.INSTANCE.toItemsDto(item));
        }
        return itemDto;

    }
}
