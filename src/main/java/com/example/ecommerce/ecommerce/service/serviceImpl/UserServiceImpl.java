package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.model.User;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import com.example.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        if(!userAlreadyPresent(user)) return null;
       user.setId(UUID.randomUUID().toString());
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public User findUserById(String id) {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()) return user.get();
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean updateUser(User user) {
        User dbUser = userRepository.findById(user.getId()).get();
        user.setId(dbUser.getId());
        userRepository.save(user);
        return true;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findById(String userId) {
        return userRepository.findById(userId);
    }

    private boolean userAlreadyPresent(User user) {
        if(userRepository.findByName(user.getName())==null) return false;
        return true;
    }
}
