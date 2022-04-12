package com.example.ecommerce.ecommerce.service.ServiceImpl;

import com.example.ecommerce.ecommerce.dto.UserDTO;
import com.example.ecommerce.ecommerce.model.User;
import com.example.ecommerce.ecommerce.repository.UserRepository;
import com.example.ecommerce.ecommerce.service.UserService;
import com.example.ecommerce.ecommerce.util.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DtoMapper dtoMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        if(userAlreadyPresent(userDTO)) return null;
        User user = dtoMapper.userDtoToDTO(userDTO);
        if(userDTO.getId().isEmpty())
       user.setId(UUID.randomUUID().toString());
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setCreationTime(LocalDateTime.now());
       return dtoMapper.userToDTO(userRepository.save(user));
    }

    @Override
    public UserDTO findUserById(String id) {
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()) return dtoMapper.userToDTO(user.get());
        return null;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserDTO> returnDto = new ArrayList<>();
        for(User user:userRepository.findAll()){
            returnDto.add(dtoMapper.userToDTO(user));
        }
        return returnDto;
    }

    @Override
    public boolean updateUser(UserDTO user) {
        User dbUser = userRepository.findById(user.getId()).get();
        user.setId(dbUser.getId());
        userRepository.save(dtoMapper.userDtoToDTO(user));
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

    @Override
    public UserDTO getUserByName(String userName) {
        return dtoMapper.userToDTO(userRepository.findByName(userName));
    }

    private boolean userAlreadyPresent(UserDTO user) {
        if(userRepository.findByEmail(user.getEmail())==null) return false;
        return true;
    }
}
