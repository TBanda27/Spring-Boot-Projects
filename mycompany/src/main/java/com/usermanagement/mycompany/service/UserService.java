package com.usermanagement.mycompany.service;

import com.usermanagement.mycompany.controller.exceptions.UserNotFoundException;
import com.usermanagement.mycompany.entity.User;
import com.usermanagement.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public User getUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            throw new UserNotFoundException("User", "Id", userId);
        }
    }


    public void deleteById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            userRepository.deleteById(userId);
        }
        else{
            throw new UserNotFoundException("User", "Id", userId);
        }

    }
}
