package com.example.ecommerce.controller;

import com.example.ecommerce.model.Supplier;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.SupplierRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.SupplierService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.DtoMapper;
import com.example.ecommerce.util.UserUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.tools.javac.util.List;
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

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private SupplierRepository supplierRepository;

    @Test
    public void addSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setSupplierName("test1");
        supplier.setId("1234");
        supplier.setAddress("new delhi");
        supplier.setCategories(List.of("shoes"));

        String inputInJson = UserUtil.mapToJson(supplier);
        String URL = "/supplier/add";
        Mockito.when(supplierRepository.save(supplier)).thenReturn(supplier);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assert.assertTrue(outputInJson.contains("new delhi"));
    }

    @Test
    public void getAllSupplier() throws Exception {
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierName("test1");
        supplier1.setId("1234");
        supplier1.setAddress("new delhi");
        supplier1.setCategories(List.of("shoes"));

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierName("test1");
        supplier2.setId("1234");
        supplier2.setAddress("new delhi");
        supplier2.setCategories(List.of("electronics"));


        java.util.List<Supplier> userList = new ArrayList<>();
        userList.add(supplier1);
        userList.add(supplier2);

        Mockito.when(supplierRepository.findAll()).thenReturn(Stream.of(supplier1, supplier2).collect(Collectors.toList()));

        String URL = "/supplier/get-all";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = UserUtil.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("electronics"));
        assertTrue(outputInJson.contains("shoes"));

    }

    @Test
    public void getSupplierById() throws Exception {
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierName("test1");
        supplier1.setId("1234");
        supplier1.setAddress("new delhi");
        supplier1.setCategories(List.of("shoes"));

        Mockito.when(supplierRepository.findById("1234")).thenReturn(java.util.Optional.of(supplier1));

        String URL = "/supplier/by-id/1234";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(supplier1);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("shoes"));
    }
}