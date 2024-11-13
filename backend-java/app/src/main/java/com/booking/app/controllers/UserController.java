package com.booking.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.entities.User;
import com.booking.app.models.AuthCredentials;
import com.booking.app.models.Response;
import com.booking.app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody AuthCredentials userDetails) {

        Response response = new Response();

        try {
            User user = userService.savUser(userDetails);
            response.setData(user.getEmail());
            response.setMessage("User registered successfully with email: " + user.getEmail());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/login")
    public Response login(@RequestBody AuthCredentials userDetails) {
        Response response = new Response();

        try {
            String token = userService.loginUser(userDetails);
            response.setData(token);
            response.setMessage("User logged in successfully");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
