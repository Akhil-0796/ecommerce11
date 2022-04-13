package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * will add user into the system
     * @param user
     * @return added userDto
     */
    @PostMapping("user/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO user){
       UserDTO response =  userService.addUser(user);
       if(response==null) return new ResponseEntity<>("User already present",HttpStatus.OK);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * will return user based on its id
     * @param userId
     * @return userDto
     */
    @GetMapping("user/by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String userId){
        UserDTO response  = userService.findUserById(userId);
        if(response == null) return new ResponseEntity<>("No user found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    /**
     * will return all the users present in the system
     * @return list of users
     */
    @GetMapping("admin/all-users")
    public List<UserDTO> getAllUsers(){
        return userService.findAllUsers();
    }

    /**
     * will update the user details
     * @param user
     * @return updated user
     */
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

    /**
     * will delete the user from the system
     * @param userId
     * @return String message
     */
    @DeleteMapping("admin/delete-by-id/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") String userId){
        if(!userService.findById(userId).isPresent()) return new ResponseEntity<>("No User with given Id",HttpStatus.NOT_FOUND);
        userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    /**
     * will return user by name
     * @param userName
     * @return user
     */
    @PostMapping("user/by-name/{userName}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable("userName") String userName){
        UserDTO response = userService.getUserByName(userName);
        if(response==null) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("hell")
    public String hello(){
        return "hello akhil";
    }

}
