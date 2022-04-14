package com.example.ecommerce.controller;

import com.example.ecommerce.model.ProductReview;
import com.example.ecommerce.model.Supplier;
import com.example.ecommerce.repository.ProductReviewRepository;
import com.example.ecommerce.repository.SupplierRepository;
import com.example.ecommerce.service.ProductReviewService;
import com.example.ecommerce.service.SupplierService;
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
public class ProductReviewControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private ProductReviewRepository productReviewRepository;

    @Test
    public void addReview() throws Exception {

        ProductReview productReview = new ProductReview();
        productReview.setReview("awesome product");
        productReview.setProductId("1234");
        productReview.setId("1234");
        productReview.setUserId("1231");

        String inputInJson = UserUtil.mapToJson(productReview);
        String URL = "/user/product-review/add";
        Mockito.when(productReviewRepository.save(productReview)).thenReturn(productReview);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assert.assertTrue(outputInJson.contains("awesome product"));
    }

    @Test
    public void getProductReview() throws Exception {
        ProductReview productReview = new ProductReview();
        productReview.setReview("awesome product");
        productReview.setProductId("1234");
        productReview.setId("1234");
        productReview.setUserId("1231");

        Mockito.when(productReviewRepository.findById("1234")).thenReturn(java.util.Optional.of(productReview));

        String URL = "/user/product-review/get/1234";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(productReview);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("awesome product"));
    }

    @Test
    public void allReviews() throws Exception {
        ProductReview productReview1 = new ProductReview();
        productReview1.setReview("awesome product");
        productReview1.setProductId("1234");
        productReview1.setId("1234");
        productReview1.setUserId("1231");

        ProductReview productReview2 = new ProductReview();
        productReview2.setReview("nice feature");
        productReview2.setProductId("1234");
        productReview2.setId("12345");
        productReview2.setUserId("12312");

        java.util.List<ProductReview> userList = new ArrayList<>();
        userList.add(productReview1);
        userList.add(productReview2);

        Mockito.when(productReviewRepository.findAll()).thenReturn(Stream.of(productReview1, productReview2).collect(Collectors.toList()));

        String URL = "/user/product-review/all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = UserUtil.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("nice feature"));
        assertTrue(outputInJson.contains("awesome product"));
    }
}