package com.amr.project.service.impl;

import com.amr.project.converter.ItemMapper;
import com.amr.project.dao.abstracts.ReadWriteDAO;
import com.amr.project.dao.abstracts.ShowcaseItemDao;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;
import com.amr.project.service.abstracts.ShowcaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShowcaseItemServiceImpl extends ReadWriteServiceImpl<Item, Long> implements ShowcaseItemService {

    private ShowcaseItemDao showcaseItemDao;
    private ItemMapper itemMapper;

    @Autowired
    public ShowcaseItemServiceImpl(ShowcaseItemDao showcaseItemDao, ItemMapper itemMapper) {
        super(showcaseItemDao);
        this.showcaseItemDao = showcaseItemDao;
        this.itemMapper = itemMapper;
    }

    /**
     * Метод для поиска 10 популярных товаров по id магазина
     * @param shopId
     * @return
     */
    @Override
    public List<ItemDto> findPopularItemsByShopId(Long shopId) {
        return itemMapper.toItemsDto(showcaseItemDao.findItemsByShopId(shopId)
                .stream().sorted(Comparator.comparing(Item::getCount, Comparator.reverseOrder()))
                .limit(10).collect(Collectors.toList()));
    }

    /**
     * Метод для поиска популярных товаров по id магазина
     * @param shopId
     * @return
     */
    @Override
    public List<ItemDto> findItemsByShopId(Long shopId) {
        return itemMapper.toItemsDto(showcaseItemDao.findItemsByShopId(shopId));
    }

    /**
     * Метод для поиска итемов по id категории и магазина
     * @param categoryId
     * @param shopId
     * @return
     */
    @Override
    public List<ItemDto> findItemsByCategoryIdAndShopId(Long categoryId, Long shopId) {
        return itemMapper.toItemsDto(showcaseItemDao.findItemsByCategoryIdAndShopId(categoryId, shopId));
    }

    /**
     * Метод используется для выборки итема по части наименования и shop id
     * @param shopId
     * @param itemName
     * @return
     */
    @Override
    public List<ItemDto> searchItemsByName(Long shopId, String itemName) {
        return itemMapper.toItemsDto(showcaseItemDao.searchItemsByName(shopId, itemName));
    }
}
