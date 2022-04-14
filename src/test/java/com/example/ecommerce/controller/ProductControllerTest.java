package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.ProductReview;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ProductReviewRepository;
import com.example.ecommerce.service.ProductReviewService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.util.DtoMapper;
import com.example.ecommerce.util.UserUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductService productService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void addProduct() throws Exception {

        Product product = new Product();
        product.setProductId("1234");
        product.setProductName("samsung");
        product.setPrice(123.0);

        String inputInJson = UserUtil.mapToJson(product);
        String URL = "/admin/product/add";
        Mockito.when(productRepository.save(product)).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assert.assertTrue(outputInJson.contains("samsung"));
    }

    @Test
    public void getAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setProductId("1234");
        product1.setProductName("samsung");
        product1.setPrice(123.0);

        Product product2 = new Product();
        product2.setProductId("12345");
        product2.setProductName("nokia");
        product2.setPrice(123.0);

        java.util.List<Product> userList = new ArrayList<>();
        userList.add(product1);
        userList.add(product2);

        Mockito.when(productRepository.findAll()).thenReturn(Stream.of(product1, product2).collect(Collectors.toList()));

        String URL = "/all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = UserUtil.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("samsung"));
        assertTrue(outputInJson.contains("nokia"));
    }

    @Test
    public void getProductById() throws Exception {
        Product product1 = new Product();
        product1.setProductId("1234");
        product1.setProductName("samsung");
        product1.setPrice(123.0);

        Mockito.when(productRepository.findById("1234")).thenReturn(java.util.Optional.of(product1));

        String URL = "/get-by-id/1234";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(product1);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("samsung"));
    }
}