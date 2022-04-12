package com.example.ecommerce.ecommerce;

import com.example.ecommerce.ecommerce.model.Product;
import com.example.ecommerce.ecommerce.repository.ProductRepository;
import com.example.ecommerce.ecommerce.service.ProductService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts(){
        Product product1 = new Product();
        product1.setProductId("12345");
        product1.setProductName("Samsung");
        product1.setPrice(123.0);
        product1.setStock(2);

        Product product2 = new Product();
        product2.setProductId("12345");
        product2.setProductName("Samsung");
        product2.setPrice(123.0);
        product2.setStock(2);

        when(productRepository.findAll()).thenReturn(Stream.of(product1,product2).collect(Collectors.toList()));
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    public void testGetProductById(){
        Product product1 = new Product();
        product1.setProductId("12345");
        product1.setProductName("Samsung");
        product1.setPrice(123.0);
        product1.setStock(2);

        when(productRepository.findById("12345")).thenReturn(java.util.Optional.of(product1));
        assertEquals("Samsung",productService.findById("12345").getProductName());
    }


}
