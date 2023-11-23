package com.hazrody.inscription.service;

import com.hazrody.inscription.dao.entity.User;
import com.hazrody.inscription.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }


}
