package com.example.ecommerce.controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.util.DtoMapper;
import com.example.ecommerce.util.UserUtil;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void addCategory() throws Exception {
        Category category = new Category();
        category.setCategoryId("1");
        category.setName("footwear");


        String inputInJson = UserUtil.mapToJson(category);
        String URL = "/admin/category/add";
        Mockito.when(categoryRepository.save(category)).thenReturn(category);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        Assert.assertTrue(outputInJson.contains("footwear"));

    }

    @Test
    public void getCategory() throws Exception {
        Category category = new Category();
        category.setCategoryId("1");
        category.setName("footwear");

        Mockito.when(categoryRepository.findById("1")).thenReturn(java.util.Optional.of(category));

        String URL = "/admin/category/get/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(category);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("footwear"));

    }
}