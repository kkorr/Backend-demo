package com.amr.project.service.impl;

import com.amr.project.converter.ItemMapper;
import com.amr.project.dao.abstracts.MainPageItemsDao;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.MainPageItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPageItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements MainPageItemService {

    private final MainPageItemsDao mainPageItemsDAO;
    private final  ItemMapper itemMapper;

    @Autowired
    public MainPageItemServiceImpl(MainPageItemsDao mainPageItemsDAO, ItemMapper itemMapper) {
        super(mainPageItemsDAO);
        this.mainPageItemsDAO = mainPageItemsDAO;
        this.itemMapper = itemMapper;
    }

    @Override
    public List<ItemDto> findPopularItems() {
        return itemMapper.toItemsDto(mainPageItemsDAO.getAll().stream()
                .sorted(Comparator.comparing(Item::getCount, Comparator.reverseOrder()))
                .limit(10).collect(Collectors.toList()));
        /*return ItemMapper.INSTANCE.toItemsDto(mainPageItemsDAO.getAll().stream()
                .sorted(Comparator.comparing(Item::getCount, Comparator.reverseOrder()))
                .limit(10).collect(Collectors.toList()));*/
    }
}
