package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.dto.UserDTO;
import com.example.ecommerce.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserDTO addUser(UserDTO user);

    UserDTO findUserById(String id);

    List<UserDTO> findAllUsers();

    boolean updateUser(UserDTO user);

    void deleteUser(String userId);

    Optional<User> findById(String userId);

    UserDTO getUserByName(String userName);
}
