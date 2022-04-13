package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.model.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {
    OrderDTO findOrderById(String orderId);

    Map<String, String> validateOrder(OrderDTO orderDTO);

    OrderDTO addOrder(OrderDTO orderDTO);

    boolean cancelOrder(String orderId);

    List<OrderDTO> getAllOrders();
}
