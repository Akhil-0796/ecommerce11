package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.dto.OrderDTO;
import com.example.ecommerce.ecommerce.model.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface OrderService {
    OrderDTO findOrderById(String orderId);

    Map<String, String> validateOrder(OrderDTO orderDTO);

    OrderDTO addOrder(OrderDTO orderDTO);

    boolean cancelOrder(String orderId);

    List<Order> getAllOrders();
}
