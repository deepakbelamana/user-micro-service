package com.dpkProjects.user.service.services.impl;

import com.dpkProjects.user.service.exceptions.ResourceNotFoundException;
import com.dpkProjects.user.service.models.Hotel;
import com.dpkProjects.user.service.models.Rating;
import com.dpkProjects.user.service.models.User;
import com.dpkProjects.user.service.repositories.UserRepo;
import com.dpkProjects.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);
        List<Rating>ratings=Arrays.stream(ratingsOfUser).toList();
        getHotelsByRatings(ratings);
        user.setRatings(ratings);
        return user;
    }

    private void getHotelsByRatings(List<Rating> ratings) {
       ratings.stream().map(rating -> {
         ResponseEntity<Hotel> hotel = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
         rating.setHotel(hotel.getBody());
         return rating;
        }).collect(Collectors.toList());
    }
}
