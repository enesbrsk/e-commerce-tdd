package com.demo.cart.controller;

import com.demo.cart.application.command.model.request.ItemToCartRequest;
import com.demo.cart.application.command.model.request.RemoveItemFromCartRequest;
import com.demo.cart.application.command.model.request.VasItemToCartRequest;
import com.demo.cart.application.command.model.response.ResponseMessage;
import com.demo.cart.application.command.service.CartCommandService;
import com.demo.cart.domain.enums.ItemType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartCommandControllerTest extends BaseRestControllerTest{

    @MockBean
    private CartCommandService cartCommandService;

    @Autowired
    MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldSaveCart_WhenValidItemToCartRequestBody() throws Exception {

        ItemToCartRequest.ItemToCartRequestPayload payload = new ItemToCartRequest.ItemToCartRequestPayload(1,7889,5003,150,2);
        ItemToCartRequest itemToCartRequest = new ItemToCartRequest(ItemType.DEFAULT_ITEM,payload);

        ResponseMessage response = new ResponseMessage(true, "İtem Sepete Başarıyla Eklenmiştir.");

        // when -  action or the behaviour that we are going test
        when(cartCommandService.createItemToCart(itemToCartRequest)).thenReturn(response);

        // then - verify the output
        mockMvc.perform(post("/api/v1/cart/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(itemToCartRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.result").value(response.getResult()))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void itShouldSaveCart_WhenValidVasItemToCartRequestBody() throws Exception {

        VasItemToCartRequest.VasItemToCartRequestPayload payload = new VasItemToCartRequest.VasItemToCartRequestPayload(1,3242,5003,150,200,2);
        VasItemToCartRequest vasItemToCartRequest = new VasItemToCartRequest(ItemType.DEFAULT_ITEM,payload);

        ResponseMessage response = new ResponseMessage(true, "Vas Item Başarıyla Eklenmiştir");

        // when -  action or the behaviour that we are going test
        when(cartCommandService.createVasItemToCart(vasItemToCartRequest)).thenReturn(response);

        // then - verify the output
        mockMvc.perform(post("/api/v1/cart/vasItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vasItemToCartRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.result").value(response.getResult()))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void itShouldRemoveItemToCart_WhenValidRemoveItemToCartRequestBody() throws Exception {


        ItemToCartRequest.ItemToCartRequestPayload payload = new ItemToCartRequest.ItemToCartRequestPayload(1,7889,5003,150,2);
        ItemToCartRequest itemToCartRequest = new ItemToCartRequest(ItemType.DEFAULT_ITEM,payload);

        cartCommandService.createItemToCart(itemToCartRequest);

        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest("removeItem",new RemoveItemFromCartRequest.Payload(1L));

        ResponseMessage response = new ResponseMessage(true, String.format("%s İtem Başarıyla Silinmiştir.",removeItemFromCartRequest.getPayload().getItemId()));

        // when -  action or the behaviour that we are going test
        when(cartCommandService.removeItemFromCart(removeItemFromCartRequest)).thenReturn(response);

        // then - verify the output
        mockMvc.perform(delete("/api/v1/cart/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(removeItemFromCartRequest)))
                .andDo(print())
                .andExpect(jsonPath("$.result").value(response.getResult()))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void itShouldRemoveAllItemToCart_WhenValidRemoveAllItemToCartRequestBody() throws Exception {

        ResponseMessage response = new ResponseMessage(true, "Bütün İtemler Kaldırıldı");

        // when -  action or the behaviour that we are going test
        when(cartCommandService.removeAllItemFromCart()).thenReturn(response);

        // then - verify the output
        mockMvc.perform(delete("/api/v1/cart/resetCart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

}
