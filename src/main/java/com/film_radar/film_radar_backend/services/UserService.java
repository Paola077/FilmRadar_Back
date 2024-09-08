package com.film_radar.film_radar_backend.services;

import com.film_radar.film_radar_backend.models.User;
import com.film_radar.film_radar_backend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    public ResponseEntity<Object> addNewUser(User user){
        if(userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Este usuario ya se encuentra registrado", HttpStatus.CREATED);
        }

        user.setCreatedAt(new Date().getTime());
        userRepository.save(user);
        return new ResponseEntity<>("El usuario se ha registrado con exito!", HttpStatus.CREATED);
    }
    public Optional<User> getUserByUsername (String username) {
        return userRepository.findByUsername(username);
    }
}
