package com.dpkProjects.user.service.services;

import com.dpkProjects.user.service.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);



}
