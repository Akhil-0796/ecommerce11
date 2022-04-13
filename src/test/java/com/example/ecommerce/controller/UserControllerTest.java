package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.DtoMapper;
import com.example.ecommerce.util.UserUtil;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@WebMvcTest(value=UserController.class,useDefaultFilters = false)
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private DtoMapper dtoMapper;


    @Test
    public void testAddUser() throws Exception {
        User user  = new User();
        user.setEmail("abc@gmail.com");
        user.setName("test1");
        user.setPassword("1234");
        String inputInJson = UserUtil.mapToJson(user);

        String URL = "/user/add-user";
        Mockito.when(userService.addUser(Mockito.any(UserDTO.class))).thenReturn(dtoMapper.userToDTO(user));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

       assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
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
        assertThat(outputInJson).isEqualTo(expectedJson);
    }


    @Test
    public void testGetUserByName() throws Exception {
        User user  = new User();
        user.setEmail("abc@gmail.com");
        user.setName("test1");
        user.setPassword("1234");
        user.setId("123");

        Mockito.when(userService.getUserByName("test1")).thenReturn(dtoMapper.userToDTO(user));

        String URL = "user/by-name/test1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = UserUtil.mapToJson(user);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void testGetAllBookedTickets() throws Exception {

        UserDTO user1 = new UserDTO();
        user1.setId("123");
        user1.setPassword("1234");
        user1.setName("ram");
        user1.setEmail("ram@gmail.com");

        UserDTO user2 = new UserDTO();
        user2.setId("1234");
        user2.setPassword("1234");
        user2.setName("ram");
        user2.setEmail("ram@gmail.com");

        List<UserDTO> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user1);

        Mockito.when(userService.findAllUsers()).thenReturn(userList);

        String URL = "/admin/all-users";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = UserUtil.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

}