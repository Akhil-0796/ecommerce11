package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.dto.PaymentDetails;
import com.example.ecommerce.ecommerce.model.Order;
import com.example.ecommerce.ecommerce.service.OrderService;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("user/order/validate")
    public ResponseEntity<Map<String,String>> validateOrder(@RequestBody Order order){
        Map<String,String> response  = orderService.validateOrder(order);
        if(response.size()==0) return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("user/order/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order response = orderService.addOrder(order);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("user/order/pay-order")
    public ResponseEntity<Order> payOrder(@PathVariable PaymentDetails paymentDetails) throws PayPalRESTException {
        Order response = orderService.pay(paymentDetails);
         return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("order/by-id/{id}")
    public ResponseEntity<Order> getOrderDetailsByOrderId(@PathVariable String orderId){
        if(!orderService.findOrderById(orderId).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Order order = orderService.findOrderById(orderId).get();
        return new ResponseEntity<>(order,HttpStatus.FOUND);
    }

     @DeleteMapping("user/cancel-order/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderId){
         if(!orderService.findOrderById(orderId).isPresent()) return new ResponseEntity<>("No Order Found.",HttpStatus.NOT_FOUND);
         boolean response  = orderService.cancelOrder(orderId);
         if(response==true) return new ResponseEntity<>("Order Cancelled",HttpStatus.OK);
         else return new ResponseEntity<>("Only Accepted or Pending Order cane be canceled",HttpStatus.OK);
     }

    @GetMapping("admin/all-orders")
    public List<Order> getAllOrder(){
        return orderService.getAllOrders();
    }


}
