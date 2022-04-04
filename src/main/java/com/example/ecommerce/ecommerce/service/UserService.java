package com.example.ecommerce.ecommerce.service;

import com.example.ecommerce.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    User addUser(User user);

    User findUserById(UUID id);

    List<User> findAllUsers();

    boolean updateUser(User user);

    void deleteUser(UUID user_id);

    Optional<User> findById(UUID user_id);
}
