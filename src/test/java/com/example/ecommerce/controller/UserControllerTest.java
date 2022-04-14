package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.DtoMapper;
import com.example.ecommerce.util.UserUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
 class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testAddUser(){
        try {
            User user = new User();
            user.setEmail("abc@gmail.com");
            user.setName("test1");
            user.setPassword("1234");
            user.setId("121");
            user.setMobile("12345");
            user.setOrderIds(com.sun.tools.javac.util.List.of("ear"));
            user.setRole("USER");

            String inputInJson = UserUtil.mapToJson(user);

            String URL = "/user/add-user";
            Mockito.when(userRepository.save(user)).thenReturn(user);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = result.getResponse();

            String outputInJson = response.getContentAsString();

            Assert.assertTrue(outputInJson.contains("abc@gmail.com"));
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetUserById() throws Exception {
        User user  = new User();
        user.setEmail("abc@gmail.com");
        user.setName("test1");
        user.setPassword("1234");
        user.setId("123");

        Mockito.when(userService.findById("123")).thenReturn(java.util.Optional.of(user));

        String URL = "/user/by-id/123";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(user);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("abc@gmail.com"));
    }


    @Test
    public void testGetUserByName() throws Exception {
        User user  = new User();
        user.setEmail("abc@gmail.com");
        user.setName("test1");
        user.setPassword("1234");
        user.setId("123");

        Mockito.when(userRepository.findByName("test1")).thenReturn(user);

        String URL = "/user/by-name/"+user.getName();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(user);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("abc@gmail.com"));
    }

    @Test
    public void testGetAllUsers() throws Exception {

        User user1 = new User();
        user1.setId("123");
        user1.setPassword("1234");
        user1.setName("ram");
        user1.setEmail("ram@gmail.com");

        User user2 = new User();
        user2.setId("1234");
        user2.setPassword("1234");
        user2.setName("ram");
        user2.setEmail("ram2@gmail.com");

        List<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user1);

        Mockito.when(userRepository.findAll()).thenReturn(Stream.of(user1, user2).collect(Collectors.toList()));

        String URL = "/admin/all-users";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = UserUtil.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertTrue(outputInJson.contains("ram@gmail.com"));
        assertTrue(outputInJson.contains("ram2@gmail.com"));
    }

}