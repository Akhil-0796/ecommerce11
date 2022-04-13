package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.model.User;

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
