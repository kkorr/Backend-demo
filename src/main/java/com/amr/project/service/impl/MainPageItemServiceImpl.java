package com.amr.project.service.impl;

import com.amr.project.converter.ItemsMapper;
import com.amr.project.dao.abstracts.MainPageItemsDAO;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.MainPageItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MainPageItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements MainPageItemService {

    private final MainPageItemsDAO mainPageItemsDAO;

    @Autowired
    public MainPageItemServiceImpl(MainPageItemsDAO mainPageItemsDAO) {
        super(mainPageItemsDAO);
        this.mainPageItemsDAO = mainPageItemsDAO;
    }

    @Override
    public List<ItemDto> findPopularItems() {
        return ItemsMapper.INSTANCE.toItemsDto(mainPageItemsDAO.getAll().stream()
                .sorted(Comparator.comparing(Item::getCount, Comparator.reverseOrder()))
                .limit(10).collect(Collectors.toList()));
    }
}
