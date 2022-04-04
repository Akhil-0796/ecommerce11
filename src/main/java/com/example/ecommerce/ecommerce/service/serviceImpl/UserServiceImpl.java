package com.example.ecommerce.ecommerce.service.serviceImpl;

import com.example.ecommerce.ecommerce.model.User;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import com.example.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        if(!userAlreadyPresent(user)) return null;
       user.setId(UUID.randomUUID());
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(10, new SecureRandom());
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    @Override
    public User findUserById(UUID id) {
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
    public void deleteUser(UUID user_id) {
        userRepository.deleteById(user_id);
    }

    @Override
    public Optional<User> findById(UUID user_id) {
        return userRepository.findById(user_id);
    }

    private boolean userAlreadyPresent(User user) {
        if(userRepository.findByName(user.getName())==null) return false;
        return true;
    }
}
