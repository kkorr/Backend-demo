package com.amr.project.converter;

import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto orderToDto(Order order);

    Order dtoToOrder(OrderDto orderDto);
}
