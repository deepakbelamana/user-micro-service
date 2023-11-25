package com.dpkProjects.user.service.services.impl;

import com.dpkProjects.user.service.exceptions.ResourceNotFoundException;
import com.dpkProjects.user.service.models.User;
import com.dpkProjects.user.service.repositories.UserRepo;
import com.dpkProjects.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist " + id));
    }
}
