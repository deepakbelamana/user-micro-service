package com.dpkProjects.user.service.controllers;

import com.dpkProjects.user.service.models.User;
import com.dpkProjects.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserService userService;
        @PostMapping()
        public ResponseEntity<User> createUser(@RequestBody User user) {
                String randomUserId = UUID.randomUUID().toString();
                user.setUserId(randomUserId);
               User user_saved =  userService.saveUser(user);
                return new ResponseEntity<User>(user_saved, HttpStatus.CREATED);
        }
        @GetMapping()
        public ResponseEntity<List<User>> getAllUsers() {
            List<User>userList =  userService.getAllUsers();
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public  ResponseEntity<User> getUserByid(@PathVariable String id)
        {
                User user = userService.getUserById(id);
                return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
}
