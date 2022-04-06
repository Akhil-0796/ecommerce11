package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.model.User;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import com.example.ecommerce.ecommerce.service.UserService;
import com.example.ecommerce.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("user/add-user")
    public User addUser(@RequestBody User user){
       return userService.addUser(user);
    }

    @GetMapping("user/by-id/{id}")
    public User getUserById(@PathVariable("id") String userId){
        return userService.findUserById(userId);
    }

    @GetMapping("admin/all-users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/hello")
    public String message(){
        return "hello";
    }

    @PutMapping("user/update-user")
    public String updateUser(@RequestBody User user){
        if(UserUtil.getCurrentUser().getEmail().equals(user.getEmail())) {
            if (!userService.findById(user.getId()).isPresent()) return "User is Not Present";
            userService.updateUser(user);
            return "Updated Successfully.";
        }else{
            return "Can not update other User";
        }
    }

    @DeleteMapping("admin/delete-by-id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String userId){
        if(!userService.findById(userId).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

}
