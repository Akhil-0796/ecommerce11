package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.model.User;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import com.example.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user){
       return userService.addUser(user);
    }

    @GetMapping("/get-user-by-id/{id}")
    public User getUserById(@PathVariable("id") UUID user_id){
        return userService.findUserById(user_id);
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @PutMapping("/update-user")
    public String updateUser(@RequestBody User user){
        if(!userService.findById(user.getId()).isPresent()) return "User is Not Present";
        userService.updateUser(user);
        return "Updated Successfully.";
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") UUID user_id){
        if(!userService.findById(user_id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.deleteUser(user_id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
