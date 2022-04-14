package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.DtoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Autowired
    private DtoMapper dtoMapper;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testFindAllUser() {
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setPassword("123");
        User user2 = new User();
        user2.setEmail("abc@gmail.com");
        user2.setPassword("123");
        when(userRepository.findAll()).thenReturn(Stream.of(user1, user2).collect(Collectors.toList()));
        assertEquals(2, userService.findAllUsers().size());
    }

    @Test
    public void testGetUserByUerName() {
        String username = "akhil";
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setPassword("123");
        user1.setName("akhil");
        when(userRepository.findByName(username)).thenReturn(user1);
        Assertions.assertEquals("akhil", userService.getUserByName(username).getName());
    }

    @Test
    public void testGetUserById() {
        String username = "akhil";
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setPassword("123");
        user1.setName("akhil");
        user1.setId("12345");
        when(userRepository.findById("12345")).thenReturn(java.util.Optional.of(user1));
        Assertions.assertEquals("akhil", userService.findUserById("12345").getName());
    }


}
