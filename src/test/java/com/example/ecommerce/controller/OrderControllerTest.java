package com.example.ecommerce.controller;

import com.example.ecommerce.Enum.OrderStatusEnum;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.util.DtoMapper;
import com.example.ecommerce.util.UserUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private OrderRepository orderRepository;


    @Test
    public void addOrder() throws Exception {
        Order order = new Order();
        order.setOrderId("1234");
        order.setOrderStatus(OrderStatusEnum.PENDING.toString());
        order.setValue(123.0);

        String inputInJson = UserUtil.mapToJson(order);
        String URL = "/user/order/add";
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assert.assertTrue(outputInJson.contains("PENDING"));
    }

    @Test
    public void getOrderDetailsByOrderId() throws Exception {
        Order order = new Order();
        order.setOrderId("1234");
        order.setOrderStatus(OrderStatusEnum.PENDING.toString());
        order.setValue(123.0);

        Mockito.when(orderRepository.findById("1234")).thenReturn(java.util.Optional.of(order));

        String URL = "/order/by-id/1234";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(order);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("PENDING"));
    }

    @Test
    public void getAllOrder() throws Exception {
        Order order1 = new Order();
        order1.setOrderId("1234");
        order1.setOrderStatus(OrderStatusEnum.PENDING.toString());
        order1.setValue(123.0);

        Order order2 = new Order();
        order2.setOrderId("12345");
        order2.setOrderStatus(OrderStatusEnum.PENDING.toString());
        order2.setValue(1235.0);

        java.util.List<Order> userList = new ArrayList<>();
        userList.add(order1);
        userList.add(order2);

        Mockito.when(orderRepository.findAll()).thenReturn(Stream.of(order1, order2).collect(Collectors.toList()));

        String URL = "/admin/all-orders";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = UserUtil.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("12345"));

    }
}