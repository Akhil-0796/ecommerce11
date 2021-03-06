package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * validate order before processing
     * @param orderDTO
     * @return key-value pair of unavailable products
     */
    @PostMapping("user/order/validate")
    public ResponseEntity<Map<String,String>> validateOrder(@RequestBody OrderDTO orderDTO){
        Map<String,String> response  = orderService.validateOrder(orderDTO);
        if(response.size()==0) return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * place order
     * @param orderDTO
     * @return OrderDTO
     */
    @PostMapping("user/order/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO response = orderService.addOrder(orderDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return order details based on order id
     * @param orderId
     * @return OrderDTO
     */
    @GetMapping("order/by-id/{orderId}")
    public ResponseEntity<?> getOrderDetailsByOrderId(@PathVariable("orderId") String orderId){
        OrderDTO response = orderService.findOrderById(orderId);
        if(response == null) return new ResponseEntity<>("No Order with given order-id",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    /**
     * cancel order based on order id
     * @param orderId
     * @return response message
     */
     @DeleteMapping("cancel-order/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderId){
         if(orderService.findOrderById(orderId)==null) return new ResponseEntity<>("No Order Found.",HttpStatus.NOT_FOUND);
         boolean response  = orderService.cancelOrder(orderId);
         if(response==true) return new ResponseEntity<>("Order Cancelled",HttpStatus.OK);
         else return new ResponseEntity<>("Only Accepted or Pending Order cane be canceled",HttpStatus.OK);
     }

    /**
     * return list of all orders
     * @return list of Order
     */
    @GetMapping("admin/all-orders")
    public List<OrderDTO> getAllOrder(){
        return orderService.getAllOrders();
    }

}
