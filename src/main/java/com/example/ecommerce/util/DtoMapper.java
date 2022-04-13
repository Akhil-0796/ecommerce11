package com.example.ecommerce.util;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.model.*;
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

    public Supplier supplierDtoToupplier(SupplierDTO supplierDTO) {
        return modelMapper.map(supplierDTO,Supplier.class);
    }

    public SupplierDTO SupplierToDTO(Supplier supplier) {
        return modelMapper.map(supplier,SupplierDTO.class);
    }
}
