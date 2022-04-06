package com.example.ecommerce.ecommerce.util;

import com.example.ecommerce.ecommerce.dto.OrderDTO;
import com.example.ecommerce.ecommerce.dto.ProductDTO;
import com.example.ecommerce.ecommerce.dto.ProductReviewDTO;
import com.example.ecommerce.ecommerce.dto.UserDTO;
import com.example.ecommerce.ecommerce.model.Order;
import com.example.ecommerce.ecommerce.model.Product;
import com.example.ecommerce.ecommerce.model.ProductReview;
import com.example.ecommerce.ecommerce.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO userToDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }

    public User userDtoToDTO(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }

    public ProductReview productReviewToProductReviewDto(ProductReviewDTO productReviewDTO) {
        return modelMapper.map(productReviewDTO,ProductReview.class);
    }

    public ProductReviewDTO productReviewToProductReviewDto(ProductReview productReview){
        return modelMapper.map(productReview,ProductReviewDTO.class);
    }

    public Product productDtoToProduct(ProductDTO productDTO){
        return modelMapper.map(productDTO,Product.class);
    }

    public ProductDTO productToProductDto(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }

    public OrderDTO orderToOrderDto(Order order){
        return modelMapper.map(order,OrderDTO.class);
    }

    public Order orderToOrderDTO(OrderDTO orderDTO){
        return modelMapper.map(orderDTO,Order.class);
    }
}
