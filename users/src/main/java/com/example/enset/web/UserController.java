package com.example.enset.web;


import com.example.enset.entities.User;
import com.example.enset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{userName}")
    public User user(@PathVariable String userName){
        return userService.findUserByUserName(userName);
    }
}
