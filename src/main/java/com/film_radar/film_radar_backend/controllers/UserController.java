package com.film_radar.film_radar_backend.controllers;

import com.film_radar.film_radar_backend.config.JWTAuthenticationConfig;
import com.film_radar.film_radar_backend.models.Login;
import com.film_radar.film_radar_backend.models.User;
import com.film_radar.film_radar_backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class UserController {

    JWTAuthenticationConfig jwtAuthenticationConfig = new JWTAuthenticationConfig();

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/logIn")
    public User Login(
            @RequestBody Login login
    ) throws IOException {
        User finalUser = null;
        Optional<User> user = userService.getUserByUsername(login.getUsername());
        if(user.isPresent()){
            String token = jwtAuthenticationConfig.getJWTToken(user.get().getEmail());
            finalUser = user.get();
            finalUser.setToken(token);
        } else {
            throw new IOException("FAIL TO CONNECT");
        }

        return finalUser;
    }

    @PostMapping("/auth/signIn")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        return userService.addNewUser(user);
    }
}
