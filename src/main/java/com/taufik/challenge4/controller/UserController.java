package com.taufik.challenge4.controller;

import com.taufik.challenge4.model.User;
import com.taufik.challenge4.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    UserServiceImpl userServiceImpl;
    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PutMapping(value="/user/{username}")
    public User updateUser(@PathVariable("username") String username, @RequestBody User newUser) {
        User user = userServiceImpl.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User with username :"+username+" is Not Found!"));
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        logger.info("User update success");
        return userServiceImpl.save(user);
    }
    @DeleteMapping(value="/user/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        User user = userServiceImpl.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User with username :"+username+" is Not Found!"));
        userServiceImpl.deleteByUsername(user.getUsername());
        logger.info("User delete success");
        return "User with username :"+username+" is deleted";
    }
}
