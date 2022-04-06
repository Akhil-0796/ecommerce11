package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.dto.PaymentDetails;
import com.example.ecommerce.ecommerce.model.Order;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface OrderService {
    Optional<Order> findOrderById(String orderId);

    Map<String, String> validateOrder(Order order);

    Order pay(PaymentDetails paymentDetails) throws PayPalRESTException;

    Order addOrder(Order order);

    boolean cancelOrder(String orderId);

    List<Order> getAllOrders();
}
