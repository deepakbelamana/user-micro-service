package com.dpkProjects.user.service.services.impl;

import com.dpkProjects.user.service.exceptions.ResourceNotFoundException;
import com.dpkProjects.user.service.models.Rating;
import com.dpkProjects.user.service.models.User;
import com.dpkProjects.user.service.repositories.UserRepo;
import com.dpkProjects.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class userServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger (UserService.class);
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
        User user=userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not exist " + id));
        //get user ratings by calling Rating service using restTemplate
        //http://localhost:8083/ratings/users/userid
        ArrayList<Rating> ratingsOfUser =  restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
        logger.info("{} ", ratingsOfUser);
        user.setRatings(ratingsOfUser);
        return user;
    }
}
