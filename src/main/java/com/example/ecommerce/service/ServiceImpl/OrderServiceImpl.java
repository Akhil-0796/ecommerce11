package com.example.ecommerce.service.ServiceImpl;

import com.example.ecommerce.Enum.OrderStatusEnum;
import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public OrderDTO findOrderById(String orderId) {
        Optional<Order> orderDb = orderRepository.findById(orderId);
        if(!orderDb.isPresent()) return null;
        return dtoMapper.orderToOrderDto(orderDb.get());
    }

    @Override
    public Map<String, String> validateOrder(OrderDTO orderDTO) {
        Map<String,String> response = new HashMap<>();
        List<Product> productList = productService.findAllProduct(orderDTO.getItems());
        for(Product product:productList){
            if(product.getStock()==0){
                response.put(product.getProductId(),"not available");
            }
        }
        return response;
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        try {
            if(orderDTO.getOrderId().isEmpty())
            orderDTO.setOrderId(UUID.randomUUID().toString());
            orderDTO.setOrderStatus(OrderStatusEnum.PENDING.toString());
            orderDTO.setOrderTime(LocalDateTime.now());
            orderRepository.save(dtoMapper.orderToOrderDTO(orderDTO));
            return orderDTO;
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
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> response = new ArrayList<>();
        for(Order order:orderRepository.findAll()) response.add(dtoMapper.orderToOrderDto(order));
        return response;
    }
}
