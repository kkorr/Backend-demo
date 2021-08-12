package com.amr.project.converter;

import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderDto orderToDto(Order order);

    OrderDto dtoToOrder(OrderDto orderDto);
}
