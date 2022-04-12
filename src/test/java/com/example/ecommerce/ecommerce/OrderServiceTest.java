package com.example.ecommerce.ecommerce;

import com.example.ecommerce.ecommerce.Enum.OrderStatusEnum;
import com.example.ecommerce.ecommerce.dto.OrderDTO;
import com.example.ecommerce.ecommerce.model.Order;
import com.example.ecommerce.ecommerce.repository.OrderRepository;
import com.example.ecommerce.ecommerce.service.OrderService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testGetAllOrder(){
        OrderDTO orderDTO  = new OrderDTO();
        orderDTO.setOrderId("12345");
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("Mobile");
        orderDTO.setItems(orderList);
        orderDTO.setValue(123.0);
        Order order = dtoMapper.orderToOrderDTO(orderDTO);
        when(orderRepository.findAll()).thenReturn(Stream.of(order).collect(Collectors.toList()));
        Order o = orderService.getAllOrders().get(0);
        assertEquals(1,orderService.getAllOrders().size());
    }

    @Test
    public void testGetOrderById(){
        OrderDTO orderDTO  = new OrderDTO();
        orderDTO.setOrderId("12345");
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("Mobile");
        orderDTO.setItems(orderList);
        orderDTO.setValue(123.0);
        Optional<Order> order = Optional.ofNullable(dtoMapper.orderToOrderDTO(orderDTO));
        when(orderRepository.findById("12345")).thenReturn(order);
        OrderDTO o = orderService.findOrderById("12345");
        assertEquals(123.0,orderService.findOrderById("12345").getValue());
    }

    @Test
    public void testCancelOrder(){
        OrderDTO orderDTO  = new OrderDTO();
        orderDTO.setOrderId("12345");
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("Mobile");
        orderDTO.setItems(orderList);
        orderDTO.setValue(123.0);
        orderDTO.setOrderStatus(OrderStatusEnum.ACCEPTED.toString());
        Optional<Order> order = Optional.ofNullable(dtoMapper.orderToOrderDTO(orderDTO));
        when(orderRepository.findById("12345")).thenReturn(order);
        boolean o = orderService.cancelOrder("12345");
        assertEquals(OrderStatusEnum.CANCELLED.toString(),orderService.findOrderById("12345").getOrderStatus());
    }
}
