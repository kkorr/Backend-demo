package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import java.util.List;

public interface MainPageItemService {
    List<ItemDto> findPopularItems();
}
