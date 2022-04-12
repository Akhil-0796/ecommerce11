package com.example.ecommerce.ecommerce.controller;

import com.example.ecommerce.ecommerce.dto.UserDTO;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import com.example.ecommerce.ecommerce.service.UserService;
import com.example.ecommerce.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("user/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user){
       UserDTO response =  userService.addUser(user);
       if(response==null) return new ResponseEntity<>("User already present",HttpStatus.OK);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("user/hell")
    public String hello(){
        return "hello";
    }

    @GetMapping("user/by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String userId){
        UserDTO response  = userService.findUserById(userId);
        if(response == null) return new ResponseEntity<>("No user found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    @GetMapping("admin/all-users")
    public List<UserDTO> getAllUsers(){
        return userService.findAllUsers();
    }

    @PutMapping("user/update-user")
    public String updateUser(@RequestBody UserDTO user){
        if(UserUtil.getCurrentUser().getEmail().equals(user.getEmail())) {
            if (!userService.findById(user.getId()).isPresent()) return "User is Not Present";
            userService.updateUser(user);
            return "Updated Successfully.";
        }else{
            return "You Can not update other User";
        }
    }

    @DeleteMapping("admin/delete-by-id/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") String userId){
        if(!userService.findById(userId).isPresent()) return new ResponseEntity<>("No User with given Id",HttpStatus.NOT_FOUND);
        userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PostMapping("user/by-name/{userName}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable("userName") String userName){
        UserDTO response = userService.getUserByName(userName);
        if(response==null) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
