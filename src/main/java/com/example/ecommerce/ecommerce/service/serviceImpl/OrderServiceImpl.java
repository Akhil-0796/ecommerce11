package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.Enum.OrderStatusEnum;
import com.example.ecommerce.ecommerce.dto.PaymentDetails;
import com.example.ecommerce.ecommerce.model.Order;
import com.example.ecommerce.ecommerce.model.Product;
import com.example.ecommerce.ecommerce.repository.OrderRepository;
import com.example.ecommerce.ecommerce.service.OrderService;
import com.example.ecommerce.ecommerce.config.PaypalService;
import com.example.ecommerce.ecommerce.service.ProductService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private PaypalService paypalService;

    @Autowired
    private ProductService productService;
    private String successUrl;
    private String cancelUrl;

    @Override
    public Optional<Order> findOrderById(String orderId) {

        return orderRepository.findById(orderId);
    }

    @Override
    public Map<String, String> validateOrder(Order order) {
        Map<String,String> response = new HashMap<>();
        List<Product> productList = productService.findAllProduct(order.getItems());
        for(Product product:productList){
            if(product.getStock()==0){
                response.put(product.getProductId(),"not available");
            }
        }
        return response;
    }

    @Override
    public Order pay(PaymentDetails paymentDetails) throws PayPalRESTException {
        Order dbOrder = orderRepository.findById(paymentDetails.getOrderId()).get();

        if(dbOrder!=null && dbOrder.getOrderStatus()==OrderStatusEnum.PENDING.toString()) {
            Payment payment = paypalService.createPayment(paymentDetails.getOrderValue(), paymentDetails.getCurrency(), paymentDetails.getMethod(), paymentDetails.getIntent(), paymentDetails.getDescription(), cancelUrl, successUrl);
            Payment paymentResponse = paypalService.executePayment(payment.getId(), payment.getPayer().getPayerInfo().getPayerId());
            if (paymentResponse.getState().equals("approved")) {
                dbOrder.setOrderStatus(OrderStatusEnum.ACCEPTED.toString());
                dbOrder.setLastUpdated(LocalDateTime.now());
                return orderRepository.save(dbOrder);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        try {
            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderStatus(OrderStatusEnum.PENDING.toString());
            order.setOrderTime(LocalDateTime.now());
            return orderRepository.save(order);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean cancelOrder(String orderId) {
        Order dbOrder  = orderRepository.findById(orderId).get();
        if(dbOrder.getOrderStatus().equals(OrderStatusEnum.ACCEPTED.toString()) || dbOrder.getOrderStatus().equals(OrderStatusEnum.PENDING.toString())){
            dbOrder.setOrderStatus(OrderStatusEnum.CANCELLED.toString());
            dbOrder.setLastUpdated(LocalDateTime.now());
            orderRepository.save(dbOrder);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
