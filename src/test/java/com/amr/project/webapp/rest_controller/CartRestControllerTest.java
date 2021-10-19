package com.amr.project.webapp.rest_controller;

import com.amr.project.AbstractIntegrationTest;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/com.arm.project.webapp.rest_controller/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CartRestControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    HttpServletRequest request;

    @Test
    void addItemToAnonCart() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);

        ShopDto shopDto = new ShopDto();
        shopDto.setId(1L);

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setQuantity(1);
        cartItemDto.setItem(itemDto);
        cartItemDto.setShop(shopDto);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cartItemDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void addItemToUserCart() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);

        ShopDto shopDto = new ShopDto();
        shopDto.setId(1L);

        UserDto user = new UserDto();
        user.setId(3L);

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setQuantity(1);
        cartItemDto.setItem(itemDto);
        cartItemDto.setShop(shopDto);
        cartItemDto.setUser(user);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cartItemDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
